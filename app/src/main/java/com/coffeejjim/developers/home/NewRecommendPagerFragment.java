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

public class NewRecommendPagerFragment extends Fragment {


    @BindView(R.id.home_new_cafe_image)
    ImageView newCafeImageView;

    public NewRecommendPagerFragment() {
        // Required empty public constructor
    }

    public static NewRecommendPagerFragment newInstance(CafeImage newCafeImage) {
        NewRecommendPagerFragment f = new NewRecommendPagerFragment();
        Bundle b = new Bundle();
        b.putSerializable("newCafeImage", newCafeImage);
        f.setArguments(b);
        return f;
    }

    CafeImage newCafeImage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            newCafeImage = (CafeImage)(getArguments().getSerializable("newCafeImage"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_new_recommend_pager, container, false);
        ButterKnife.bind(this, view);
        Glide.with(newCafeImageView.getContext())
                .load(newCafeImage.getImageUrl()).into(newCafeImageView);
        return view;
    }

    @OnClick(R.id.home_new_cafe_image)
    public void onNewCafeDeatail(){
        moveCafeDetailActivity();
    }

    public void moveCafeDetailActivity(){
        Intent intent = new Intent(getActivity(),CafeDetailActivity.class);
        startActivity(intent);
    }
}
