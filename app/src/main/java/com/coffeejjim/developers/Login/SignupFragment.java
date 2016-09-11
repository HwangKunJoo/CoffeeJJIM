package com.coffeejjim.developers.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.data.Owner;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.LoginIdCheckedRequest;
import com.coffeejjim.developers.request.OwnerSignUpRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupFragment extends Fragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.signup_name_edit)
    EditText ownerNameView;
    @BindView(R.id.signup_id_edit)
    EditText ownerIdView;
    @BindView(R.id.signup_password_edit)
    EditText ownerPasswordView;
    @BindView(R.id.signup_phone_edit)
    EditText ownerPhoneNumberView;
    @BindView(R.id.signup_email_edit)
    EditText ownerEmailView;
    @BindView(R.id.signup_cafe_name_edit)
    EditText cafeNameView;
    @BindView(R.id.signup_cafe_address_edit)
    EditText addressView;
    @BindView(R.id.signup_cafe_phone_edit)
    EditText cafePhoneNameView;

    private static final int SEARCH_ADDRESS = 1;
    String cafeAddress;


    //    public static SignupFragment newInstance(String data) {
//        SignupFragment fragment = new SignupFragment();
//        Bundle b = new Bundle();
//        b.putString("data", data);
//        fragment.setArguments(b);
//        return fragment;
//    }
//
    public SignupFragment() {
        // Required empty public constructor
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Bundle b = getArguments();
//        if (b != null) {
//            inputAddress = b.getString("data");
//        }
//    }
//

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_signup, container, false);
        ButterKnife.bind(this, view);
        setCustomActionbar();
        setHasOptionsMenu(true);


        String[] str = getResources().getStringArray(R.array.emailArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, str);
        Spinner spi = (Spinner) view.findViewById(R.id.signup_email_spinner);
        spi.setAdapter(adapter);
        spi.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );

        return view;
    }


    // 주소를 기반으로 위도, 경도 계산하는 함수 필요

    // 토큰 받아오는 함수도 필요

    // 회원가입 null값 처리


    @OnClick(R.id.btn_signup_id_duplication_check)
    public void onIsIdChecked() {

        final String ownerId = ownerIdView.getText().toString();

        LoginIdCheckedRequest LICRequest = new LoginIdCheckedRequest(getContext(), ownerId);
        NetworkManager.getInstance().getNetworkData(LICRequest, new NetworkManager.OnResultListener<NetworkResult<Owner>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Owner>> request, NetworkResult<Owner> result) {
                if(!TextUtils.isEmpty(ownerId) && result.getCode() != 2) {
                    IdDuplicationCheckDialogFragment idDuplicationCheckDialogFragment
                            = new IdDuplicationCheckDialogFragment();
                    idDuplicationCheckDialogFragment.show(getFragmentManager(), "IdDuplicationCheckDialog");
                }else{
                    IdDuplicationDialogFragment duplicationDialogFragment
                            = IdDuplicationDialogFragment.newInstance(ownerId);
                    duplicationDialogFragment.show(getFragmentManager(), "IdDuplicationDialog");
                }
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Owner>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(),"실패",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.signup_join_button)
    public void onSignupComlete() {
        String ownerName = ownerNameView.getText().toString();
        String ownerId = ownerIdView.getText().toString();
        String ownerPassword = ownerPasswordView.getText().toString();
        String ownerEmail = ownerEmailView.getText().toString();
        String ownerPhoneNumber = ownerPhoneNumberView.getText().toString();
        String cafeName = cafeNameView.getText().toString();
        String cafePhoneNumber = cafePhoneNameView.getText().toString();

        OwnerSignUpRequest OSRequest = new OwnerSignUpRequest(getContext(), ownerName, ownerId, ownerPassword, ownerPhoneNumber,
                ownerEmail, cafeName, cafeAddress, "32.12313", "127.12341", cafePhoneNumber, "123123dasda");

        NetworkManager.getInstance().getNetworkData(OSRequest, new NetworkManager.OnResultListener<NetworkResult<Owner>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Owner>> request, NetworkResult<Owner> result) {
                //property에 쓸 게 있으면 담아둠
                Toast.makeText(getContext(), "성공", Toast.LENGTH_SHORT).show();
                ((LoginActivity) getActivity()).moveProviderHomeActivity();
                getActivity().finish();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Owner>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "실패", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void setCustomActionbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            getActivity().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.signup_search_btn)
    public void onSearchAddress() {
        Intent intent = new Intent(getActivity(), SearchAddressActivity.class);
        startActivityForResult(intent, SEARCH_ADDRESS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SEARCH_ADDRESS || resultCode == Activity.RESULT_OK) {
            cafeAddress = data.getExtras().getString("data");
            addressView.setText(cafeAddress);
//            Bundle addressData = new Bundle();
//            addressData.putString("data", findAddress);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}