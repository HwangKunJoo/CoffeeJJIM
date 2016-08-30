package com.coffeejjim.developers.provider.usermanagement.uservisitcount;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Estimate;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class UserVisitCountViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.user_visit_count_description_visit_date)
    TextView userVisitCountDateView;
    @BindView(R.id.user_visit_count_description_visit_time)
    TextView userVisitCountTimeView;
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
                    listener.onUserVisitCountItemClick(v, estimate, getAdapterPosition());
                }
            }
        });
        ButterKnife.bind(this, itemView);
    }

    public interface OnUserVisitCountItemClickListener {
        public void onUserVisitCountItemClick(View view, Estimate estimate, int position);
    }

    OnUserVisitCountItemClickListener listener;

    public void setOnUserVisitCountItemClickListener(OnUserVisitCountItemClickListener listener) {
        this.listener = listener;
    }

    Estimate estimate;

    public void setEstimate(Estimate estimate) {
        this.estimate = estimate;
        userVisitCountDateView.setText(estimate.getReservationDate());
        userVisitCountTimeView.setText(estimate.getReservationTime());
        userVisitCountPeopleView.setText(""+estimate.getPeople());
        userVisitCountPriceView.setText(estimate.getPrice());

    }
}
