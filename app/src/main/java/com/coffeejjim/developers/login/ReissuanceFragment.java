package com.coffeejjim.developers.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.data.Owner;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.OwnerInfoFindRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReissuanceFragment extends Fragment {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fr_reissuance_email_edit)
    EditText ownerEmailView;

    public ReissuanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_reissuance, container, false);
        ButterKnife.bind(this,view);
        setCustomActionbar();
        setHasOptionsMenu(true);
        return view;
    }

    @OnClick(R.id.btn_find_owner_info)
    public void onFindInfo(){
        String ownerEmail = ownerEmailView.getText().toString();
        OwnerInfoFindRequest ownerInfoFindRequest = new OwnerInfoFindRequest(getContext(),ownerEmail);
        NetworkManager.getInstance().getNetworkData(ownerInfoFindRequest, new NetworkManager.OnResultListener<NetworkResult<Owner>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Owner>> request, NetworkResult<Owner> result) {
                Toast.makeText(getContext(),"이메일이 전송되었습니다.",Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Owner>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(),"이메일이 전송이 실패하였습니다.\n 메일주소를 확인해 주세요.",Toast.LENGTH_SHORT).show();
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
        if(id == android.R.id.home){
            getActivity().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
