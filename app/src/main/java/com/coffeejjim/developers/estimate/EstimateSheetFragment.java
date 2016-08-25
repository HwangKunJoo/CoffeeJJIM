package com.coffeejjim.developers.estimate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class EstimateSheetFragment extends Fragment {


    public EstimateSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_estimate_sheet, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.estimate_sheet_present_btn)
    public void onEstimateCheckDialogButtonClick() {
        onDialogFragmentClick();
    }

    public void onDialogFragmentClick() {
        EstimateCheckDialogFragment f = new EstimateCheckDialogFragment();
        f.show(getFragmentManager(), "dialog");
    }
}
