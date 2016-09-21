package com.coffeejjim.developers.owner;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Cafe;
import com.coffeejjim.developers.data.CafeInfo;
import com.coffeejjim.developers.data.ContentData;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.CafeImagesUploadRequest;
import com.coffeejjim.developers.request.CafeInfoEditRequest;
import com.coffeejjim.developers.request.CafeInfoRequest;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class OwnerHomeEditFragment extends Fragment {

    @BindView(R.id.provider_home_edit_cafe_name_text)
    EditText cafeNameView;
    @BindView(R.id.provider_home_edit_cafe_main_image)
    CircleImageView cafeMainImageView;
    @BindView(R.id.provider_home_edit_address)
    EditText cafeAddressView;
    //    @BindView(R.id.provider_home_daytime)
//    TextView cafeBusinessTimeView;
    @BindView(R.id.provider_home_edit_phone)
    EditText cafePhoneNumberView;

    @BindView(R.id.provider_home_edit_preview_first_image)
    CircleImageView firstPreviewImageView;
    @BindView(R.id.provider_home_edit_preview_second_image)
    CircleImageView secondPreviewImageView;
    @BindView(R.id.provider_home_edit_preview_third_image)
    CircleImageView thirdPreviewImageView;
    @BindView(R.id.provider_home_edit_preview_fourth_image)
    CircleImageView fourthPreviewImageView;

    @BindView(R.id.provider_home_edit_options_wifi)
    ImageView optionWifiImageView;
    @BindView(R.id.provider_home_edit_options_working_time)
    ImageView optionDaysImageView;
    @BindView(R.id.provider_home_edit_options_plag)
    ImageView optionSocketImageView;
    @BindView(R.id.provider_home_edit_options_parking)
    ImageView optionParkingImageView;

    private static final String FIELD_SAVE_FILE = "savedfile";
    private static final String FIELD_UPLOAD_FILE = "uploadfile";

    File savedFile = null;
    File uploadFile = null;

    CafeInfo cafeInfo;

    boolean isMainChanged = false;
    boolean isFirstChanged = false;
    boolean isSecondChanged = false;
    boolean isThirdChanged = false;
    boolean isFourthChanged = false;


    public OwnerHomeEditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fr_owner_home_edit, container, false);
        ButterKnife.bind(this, view);

        if (savedInstanceState != null) {
            String path = savedInstanceState.getString(FIELD_SAVE_FILE);
            if (!TextUtils.isEmpty(path)) {
                savedFile = new File(path);
            }
//            path = savedInstanceState.getString(FIELD_UPLOAD_FILE);
//            if (!TextUtils.isEmpty(path)) {
//                uploadFile = new File(path);
//                Glide.with(this)
//                        .load(uploadFile)
//                        .into(firstPreviewImageView); // 처리해야됨
//            }
        }

        CafeInfoRequest CIRequest = new CafeInfoRequest(getContext(), 0);
        NetworkManager.getInstance().getNetworkData(CIRequest, new NetworkManager.OnResultListener<NetworkResult<CafeInfo>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<CafeInfo>> request, NetworkResult<CafeInfo> result) {
                cafeInfo = result.getResult();
                cafeInfoInit();
                Toast.makeText(getContext(), "서어어어엉어공", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<CafeInfo>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "시이이이일패애애애ㅐ", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    @OnClick(R.id.provider_home_edit_cafe_main_image)
    public void onMainImageClick(){
        getImage(RC_GET_MAIN, RC_CAPTURE_MAIN_IMAGE);
        isMainChanged = true;
    }

    @OnClick(R.id.provider_home_edit_preview_first_image)
    public void onPreviewFirstImageClick() {
        getImage(RC_GET_FIRST, RC_CATPURE_FIRST_IMAGE);
        isFirstChanged = true;
    }

    @OnClick(R.id.provider_home_edit_preview_second_image)
    public void onPreviewSecondImageClick() {
        getImage(RC_GET_SECOND, RC_CATPURE_SECOND_IMAGE);
        isSecondChanged = true;
    }

    @OnClick(R.id.provider_home_edit_preview_third_image)
    public void onPreviewThirdImageClick() {
        getImage(RC_GET_THIRD, RC_CATPURE_THIRD_IMAGE);
        isThirdChanged = true;
    }

    @OnClick(R.id.provider_home_edit_preview_fourth_image)
    public void onPreviewFOURTHImageClick() {
        getImage(RC_GET_FOURTH, RC_CATPURE_FOURTH_IMAGE);
        isFourthChanged = true;
    }

    private static final int RC_CAPTURE_MAIN_IMAGE = 1;
    private static final int RC_CATPURE_FIRST_IMAGE = 2;
    private static final int RC_CATPURE_SECOND_IMAGE = 3;
    private static final int RC_CATPURE_THIRD_IMAGE = 4;
    private static final int RC_CATPURE_FOURTH_IMAGE = 5;

    private static final int RC_GET_MAIN = 10;
    private static final int RC_GET_FIRST = 20;
    private static final int RC_GET_SECOND = 30;
    private static final int RC_GET_THIRD = 40;
    private static final int RC_GET_FOURTH = 50;

    private void getImage(final int code, final int captureCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select Image");
        builder.setItems(R.array.select_image, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        getGalleryImage(code);
                        break;
                    case 1:
                        getCaptureImage(captureCode);
                        break;
                }
            }
        });
        builder.create().show();
    }

    private void getGalleryImage(int code) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        switch (code) {
            case RC_GET_MAIN:{
                startActivityForResult(intent, RC_GET_MAIN);
                break;
            }
            case RC_GET_FIRST: {
                startActivityForResult(intent, RC_GET_FIRST);
                break;
            }
            case RC_GET_SECOND: {
                startActivityForResult(intent, RC_GET_SECOND);
                break;
            }
            case RC_GET_THIRD: {
                startActivityForResult(intent, RC_GET_THIRD);
                break;
            }
            case RC_GET_FOURTH: {
                startActivityForResult(intent, RC_GET_FOURTH);
                break;
            }
        }
    }

    private void getCaptureImage(int captureCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getSaveFile());
        switch (captureCode) {
            case RC_CAPTURE_MAIN_IMAGE: {
                startActivityForResult(intent, RC_CAPTURE_MAIN_IMAGE);
                break;
            }
            case RC_CATPURE_FIRST_IMAGE: {
                startActivityForResult(intent, RC_CATPURE_FIRST_IMAGE);
                break;
            }
            case RC_CATPURE_SECOND_IMAGE: {
                startActivityForResult(intent, RC_CATPURE_SECOND_IMAGE);
                break;
            }
            case RC_CATPURE_THIRD_IMAGE: {
                startActivityForResult(intent, RC_CATPURE_THIRD_IMAGE);
                break;
            }
            case RC_CATPURE_FOURTH_IMAGE: {
                startActivityForResult(intent, RC_CATPURE_FOURTH_IMAGE);
                break;
            }
        }
    }

    private Uri getSaveFile() {
        File dir = getActivity().getExternalFilesDir("capture");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        savedFile = new File(dir, "my_image_" + System.currentTimeMillis() + ".jpeg");
        return Uri.fromFile(savedFile);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (savedFile != null) {
            outState.putString(FIELD_SAVE_FILE, savedFile.getAbsolutePath());
        }
        if (uploadFile != null) {
            outState.putString(FIELD_UPLOAD_FILE, uploadFile.getAbsolutePath());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_GET_MAIN) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                Cursor c = getActivity().getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                if (c.moveToNext()) {
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    uploadFile = new File(path);
                    Glide.with(this)
                            .load(uploadFile)
                            .into(cafeMainImageView);
                }
            }
        } else if (requestCode == RC_CAPTURE_MAIN_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                uploadFile = savedFile;
                Glide.with(this)
                        .load(uploadFile)
                        .into(cafeMainImageView);
            }
        }
        if (requestCode == RC_GET_FIRST) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                Cursor c = getActivity().getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                if (c.moveToNext()) {
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    uploadFile = new File(path);
                    Glide.with(this)
                            .load(uploadFile)
                            .into(firstPreviewImageView);
                }
            }
        } else if (requestCode == RC_CATPURE_FIRST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                uploadFile = savedFile;
                Glide.with(this)
                        .load(uploadFile)
                        .into(firstPreviewImageView);
            }
        }
        if (requestCode == RC_GET_SECOND) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                Cursor c = getActivity().getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                if (c.moveToNext()) {
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    uploadFile = new File(path);
                    Glide.with(this)
                            .load(uploadFile)
                            .into(secondPreviewImageView);
                }
            }
        } else if (requestCode == RC_CATPURE_SECOND_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                uploadFile = savedFile;
                Glide.with(this)
                        .load(uploadFile)
                        .into(secondPreviewImageView);
            }
        }
        if (requestCode == RC_GET_THIRD) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                Cursor c = getActivity().getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                if (c.moveToNext()) {
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    uploadFile = new File(path);
                    Glide.with(this)
                            .load(uploadFile)
                            .into(thirdPreviewImageView);
                }
            }
        } else if (requestCode == RC_CATPURE_THIRD_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                uploadFile = savedFile;
                Glide.with(this)
                        .load(uploadFile)
                        .into(thirdPreviewImageView);
            }
        }
        if (requestCode == RC_GET_FOURTH) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                Cursor c = getActivity().getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                if (c.moveToNext()) {
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    uploadFile = new File(path);
                    Glide.with(this)
                            .load(uploadFile)
                            .into(fourthPreviewImageView);
                }
            }
        } else if (requestCode == RC_CATPURE_FOURTH_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                uploadFile = savedFile;
                Glide.with(this)
                        .load(uploadFile)
                        .into(fourthPreviewImageView);
            }
        }
    }


    @OnClick(R.id.provider_home_complete_btn)
    public void onProviderHome() {
        String cafeName = cafeNameView.getText().toString();
        String cafeAddress = cafeAddressView.getText().toString();
        String cafePhoneNumber = cafePhoneNumberView.getText().toString();
        if (isMainChanged == true) {
            if (uploadFile != null) {
                CafeImagesUploadRequest cafeImagesUploadRequest
                        = new CafeImagesUploadRequest(getContext(), uploadFile, 1);
                NetworkManager.getInstance().getNetworkData(cafeImagesUploadRequest, new NetworkManager.OnResultListener<NetworkResult<ContentData>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<ContentData>> request, NetworkResult<ContentData> result) {
                        Toast.makeText(getActivity(), "메인 이미지 업로드 성공", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<ContentData>> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(getActivity(), "메인 이미지 업로드 실패", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        if (isFirstChanged == true) {
            if (uploadFile != null) {
                CafeImagesUploadRequest cafeImagesUploadRequest
                        = new CafeImagesUploadRequest(getContext(), uploadFile, 2);
                NetworkManager.getInstance().getNetworkData(cafeImagesUploadRequest, new NetworkManager.OnResultListener<NetworkResult<ContentData>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<ContentData>> request, NetworkResult<ContentData> result) {
                        Toast.makeText(getActivity(), "첫번째 이미지 업로드 성공", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<ContentData>> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(getActivity(), "첫번째 이미지 업로드 실패", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        if (isSecondChanged == true) {
            if (uploadFile != null) {
                CafeImagesUploadRequest cafeImagesUploadRequest
                        = new CafeImagesUploadRequest(getContext(), uploadFile, 3);
                NetworkManager.getInstance().getNetworkData(cafeImagesUploadRequest, new NetworkManager.OnResultListener<NetworkResult<ContentData>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<ContentData>> request, NetworkResult<ContentData> result) {
                        Toast.makeText(getActivity(), "2번째 이미지 업로드 성공", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<ContentData>> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(getActivity(), "2번째 이미지 업로드 실패", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        if (isThirdChanged == true) {
            if (uploadFile != null) {
                CafeImagesUploadRequest cafeImagesUploadRequest
                        = new CafeImagesUploadRequest(getContext(), uploadFile, 4);
                NetworkManager.getInstance().getNetworkData(cafeImagesUploadRequest, new NetworkManager.OnResultListener<NetworkResult<ContentData>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<ContentData>> request, NetworkResult<ContentData> result) {
                        Toast.makeText(getActivity(), "3번째 이미지 업로드 성공", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<ContentData>> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(getActivity(), "3번째 이미지 업로드 실패", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        if (isFourthChanged == true) {
            if (uploadFile != null) {
                CafeImagesUploadRequest cafeImagesUploadRequest
                        = new CafeImagesUploadRequest(getContext(), uploadFile, 5);
                NetworkManager.getInstance().getNetworkData(cafeImagesUploadRequest, new NetworkManager.OnResultListener<NetworkResult<ContentData>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<ContentData>> request, NetworkResult<ContentData> result) {
                        Toast.makeText(getActivity(), "4번째 이미지 업로드 성공", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<ContentData>> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(getActivity(), "4번째 이미지 업로드 실패", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        CafeInfoEditRequest CIERequest = new CafeInfoEditRequest(getContext(), "0", cafeAddress, 37.477445, 126.961314,
                cafePhoneNumber, "13123sadasd", 1, 0, 0, 0);
        NetworkManager.getInstance().getNetworkData(CIERequest, new NetworkManager.OnResultListener<NetworkResult<Cafe>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Cafe>> request, NetworkResult<Cafe> result) {
                Toast.makeText(getContext(), "정보 변경", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Cafe>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "정보 변경 실패", Toast.LENGTH_SHORT).show();
            }
        });
        ((OwnerHomeActivity) getActivity()).changeProviderHome();
    }

    public void cafeInfoInit() {
        cafeNameView.setText(cafeInfo.getCafeInfo().getCafeName());
        cafeAddressView.setText(cafeInfo.getCafeInfo().getCafeAddress());
        cafePhoneNumberView.setText(cafeInfo.getCafeInfo().getCafePhoneNumber());
        //cafeBusinessTimeView.setText(cafeInfo.getCafeInfo().getBusinessHour());

        //메인으로 설정한 사진 보여주는 코드 필요함
        Glide.with(cafeMainImageView.getContext())
                .load(cafeInfo.getImages().get(0).getImageUrl()).into(cafeMainImageView);
        Glide.with(firstPreviewImageView.getContext())
                .load(cafeInfo.getImages().get(1).getImageUrl()).into(firstPreviewImageView);
        Glide.with(secondPreviewImageView.getContext())
                .load(cafeInfo.getImages().get(2).getImageUrl()).into(secondPreviewImageView);
        Glide.with(thirdPreviewImageView.getContext())
                .load(cafeInfo.getImages().get(3).getImageUrl()).into(thirdPreviewImageView);
        Glide.with(fourthPreviewImageView.getContext())
                .load(cafeInfo.getImages().get(4).getImageUrl()).into(fourthPreviewImageView);


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
