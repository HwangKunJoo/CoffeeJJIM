package com.coffeejjim.developers.extrafunctions.notification;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.NotiContent;
import com.coffeejjim.developers.data.Notification;

import java.util.List;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class NotificationRecyclerAdapter extends ExpandableRecyclerAdapter<NotiTitleViewHolder, NotiContentViewHolder>{

    private LayoutInflater mInflator;

    public NotificationRecyclerAdapter(Context context, @NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflator = LayoutInflater.from(context);
    }

    @Override
    public NotiTitleViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View notiTitleView = mInflator.inflate(R.layout.view_notification_title, parentViewGroup, false);
        return new NotiTitleViewHolder(notiTitleView);
    }

    @Override
    public NotiContentViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View notiContentView = mInflator.inflate(R.layout.view_notification_content, childViewGroup, false);
        return new NotiContentViewHolder(notiContentView);
    }

    @Override
    public void onBindParentViewHolder(NotiTitleViewHolder notiTitleViewHolder, int position, ParentListItem parentListItem) {
        Notification notification = (Notification) parentListItem;
        notiTitleViewHolder.bind(notification);
    }

    @Override
    public void onBindChildViewHolder(NotiContentViewHolder notiContentViewHolder, int position, Object childListItem) {
        NotiContent notiContent = (NotiContent) childListItem;
        notiContentViewHolder.bind(notiContent);
    }
}
