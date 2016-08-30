package com.coffeejjim.developers.provider.usermanagement.uservisitcount;

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
public class UserVisitCountRecyclerAdapter extends RecyclerView.Adapter<UserVisitCountViewHolder>
        implements UserVisitCountViewHolder.OnUserVisitCountItemClickListener{

    List<Estimate> items = new ArrayList<>();

    public void add(Estimate e){
        items.add(e);
        notifyDataSetChanged();
    }

    @Override
    public UserVisitCountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_user_visit_count, parent, false);
        UserVisitCountViewHolder holder = new UserVisitCountViewHolder(view);
        holder.setOnUserVisitCountItemClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(UserVisitCountViewHolder holder, int position) {
        holder.setEstimate(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnAdapterItemClickLIstener {
        public void onAdapterItemClick(View view, Estimate estimate, int position);
    }

    OnAdapterItemClickLIstener listener;

    public void setOnAdapterItemClickListener(OnAdapterItemClickLIstener listener) {
        this.listener = listener;
    }

    @Override
    public void onUserVisitCountItemClick(View view, Estimate estimate, int position) {
        listener.onAdapterItemClick(view, estimate, position);
    }
}