package com.coffeejjim.developers.extrafunctions.auctionlist;

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
 * Created by acer on 2016-08-28.
 */
public class AuctionListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.auctionlist_time_text)
    TextView auctionListTimeView;
    @BindView(R.id.auctionlist_cafe_title_text)
    TextView auctionListCafenameView;
    @BindView(R.id.auctionlist_price_text)
    TextView auctionListPrice;
    @BindView(R.id.auctionlist_estimate_btn)
    Button btn_estimate;

    @BindView(R.id.auction_list_reservation_fail_image)
    ImageView reservationFailImageView;

    public AuctionListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onAuctionListItemClick(v, estimate, getAdapterPosition());
                }
            }
        });
        btn_estimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onAuctionListButtonClick(v, estimate, getAdapterPosition());
                }
            }
        });
    }

    public interface OnAuctionListItemClickListener {
        public void onAuctionListItemClick(View view, Estimate estimate, int position);
        public void onAuctionListButtonClick(View view, Estimate estimate, int position);
    }

    OnAuctionListItemClickListener listener;

    public void setOnAuctionListItemClickListener(OnAuctionListItemClickListener listener) {
        this.listener = listener;
    }

    Estimate estimate;

    public void setEstimate(Estimate estimate) {
        this.estimate = estimate;
        auctionListTimeView.setText(estimate.getReservationTime().toString().substring(11));
        auctionListCafenameView.setText(estimate.getCafeName());
        auctionListPrice.setText(""+estimate.getBidPrice());
        reservationFailImageView.setImageResource(R.drawable.ic_reservationsuccess);

    }
}
