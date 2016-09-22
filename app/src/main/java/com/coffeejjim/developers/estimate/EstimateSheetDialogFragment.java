package com.coffeejjim.developers.estimate;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Estimate;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.EstimateRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class EstimateSheetDialogFragment extends DialogFragment {

    @BindView(R.id.fr_estimate_sheet_dialog_location_text)
    TextView locationView;
    @BindView(R.id.fr_estimate_sheet_dialog_date_text)
    TextView dateView;
    @BindView(R.id.fr_estimate_sheet_dialog_time_text)
    TextView timeView;
    @BindView(R.id.fr_estimate_sheet_dialog_person_text)
    TextView personView;
    @BindView(R.id.fr_estimate_sheet_dialog_auction_time_text)
    TextView auctionTimeView;
    @BindView(R.id.fr_estimate_sheet_dialog_wifi)
    ImageView wifiView;
    @BindView(R.id.fr_estimate_sheet_dialog_socket)
    ImageView socketView;
    @BindView(R.id.fr_estimate_sheet_dialog_parking)
    ImageView parkingView;
    @BindView(R.id.fr_estimate_sheet_dialog_days)
    ImageView daysView;


    public EstimateSheetDialogFragment() {
        // Required empty public constructor
    }

    public static EstimateSheetDialogFragment newInstance(Estimate estimate) {
        EstimateSheetDialogFragment f = new EstimateSheetDialogFragment();
        Bundle b = new Bundle();
        b.putSerializable("estimate", estimate);
        f.setArguments(b);
        return f;
    }

    Estimate estimate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            estimate = (Estimate) getArguments().getSerializable("estimate");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_estimate_sheet_dialog, container, false);
        ButterKnife.bind(this, view);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCancelable(false);
        setEstimateInfo();
        return view;
    }

    public void setEstimateInfo() {
        locationView.setText(estimate.getAddress());
        dateView.setText(estimate.getReservationTime().toString().substring(0,10));
        timeView.setText(estimate.getReservationTime().toString().substring(11,19));
        personView.setText(""+estimate.getPeople());
        auctionTimeView.setText(""+estimate.getAuctionTime());
        if(estimate.getWifi() == 1){
            wifiView.setImageResource(R.drawable.options_wifi_on);
        }else{
            wifiView.setImageResource(R.drawable.options_wifi_off);
        }
        if(estimate.getSocket() == 1){
            socketView.setImageResource(R.drawable.options_plag_on);
        }else{
            socketView.setImageResource(R.drawable.options_plag_off);
        }
        if(estimate.getParking() == 1){
            parkingView.setImageResource(R.drawable.options_parking_on);
        }else{
            parkingView.setImageResource(R.drawable.options_parking_off);
        }
        if(estimate.getDays() == 1){
            daysView.setImageResource(R.drawable.options_workingtime_on);
        }else{
            daysView.setImageResource(R.drawable.options_workingtime_off);
        }
    }

    @OnClick(R.id.fr_estimate_sheet_dialog_present_btn)
    public void onPresentEstimate(){
        EstimateRequest ERequest = new EstimateRequest(getContext(), estimate.getPeople(), 37.477025, 126.963493
                , estimate.getReservationTime(), estimate.getWifi(),
                estimate.getDays(), estimate.getParking(), estimate.getSocket(), estimate.getAuctionTime());
        NetworkManager.getInstance().getNetworkData(ERequest, new NetworkManager.OnResultListener<NetworkResult<Estimate>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Estimate>> request, NetworkResult<Estimate> result) {
                if (result.getCode() == 0) {
                    Toast.makeText(getContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "성공", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Estimate>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "실패", Toast.LENGTH_SHORT).show();
            }
        });
        getDialog().dismiss();
    }

    @OnClick(R.id.fr_estimate_sheet_dialog_cancle_btn)
    public void onCancel(){
        getDialog().dismiss();
    }
}
