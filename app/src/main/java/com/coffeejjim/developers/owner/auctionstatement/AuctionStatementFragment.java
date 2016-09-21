package com.coffeejjim.developers.owner.auctionstatement;


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
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.owner.BookingInfoActivity;
import com.coffeejjim.developers.request.AuctionListRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuctionStatementFragment extends Fragment {


    @BindView(R.id.auction_statement_rv_list)
    RecyclerView auctionStatementRecyclerView;
    AuctionStatementRecyclerAdapter mAdapter;

    public AuctionStatementFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new AuctionStatementRecyclerAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_auction_statement, container, false);
        ButterKnife.bind(this, view);


        mAdapter.setOnAdapterItemClickListener(new AuctionStatementRecyclerAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, Estimate estimate, int position) {

            }

            @Override
            public void onAdapterButtonClick(View view, Estimate estimate, int position) {
                Intent intent = new Intent(getActivity(), BookingInfoActivity.class);
                intent.putExtra("estimate",estimate);
                startActivity(intent);
            }
        });

        auctionStatementRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        auctionStatementRecyclerView.setLayoutManager(manager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        AuctionListRequest ALRequest = new AuctionListRequest(getContext(), 1, 2016, 9);
        NetworkManager.getInstance().getNetworkData(ALRequest, new NetworkManager.OnResultListener<NetworkResult<List<Estimate>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<Estimate>>> request, NetworkResult<List<Estimate>> result) {
                List<Estimate> estimateList = result.getResult();
                mAdapter.clear();
                mAdapter.addAll(estimateList);
                Toast.makeText(getContext(), "qqq", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<Estimate>>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "qqq123", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
