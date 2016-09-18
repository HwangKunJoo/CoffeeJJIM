package com.coffeejjim.developers.event;


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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailFragment extends Fragment {

    @BindView(R.id.event_detail_image)
    ImageView eventImageView;


    public EventDetailFragment() {
        // Required empty public constructor
    }

    public static EventDetailFragment newInstance(Event eventImage) {
        EventDetailFragment f = new EventDetailFragment();
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
        View view = inflater.inflate(R.layout.fr_event_detail, container, false);
        ButterKnife.bind(this, view);
        Glide.with(eventImageView.getContext())
                .load(eventImage.getImageUrl()).into(eventImageView);
        return view;
    }

}
