package com.coffeejjim.developers.cafedetail;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.CafeImage;
import com.coffeejjim.developers.data.CafeInfo;
import com.coffeejjim.developers.data.Event;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.CafeDetailRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CafeDetailFragment extends Fragment {

    @BindView(R.id.cafe_detail_image_pager)
    ViewPager cafeDetailImagePager;
    @BindView(R.id.cafe_detail_cafe_name)
    TextView cafeNameView;
    @BindView(R.id.cafe_detail_address)
    TextView cafeAddressView;
    @BindView(R.id.cafe_detail_dial)
    TextView cafePhoneNumberView;
    @BindView(R.id.cafe_detail_business_time)
    TextView cafeBusinessTimeView;
    @BindView(R.id.cafe_detail_preview_image_first)
    CircleImageView firstPreviewImageView;
    @BindView(R.id.cafe_detail_preview_image_second)
    CircleImageView secondPreviewImageView;
    @BindView(R.id.cafe_detail_preview_image_third)
    CircleImageView thirdPreviewImageView;
    @BindView(R.id.cafe_detail_preview_image_fourth)
    CircleImageView fourthPreviewImageView;
    @BindView(R.id.cafe_detail_options_wifi)
    ImageView optionWifiImageView;
    @BindView(R.id.cafe_detail_options_working_time)
    ImageView optionDaysImageView;
    @BindView(R.id.cafe_detail_options_plag)
    ImageView optionSocketImageView;
    @BindView(R.id.cafe_detail_options_parking)
    ImageView optionParkingImageView;


    CafeDetailImagePagerAdapter cafeDetailImagePagerAdapter;
    CafeInfo cafeInfo;


    public CafeDetailFragment() {
        // Required empty public constructor
    }

    public static CafeDetailFragment newInstance(CafeImage cafeDetailInfo){
        CafeDetailFragment f = new CafeDetailFragment();
        Bundle b = new Bundle();
        b.putSerializable("cafeDetailInfo", cafeDetailInfo);
        f.setArguments(b);
        return f;
    }

    CafeImage cafeDetailInfo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cafeDetailInfo = (CafeImage) (getArguments().getSerializable("cafeDetailInfo"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_cafe_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_move_map)
    public void onCafeLocation() {
        changeCafeLocationMap();
    }

    public void changeCafeLocationMap() {
        Intent intent = new Intent(getActivity(), CafeLocationMapActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_move_dial_pad)
    public void onDialPad() {
        moveDialPadActivity();
    }

    public void moveDialPadActivity() {
        Intent intent = new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:010-9374-4512"));
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        String cafeId = ""+cafeDetailInfo.getCafeId();
        CafeDetailRequest CDRequest = new CafeDetailRequest(getContext(), cafeId);
        NetworkManager.getInstance().getNetworkData(CDRequest, new NetworkManager.OnResultListener<NetworkResult<CafeInfo>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<CafeInfo>> request, NetworkResult<CafeInfo> result) {
                cafeInfo = result.getResult();
                cafeDetailImagePagerAdapter = new CafeDetailImagePagerAdapter(getChildFragmentManager(), cafeInfo);
                cafeDetailImagePager.setAdapter(cafeDetailImagePagerAdapter);
                cafeInit();
                Toast.makeText(getContext(), "성공요요요요", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<CafeInfo>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "실패요요요요요", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void cafeInit() {
        cafeNameView.setText(cafeInfo.getCafeInfo().getCafeName());
        cafeAddressView.setText(cafeInfo.getCafeInfo().getCafeAddress());
        cafePhoneNumberView.setText(cafeInfo.getCafeInfo().getCafePhoneNumber());
        cafeBusinessTimeView.setText(cafeInfo.getCafeInfo().getBusinessHour());
        Glide.with(firstPreviewImageView.getContext())
                .load(cafeInfo.getImages().get(0).getImageUrl()).into(firstPreviewImageView);
        Glide.with(secondPreviewImageView.getContext())
                .load(cafeInfo.getImages().get(1).getImageUrl()).into(secondPreviewImageView);
        Glide.with(thirdPreviewImageView.getContext())
                .load(cafeInfo.getImages().get(2).getImageUrl()).into(thirdPreviewImageView);
        Glide.with(fourthPreviewImageView.getContext())
                .load(cafeInfo.getImages().get(3).getImageUrl()).into(fourthPreviewImageView);
        if (cafeInfo.getCafeInfo().getWifi() == 1) {
            optionWifiImageView.setVisibility(View.VISIBLE);
        }
        if (cafeInfo.getCafeInfo().getSocket() == 1) {
            optionSocketImageView.setVisibility(View.VISIBLE);
        }
        if (cafeInfo.getCafeInfo().getParking() == 1) {
            optionParkingImageView.setVisibility(View.VISIBLE);
        }
        if (cafeInfo.getCafeInfo().getDays() == 1) {
            optionDaysImageView.setVisibility(View.VISIBLE);
        }
    }
}
