package com.coffeejjim.developers.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.coffeejjim.developers.CoffeeJJIMApplication;
import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.home.HomeActivity;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.manager.PropertyManager;
import com.coffeejjim.developers.request.AddPhoneNumberRequest;
import com.coffeejjim.developers.request.KakaoLoginRequest;
import com.kakao.auth.Session;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneInfoDialogFragment extends DialogFragment {

    @BindView(R.id.fr_phone_info_edit)
    EditText phoneNumberView;

    public PhoneInfoDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_phone_info_dialog, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getFragmentManager().findFragmentByTag("phone");
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.fr_phone_info_send_btn)
    public void sendPhoneInfo() {
        final String phoneNumber = phoneNumberView.getText().toString();
        PropertyManager.getInstance().setKeyPhonenumber(phoneNumber);
        final String access_token = Session.getCurrentSession().getAccessToken();
        String fcmToken = PropertyManager.getInstance().getRegistrationId();
        KakaoLoginRequest KLRequest =
                new KakaoLoginRequest(CoffeeJJIMApplication.getCoffeeJJIMApplicationContext(),
                        access_token, fcmToken);
        NetworkManager.getInstance().getNetworkData(KLRequest, new NetworkManager.OnResultListener<NetworkResult<Object>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Object>> request, NetworkResult<Object> result) {
                AddPhoneNumberRequest addPhoneNumberRequest = new AddPhoneNumberRequest(getContext(), phoneNumber);
                NetworkManager.getInstance().getNetworkData(addPhoneNumberRequest, new NetworkManager.OnResultListener<NetworkResult<String>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<String>> request, NetworkResult<String> result) {
                        Toast.makeText(getContext(), "전화번호 등록", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<String>> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(getContext(), "전화번호 등록 실패, 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onFail(NetworkRequest<NetworkResult<Object>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "전화번호 등록 실패 또는 억세스 토큰 전달 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.fr_phone_info_cancel_btn)
    public void cancelPhonInfo(){
        getDialog().dismiss();
    }

}
