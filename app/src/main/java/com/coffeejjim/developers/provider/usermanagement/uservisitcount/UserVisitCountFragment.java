package com.coffeejjim.developers.provider.usermanagement.uservisitcount;


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
public class UserVisitCountFragment extends Fragment {

    @BindView(R.id.user_visit_count_rv_list)
    RecyclerView userVisitCountRecyclerView;
    UserVisitCountRecyclerAdapter mAdapter;


    public UserVisitCountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_visit, container, false);
        ButterKnife.bind(this, view);

        mAdapter = new UserVisitCountRecyclerAdapter();
        mAdapter.setOnAdapterItemClickListener(new UserVisitCountRecyclerAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, Estimate estimate, int position) {

            }
        });

        userVisitCountRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        userVisitCountRecyclerView.setLayoutManager(manager);

        initData();
        return view;
    }

    public void initData() {

        for (int i = 0; i < 10; i++) {
            Estimate e = new Estimate();
            e.setPrice("12000원");
            e.setPeople(i);
            e.setReservationDate("2016. 08. 29");
            e.setReservationTime("11 : " + i);
            mAdapter.add(e);
        }
    }
}

