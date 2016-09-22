package com.coffeejjim.developers.extrafunctions.auctionlist;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Estimate;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuctionListDialogFragment extends DialogFragment {

    @BindView(R.id.fr_auction_list_dialog_location_text)
    TextView auctionListLocation;
    @BindView(R.id.fr_auction_list_dialog_date_text)
    TextView auctionListDate;
    @BindView(R.id.fr_auction_list_dialog_time_text)
    TextView auctionListTime;
    @BindView(R.id.fr_auction_list_dialog_person_text)
    TextView auctionListPerson;
    @BindView(R.id.fr_auction_list_dialog_option_wifi)
    ImageView auctionListWifi;
    @BindView(R.id.fr_auction_list_dialog_socket)
    ImageView auctionListSocket;
    @BindView(R.id.fr_auction_list_dialog_parking)
    ImageView auctionListParking;
    @BindView(R.id.fr_auction_list_dialog_days)
    ImageView auctionListDays;

    public AuctionListDialogFragment() {
        // Required empty public constructor
    }

    public static AuctionListDialogFragment newInstance(Estimate estimate) {
        AuctionListDialogFragment f = new AuctionListDialogFragment();
        Bundle b = new Bundle();
        b.putSerializable("", estimate);
    }
    Estimate estimate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_auction_list_dialog, container, false);
        ButterKnife.bind(this, view);

        auctionListLocation.setText(estimate.getAddress());
        auctionListDate.setText(estimate.getReservationDate().toString().substring(0,10));
        auctionListTime.setText(estimate.getReservationTime().toString().substring((11,19));
        auctionListPerson.setText(""+estimate.getPeople());

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCancelable(false);

        return view;
    }

}
