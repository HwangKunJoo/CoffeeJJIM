package com.coffeejjim.developers.estimate.provider;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Estimate;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.data.Proposal;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.ProposalRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProposalFragment extends Fragment {

    @BindView(R.id.proposal_price_edit)
    EditText bidPriceView;
    @BindView(R.id.btn_proposal_present)
    Button btn_proposal;
    @BindView(R.id.proposal_date)
    TextView dateView;
    @BindView(R.id.proposal_time)
    TextView timeView;
    @BindView(R.id.proposal_person)
    TextView personView;
    @BindView(R.id.proposal_endtime)
    TextView auctionTimeView;


    public ProposalFragment() {
        // Required empty public constructor
    }

    public static ProposalFragment newInstance(Estimate estimate){
        ProposalFragment f = new ProposalFragment();
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
            estimate = (Estimate)getArguments().getSerializable("estimate");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fr_proposal, container, false);
        ButterKnife.bind(this, view);
        dateView.setText(estimate.getReservationTime().toString().substring(0,10));
        timeView.setText(estimate.getReservationTime().toString().substring(11,19));
        personView.setText(""+estimate.getPeople());
        auctionTimeView.setText(""+estimate.getAuctionTime());

        btn_proposal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postProposal();
                onAlertDialog(view);
            }
        });
        return view;
    }

    public void postProposal(){
        String bidPrice = bidPriceView.getText().toString();
        ProposalRequest PRequest = new ProposalRequest(getContext(), estimate.getEstimateId(), bidPrice);
        NetworkManager.getInstance().getNetworkData(PRequest, new NetworkManager.OnResultListener<NetworkResult<Proposal>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Proposal>> request, NetworkResult<Proposal> result) {
                Toast.makeText(getActivity(), "확인", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Proposal>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getActivity(), "취소", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onAlertDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Coffee JJIM");
        builder.setMessage("입찰 하시겠습니까?");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                //Toast.makeText(getActivity(), "확인", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                //Toast.makeText(getActivity(), "취소", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setCancelable(false);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                //Toast.makeText(getActivity(), "취소 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        builder.setCancelable(false);
        dialog.show();
    }
}
