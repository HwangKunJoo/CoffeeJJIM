package com.coffeejjim.developers.extrafunctions.notification;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.NotiContent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-31.
 */
public class NotiContentViewHolder extends ChildViewHolder {


    @BindView(R.id.notification_desc)
    TextView notiDescView;


    public NotiContentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(NotiContent notiContent){
        notiDescView.setText(notiContent.getNotiDesc());
    }
}
