package com.coffeejjim.developers.reservation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Proposal;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class CafeReservationListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.cafelist_title_text)
    TextView titleView;
    @BindView(R.id.cafelist_address_text)
    TextView addressView;
    @BindView(R.id.cafelist_distance_text)
    TextView distanceView;
    @BindView(R.id.cafelist_price_text)
    TextView priceView;
    @BindView(R.id.cafelist_photo_image)
    ImageView photoView;
    @BindView(R.id.btn_cafe_list_reservation)
    Button btn_reservation;
    @BindView(R.id.cafelist_option_wifi)
    ImageView optionWifiImageView;
    @BindView(R.id.cafelist_option_parking)
    ImageView optionParkingImageView;
    @BindView(R.id.cafelist_option_plag)
    ImageView optionSocketImageView;
    @BindView(R.id.cafelist_option_workingtime)
    ImageView optionDaysImageView;




    public interface OnCafeItemClickListener {
        public void onCafeItemClick(View view, Proposal proposal, int position);

        public void onCafeItemButtonClick(View view, Proposal proposal, int position);
    }

    OnCafeItemClickListener listener;

    public void setOnCafeItemClickListener(OnCafeItemClickListener listener) {
        this.listener = listener;
    }

    public CafeReservationListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onCafeItemClick(v, proposal, getAdapterPosition());
                }
            }
        });

        btn_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onCafeItemButtonClick(view, proposal, getAdapterPosition());
                }
            }
        });
    }

    Proposal proposal;

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
        titleView.setText(proposal.getCafe().getCafeName());
        addressView.setText(proposal.getCafe().getAddress());
        distanceView.setText(proposal.getCafe().getDistance());
        priceView.setText(proposal.getBidPrice());
        Glide.with(photoView.getContext())
                .load(proposal.getCafeImage().getImageUrl())
                .into(photoView);
        if(proposal.getOptions().isWifi()){
            optionWifiImageView.setVisibility(View.VISIBLE);
        }
        if(proposal.getOptions().isSocket()){
            optionSocketImageView.setVisibility(View.VISIBLE);
        }
        if(proposal.getOptions().isParking()){
            optionParkingImageView.setVisibility(View.VISIBLE);
        }
        if(proposal.getOptions().isDays()){
            optionDaysImageView.setVisibility(View.VISIBLE);
        }

    }
}
