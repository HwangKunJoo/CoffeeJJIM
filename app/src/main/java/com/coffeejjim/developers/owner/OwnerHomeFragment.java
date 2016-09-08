package com.coffeejjim.developers.owner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.CafeInfo;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.CafeInfoRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class OwnerHomeFragment extends Fragment {

    @BindView(R.id.provider_home_cafe_name_text)
    TextView cafeNameView;
    @BindView(R.id.provider_home_cafe_main_image)
    CircleImageView cafeMainImageView;
    @BindView(R.id.provider_home_address)
    TextView cafeAddressView;
    @BindView(R.id.provider_home_daytime)
    TextView cafeBusinessTimeView;
    @BindView(R.id.provider_home_phone)
    TextView cafePhoneNumberView;
    @BindView(R.id.provider_home_preview_first_image)
    CircleImageView firstPreviewImageView;
    @BindView(R.id.provider_home_preview_second_image)
    CircleImageView secondPreviewImageView;
    @BindView(R.id.provider_home_preview_third_image)
    CircleImageView thirdPreviewImageView;
    @BindView(R.id.provider_home_preview_forth_image)
    CircleImageView fourthPreviewImageView;
    @BindView(R.id.provider_home_options_wifi)
    ImageView optionWifiImageView;
    @BindView(R.id.provider_home_options_working_time)
    ImageView optionDaysImageView;
    @BindView(R.id.provider_home_options_plag)
    ImageView optionSocketImageView;
    @BindView(R.id.provider_home_options_parking)
    ImageView optionParkingImageView;

    CafeInfo cafeInfo;

    public OwnerHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_owner_home, container, false);
        ButterKnife.bind(this, view);

        CafeInfoRequest CIRequest = new CafeInfoRequest(getContext(), 0);
        NetworkManager.getInstance().getNetworkData(CIRequest, new NetworkManager.OnResultListener<NetworkResult<CafeInfo>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<CafeInfo>> request, NetworkResult<CafeInfo> result) {
                cafeInfo = result.getResult();
                cafeInfoInit();
                //Toast.makeText(getContext(),"서어어어엉어공",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<CafeInfo>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(),"시이이이일패애애애ㅐ",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void cafeInfoInit(){
        cafeNameView.setText(cafeInfo.getCafeInfo().getCafeName());
        cafeAddressView.setText(cafeInfo.getCafeInfo().getCafeAddress());
        cafePhoneNumberView.setText(cafeInfo.getCafeInfo().getCafePhoneNumber());
        cafeBusinessTimeView.setText(cafeInfo.getCafeInfo().getBusinessHour());

        //메인으로 설정한 사진 보여주는 코드 필요함
        Glide.with(cafeMainImageView.getContext())
                .load(cafeInfo.getImages().get(0).getImageUrl()).into(cafeMainImageView);
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



    @OnClick(R.id.provider_home_edit_btn)
    public void onProviderEdit() {
        ((OwnerHomeActivity)getActivity()).changeProviderHomeEdit();
    }


}
