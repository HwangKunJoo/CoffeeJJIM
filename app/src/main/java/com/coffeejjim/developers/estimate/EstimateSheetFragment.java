package com.coffeejjim.developers.estimate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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

        String[] str = getResources().getStringArray(R.array.auction_time_Array);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,str);
        Spinner spi = (Spinner)view.findViewById(R.id.estimate_sheet_endtime_spinner);
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

    @OnClick(R.id.btn_estimate_sheet_present)
    public void onEstimateCheckDialogButtonClick() {
        onDialogFragmentClick();
    }

    public void onDialogFragmentClick() {
        EstimateCheckDialogFragment f = new EstimateCheckDialogFragment();
        f.show(getFragmentManager(), "dialog");
    }
}
