package com.coffeejjim.developers.cafedetail;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.coffeejjim.developers.data.CafeDetail;
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
    CafeDetail cafeDetail;


    public CafeDetailFragment() {
        // Required empty public constructor
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
        //cafeId 어디서 디테일로 들어오는지 확인해서 구분해줘야 됨.
        // 툴바를 여기다 만들던지 아이디값 넘겨줘야됨 액티비티에
        CafeDetailRequest CDRequest = new CafeDetailRequest(getContext(), "123");
        NetworkManager.getInstance().getNetworkData(CDRequest, new NetworkManager.OnResultListener<NetworkResult<CafeDetail>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<CafeDetail>> request, NetworkResult<CafeDetail> result) {
                cafeDetail = result.getResult();
                cafeDetailImagePagerAdapter = new CafeDetailImagePagerAdapter(getChildFragmentManager(), cafeDetail);
                cafeDetailImagePager.setAdapter(cafeDetailImagePagerAdapter);
                cafeInit();
                Toast.makeText(getContext(), "성공요요요요", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<CafeDetail>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "실패요요요요요", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void cafeInit() {
        cafeNameView.setText(cafeDetail.getCafeInfo().getCafeName());
        cafeAddressView.setText(cafeDetail.getCafeInfo().getCafeAddress());
        cafePhoneNumberView.setText(cafeDetail.getCafeInfo().getCafePhoneNumber());
        cafeBusinessTimeView.setText(cafeDetail.getCafeInfo().getBusinessHour());
        Glide.with(firstPreviewImageView.getContext())
                .load(cafeDetail.getImages().get(0).getImageUrl()).into(firstPreviewImageView);
        Glide.with(secondPreviewImageView.getContext())
                .load(cafeDetail.getImages().get(1).getImageUrl()).into(secondPreviewImageView);
        Glide.with(thirdPreviewImageView.getContext())
                .load(cafeDetail.getImages().get(2).getImageUrl()).into(thirdPreviewImageView);
        Glide.with(fourthPreviewImageView.getContext())
                .load(cafeDetail.getImages().get(3).getImageUrl()).into(fourthPreviewImageView);
        if (cafeDetail.getCafeInfo().getWifi() == 1) {
            optionWifiImageView.setVisibility(View.VISIBLE);
        }
        if (cafeDetail.getCafeInfo().getSocket() == 1) {
            optionSocketImageView.setVisibility(View.VISIBLE);
        }
        if (cafeDetail.getCafeInfo().getParking() == 1) {
            optionParkingImageView.setVisibility(View.VISIBLE);
        }
        if (cafeDetail.getCafeInfo().getDays() == 1) {
            optionDaysImageView.setVisibility(View.VISIBLE);
        }
    }
}
