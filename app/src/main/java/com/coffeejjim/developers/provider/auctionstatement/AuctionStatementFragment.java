package com.coffeejjim.developers.provider.auctionstatement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Estimate;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuctionStatementFragment extends Fragment {


    @BindView(R.id.auction_statement_rv_list)
    RecyclerView auctionStatementRecyclerView;
    AuctionStatementRecyclerAdapter mAdapter;

    public AuctionStatementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auction_statement, container, false);
        ButterKnife.bind(this, view);

        mAdapter = new AuctionStatementRecyclerAdapter();
        mAdapter.setOnAdapterItemClickListener(new AuctionStatementRecyclerAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, Estimate estimate, int position) {

            }
        });

        auctionStatementRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        auctionStatementRecyclerView.setLayoutManager(manager);

        initData();
        return view;
    }

    public void initData() {
        for (int i = 0; i < 10; i++) {
            Estimate estimate = new Estimate();
            estimate.setEndTime("11 : 0 " + i);
            estimate.setPeople(i);
            estimate.setUserNickname("User. " + i);
            estimate.setPrice(i + "2000원");
            if(i/2 == 0){
                estimate.setReserved(true);
            }else
                estimate.setReserved(false);
            mAdapter.add(estimate);
        }
    }


}
