package com.coffeejjim.developers.extrafunctions.likelist;

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
public class LikeListRecyclerAdapter extends RecyclerView.Adapter<LikeListViewHolder> implements LikeListViewHolder.OnLikeListItemClickListener{

    boolean visiblity = false;

    public LikeListRecyclerAdapter(boolean visiblity) {
        this.visiblity = visiblity;
    }

    List<Cafe> items = new ArrayList<>();

    public void add(Cafe c) {
        items.add(c);
        notifyDataSetChanged();
    }

    public void delete(Cafe c){
        items.remove(c);
        notifyDataSetChanged();
    }

    public void clear(Cafe c){
        items.clear();
        notifyDataSetChanged();
    }


    @Override
    public LikeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_like_list, parent, false);
        LikeListViewHolder holder = new LikeListViewHolder(view);
        holder.setOnLikeListItemClickListener(this);
        if(visiblity){
            holder.btn_dislike.setVisibility(View.VISIBLE);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(LikeListViewHolder holder, int position) {
        holder.setCafe(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnAdapterItemClickLIstener {
        public void onAdapterItemClick(View view, Cafe cafe, int position);
    }

    OnAdapterItemClickLIstener listener;
    public void setOnAdapterItemClickListener(OnAdapterItemClickLIstener listener) {
        this.listener = listener;
    }

    @Override
    public void onLikeListItemClick(View view, Cafe cafe, int position) {
        listener.onAdapterItemClick(view, cafe, position);
    }

//    @OnClick(R.id.btn_dislike)
//    public void DeleteItems(){
//
//    }
}
