package com.coffeejjim.developers.estimate;

import android.Manifest;
import android.app.TimePickerDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Estimate;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.EstimateRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class EstimateSheetFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

    @BindView(R.id.estimate_sheet_date_edit)
    EditText dateView;
    @BindView(R.id.estimate_sheet_location_edit)
    EditText locationView;
    @BindView(R.id.estimate_sheet_people_edit)
    EditText peopleView;
    @BindView(R.id.estimate_sheet_time_edit)
    EditText timeView;
    @BindView(R.id.estimate_sheet_wifi_btn)
    ImageButton btn_wifi;
    @BindView(R.id.estimate_sheet_parking_btn)
    ImageButton btn_parking;
    @BindView(R.id.estimate_sheet_socket_btn)
    ImageButton btn_socket;
    @BindView(R.id.estimate_sheet_days_btn)
    ImageButton btn_days;
    @BindView(R.id.estimate_sheet_auction_time)
    EditText auctionTimeView;

    GoogleApiClient mApiClient;

    String longitude;
    String latitude;

    int optionWifi = 0;
    int optionParking = 0;
    int optionSocket = 0;
    int optionDays = 0;

    public EstimateSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_estimate_sheet, container, false);
        ButterKnife.bind(this, view);

        mApiClient = new GoogleApiClient.Builder(getContext())
                .addApi(LocationServices.API)
                .addApi(ActivityRecognition.API)
                .addConnectionCallbacks(this)
                .enableAutoManage(getActivity(), this)
                .build();


        return view;
    }


    boolean isConnected = false;

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        isConnected = true;
        getLocation();
    }

    private void getLocation() {
        if (!isConnected) return;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Location location = LocationServices.FusedLocationApi.getLastLocation(mApiClient);
        if (location != null) {
            setLocation(location);
        }

        LocationRequest request = new LocationRequest();
        request.setFastestInterval(10000);
        request.setInterval(20000);
        request.setNumUpdates(1);
        request.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        LocationServices.FusedLocationApi.requestLocationUpdates(mApiClient, request, mListener);

    }

    LocationListener mListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            setLocation(location);
        }
    };

    private void setLocation(Location location) {
        latitude = "" + location.getLatitude();
        longitude = "" + location.getLongitude();
    }

    @Override
    public void onConnectionSuspended(int i) {
        isConnected = false;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @OnClick(R.id.estimate_sheet_time)
    void onTimeClick() {

        GregorianCalendar calendar = new GregorianCalendar();
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                String reservationTime = String.format("%d:%02d", hourOfDay, minute);
                timeView.setText(reservationTime + ":00");
            }
        }, hour, minute, false);


        timePickerDialog.show();
    }


    @OnClick(R.id.estimate_sheet_calendar)
    void onDateClick() {
        CalendarDialogFragment calendarDialogFragment = CalendarDialogFragment.newInstance(CalendarDialogFragment.FRAG_RESERVATION);
        calendarDialogFragment.show(getActivity().getSupportFragmentManager(), "calendarDialog");

        calendarDialogFragment.setOnCalendarDateChanged(new CalendarDialogFragment.OnCalendarDateChangedListener() {
            @Override
            public void onCalendarDateChanged(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int year = date.getYear();
                int month = date.getMonth() + 1;
                int day = date.getDay();
                String reservationDate = String.format("%d-%02d-%02d", year, month, day);
                dateView.setText(reservationDate);
            }
        });
    }

    @OnClick(R.id.estimate_sheet_wifi_btn)
    public void onWifiClick() {
        if (optionWifi == 1) {
            optionWifi = 0;
            btn_wifi.setImageResource(R.drawable.options_wifi_off_120);
        } else {
            optionWifi = 1;
            btn_wifi.setImageResource(R.drawable.options_wifi_on_120);
        }
    }

    @OnClick(R.id.estimate_sheet_parking_btn)
    public void onParkingClick() {
        if (optionParking == 1) {
            optionParking = 0;
            btn_parking.setImageResource(R.drawable.options_parking_off_120);
        } else {
            optionParking = 1;
            btn_parking.setImageResource(R.drawable.options_parking_on_120);
        }
    }

    @OnClick(R.id.estimate_sheet_days_btn)
    public void onDaysClick() {
        if (optionDays == 1) {
            optionDays = 0;
            btn_days.setImageResource(R.drawable.options_workingtime_off_120);
        } else {
            optionDays = 1;
            btn_days.setImageResource(R.drawable.options_wprkingtime_on_120);
        }
    }

    @OnClick(R.id.estimate_sheet_socket_btn)
    public void onSocketClick() {
        if (optionSocket == 1) {
            optionSocket = 0;
            btn_socket.setImageResource(R.drawable.options_plag_off_120);
        } else {
            optionSocket = 1;
            btn_socket.setImageResource(R.drawable.options_plag_on_120);
        }
    }


//    public static boolean isStringInt(String s) {
//        try {
//            Integer.parseInt(s);
//            return true;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }


    @OnClick(R.id.btn_estimate_sheet_present)
    public void onEstimateCheckDialogButtonClick() {
        if(TextUtils.isEmpty(dateView.getText().toString())){
            Toast.makeText(getContext(), "날짜나 시간 입력이 되지 않았습니다. \n 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(peopleView.getText().toString())) {
            Toast.makeText(getContext(), "예약 인원이 없습니다. \n 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(auctionTimeView.getText().toString())) {
            Toast.makeText(getContext(), "경매 시간을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

//        if (!isStringInt(peopleView.getText().toString())) {
//            Toast.makeText(getContext(), "인원 수를 잘못입력하셨습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
//            return;
//        }
        String location = locationView.getText().toString();
        String reservationTime = dateView.getText().toString() + " " + timeView.getText().toString();
        int people = Integer.parseInt(peopleView.getText().toString());
        int auctionTime = Integer.parseInt(auctionTimeView.getText().toString());

        EstimateRequest ERequest = new EstimateRequest(getContext(), people, 37.477025, 126.963493
                , reservationTime, optionWifi, optionDays, optionParking, optionSocket, auctionTime);
        NetworkManager.getInstance().getNetworkData(ERequest, new NetworkManager.OnResultListener<NetworkResult<Estimate>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Estimate>> request, NetworkResult<Estimate> result) {
                if (result.getCode() == 0) {
                    Toast.makeText(getContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "성공", Toast.LENGTH_SHORT).show();
                    onDialogFragmentClick();

                }
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Estimate>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onDialogFragmentClick() {
        FragmentManager f = getActivity().getSupportFragmentManager();
        EstimateSheetDialogFragment estimateSheetDialogFragment = new EstimateSheetDialogFragment();
        estimateSheetDialogFragment.show(f, "dialog");
    }

}
