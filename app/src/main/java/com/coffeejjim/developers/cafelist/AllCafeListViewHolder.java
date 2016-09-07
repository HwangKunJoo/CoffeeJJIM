package com.coffeejjim.developers.cafelist;

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
 * Created by Tacademy on 2016-08-26.
 */
public class AllCafeListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.allcafelist_cafe_name_text)
    TextView cafeNameView;
    @BindView(R.id.allcafelist_address_text)
    TextView addressView;
    @BindView(R.id.allcafelist_distance_text)
    TextView distanceView;
    @BindView(R.id.allcafelist_cafe_main_image)
    ImageView cafeMainImageView;
    @BindView(R.id.all_cafe_list_options_wifi)
    ImageView cafeOptionsImageView;
    @BindView(R.id.btn_all_cafe_list_detail)
    Button btn_detail_cafe;


    public AllCafeListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onAllCafeItemClick(v, cafe, getAdapterPosition());
                }
            }
        });
        btn_detail_cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onAllCafeButtonClick(view, cafe, getAdapterPosition());
                }
            }
        });

    }

    public interface OnAllCafeItemClickListener {
        public void onAllCafeItemClick(View view, Cafe cafe, int position);
        public void onAllCafeButtonClick(View view, Cafe cafe, int position);
    }

    OnAllCafeItemClickListener listener;

    public void setOnAllCafeItemClickListener(OnAllCafeItemClickListener listener) {
        this.listener = listener;
    }

    Cafe cafe;

    public void setCafe(Cafe cafe) {
        this.cafe = cafe;
        cafeNameView.setText(cafe.getCafeName());
    }
}
