package com.coffeejjim.developers.extrafunctions.settings;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.AuctionRangeEditRequest;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_settings, container, false);
        ButterKnife.bind(this, view);
        putAuctionRange();



        return view;
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

//    @OnClick(R.id.activity_settings_vi_switch)
//    public void onvibSwitch( boolean isChecked) {
//
//        if (isChecked == true) {
//            Toast.makeText(getContext(), "진동 On", Toast.LENGTH_SHORT).show();
//
//            Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
//            vibrator.vibrate(200);
//
//        } else {
//            Toast.makeText(getContext(), "진동 Off", Toast.LENGTH_SHORT).show();
//        }
//
//    }

}
