package com.coffeejjim.developers.extrafunctions.auctionlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Estimate;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by acer on 2016-08-28.
 */
public class AuctionListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.auctionlist_time_text)
    TextView auctionListTimeView;
    @BindView(R.id.auctionlist_nickname_text)
    TextView auctionListCafenameView;
    @BindView(R.id.auctionlist_price_text)
    TextView auctionListPrice;

    @BindView(R.id.auction_list_reservation_fail_image)
    ImageView reservationFailImageView;

    public AuctionListViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onAuctionListItemClick(v, estimate, getAdapterPosition());
                }
            }
        });
        ButterKnife.bind(this, itemView);
    }

    public interface OnAuctionListItemClickListener {
        public void onAuctionListItemClick(View view, Estimate estimate, int position);
    }

    OnAuctionListItemClickListener listener;

    public void setOnAuctionListItemClickListener(OnAuctionListItemClickListener listener) {
        this.listener = listener;
    }

    Estimate estimate;

    public void setEstimate(Estimate estimate) {
        this.estimate = estimate;
        auctionListTimeView.setText(estimate.getReservationTime());
        auctionListCafenameView.setText(estimate.getCafeName());
        auctionListPrice.setText(estimate.getPrice());


        if(!estimate.isReserved()){
            reservationFailImageView.setImageResource(R.drawable.reservationfail);
        }else
            reservationFailImageView.setImageResource(R.drawable.reservationsuccess);

    }
}
