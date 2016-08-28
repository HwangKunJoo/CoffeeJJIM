package com.coffeejjim.developers.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.cafedetail.CafeDetailActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewRecommendPagerFragment extends Fragment {

    public NewRecommendPagerFragment() {
        // Required empty public constructor
    }

    public static NewRecommendPagerFragment newInstance(int newPhoto) {
        NewRecommendPagerFragment f = new NewRecommendPagerFragment();
        Bundle b = new Bundle();
        b.putInt("newPhoto", newPhoto);
        f.setArguments(b);
        return f;
    }

    int newPhoto;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            newPhoto = getArguments().getInt("newPhoto");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_new_recommend_pager, container, false);
        ButterKnife.bind(this, view);
        ImageView eventPhotoView = (ImageView) view.findViewById(R.id.newSample);
        eventPhotoView.setImageResource(newPhoto);
        return view;
    }

    @OnClick(R.id.newSample)
    public void onEventDeatail(){
        moveCafeDetailActivity();
    }

    public void moveCafeDetailActivity(){
        Intent intent = new Intent((HomeActivity)(getActivity()),CafeDetailActivity.class);
        startActivity(intent);
    }
}
