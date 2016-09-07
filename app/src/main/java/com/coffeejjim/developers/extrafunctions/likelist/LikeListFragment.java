package com.coffeejjim.developers.extrafunctions.likelist;


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
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.LikeListRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LikeListFragment extends Fragment {

    private static final boolean SET_GONE = false;


    @BindView(R.id.like_rv_list)
    RecyclerView likeListRecyclerView;

    LikeListRecyclerAdapter mAdapter;

    public LikeListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new LikeListRecyclerAdapter(SET_GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_like_list, container, false);
        ButterKnife.bind(this, view);

        mAdapter.setOnAdapterItemClickListener(new LikeListRecyclerAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, Cafe cafe, int position) {
                moveCafeDetailActivity();
            }

            @Override
            public void onAdapterButtonClick(View view, Cafe cafe, int position) {

            }
        });


        likeListRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        likeListRecyclerView.setLayoutManager(manager);


        return view;
    }


    public void moveCafeDetailActivity() {
        Intent intent = new Intent(getActivity(), CafeDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        LikeListRequest ADRequest = new LikeListRequest(getContext(), "1", "10");
        NetworkManager.getInstance().getNetworkData(ADRequest, new NetworkManager.OnResultListener<NetworkResult<List<Cafe>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<Cafe>>> request, NetworkResult<List<Cafe>> result) {
                List<Cafe> likeList = result.getResult();
                mAdapter.clear();
                mAdapter.addAll(likeList);
                Toast.makeText(getContext(), "yap", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<Cafe>>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "network fail", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
