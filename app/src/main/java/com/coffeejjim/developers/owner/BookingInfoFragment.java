package com.coffeejjim.developers.owner;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Estimate;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.BookingInfoRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingInfoFragment extends Fragment {

    @BindView(R.id.proposal_phone)
    TextView phoneNumberView;
    @BindView(R.id.proposal_date)
    TextView dateView;
    @BindView(R.id.proposal_time)
    TextView timeView;
    @BindView(R.id.proposal_nickname)
    TextView nicknameView;
    @BindView(R.id.proposal_price)
    TextView priceView;

    static boolean notiNumChecked = false;

    public BookingInfoFragment() {
        // Required empty public constructor
    }

    public static BookingInfoFragment newInstance(Estimate estimate) {
        BookingInfoFragment f = new BookingInfoFragment();
        Bundle b = new Bundle();
        b.putSerializable("estimate", estimate);
        f.setArguments(b);
        return f;
    }

    public static BookingInfoFragment newInstance(String estimateId, String proposalId) {
        BookingInfoFragment f = new BookingInfoFragment();
        Bundle b = new Bundle();
        b.putString("estimateId", estimateId);
        b.putString("proposalId", proposalId);
        f.setArguments(b);
        notiNumChecked = true;
        return f;
    }

    Estimate estimate;
    String estimateId;
    String proposalId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            if (!notiNumChecked) {
                estimate = (Estimate) getArguments().getSerializable("estimate");
            } else {
                estimateId = getArguments().getString("estimateId");
                proposalId = getArguments().getString("proposalId");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_booking_info, container, false);
        ButterKnife.bind(this, view);
        if (!notiNumChecked) {
            phoneNumberView.setText(estimate.getPhoneNumber());
            dateView.setText(estimate.getReservationTime().toString().substring(0, 10));
            timeView.setText(estimate.getReservationTime().toString().substring(11, 19));
            nicknameView.setText(estimate.getNickname());
            priceView.setText("" + estimate.getBidPrice());
        }else{
            BookingInfoRequest bookingInfoRequest = new BookingInfoRequest(getContext(), estimateId, proposalId);
            NetworkManager.getInstance().getNetworkData(bookingInfoRequest, new NetworkManager.OnResultListener<NetworkResult<Estimate>>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResult<Estimate>> request, NetworkResult<Estimate> result) {
                    Toast.makeText(getContext(), "씨발 집 가고 싶다", Toast.LENGTH_SHORT).show();
                    Estimate estimate = result.getResult();
                    dateView.setText(estimate.getReservationTime().toString().substring(0, 10));
                    timeView.setText(estimate.getReservationTime().toString().substring(11, 19));
                    nicknameView.setText(estimate.getNickname());
                    priceView.setText("" + estimate.getBidPrice());

                }

                @Override
                public void onFail(NetworkRequest<NetworkResult<Estimate>> request, int errorCode, String errorMessage, Throwable e) {
                    Toast.makeText(getContext(), "코딩이나 쳐 해 씨발", Toast.LENGTH_SHORT).show();
                }
            });
        }

        return view;
    }
}
