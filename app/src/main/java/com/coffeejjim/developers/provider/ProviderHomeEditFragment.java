package com.coffeejjim.developers.provider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProviderHomeEditFragment extends Fragment {

    public ProviderHomeEditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fr_provider_home_edit, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.providerhome_edit_ok_btn)
    public void onProviderHome(){
        ((ProviderHomeActivity)getActivity()).changeProviderHome();
    }

}
