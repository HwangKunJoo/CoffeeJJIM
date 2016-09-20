package com.coffeejjim.developers.login;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneInfoDialogFragment extends DialogFragment {


    public PhoneInfoDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_phone_info_dialog, container, false);
        ButterKnife.bind(this,view);


        return view;
    }

    @OnClick(R.id.fr_phone_info_send_btn)
    public void sendPhonInfo() {
        ((LoginActivity) getActivity()).moveHomeActivity();
    }

}
