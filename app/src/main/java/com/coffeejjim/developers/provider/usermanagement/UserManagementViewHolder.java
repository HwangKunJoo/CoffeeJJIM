package com.coffeejjim.developers.provider.usermanagement;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Customer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class UserManagementViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.user_management_nickname)
    TextView userManagementNicknameView;
    @BindView(R.id.user_management_title_visit_count)
    TextView userManagementVisitCountView;
    @BindView(R.id.user_management_like)
    ImageView userManagementLikeView;


    public UserManagementViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onUserManagementItemClick(v, customer, getAdapterPosition());
                }
            }
        });
        ButterKnife.bind(this, itemView);
    }

    public interface OnUserManagementItemClickListener {
        public void onUserManagementItemClick(View view, Customer customer, int position);
    }

    OnUserManagementItemClickListener listener;

    public void setOnUserManagementItemClickListener(OnUserManagementItemClickListener listener) {
        this.listener = listener;
    }

    Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;
        userManagementNicknameView.setText(customer.getNickName());
        userManagementVisitCountView.setText(""+customer.getVisitCount());

        if(!customer.isLiked()){
            userManagementLikeView.setImageResource(R.drawable.likeempty);
        }else
            userManagementLikeView.setImageResource(R.drawable.likefull);

    }
}
