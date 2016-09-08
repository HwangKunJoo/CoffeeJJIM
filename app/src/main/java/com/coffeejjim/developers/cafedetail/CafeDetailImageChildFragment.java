package com.coffeejjim.developers.cafedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.CafeImage;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CafeDetailImageChildFragment extends Fragment {

    @BindView(R.id.cafe_detail_image)
    ImageView cafeDetailImageView;

    public CafeDetailImageChildFragment() {
        // Required empty public constructor
    }

    public static CafeDetailImageChildFragment newInstance(CafeImage cafeImage) {
        CafeDetailImageChildFragment f = new CafeDetailImageChildFragment();
        Bundle b = new Bundle();
        b.putSerializable("cafeImage", cafeImage);
        f.setArguments(b);
        return f;
    }

    CafeImage cafeImage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cafeImage = (CafeImage) getArguments().getSerializable("cafeImage");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_cafe_detail_image, container, false);
        ButterKnife.bind(this, view);

        Glide.with(cafeDetailImageView.getContext())
                .load(cafeImage.getImageUrl()).into(cafeDetailImageView);

        return view;
    }
}
