package com.coffeejjim.developers.reservation;

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
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.data.Proposal;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.CafeReservationRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CafeReservationListFragment extends Fragment {

    @BindView(R.id.rv_list)
    RecyclerView listView;
    CafeReservationListRecyclerAdapter mAdapter;

    public CafeReservationListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new CafeReservationListRecyclerAdapter();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_cafe_reservation_list, container, false);
        ButterKnife.bind(this, view);

        mAdapter.setOnAdapterItemClickListener(new CafeReservationListRecyclerAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, Proposal proposal, int position) {
                moveCafeDetailActivity();
            }

            @Override
            public void onAdapterButtonClick(View view, Proposal proposal, int position) {
                CafeReservationCheckDialogFragment f = new CafeReservationCheckDialogFragment();
                f.show(getFragmentManager(), "dialog");
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
        CafeReservationRequest CRRequest = new CafeReservationRequest(getContext(), "1", "10");
        NetworkManager.getInstance().getNetworkData(CRRequest, new NetworkManager.OnResultListener<NetworkResult<List<Proposal>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<Proposal>>> request, NetworkResult<List<Proposal>> result) {
                List<Proposal> proposalList = result.getResult();
                mAdapter.clear();
                mAdapter.addAll(proposalList);
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<Proposal>>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "network fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
