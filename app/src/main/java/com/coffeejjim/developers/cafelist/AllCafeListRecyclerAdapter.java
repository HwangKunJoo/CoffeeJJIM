package com.coffeejjim.developers.cafelist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Cafe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class AllCafeListRecyclerAdapter extends RecyclerView.Adapter<AllCafeListViewHolder>implements AllCafeListViewHolder.OnAllCafeItemClickListener{
    List<Cafe> items = new ArrayList<>();

    public void add(Cafe c) {
        items.add(c);
        notifyDataSetChanged();
    }

    @Override
    public AllCafeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_all_cafe_list, parent, false);
        AllCafeListViewHolder holder = new AllCafeListViewHolder(view);
        holder.setOnAllCafeItemClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(AllCafeListViewHolder holder, int position) {
        holder.setCafe(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnAdapterItemClickLIstener {
        public void onAdapterItemClick(View view, Cafe cafe, int position);
        public void onAdapterButtonClick(View view, Cafe cafe, int position);
    }

    OnAdapterItemClickLIstener listener;
    public void setOnAdapterItemClickListener(OnAdapterItemClickLIstener listener) {
        this.listener = listener;
    }


    @Override
    public void onAllCafeItemClick(View view, Cafe cafe, int position) {
        listener.onAdapterItemClick(view, cafe, position);
    }

    @Override
    public void onAllCafeButtonClick(View view, Cafe cafe, int position) {
        listener.onAdapterButtonClick(view, cafe, position);
    }
}
