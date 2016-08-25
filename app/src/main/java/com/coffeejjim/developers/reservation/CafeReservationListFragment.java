package com.coffeejjim.developers.reservation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.cafedetail.CafeDetailActivity;
import com.coffeejjim.developers.data.Cafe;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CafeReservationListFragment extends Fragment {

    public CafeReservationListFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.rv_list)
    RecyclerView listView;
    CafeListRecyclerAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_cafe_reservation_list, container, false);
        ButterKnife.bind(this, view);

        mAdapter = new CafeListRecyclerAdapter();
        mAdapter.setOnAdapterItemClickListener(new CafeListRecyclerAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, Cafe cafe, int position) {
                moveCafeDetailActivity();
            }
        });

        listView.setAdapter(mAdapter);

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        GridLayoutManager manager =
//                new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        listView.setLayoutManager(manager);

        initData();
        return view;
    }

    int[] resIds = {R.drawable.hwang, R.drawable.event1, R.drawable.event2,
            R.drawable.bestsample1, R.drawable.sample1, R.drawable.event4,
            R.drawable.bestsample2, R.drawable.hwang};

    public void initData() {
        for (int i = 0; i < 20; i++) {
            Cafe c = new Cafe();
            c.setCafeName("CAFE NO. " + i);
            c.setAddress("Address: " + i);
            c.setDistance(i + " km");
            c.setPrice(i + " won");
            c.setPhoto(ContextCompat.getDrawable(getContext(), resIds[i % resIds.length]));
            mAdapter.add(c);
        }
    }

    public void moveCafeDetailActivity() {
        Intent intent = new Intent(getActivity(), CafeDetailActivity.class);
        startActivity(intent);
        getActivity().finish();
    }


}
