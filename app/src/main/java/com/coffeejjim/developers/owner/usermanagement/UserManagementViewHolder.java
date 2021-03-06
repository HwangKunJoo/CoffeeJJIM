package com.coffeejjim.developers.owner.usermanagement;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
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
    @BindView(R.id.user_management_visit_count)
    TextView userManagementVisitCountView;
    @BindView(R.id.user_management_like)
    ImageView userManagementLikeView;
    @BindView(R.id.btn_user_management_visit_count_info)
    Button btn_visit_count_info;

    public static final int BTN_VISIT_COUNT = 1;



    public UserManagementViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        btn_visit_count_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onUserManagementItemClick(view, customer, getAdapterPosition(), BTN_VISIT_COUNT);
                }
            }
        });
    }

    public interface OnUserManagementItemClickListener {
        public void onUserManagementItemClick(View view, Customer customer, int position, int flag);
    }

    OnUserManagementItemClickListener listener;

    public void setOnUserManagementItemClickListener(OnUserManagementItemClickListener listener) {
        this.listener = listener;
    }


    Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;
        userManagementNicknameView.setText(customer.getNickname());
        userManagementVisitCountView.setText(""+customer.getVisitTime());

        if(customer.getBookmark() == -1){
            userManagementLikeView.setImageResource(R.drawable.btn_likeempty);
        }else
            userManagementLikeView.setImageResource(R.drawable.btn_likefull);

    }
}
