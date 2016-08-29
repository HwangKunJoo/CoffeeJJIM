package com.coffeejjim.developers.provider.usermanagement;


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
public class UserManagementFragment extends Fragment {


    @BindView(R.id.user_management_rv_list)
    RecyclerView userManagementRecyclerView;
    UserManagementRecyclerAdapter mAdapter;

    public UserManagementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_user_management, container, false);
        ButterKnife.bind(this, view);

        mAdapter = new UserManagementRecyclerAdapter();
        mAdapter.setOnAdapterItemClickListener(new UserManagementRecyclerAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, Customer customer, int position) {

            }
        });

        userManagementRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        userManagementRecyclerView.setLayoutManager(manager);

        initData();
        return view;
    }

    public void initData(){
        for(int i =0; i<5; i++){
            Customer c = new Customer();
            c.setNickName("User No." + i);
            c.setVisitCount(i);
            if(i == 2) {
                c.setLiked(false);
            }
            if(i == 4){
                c.setLiked(true);
            }
            mAdapter.add(c);
        }

    }

}
