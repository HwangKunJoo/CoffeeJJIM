package com.coffeejjim.developers.reservation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Cafe;

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



    public interface OnCafeItemClickListener {
        public void onCafeItemClick(View view, Cafe cafe, int position);
        public void onCafeItemButtonClick(View view, Cafe cafe, int position);
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
                    listener.onCafeItemClick(v, cafe, getAdapterPosition());
                }
            }
        });

        btn_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onCafeItemButtonClick(view, cafe, getAdapterPosition());
                }
            }
        });
    }

    Cafe cafe;

    public void setCafe(Cafe cafe) {
        this.cafe = cafe;
        titleView.setText(cafe.getCafeName());
        addressView.setText(cafe.getAddress());
        distanceView.setText(cafe.getDistance());
        priceView.setText(cafe.getPrice());
        photoView.setImageDrawable(cafe.getPhoto());
    }


//    @OnClick(R.id.cafelist_resevation_btn)
//    public void onCafeDetail(){
//        moveCafeDetailActivity();
//    }
//
//    public void moveCafeDetailActivity(){
//        Intent intent = new Intent(((CafeReservationListActivity)getActivity()) , CafeDetailActivity.class);
//    }
}
