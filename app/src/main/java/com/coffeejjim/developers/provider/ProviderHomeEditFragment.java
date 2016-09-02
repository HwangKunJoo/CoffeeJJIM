package com.coffeejjim.developers.provider;

import android.os.Bundle;
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

public class ProviderHomeEditFragment extends Fragment {

    public ProviderHomeEditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fr_provider_home_edit, container, false);
        ButterKnife.bind(this, view);

        String[] str1 = getResources().getStringArray(R.array.working_time_Array);
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,str1);
        Spinner spi1 = (Spinner)view.findViewById(R.id.provider_home_edit_working_daytime_spinner1);
        spi1.setAdapter(adapter1);
        spi1.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );

        String[] str2 = getResources().getStringArray(R.array.working_time_Array);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,str2);
        Spinner spi2 = (Spinner)view.findViewById(R.id.provider_home_edit_working_daytime_spinner2);
        spi2.setAdapter(adapter2);
        spi2.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );

        String[] str3 = getResources().getStringArray(R.array.working_time_Array);
        ArrayAdapter<String> adapter3=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,str3);
        Spinner spi3 = (Spinner)view.findViewById(R.id.provider_home_edit_working_weektime_spinner1);
        spi3.setAdapter(adapter3);
        spi3.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );

        String[] str4 = getResources().getStringArray(R.array.working_time_Array);
        ArrayAdapter<String> adapter4=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,str4);
        Spinner spi4 = (Spinner)view.findViewById(R.id.provider_home_edit_working_weektime_spinner2);
        spi4.setAdapter(adapter4);
        spi4.setOnItemSelectedListener(
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

    @OnClick(R.id.provider_home_complete_btn)
    public void onProviderHome(){
        ((ProviderHomeActivity)getActivity()).changeProviderHome();
    }

}
