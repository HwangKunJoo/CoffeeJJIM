package com.coffeejjim.developers.owner.auctionprocess;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Estimate;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class AuctionProcessViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.auction_process_end_time)
    TextView deadlineTimeView;
    @BindView(R.id.auction_process_people)
    TextView peopleView;
    @BindView(R.id.auction_process_auction_start_time)
    TextView auctionStartTimeView;
    @BindView(R.id.auction_process_user_nickname)
    TextView userNicknameView;
    @BindView(R.id.auction_process_options_parking)
    ImageView optionParkingImageView;
    @BindView(R.id.auction_process_options_plag)
    ImageView optionSocketImageView;
    @BindView(R.id.auction_process_options_wifi)
    ImageView optionWifiImageView;
    @BindView(R.id.auction_process_options_working_time)
    ImageView optionDaysImageView;

    @BindView(R.id.btn_estimate_sheet_confirm)
    Button btn_confirm;

    public AuctionProcessViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onAuctionProcessItemClick(v, estimate, getAdapterPosition());
                }
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onAuctionProcessButtonClick(view, estimate, getAdapterPosition());
                }
            }
        });

    }

    public interface OnAuctionProcessItemClickListener {
        public void onAuctionProcessItemClick(View view, Estimate estimate, int position);
        public void onAuctionProcessButtonClick(View view, Estimate estimate, int position);
    }

    OnAuctionProcessItemClickListener listener;

    public void setOnAuctionProcessItemClickListener(OnAuctionProcessItemClickListener listener) {
        this.listener = listener;
    }

    Estimate estimate;

    public void setEstimate(Estimate estimate) {
        this.estimate = estimate;
        userNicknameView.setText(estimate.getNickname());
        deadlineTimeView.setText(estimate.getDeadlineTime());
        peopleView.setText(""+estimate.getPeople());
        auctionStartTimeView.setText(estimate.getAuctionTime());
        if(estimate.getWifi() == 1){
            optionWifiImageView.setVisibility(View.VISIBLE);
        }
        if(estimate.getSocket() == 1){
            optionSocketImageView.setVisibility(View.VISIBLE);
        }
        if(estimate.getParking() == 1){
            optionParkingImageView.setVisibility(View.VISIBLE);
        }
        if(estimate.getDays() == 1){
            optionDaysImageView.setVisibility(View.VISIBLE);
        }
    }
}
