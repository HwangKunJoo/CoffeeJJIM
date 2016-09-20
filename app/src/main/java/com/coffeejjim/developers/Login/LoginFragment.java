package com.coffeejjim.developers.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.coffeejjim.developers.CoffeeJJIMApplication;
import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.data.Owner;
import com.coffeejjim.developers.home.HomeActivity;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.manager.PropertyManager;
import com.coffeejjim.developers.request.KakaoLoginRequest;
import com.coffeejjim.developers.request.OwnerLoginRequest;
import com.kakao.auth.ErrorCode;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment {

    @BindView(R.id.provider_input_id_edit)
    EditText ownerLoginIdView;
    @BindView(R.id.provider_input_pw_edit)
    EditText ownerPasswordView;
    @BindView(R.id.provider_signup_visible_layout)
    View visibleLayout;
    @BindView(R.id.login_kakao_btn)
    LoginButton kakaoLogin;



    SessionCallback callback;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_login, container, false);
        ButterKnife.bind(this, view);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {

            UserManagement.requestMe(new MeResponseCallback() {

                @Override
                public void onFailure(ErrorResult errorResult) {
                    String message = "failed to get user info. msg=" + errorResult;
                    Logger.d(message);

                    ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                    if (result == ErrorCode.CLIENT_ERROR_CODE) {
                        getActivity().finish();
                    } else {
                        //redirectMainActivity();
                    }
                }

                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                }

                @Override
                public void onNotSignedUp() {
                }

                @Override
                public void onSuccess(UserProfile userProfile) {
                    //로그인에 성공하면 로그인한 사용자의 일련번호, 닉네임, 이미지url등을 리턴합니다.
                    //사용자 ID는 보안상의 문제로 제공하지 않고 일련번호는 제공합니다.
                    Log.e("UserProfile", userProfile.toString());
                    Toast.makeText(getContext(), "이리 들어오나 보자2", Toast.LENGTH_SHORT).show();
                    final String access_token = Session.getCurrentSession().getAccessToken();
                    String fcmToken = PropertyManager.getInstance().getRegistrationId();
                    KakaoLoginRequest KLRequest =
                            new KakaoLoginRequest(CoffeeJJIMApplication.getCoffeeJJIMApplicationContext(),
                                    access_token, fcmToken);
                    NetworkManager.getInstance().getNetworkData(KLRequest, new NetworkManager.OnResultListener<NetworkResult<Object>>() {
                        @Override
                        public void onSuccess(NetworkRequest<NetworkResult<Object>> request, NetworkResult<Object> result) {
                            Toast.makeText(getContext(), "access token 전달 성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), HomeActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }

                        @Override
                        public void onFail(NetworkRequest<NetworkResult<Object>> request, int errorCode, String errorMessage, Throwable e) {
                            Toast.makeText(getContext(), "access token 전달 실패", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

        }


        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if (exception != null) {
                Logger.e(exception);
            }
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();// 세션 연결이 실패했을때 로그인 화면 다시
        }
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
        final String ownerId = ownerLoginIdView.getText().toString();
        final String password = ownerPasswordView.getText().toString();
        String fcmToken = PropertyManager.getInstance().getRegistrationId();

        OwnerLoginRequest OLRequest = new OwnerLoginRequest(getContext(), ownerId, password, fcmToken);
        NetworkManager.getInstance().getNetworkData(OLRequest, new NetworkManager.OnResultListener<NetworkResult<Owner>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Owner>> request, NetworkResult<Owner> result) {
                Toast.makeText(getContext(), "성공", Toast.LENGTH_SHORT).show();
                ((LoginActivity) getActivity()).moveProviderHomeActivity();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Owner>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "아이디 또는 비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.signup_go_text)
    public void onSingup() {
        ((LoginActivity) getActivity()).changeSingup();
    }

    @OnClick(R.id.find_id_go_btn)
    public void onFindInfo() {
        ((LoginActivity) getActivity()).changeReissuance();
    }

    @OnClick(R.id.button)
    public void onLogin() {
        ((LoginActivity) getActivity()).moveHomeActivity();
    }

}
