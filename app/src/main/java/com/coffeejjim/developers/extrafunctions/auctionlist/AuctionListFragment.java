package com.coffeejjim.developers.extrafunctions.auctionlist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Estimate;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.AuctionListRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuctionListFragment extends Fragment {

    @BindView(R.id.auction_rv_list)
    RecyclerView auctionListRecyclerView;
    AuctionListRecyclerAdapter mAdapter;

    private Button estimateDialog;

    public static final int AUCTION_LIST_CALL = 110;

    public AuctionListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new AuctionListRecyclerAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_auction_list, container, false);
        ButterKnife.bind(this, view);


        mAdapter.setOnAdapterItemClickListener(new AuctionListRecyclerAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, Estimate estimate, int position) {

            }

            @Override
            public void onAdapterButtonClick(View view, Estimate estimate, int position) {

                ////////////////////////////////    1차    ///////////////////////////////
//                getFragmentManager().beginTransaction().replace(R.id.container, new EstimateSheetDialogFragment())
//                        .addToBackStack(null).commit();

                ////////////////////////////////    2차    ////////////////////////////
//                EstimateSheetDialogFragment estimateSheetDialogFragment
//                        = EstimateSheetDialogFragment.newInstance(AUCTION_LIST_CALL);
//                estimateSheetDialogFragment.show(getFragmentManager(), "Dialog");

                ////////////////////////////////   3차   ///////////////////////////////
                FragmentManager f = getActivity().getSupportFragmentManager();
                AuctionListDialogFragment auctionListDialogFragment = new AuctionListDialogFragment();
                auctionListDialogFragment.show(f, "dialog");

            }
        });

        auctionListRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        auctionListRecyclerView.setLayoutManager(manager);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        AuctionListRequest ALRequest = new AuctionListRequest(getContext(), 0, 2016, 12);
        NetworkManager.getInstance().getNetworkData(ALRequest, new NetworkManager.OnResultListener<NetworkResult<List<Estimate>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<Estimate>>> request, NetworkResult<List<Estimate>> result) {
                List<Estimate> estimateList = result.getResult();
                mAdapter.clear();
                mAdapter.addAll(estimateList);
                Toast.makeText(getContext(), "qqq", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<Estimate>>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "qqq123", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
