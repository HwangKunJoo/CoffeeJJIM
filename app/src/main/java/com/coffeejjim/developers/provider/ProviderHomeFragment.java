package com.coffeejjim.developers.provider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProviderHomeFragment extends Fragment {



    public ProviderHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_provider_home, container, false);
        ButterKnife.bind(this, view);

        return view;
    }



    @OnClick(R.id.provider_home_edit_btn)
    public void onProviderEdit() {
        ((ProviderHomeActivity)getActivity()).changeProviderHomeEdit();
    }


}
