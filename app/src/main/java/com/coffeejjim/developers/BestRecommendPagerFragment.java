package com.coffeejjim.developers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class BestRecommendPagerFragment extends Fragment {

    public BestRecommendPagerFragment() {
        // Required empty public constructor
    }

    public static BestRecommendPagerFragment newInstance(int bestPhoto) {
        BestRecommendPagerFragment f = new BestRecommendPagerFragment();
        Bundle b = new Bundle();
        b.putInt("bestPhoto", bestPhoto);
        f.setArguments(b);
        return f;
    }

    int bestPhoto;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bestPhoto = getArguments().getInt("bestPhoto");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_best_recommend_pager, container, false);
        ButterKnife.bind(this,view);
        ImageView eventPhotoView = (ImageView) view.findViewById(R.id.bestSample);
        eventPhotoView.setImageResource(bestPhoto);
        return view;
    }

    @OnClick(R.id.bestSample)
    public void onEventDeatail(){
        moveCafeDetailActivity();
    }

    public void moveCafeDetailActivity(){
        Intent intent = new Intent((HomeActivity)(getActivity()),CafeDetailActivity.class);
        startActivity(intent);
    }
}
