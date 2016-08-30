package com.coffeejjim.developers.provider.auctionprocess;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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
    TextView endTimeView;
    @BindView(R.id.auction_process_people)
    TextView peopleView;
    @BindView(R.id.auction_process_reservation_time)
    TextView reservationTimeView;
    @BindView(R.id.auction_process_reservation_date)
    TextView reservationDateView;
    @BindView(R.id.auction_process_user_nickname)
    TextView userNicknameView;
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
        userNicknameView.setText(estimate.getUserNickname());
        endTimeView.setText(estimate.getEndTime());
        peopleView.setText(""+estimate.getPeople());
        reservationTimeView.setText(estimate.getReservationTime());
        reservationDateView.setText(estimate.getReservationDate());
    }
}
