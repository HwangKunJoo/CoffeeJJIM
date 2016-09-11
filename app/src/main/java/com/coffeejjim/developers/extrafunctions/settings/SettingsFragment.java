package com.coffeejjim.developers.extrafunctions.settings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.AuctionRangeEditRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {


    @BindView(R.id.activity_settings_distance_spinner)
    Spinner auctionRangeSpi;

    String auctionRange;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_settings, container, false);
        ButterKnife.bind(this, view);
        setAuctionRangeSpinner();
        return view;
    }

    public void setAuctionRangeSpinner(){
        final String[] auctionRange = getResources().getStringArray(R.array.local_distance_Array);
        final ArrayAdapter<String> mAdapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,auctionRange);
        auctionRangeSpi.setAdapter(mAdapter);
        auctionRangeSpi.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                        putAuctionRange();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                }
        );
    }

    public void putAuctionRange(){
        AuctionRangeEditRequest ARERequest = new AuctionRangeEditRequest(getContext(), "5");
        NetworkManager.getInstance().getNetworkData(ARERequest, new NetworkManager.OnResultListener<NetworkResult<Integer>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Integer>> request, NetworkResult<Integer> result) {
                Toast.makeText(getContext(),"123", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Integer>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(),"123123", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
