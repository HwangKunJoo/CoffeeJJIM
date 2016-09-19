package com.coffeejjim.developers.extrafunctions.auctionlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Estimate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class AuctionListRecyclerAdapter extends RecyclerView.Adapter<AuctionListViewHolder> implements AuctionListViewHolder.OnAuctionListItemClickListener{

    List<Estimate> items = new ArrayList<>();

    public void add(Estimate e) {
        items.add(e);
        notifyDataSetChanged();
    }

    public void addAll(List<Estimate> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public AuctionListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_auction_list, parent, false);
        AuctionListViewHolder holder = new AuctionListViewHolder(view);
        holder.setOnAuctionListItemClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(AuctionListViewHolder holder, int position) {
        holder.setEstimate(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnAdapterItemClickLIstener {
        public void onAdapterItemClick(View view, Estimate estimate, int position);
        public void onAdapterButtonClick(View view, Estimate estimate, int position);
    }

    OnAdapterItemClickLIstener listener;
    public void setOnAdapterItemClickListener(OnAdapterItemClickLIstener listener) {
        this.listener = listener;
    }


    @Override
    public void onAuctionListItemClick(View view, Estimate estimate, int position) {
        listener.onAdapterItemClick(view, estimate, position);
    }

    @Override
    public void onAuctionListButtonClick(View view, Estimate estimate, int position) {
        listener.onAdapterButtonClick(view, estimate, position);
    }
}
