package com.coffeejjim.developers.cafedetail;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CafeDetailFragment extends Fragment {

    @BindView(R.id.cafe_detail_image_pager)
    ViewPager cafeDetailImagePager;

    CafeDetailImagePagerAdapter cafeDetailImagePagerAdapter;


    public CafeDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cafe_detail, container, false);
        ButterKnife.bind(this, view);
        cafeDetailImagePagerAdapter= new CafeDetailImagePagerAdapter(getChildFragmentManager());
        cafeDetailImagePager.setAdapter(cafeDetailImagePagerAdapter);
        return view;
    }

    @OnClick(R.id.btn_move_map)
    public void onCafeLocation(){
        changeCafeLocationMap();
    }

    public void changeCafeLocationMap(){
        Intent intent = new Intent(getActivity(), CafeLocationMapActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_move_dial_pad)
    public void onDialPad(){
        moveDialPadActivity();
    }

    public void moveDialPadActivity(){
        Intent intent = new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:010-9374-4512"));
        startActivity(intent);
    }

}
