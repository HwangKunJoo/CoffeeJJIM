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
import com.coffeejjim.developers.data.Event;
import com.coffeejjim.developers.event.EventDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeEventPagerFragment extends Fragment {

    @BindView(R.id.home_event_image)
    ImageView eventImageView;


    public HomeEventPagerFragment() {
        // Required empty public constructor
    }

    public static HomeEventPagerFragment newInstance(Event eventImage) {
        HomeEventPagerFragment f = new HomeEventPagerFragment();
        Bundle b = new Bundle();
        b.putSerializable("eventImage", eventImage);
        f.setArguments(b);
        return f;
    }

    Event eventImage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            eventImage = (Event) (getArguments().getSerializable("eventImage"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_home_event_pager, container, false);
        ButterKnife.bind(this, view);
        Glide.with(eventImageView.getContext())
                .load(eventImage.getThumbnailUrl()).into(eventImageView);
        return view;
    }

    @OnClick(R.id.home_event_image)
    public void onEventDeatail() {
        moveEventDetailActivity();
    }

    public void moveEventDetailActivity() {
        Intent intent = new Intent(getActivity(), EventDetailActivity.class);
        intent.putExtra("eventImage", eventImage);
        startActivity(intent);
    }

}
