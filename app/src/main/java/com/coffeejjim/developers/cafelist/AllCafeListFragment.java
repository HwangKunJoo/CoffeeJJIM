package com.coffeejjim.developers.cafelist;


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
import com.coffeejjim.developers.cafedetail.CafeDetailActivity;
import com.coffeejjim.developers.data.Cafe;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.extrafunctions.likelist.LikeListRecyclerAdapter;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.AllCafeListRequest;
import com.coffeejjim.developers.request.LikeListRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllCafeListFragment extends Fragment {


    @BindView(R.id.all_cafe_rv_list)
    RecyclerView listView;
    AllCafeListRecyclerAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new AllCafeListRecyclerAdapter();
    }


    public AllCafeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fr_all_cafe_list, container, false);
        ButterKnife.bind(this, view);

        getChildFragmentManager().beginTransaction()
                .add(R.id.container, new CafeMapChildFragment())
                .commit();

        mAdapter = new AllCafeListRecyclerAdapter();
        mAdapter.setOnAdapterItemClickListener(new AllCafeListRecyclerAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, Cafe cafe, int position) {
                moveCafeDetailActivity();
            }

            @Override
            public void onAdapterButtonClick(View view, Cafe cafe, int position) {
                moveCafeDetailActivity();
            }
        });

        listView.setAdapter(mAdapter);

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(manager);

        return view;
    }


    public void moveCafeDetailActivity() {
        Intent intent = new Intent(getActivity(), CafeDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        AllCafeListRequest ACLRequest = new AllCafeListRequest(getContext(), "1", "10", 37, 126);
        NetworkManager.getInstance().getNetworkData(ACLRequest, new NetworkManager.OnResultListener<NetworkResult<List<Cafe>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<Cafe>>> request, NetworkResult<List<Cafe>> result) {
                List<Cafe> allCafeList = result.getResult();
                mAdapter.clear();
                mAdapter.addAll(allCafeList);
                Toast.makeText(getContext(), "yap", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<Cafe>>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "network fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
