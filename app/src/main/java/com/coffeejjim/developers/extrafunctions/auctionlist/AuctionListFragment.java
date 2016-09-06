package com.coffeejjim.developers.extrafunctions.auctionlist;


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
public class AuctionListFragment extends Fragment {

    @BindView(R.id.auction_rv_list)
    RecyclerView auctionListRecyclerView;
    AuctionListRecyclerAdapter mAdapter;

    public AuctionListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_auction_list, container, false);
        ButterKnife.bind(this, view);

        mAdapter = new AuctionListRecyclerAdapter();
        mAdapter.setOnAdapterItemClickListener(new AuctionListRecyclerAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, Estimate estimate, int position) {

            }
        });

        auctionListRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        auctionListRecyclerView.setLayoutManager(manager);

        initData();
        return view;
    }

    public void initData() {
        for (int i = 0; i < 20; i++) {
            Estimate estimate = new Estimate();
            estimate.setReservationTime("11 : 00");
            estimate.setCafeName("LATTE KING");
            estimate.setAddress("서울시 봉천구 낙성대1길");
            estimate.setPrice("13,000");

            if(i/2 == 0){
                estimate.setReserved(true);
            }else
                estimate.setReserved(false);
            mAdapter.add(estimate);
        }
    }

}
