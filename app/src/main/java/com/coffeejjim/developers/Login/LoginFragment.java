package com.coffeejjim.developers.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.coffeejjim.developers.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment {

    @BindView(R.id.provider_input_id_edit)
    EditText ownerLoginIdView;
    @BindView(R.id.provider_input_pw_edit)
    EditText ownerPasswordView;

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
    public void onLogin() {
        ((LoginActivity) getActivity()).moveHomeActivity();
    }


    @OnClick(R.id.login_provider_text)
    public void onSignupLayoutVisible() {
        if (visibleLayout.getVisibility() == View.GONE) {
            visibleLayout.setVisibility(View.VISIBLE);
        } else if (visibleLayout.getVisibility() == View.VISIBLE) {
            visibleLayout.setVisibility(View.GONE);
        }

    }

    @OnClick(R.id.login_provider_btn)
    public void onProviderLogin() {
//        final String ownerId = ownerLoginIdView.getText().toString();
//        final String password = ownerPasswordView.getText().toString();
//
//        OwnerLoginRequest OLRequest = new OwnerLoginRequest(getContext(), ownerId, password, "adasdwewe");
//        NetworkManager.getInstance().getNetworkData(OLRequest, new NetworkManager.OnResultListener<NetworkResult<Owner>>() {
//            @Override
//            public void onSuccess(NetworkRequest<NetworkResult<Owner>> request, NetworkResult<Owner> result) {
//                Toast.makeText(getContext(), "성공", Toast.LENGTH_SHORT).show();
//                ((LoginActivity) getActivity()).moveProviderHomeActivity();
//            }
//
//            @Override
//            public void onFail(NetworkRequest<NetworkResult<Owner>> request, int errorCode, String errorMessage, Throwable e) {
//                Toast.makeText(getContext(), "실패", Toast.LENGTH_SHORT).show();
//            }
//        });

        ((LoginActivity) getActivity()).moveProviderHomeActivity();
    }

    @OnClick(R.id.signup_go_text)
    public void onSingup() {
        ((LoginActivity) getActivity()).changeSingup();
    }

    @OnClick(R.id.find_id_go_btn)
    public void onFindInfo() {
        ((LoginActivity) getActivity()).changeReissuance();
    }
}
