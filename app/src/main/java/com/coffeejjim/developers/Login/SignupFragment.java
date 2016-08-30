package com.coffeejjim.developers.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupFragment extends Fragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_signup, container, false);
        ButterKnife.bind(this,view);
        setCustomActionbar();
        setHasOptionsMenu(true);
        return view;
    }

    @OnClick(R.id.signup_join_button)
    public void onSingupComlete() {
        ((LoginActivity)getActivity()).moveProviderHomeActivity();
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
}
