package com.coffeejjim.developers.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.provider_signup_visible_layout)
    View visibleLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.login_kakao_btn)
    public void onLogin(){
        ((LoginActivity)getActivity()).moveHomeActivity();
    }


    @OnClick(R.id.login_provider_text)
    public void onSignupLayoutVisible() {
        visibleLayout.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.login_provider_btn)
    public void onProviderLogin(){
        ((LoginActivity)getActivity()).moveHomeActivity();
    }

    @OnClick(R.id.signup_go_text)
    public void onSingup(){
        ((LoginActivity)getActivity()).changeSingup();
    }

    @OnClick(R.id.find_id_go_btn)
    public void onFindInfo(){
        ((LoginActivity)getActivity()).moveFindInfoActivity();
    }
}
