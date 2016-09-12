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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
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
        GoogleApiClient.ConnectionCallbacks  {

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
    @BindView(R.id.estimate_sheet_endtime_spinner)
    Spinner auctionTimeSpi;

    GoogleApiClient mApiClient;

    String longitude;
    String latitude;

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
        setEstimateSpinner();

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
                String reservationTime = String.format("%d:%d", hourOfDay, minute);
                timeView.setText(reservationTime);
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
                String reservationDate = String.format("%d-%d-%d", year, month, day);
                dateView.setText(reservationDate);
            }
        });
    }

    private void setEstimateSpinner() {
        String[] str = getResources().getStringArray(R.array.auction_time_Array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, str);
        auctionTimeSpi.setAdapter(adapter);
        auctionTimeSpi.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );
    }
    //옵션들 선택 이미지 바꾸기, 위치 변환,
    @OnClick(R.id.btn_estimate_sheet_present)
    public void onEstimateCheckDialogButtonClick() {
        String location = locationView.getText().toString();
        String reservationTime = dateView.getText().toString() +" "+ timeView.getText().toString();

        EstimateRequest ERequest = new EstimateRequest(getContext(),3,37.477025,126.963493,"2016-12-12 16:43:23",1,0,0,0,20);
        NetworkManager.getInstance().getNetworkData(ERequest, new NetworkManager.OnResultListener<NetworkResult<Estimate>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<Estimate>> request, NetworkResult<Estimate> result) {
                Toast.makeText(getContext(), "성공", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<Estimate>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "실패", Toast.LENGTH_SHORT).show();
            }
        });

        onDialogFragmentClick();
    }

    public void onDialogFragmentClick() {
        EstimateCheckDialogFragment f = new EstimateCheckDialogFragment();
        f.show(getFragmentManager(), "dialog");
    }

}
