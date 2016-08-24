package com.coffeejjim.developers.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupFragment extends Fragment {

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_signup, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.signup_join_button)
    public void onSingupComlete() {
        ((LoginActivity)getActivity()).moveHomeActivity();
    }
}
