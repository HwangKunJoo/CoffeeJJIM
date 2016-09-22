package com.coffeejjim.developers.owner.usermanagement.uservisitcount;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.cafedetail.CafeDetailFragment;
import com.coffeejjim.developers.data.Customer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserVisitCountFragment extends Fragment {

    @BindView(R.id.user_visit_count_rv_list)
    RecyclerView userVisitCountRecyclerView;
    UserVisitCountRecyclerAdapter mAdapter;

    @BindView(R.id.user_visit_count_user_nickname)
    TextView userVisitNickname;
    @BindView(R.id.user_visit_count_user_visit_count)
    TextView userVisitCount;
    @BindView(R.id.user_visit_count_user_like_image)
    ImageView likeView;


    public UserVisitCountFragment() {
        // Required empty public constructor
    }

    public static CafeDetailFragment newInstance(Customer customer) {
        CafeDetailFragment f = new CafeDetailFragment();
        Bundle b = new Bundle();
        b.putSerializable("userManagementInfo", customer);
        f.setArguments(b);
        return f;
    }

    Customer customer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            customer = (Customer) getArguments().getSerializable("userManagementInfo");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_user_visit, container, false);
        ButterKnife.bind(this, view);

//        userVisitNickname.setText(customer.getNickname());
//        userVisitCount.setText(""+customer.getVisitTime());
//        if(customer.getBookmark() == -1){
//            likeView.setImageResource(R.drawable.btn_likeempty);
//        }else{
//            likeView.setImageResource(R.drawable.btn_likefull);
//        }


        mAdapter = new UserVisitCountRecyclerAdapter();
        mAdapter.setOnAdapterItemClickListener(new UserVisitCountRecyclerAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, Customer customer, int position) {


            }
        });

        userVisitCountRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        userVisitCountRecyclerView.setLayoutManager(manager);

        return view;
    }

}