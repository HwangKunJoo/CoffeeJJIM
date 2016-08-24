package com.coffeejjim.developers.estimate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.R;

import butterknife.ButterKnife;


public class EstimateSheetFragment extends Fragment {


    public EstimateSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_estimate_sheet, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
