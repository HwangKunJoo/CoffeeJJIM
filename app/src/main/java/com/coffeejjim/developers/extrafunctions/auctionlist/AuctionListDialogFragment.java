package com.coffeejjim.developers.extrafunctions.auctionlist;


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
public class AuctionListDialogFragment extends DialogFragment {


    public AuctionListDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_auction_list_dialog, container, false);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCancelable(false);

        return view;
    }

}
