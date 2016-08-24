package com.coffeejjim.developers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
        ImageView eventPhotoView = (ImageView) view.findViewById(R.id.newSample);
        eventPhotoView.setImageResource(newPhoto);
        return view;
    }
}
