package com.coffeejjim.developers.owner.auctionstatement;

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
public class AuctionStatementViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.auction_statement_time)
    TextView auctionStatementEndTimeView;
    @BindView(R.id.auction_statement_user_nickname)
    TextView auctionStatementUserNicknameView;
    @BindView(R.id.auction_statement_people)
    TextView auctionStatementPeopleView;
    @BindView(R.id.auction_statement_price)
    TextView auctionStatementPriceView;
    @BindView(R.id.auction_statement_reservation_fail_image)
    ImageView reservationFailImageView;
    @BindView(R.id.btn_reservation_booking_info)
    Button btn_booking_info;

    public AuctionStatementViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onAuctionStatementItemClick(v, estimate, getAdapterPosition());
                }
            }
        });
        btn_booking_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onAuctionStatementButtonClick(view, estimate, getAdapterPosition());
                }
            }
        });

    }
    public interface OnAuctionStatementItemClickListener {
        public void onAuctionStatementItemClick(View view, Estimate estimate, int position);
        public void onAuctionStatementButtonClick(View view, Estimate estimate, int position);
    }

    OnAuctionStatementItemClickListener listener;

    public void setOnAuctionStatementItemClickListener(OnAuctionStatementItemClickListener listener) {
        this.listener = listener;
    }

    Estimate estimate;

    public void setEstimate(Estimate estimate) {
        this.estimate = estimate;
        auctionStatementUserNicknameView.setText(estimate.getUserNickname());
        auctionStatementEndTimeView.setText(estimate.getEndTime());
        auctionStatementPriceView.setText(estimate.getPrice());
        auctionStatementPeopleView.setText(""+estimate.getPeople());
        if(!estimate.isReserved()){
            reservationFailImageView.setImageResource(R.drawable.reservationfail);
        }else
            reservationFailImageView.setImageResource(R.drawable.reservationsuccess);

    }
}
