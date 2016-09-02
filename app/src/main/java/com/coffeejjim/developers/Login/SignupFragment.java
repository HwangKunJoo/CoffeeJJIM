package com.coffeejjim.developers.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.coffeejjim.developers.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupFragment extends Fragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    String inputAddress;

    @BindView(R.id.signup_address_edit)
    EditText addressView;

    private static final int SEARCH_ADDRESS = 1;
//
//    public static SignupFragment newInstance(String data) {
//        SignupFragment fragment = new SignupFragment();
//        Bundle b = new Bundle();
//        b.putString("data", data);
//        fragment.setArguments(b);
//        return fragment;
//    }
//
//    public SignupFragment() {
//        // Required empty public constructor
//    }
//
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
        ButterKnife.bind(this,view);
        setCustomActionbar();
        setHasOptionsMenu(true);



        String[] str = getResources().getStringArray(R.array.emailArray);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,str);
        Spinner spi = (Spinner)view.findViewById(R.id.signup_email_spinner);
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



    @OnClick(R.id.signup_join_button)
    public void onSingupComlete() {
        ((LoginActivity)getActivity()).moveProviderHomeActivity();
        getActivity().finish();
    }


    private void setCustomActionbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            getActivity().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.signup_search_btn)
    public void onSearchAddress(){
        Intent intent = new Intent(getActivity(), SearchAddressActivity.class);
        startActivityForResult(intent,SEARCH_ADDRESS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SEARCH_ADDRESS || resultCode == Activity.RESULT_OK) {
            inputAddress = data.getExtras().getString("data");
            addressView.setText(inputAddress);
//            Bundle addressData = new Bundle();
//            addressData.putString("data", findAddress);
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}