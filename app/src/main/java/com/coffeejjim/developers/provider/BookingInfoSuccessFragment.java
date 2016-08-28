package com.coffeejjim.developers.provider;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingInfoSuccessFragment extends DialogFragment {


    public BookingInfoSuccessFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_booking_info_success, container);
        return view;
    }

}
