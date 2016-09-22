package com.coffeejjim.developers.owner.usermanagement.uservisitcount;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Customer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class UserVisitCountViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.user_visit_count_description_visit_date)
    TextView userVisitCountDateView;
    @BindView(R.id.user_visit_count_description_visit_time)
    TextView userVisitTimeView;
    @BindView(R.id.user_visit_count_description_people)
    TextView userVisitCountPeopleView;
    @BindView(R.id.user_visit_count_description_price)
    TextView userVisitCountPriceView;

    public UserVisitCountViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onUserVisitCountItemClick(v, customer, getAdapterPosition());
                }
            }
        });
        ButterKnife.bind(this, itemView);
    }

    public interface OnUserVisitCountItemClickListener {
        public void onUserVisitCountItemClick(View view, Customer customer, int position);
    }

    OnUserVisitCountItemClickListener listener;

    public void setOnUserVisitCountItemClickListener(OnUserVisitCountItemClickListener listener) {
        this.listener = listener;
    }

    Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;
        String reservationTime = customer.getReservationTime();
        userVisitCountDateView.setText(reservationTime.substring(0,9));
        userVisitTimeView.setText(reservationTime.substring(11,18));
        userVisitCountPeopleView.setText(""+customer.getPeople());
        userVisitCountPriceView.setText(""+customer.getBidPrice());

    }
}
