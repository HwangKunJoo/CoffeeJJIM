package com.coffeejjim.developers.owner.usermanagement.uservisitcount;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Customer;

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
        View view = inflater.inflate(R.layout.fr_user_visit, container, false);
        ButterKnife.bind(this, view);

        mAdapter = new UserVisitCountRecyclerAdapter();
        mAdapter.setOnAdapterItemClickListener(new UserVisitCountRecyclerAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, Customer customer, int position) {

            }
        });

        userVisitCountRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        userVisitCountRecyclerView.setLayoutManager(manager);

        return view;
    }

}

