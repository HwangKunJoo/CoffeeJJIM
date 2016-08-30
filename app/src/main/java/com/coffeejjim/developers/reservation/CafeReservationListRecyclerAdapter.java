package com.coffeejjim.developers.reservation;

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
public class CafeReservationListRecyclerAdapter extends RecyclerView.Adapter<CafeReservationListViewHolder> implements CafeReservationListViewHolder.OnCafeItemClickListener{

    List<Cafe> items = new ArrayList<>();

    public void add(Cafe c) {
        items.add(c);
        notifyDataSetChanged();
    }

    @Override
    public CafeReservationListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_cafe_reservation_list, parent, false);
        CafeReservationListViewHolder holder = new CafeReservationListViewHolder(view);
        holder.setOnCafeItemClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(CafeReservationListViewHolder holder, int position) {
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
    public void onCafeItemClick(View view, Cafe cafe, int position) {
        listener.onAdapterItemClick(view, cafe, position);
    }

    @Override
    public void onCafeItemButtonClick(View view, Cafe cafe, int position) {
        listener.onAdapterButtonClick(view, cafe, position);
    }
}
