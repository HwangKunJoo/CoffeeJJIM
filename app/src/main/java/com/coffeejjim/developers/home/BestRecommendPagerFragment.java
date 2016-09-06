package com.coffeejjim.developers.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.coffeejjim.developers.R;
import com.coffeejjim.developers.cafedetail.CafeDetailActivity;
import com.coffeejjim.developers.data.CafeImage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BestRecommendPagerFragment extends Fragment {

    @BindView(R.id.home_best_cafe_image)
    ImageView bestCafeImageView;

    public BestRecommendPagerFragment() {
        // Required empty public constructor
    }

    public static BestRecommendPagerFragment newInstance(CafeImage bestImage) {
        BestRecommendPagerFragment f = new BestRecommendPagerFragment();
        Bundle b = new Bundle();
        b.putSerializable("bestImage", bestImage);
        f.setArguments(b);
        return f;
    }

    CafeImage bestImage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bestImage = (CafeImage)(getArguments().getSerializable("bestImage"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_best_recommend_pager, container, false);
        ButterKnife.bind(this,view);
        Glide.with(bestCafeImageView.getContext())
                .load(bestImage.getImageUrl()).into(bestCafeImageView);
        return view;
    }


    @OnClick(R.id.home_best_cafe_image)
    public void onEventDeatail(){
        moveCafeDetailActivity();
    }

    public void moveCafeDetailActivity(){
        Intent intent = new Intent(getActivity(),CafeDetailActivity.class);
        startActivity(intent);
    }
}