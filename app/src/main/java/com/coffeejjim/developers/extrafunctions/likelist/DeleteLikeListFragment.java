package com.coffeejjim.developers.extrafunctions.likelist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Cafe;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.DeleteLikeListRequest;
import com.coffeejjim.developers.request.LikeListRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteLikeListFragment extends Fragment {

    @BindView(R.id.dislike_rv_list)
    RecyclerView dislikeListRecyclerView;
    LikeListRecyclerAdapter mAdapter;

    private static final boolean SET_VISIBLE = true;


    public DeleteLikeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_dislike, container, false);

        ButterKnife.bind(this, view);

        mAdapter = new LikeListRecyclerAdapter(SET_VISIBLE);
        mAdapter.setOnAdapterItemClickListener(new LikeListRecyclerAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, Cafe cafe, int position) {

            }

            @Override
            public void onAdapterButtonClick(View view, final Cafe cafe, int position) {
                DeleteLikeListRequest DLLRequest = new DeleteLikeListRequest(getContext(),cafe.getCafeId());
                NetworkManager.getInstance().getNetworkData(DLLRequest, new NetworkManager.OnResultListener<NetworkResult<Cafe>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<Cafe>> request, NetworkResult<Cafe> result) {
                        mAdapter.delete(cafe);
                        Toast.makeText(getContext(), "yap지워졌당", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<Cafe>> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(getContext(), "failfailfailfail", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        dislikeListRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        dislikeListRecyclerView.setLayoutManager(manager);


        return view;
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
