package com.coffeejjim.developers.reservation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Proposal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class CafeReservationListRecyclerAdapter extends RecyclerView.Adapter<CafeReservationListViewHolder> implements CafeReservationListViewHolder.OnCafeItemClickListener{

    List<Proposal> items = new ArrayList<>();

    public void add(Proposal p) {
        items.add(p);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Proposal> items) {
        this.items.addAll(items);
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
        holder.setProposal(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnAdapterItemClickLIstener {
        public void onAdapterItemClick(View view, Proposal proposal, int position);
        public void onAdapterButtonClick(View view, Proposal proposal, int position);
    }

    OnAdapterItemClickLIstener listener;
    public void setOnAdapterItemClickListener(OnAdapterItemClickLIstener listener) {
        this.listener = listener;
    }


    @Override
    public void onCafeItemClick(View view, Proposal proposal, int position) {
        listener.onAdapterItemClick(view, proposal, position);
    }

    @Override
    public void onCafeItemButtonClick(View view, Proposal proposal, int position) {
        listener.onAdapterButtonClick(view, proposal, position);
    }
}
