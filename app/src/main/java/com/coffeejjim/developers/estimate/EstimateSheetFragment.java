package com.coffeejjim.developers.estimate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.coffeejjim.developers.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class EstimateSheetFragment extends Fragment {

    @BindView(R.id.estimate_sheet_date_edit)
    EditText dateView;

    @BindView(R.id.estimate_sheet_location_edit)
    EditText locationView;

    @BindView(R.id.estimate_sheet_people_edit)
    EditText peopleView;

    @BindView(R.id.estimate_sheet_time_edit)
    EditText timeView;

    @BindView(R.id.estimate_sheet_wifi_btn)
    ImageButton btn_wifi;

    @BindView(R.id.estimate_sheet_parking_btn)
    ImageButton btn_parking;

    @BindView(R.id.estimate_sheet_socket_btn)
    ImageButton btn_socket;

    @BindView(R.id.estimate_sheet_days_btn)
    ImageButton btn_days;

    @BindView(R.id.estimate_sheet_endtime_spinner)
    Spinner auctionTimeSpi;



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

        setEstimateSpinner();


        return view;
    }

    private void setEstimateSpinner(){
        String[] str = getResources().getStringArray(R.array.auction_time_Array);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,str);
        auctionTimeSpi.setAdapter(adapter);
        auctionTimeSpi.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );
    }

    @OnClick(R.id.btn_estimate_sheet_present)
    public void onEstimateCheckDialogButtonClick()  {
        String location = locationView.getText().toString();


        onDialogFragmentClick();
    }

    public void onDialogFragmentClick() {
        EstimateCheckDialogFragment f = new EstimateCheckDialogFragment();
        f.show(getFragmentManager(), "dialog");
    }
}
