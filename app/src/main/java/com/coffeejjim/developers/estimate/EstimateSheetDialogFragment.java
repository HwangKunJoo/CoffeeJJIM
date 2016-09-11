package com.coffeejjim.developers.estimate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class EstimateSheetDialogFragment extends Fragment {


    public EstimateSheetDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fr_estimate_sheet_dialog, container, false);
    }

}
