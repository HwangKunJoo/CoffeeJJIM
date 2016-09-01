package com.coffeejjim.developers.extrafunctions.likelist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Cafe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by acer on 2016-08-28.
 */
public class LikeListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.like_list_cafe_name)
    TextView cafeNameView;
    @BindView(R.id.like_list_address)
    TextView addressView;
    @BindView(R.id.like_list_cafe_image)
    ImageView cafeMainImageView;
    @BindView(R.id.like_list_option_wifi)
    ImageView cafeOptionsImageView;
    @BindView(R.id.btn_dislike)
    ImageButton btn_dislike;



    public LikeListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onLikeListItemClick(v, cafe, getAdapterPosition());
                }
            }
        });
        btn_dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onDislikeButtonClick(view, cafe, getAdapterPosition());
                }
            }
        });
    }

    public interface OnLikeListItemClickListener {
        public void onLikeListItemClick(View view, Cafe cafe, int position);
        public void onDislikeButtonClick(View view, Cafe cafe, int position);
    }

    OnLikeListItemClickListener listener;

    public void setOnLikeListItemClickListener(OnLikeListItemClickListener listener) {
        this.listener = listener;
    }

    Cafe cafe;

    public void setCafe(Cafe cafe) {
        this.cafe = cafe;
        cafeNameView.setText(cafe.getCafeName());
        addressView.setText(cafe.getAddress());
        cafeMainImageView.setImageDrawable(cafe.getPhoto());
        cafeOptionsImageView.setImageDrawable(cafe.getOptions());
    }


}
