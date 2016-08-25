package com.coffeejjim.developers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProviderHomeFragment extends Fragment {

    public ProviderHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fr_provider_home, container, false);
//        Button btn = (Button)view.findViewById(R.id.providerhome_modify_btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ((ProviderHomeActivity)getActivity()).changeProviderHomeEdit();
//            }
//        });
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.providerhome_modify_btn)
    public void onProvider(){
        ((ProviderHomeActivity)getActivity()).changeProviderHomeEdit();
    }

}
