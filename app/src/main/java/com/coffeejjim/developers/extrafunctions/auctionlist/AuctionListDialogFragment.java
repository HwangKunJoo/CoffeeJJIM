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
    TextView timeView;
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
    @BindView(R.id.fr_auction_list_dialog_endtime_text)
    TextView auctionTimeView;

    public AuctionListDialogFragment() {
        // Required empty public constructor
    }

    public static AuctionListDialogFragment newInstance(Estimate estimate) {
        AuctionListDialogFragment f = new AuctionListDialogFragment();
        Bundle b = new Bundle();
        b.putSerializable("estimate", estimate);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            estimate = (Estimate) getArguments().getSerializable("estimate");
        }
    }

    Estimate estimate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_auction_list_dialog, container, false);
        ButterKnife.bind(this, view);

        auctionListLocation.setText(estimate.getAddress());
        auctionListDate.setText(estimate.getReservationTime().toString().substring(0,10));
        timeView.setText(estimate.getReservationTime().toString().substring(11,19));
        auctionListPerson.setText(""+estimate.getPeople());
        if(estimate.getWifi() == 1){
            auctionListWifi.setImageResource(R.drawable.options_wifi_on);
        }else{
            auctionListWifi.setImageResource(R.drawable.options_wifi_off);
        }
        if(estimate.getSocket() == 1){
            auctionListSocket.setImageResource(R.drawable.options_plag_on);
        }else{
            auctionListSocket.setImageResource(R.drawable.options_plag_off);
        }
        if(estimate.getParking() == 1){
            auctionListParking.setImageResource(R.drawable.options_parking_on);
        }else{
            auctionListParking.setImageResource(R.drawable.options_parking_off);
        }
        if(estimate.getDays() == 1){
            auctionListDays.setImageResource(R.drawable.options_workingtime_on);
        }else{
            auctionListDays.setImageResource(R.drawable.options_workingtime_off);
        }
        auctionTimeView.setText(""+estimate.getAuctionTime());

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCancelable(false);

        return view;
    }

}
