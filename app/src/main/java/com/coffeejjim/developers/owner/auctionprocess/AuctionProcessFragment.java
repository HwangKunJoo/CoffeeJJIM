package com.coffeejjim.developers.owner.auctionprocess;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Estimate;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.estimate.provider.ProposalActivity;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.AuctionProcessRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuctionProcessFragment extends Fragment {

    @BindView(R.id.auction_process_rv_list)
    RecyclerView auctionProcessRecyclerView;

    AuctionProcessRecyclerAdapter mAdapter;


    public AuctionProcessFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new AuctionProcessRecyclerAdapter();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_auction_process, container, false);

        ButterKnife.bind(this, view);

        mAdapter = new AuctionProcessRecyclerAdapter();
        mAdapter.setOnAdapterItemClickListener(new AuctionProcessRecyclerAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, Estimate estimate, int position) {

            }

            @Override
            public void onAdapterButtonClick(View view, Estimate estimate, int position) {
                Intent intent = new Intent(getActivity(), ProposalActivity.class);
                startActivity(intent);
            }
        });

        auctionProcessRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        auctionProcessRecyclerView.setLayoutManager(manager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        AuctionProcessRequest APRequest = new AuctionProcessRequest(getContext(), "1", "10");
        NetworkManager.getInstance().getNetworkData(APRequest, new NetworkManager.OnResultListener<NetworkResult<List<Estimate>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<Estimate>>> request, NetworkResult<List<Estimate>> result) {
                List<Estimate> estimateList = result.getResult();
                mAdapter.clear();
                mAdapter.addAll(estimateList);
                Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<Estimate>>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "fffffffffffffail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
