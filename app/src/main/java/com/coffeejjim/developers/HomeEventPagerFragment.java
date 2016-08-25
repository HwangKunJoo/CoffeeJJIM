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


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeEventPagerFragment extends Fragment {


    public HomeEventPagerFragment() {
        // Required empty public constructor
    }

    public static HomeEventPagerFragment newInstance(int eventPhoto) {
        HomeEventPagerFragment f = new HomeEventPagerFragment();
        Bundle b = new Bundle();
        b.putInt("eventPhoto", eventPhoto);
        f.setArguments(b);
        return f;
    }

    int eventPhoto;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            eventPhoto = getArguments().getInt("eventPhoto");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_home_event_pager, container, false);
        ButterKnife.bind(this, view);
        ImageView eventPhotoView = (ImageView) view.findViewById(R.id.eventSample);
        eventPhotoView.setImageResource(eventPhoto);
        return view;
    }

    @OnClick(R.id.eventSample)
    public void onEventDeatail(){
        moveEventDetailActivity();
    }

    public void moveEventDetailActivity(){
        Intent intent = new Intent((HomeActivity)(getActivity()),EventDetailActivity.class);
        startActivity(intent);
    }

}
