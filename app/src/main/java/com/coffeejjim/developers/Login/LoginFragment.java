package com.coffeejjim.developers.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.data.Owner;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.OwnerLoginRequest;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
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

    private SessionCallback callback;

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
            Toast.makeText(getContext(), "이리 들어오나 보자", Toast.LENGTH_SHORT).show();
            redirectSignupActivity();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if (exception != null) {
                Logger.e(exception);
            }
            Toast.makeText(getContext(), "이리 들어오나 보자1", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();// 세션 연결이 실패했을때 로그인 화면 다시
        }
    }

    protected void redirectSignupActivity() {       //세션 연결 성공 시 SignupActivity로 넘김
        final Intent intent = new Intent(getActivity(), KakaoSignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        getActivity().finish();
    }


//    @OnClick(R.id.login_kakao_btn)
//    public void onLogin() {
//        ((LoginActivity) getActivity()).moveHomeActivity();
//    }


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
        //코드로 아이디랑 비밀번호 다를 때 같이 처리
        //코드2랑 에러코드 401이면 아이디 또는 비밀번호가 다릅니다.
        final String ownerId = ownerLoginIdView.getText().toString();
        final String password = ownerPasswordView.getText().toString();

        OwnerLoginRequest OLRequest = new OwnerLoginRequest(getContext(), ownerId, password, "adasdwewe");
        NetworkManager.getInstance().getNetworkData(OLRequest, new NetworkManager.OnResultListener<NetworkResult<Owner>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Owner>> request, NetworkResult<Owner> result) {
                Toast.makeText(getContext(), "성공", Toast.LENGTH_SHORT).show();
                ((LoginActivity) getActivity()).moveProviderHomeActivity();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Owner>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "실패", Toast.LENGTH_SHORT).show();
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
}
