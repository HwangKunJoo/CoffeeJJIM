package com.coffeejjim.developers.provider.auctionprocess;


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
public class AuctionProcessFragment extends Fragment {

    @BindView(R.id.auction_process_rv_list)
    RecyclerView auctionProcessRecyclerView;

    AuctionProcessRecyclerAdapter mAdapter;


    public AuctionProcessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auction_process, container, false);

        ButterKnife.bind(this, view);

        mAdapter = new AuctionProcessRecyclerAdapter();
        mAdapter.setOnAdapterItemClickListener(new AuctionProcessRecyclerAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, Estimate estimate, int position) {

            }
        });

        auctionProcessRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        auctionProcessRecyclerView.setLayoutManager(manager);

        initData();
        return view;
    }


    public void initData() {
        for (int i = 0; i < 10; i++) {
            Estimate e = new Estimate();
            e.setUserNickname("User " + i );
            e.setCafeName("Cafe No. " + i);
            e.setAddress("경기도 성남시 분당구 " + i);
            e.setPeople(i);
            e.setEndTime("11 : " + i);
            e.setReservationDate("2016. 08. 29");
            e.setReservationTime("11 : " + i);
            mAdapter.add(e);
        }
    }

}
