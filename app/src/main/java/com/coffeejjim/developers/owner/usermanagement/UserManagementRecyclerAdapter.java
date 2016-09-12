package com.coffeejjim.developers.owner.usermanagement;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-23.
 */
public class UserManagementRecyclerAdapter extends RecyclerView.Adapter<UserManagementViewHolder>
        implements UserManagementViewHolder.OnUserManagementItemClickListener {

    List<Customer> items = new ArrayList<>();

    public void add(Customer c) {
        items.add(c);
        notifyDataSetChanged();
    }

    public void addAll(List<Customer> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void clear(){
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public UserManagementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_user_management, parent, false);
        UserManagementViewHolder holder = new UserManagementViewHolder(view);
        holder.setOnUserManagementItemClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(UserManagementViewHolder holder, int position) {
        holder.setCustomer(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnAdapterItemClickLIstener {
        public void onAdapterItemClick(View view, Customer customer, int position, int flag);
    }

    OnAdapterItemClickLIstener listener;

    public void setOnAdapterItemClickListener(OnAdapterItemClickLIstener listener) {
        this.listener = listener;
    }


    @Override
    public void onUserManagementItemClick(View view, Customer customer, int position, int flag) {
        listener.onAdapterItemClick(view, customer, position, flag);
    }
}
