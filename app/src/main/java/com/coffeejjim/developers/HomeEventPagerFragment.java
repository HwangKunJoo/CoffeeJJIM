package com.coffeejjim.developers;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


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
        ImageView eventPhotoView = (ImageView) view.findViewById(R.id.eventSample);
        eventPhotoView.setImageResource(eventPhoto);
        return eventPhotoView;
    }


//    // 프래그먼트의 뷰 인스턴스
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        if (v != null) {
//            ViewGroup parent = (ViewGroup) v.getParent();
//            if (parent != null) {
//                parent.removeView(v);
//            }
//        }
//
//    }
}
