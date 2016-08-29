package com.coffeejjim.developers.extrafunctions.likelist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Cafe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DislikeFragment extends Fragment {

    @BindView(R.id.dislike_rv_list)
    RecyclerView dislikeListRecyclerView;
    LikeListRecyclerAdapter mAdapter;

    private static final boolean SET_VISIBLE = true;


    public DislikeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dislike, container, false);

        ButterKnife.bind(this, view);

        mAdapter = new LikeListRecyclerAdapter(SET_VISIBLE);
        mAdapter.setOnAdapterItemClickListener(new LikeListRecyclerAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, Cafe cafe, int position) {
                mAdapter.delete(cafe);
            }
        });

        dislikeListRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        dislikeListRecyclerView.setLayoutManager(manager);

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
            c.setPhoto(ContextCompat.getDrawable(getContext(), resIds[i % resIds.length]));
            mAdapter.add(c);
        }
    }

}
