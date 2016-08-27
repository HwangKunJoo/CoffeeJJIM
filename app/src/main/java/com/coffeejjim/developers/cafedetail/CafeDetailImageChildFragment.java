package com.coffeejjim.developers.cafedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.coffeejjim.developers.R;

import butterknife.ButterKnife;

public class CafeDetailImageChildFragment extends Fragment {

    public CafeDetailImageChildFragment() {
        // Required empty public constructor
    }

    public static CafeDetailImageChildFragment newInstance(int cafeDetailImage) {
        CafeDetailImageChildFragment f = new CafeDetailImageChildFragment();
        Bundle b = new Bundle();
        b.putInt("cafeDetailImage", cafeDetailImage);
        f.setArguments(b);
        return f;
    }

    int cafeDetailImage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cafeDetailImage = getArguments().getInt("cafeDetailImage");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_cafe_detail_image, container, false);
        ButterKnife.bind(this, view);
        ImageView eventPhotoView = (ImageView) view.findViewById(R.id.cafe_detail_image);
        eventPhotoView.setImageResource(cafeDetailImage);
        return view;
    }
}
