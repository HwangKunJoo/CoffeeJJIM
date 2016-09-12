package com.coffeejjim.developers.owner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Cafe;
import com.coffeejjim.developers.data.CafeInfo;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.CafeInfoEditRequest;
import com.coffeejjim.developers.request.CafeInfoRequest;

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
    @BindView(R.id.provider_home_edit_preview_forth_image)
    CircleImageView fourthPreviewImageView;

    @BindView(R.id.provider_home_edit_options_wifi)
    ImageView optionWifiImageView;
    @BindView(R.id.provider_home_edit_options_working_time)
    ImageView optionDaysImageView;
    @BindView(R.id.provider_home_edit_options_plag)
    ImageView optionSocketImageView;
    @BindView(R.id.provider_home_edit_options_parking)
    ImageView optionParkingImageView;

    private static final int PICK_FROM_GALLERY = 1;

    CafeInfo cafeInfo;

    public OwnerHomeEditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fr_owner_home_edit, container, false);
        ButterKnife.bind(this, view);

        CafeInfoRequest CIRequest = new CafeInfoRequest(getContext(), 0);
        NetworkManager.getInstance().getNetworkData(CIRequest, new NetworkManager.OnResultListener<NetworkResult<CafeInfo>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<CafeInfo>> request, NetworkResult<CafeInfo> result) {
                cafeInfo = result.getResult();
                cafeInfoInit();
                Toast.makeText(getContext(),"서어어어엉어공",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<CafeInfo>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(),"시이이이일패애애애ㅐ",Toast.LENGTH_SHORT).show();
            }
        });

        String[] str1 = getResources().getStringArray(R.array.working_time_Array);
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,str1);
        Spinner spi1 = (Spinner)view.findViewById(R.id.provider_home_edit_working_daytime_spinner1);
        spi1.setAdapter(adapter1);
        spi1.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );

        String[] str2 = getResources().getStringArray(R.array.working_time_Array);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,str2);
        Spinner spi2 = (Spinner)view.findViewById(R.id.provider_home_edit_working_daytime_spinner2);
        spi2.setAdapter(adapter2);
        spi2.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );

        String[] str3 = getResources().getStringArray(R.array.working_time_Array);
        ArrayAdapter<String> adapter3=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,str3);
        Spinner spi3 = (Spinner)view.findViewById(R.id.provider_home_edit_working_weektime_spinner1);
        spi3.setAdapter(adapter3);
        spi3.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );

        String[] str4 = getResources().getStringArray(R.array.working_time_Array);
        ArrayAdapter<String> adapter4=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,str4);
        Spinner spi4 = (Spinner)view.findViewById(R.id.provider_home_edit_working_weektime_spinner2);
        spi4.setAdapter(adapter4);
        spi4.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );

        ////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////                     이미지 받아오기 mini                       //////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////


//        if (savedInstanceState != null) {
//            String path = savedInstanceState.getString(FIELD_SAVE_FILE);
//            if (!TextUtils.isEmpty(path)) {
//                savedFile = new File(path);
//            }
//            path = savedInstanceState.getString(FIELD_UPLOAD_FILE);
//            if (!TextUtils.isEmpty(path)) {
//                uploadFile = new File(path);
//                Glide.with(this)
//                        .load(uploadFile)
//                        .into(firstPreviewImageView);
//            }
//        }


        ////////////////////////////////////////////////////////////////////////////////////////////////////

        return view;
    }

    @OnClick(R.id.provider_home_complete_btn)
    public void onProviderHome(){
        String cafeAddress = cafeAddressView.getText().toString();
        String cafePhoneNumber = cafePhoneNumberView.getText().toString(); // 카페 이름 추가해야됨
        CafeInfoEditRequest CIERequest = new CafeInfoEditRequest(getContext(),"0",cafeAddress, 37.477445, 126.961314,
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
        ((OwnerHomeActivity)getActivity()).changeProviderHome();
    }

    public void cafeInfoInit(){
        cafeNameView.setText(cafeInfo.getCafeInfo().getCafeName());
        cafeAddressView.setText(cafeInfo.getCafeInfo().getCafeAddress());
        cafePhoneNumberView.setText(cafeInfo.getCafeInfo().getCafePhoneNumber());
        //cafeBusinessTimeView.setText(cafeInfo.getCafeInfo().getBusinessHour());

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


    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////                     이미지 받아오기 mini                         //////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////



//    private static final String FIELD_SAVE_FILE = "savedfile";
//    private static final String FIELD_UPLOAD_FILE = "uploadfile";
//
//    File savedFile = null;
//    File uploadFile = null;
//
//    private static final int INDEX_GALLERY = 0;
//    private static final int INDEX_CAMERA = 1;
//
//    private static final int RC_GET_FIRST = 1;
//    private static final int RC_GET_SECOND = 2;
//    private static final int RC_GET_THIRD = 3;
//    private static final int RC_GET_FORTH = 4;
//
//    private static final int RC_CATPURE_IMAGE = 5;
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        if (savedFile != null) {
//            outState.putString(FIELD_SAVE_FILE, savedFile.getAbsolutePath());
//        }
//        if (uploadFile != null) {
//            outState.putString(FIELD_UPLOAD_FILE, uploadFile.getAbsolutePath());
//        }
//    }
//
//    @OnClick(R.id.provider_home_preview_first_image)
//    public void onProviderPreviewFirstImageUpload(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
//        builder.setTitle("이미지 선택");
//        builder.setItems(R.array.select_image, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                switch (i) {
//                    case INDEX_GALLERY :{
//                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                        intent.setType("image/*");
//                        startActivityForResult(intent, RC_GET_FIRST);
//                        break;}
//                    case INDEX_CAMERA :{
//                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        intent.putExtra(MediaStore.EXTRA_OUTPUT, getSaveFile());
//                        startActivityForResult(intent, RC_CATPURE_IMAGE);
//                        break;}
//                }
//            }
//        });
//        builder.create().show();
//
//    }
//
//    @OnClick(R.id.provider_home_preview_second_image)
//    public void onProviderPreviewSecondImageUpload(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
//        builder.setTitle("이미지 선택");
//        builder.setItems(R.array.select_image, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                switch (i) {
//                    case INDEX_GALLERY :{
//                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                        intent.setType("image/*");
//                        startActivityForResult(intent, RC_GET_SECOND);
//                        break;}
//                    case INDEX_CAMERA :{
//                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        intent.putExtra(MediaStore.EXTRA_OUTPUT, getSaveFile());
//                        startActivityForResult(intent, RC_CATPURE_IMAGE);
//                        break;}
//                }
//            }
//        });
//        builder.create().show();
//    }
//
//    @OnClick(R.id.provider_home_preview_third_image)
//    public void onProviderPreviewThirdImageUpload(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
//        builder.setTitle("이미지 선택");
//        builder.setItems(R.array.select_image, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                switch (i) {
//                    case INDEX_GALLERY :{
//                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                        intent.setType("image/*");
//                        startActivityForResult(intent, RC_GET_THIRD);
//                        break;}
//                    case INDEX_CAMERA :{
//                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        intent.putExtra(MediaStore.EXTRA_OUTPUT, getSaveFile());
//                        startActivityForResult(intent, RC_CATPURE_IMAGE);
//                        break;}
//                }
//            }
//        });
//        builder.create().show();
//    }
//
//    @OnClick(R.id.provider_home_edit_preview_forth_image)
//    public void onProviderPreviewForthImageUpload(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
//        builder.setTitle("이미지 선택");
//        builder.setItems(R.array.select_image, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                switch (i) {
//                    case INDEX_GALLERY :{
//                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                        intent.setType("image/*");
//                        startActivityForResult(intent, RC_GET_FORTH);
//                        break;}
//                    case INDEX_CAMERA :{
//                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        intent.putExtra(MediaStore.EXTRA_OUTPUT, getSaveFile());
//                        startActivityForResult(intent, RC_CATPURE_IMAGE);
//                        break;}
//                }
//            }
//        });
//        builder.create().show();
//    }
//
//    private Uri getSaveFile() {
//        File dir = getActivity().getExternalFilesDir("capture");
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//        savedFile = new File(dir, "my_image_" + System.currentTimeMillis() + ".jpeg");
//        return Uri.fromFile(savedFile);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RC_GET_FIRST) {
//            if (resultCode == Activity.RESULT_OK) {
//                Uri uri = data.getData();
//                Cursor c = getActivity().getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
//                if (c.moveToNext()) {
//                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
//                    uploadFile = new File(path);
//                    Glide.with(this)
//                            .load(uploadFile)
//                            .into(firstPreviewImageView);
//                }
//            }
//        } else if (requestCode == RC_GET_SECOND) {
//            if (resultCode == Activity.RESULT_OK) {
//                Uri uri = data.getData();
//                Cursor c = getActivity().getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
//                if (c.moveToNext()) {
//                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
//                    uploadFile = new File(path);
//                    Glide.with(this)
//                            .load(uploadFile)
//                            .into(secondPreviewImageView);
//                }
//            }
//
//        }
//        else if (requestCode == RC_GET_THIRD) {
//            if (resultCode == Activity.RESULT_OK) {
//                Uri uri = data.getData();
//                Cursor c = getActivity().getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
//                if (c.moveToNext()) {
//                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
//                    uploadFile = new File(path);
//                    Glide.with(this)
//                            .load(uploadFile)
//                            .into(thirdPreviewImageView);
//                }
//            }
//
//        }
//        else if (requestCode == RC_GET_FORTH) {
//            if (resultCode == Activity.RESULT_OK) {
//                Uri uri = data.getData();
//                Cursor c = getActivity().getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
//                if (c.moveToNext()) {
//                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
//                    uploadFile = new File(path);
//                    Glide.with(this)
//                            .load(uploadFile)
//                            .into(fourthPreviewImageView);
//                }
//            }
//
//        } else if (requestCode == RC_CATPURE_IMAGE) {
//            if (resultCode == Activity.RESULT_OK) {
//                uploadFile = savedFile;
//                Glide.with(this)
//                        .load(uploadFile)
//                        .into(firstPreviewImageView);
//            }
//        }
//    }


}
