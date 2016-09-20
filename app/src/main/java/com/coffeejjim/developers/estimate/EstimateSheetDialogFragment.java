package com.coffeejjim.developers.estimate;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.coffeejjim.developers.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class EstimateSheetDialogFragment extends DialogFragment {


    public EstimateSheetDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_estimate_sheet_dialog, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return view;
    }

}
