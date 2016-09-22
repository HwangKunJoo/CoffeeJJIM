package com.coffeejjim.developers.owner.usermanagement;


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
import com.coffeejjim.developers.data.Customer;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.owner.usermanagement.uservisitcount.UserVisitCountActivity;
import com.coffeejjim.developers.request.UserManagementRequest;

import java.util.List;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new UserManagementRecyclerAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_user_management, container, false);
        ButterKnife.bind(this, view);

        mAdapter.setOnAdapterItemClickListener(new UserManagementRecyclerAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, Customer customer, int position, int flag) {

                Intent intent = new Intent(getActivity(), UserVisitCountActivity.class);
                intent.putExtra("userManagementInfo",customer);
                startActivity(intent);
            }
        });

        userManagementRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        userManagementRecyclerView.setLayoutManager(manager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        UserManagementRequest UMRequest = new UserManagementRequest(getContext(), "1", "10");
        NetworkManager.getInstance().getNetworkData(UMRequest, new NetworkManager.OnResultListener<NetworkResult<List<Customer>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<Customer>>> request, NetworkResult<List<Customer>> result) {
                List<Customer> customers = result.getResult();
                mAdapter.clear();
                mAdapter.addAll(customers);
                Toast.makeText(getContext(), "yap123123", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<Customer>>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "yap1231234555", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
