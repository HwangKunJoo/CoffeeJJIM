package com.coffeejjim.developers.cafelist;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.cafedetail.CafeDetailActivity;
import com.coffeejjim.developers.data.Cafe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllCafeListFragment extends Fragment {


    @BindView(R.id.all_cafe_rv_list)
    RecyclerView listView;
    AllCafeListRecyclerAdapter mAdapter;


    public AllCafeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fr_all_cafe_list, container, false);
        ButterKnife.bind(this, view);

        getChildFragmentManager().beginTransaction()
                .add(R.id.container, new CafeMapChildFragment())
                .commit();

        mAdapter = new AllCafeListRecyclerAdapter();
        mAdapter.setOnAdapterItemClickListener(new AllCafeListRecyclerAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, Cafe cafe, int position) {
                moveCafeDetailActivity();
            }
        });

        listView.setAdapter(mAdapter);

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(manager);

        initData();
        return view;
    }

    int[] resIds = {R.drawable.hwang, R.drawable.event1, R.drawable.event2,
            R.drawable.bestsample1, R.drawable.sample1, R.drawable.event4,
            R.drawable.bestsample2, R.drawable.hwang};

    public void initData() {
        for (int i = 0; i < 20; i++) {
            Cafe c = new Cafe();
            c.setCafeName("CAFE NO. " + i);
            c.setAddress("Address: " + i);
            c.setDistance(i + " km");
            c.setPhoto(ContextCompat.getDrawable(getContext(), resIds[i % resIds.length]));
            c.setOptions(ContextCompat.getDrawable(getContext(), resIds[i % resIds.length]));
            mAdapter.add(c);
        }
    }

    public void moveCafeDetailActivity() {
        Intent intent = new Intent(getActivity(), CafeDetailActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
