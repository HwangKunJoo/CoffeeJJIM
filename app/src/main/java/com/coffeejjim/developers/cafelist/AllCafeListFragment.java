package com.coffeejjim.developers.cafelist;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.cafedetail.CafeDetailActivity;
import com.coffeejjim.developers.data.Cafe;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.AllCafeListRequest;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllCafeListFragment extends Fragment {

    @BindView(R.id.cafe_location_map)
    TMapView mapView;
    @BindView(R.id.cafe_list_location_search_edit)
    EditText searchEditView;
    @BindView(R.id.all_cafe_rv_list)
    RecyclerView listView;

    AllCafeListRecyclerAdapter mAdapter;
    LocationManager mLM;
    String mProvider = LocationManager.NETWORK_PROVIDER;
    String keyword = null;
    List<Cafe> allCafeList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new AllCafeListRecyclerAdapter();
    }


    public AllCafeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fr_all_cafe_list, container, false);
        ButterKnife.bind(this, view);

        keyword = searchEditView.getText().toString();

        mAdapter = new AllCafeListRecyclerAdapter();
        mAdapter.setOnAdapterItemClickListener(new AllCafeListRecyclerAdapter.OnAdapterItemClickLIstener() {
            @Override
            public void onAdapterItemClick(View view, Cafe cafe, int position) {
                Intent intent = new Intent(getActivity(), CafeDetailActivity.class);
                intent.putExtra("cafeId", cafe.getCafeId());
                startActivity(intent);
            }

            @Override
            public void onAdapterButtonClick(View view, Cafe cafe, int position) {
                Intent intent = new Intent(getActivity(), CafeDetailActivity.class);
                intent.putExtra("cafeId", cafe.getCafeId());
                startActivity(intent);
            }
        });

        listView.setAdapter(mAdapter);

        LinearLayoutManager manager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(manager);

        mLM = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        mapView.setOnApiKeyListener(new TMapView.OnApiKeyListenerCallback() {
            @Override
            public void SKPMapApikeySucceed() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setupMap();
                    }
                });
            }

            @Override
            public void SKPMapApikeyFailed(String s) {

            }
        });
        mapView.setSKPMapApiKey("3415a236-8bea-3965-8ede-fff96cb1b594");
        mapView.setLanguage(TMapView.LANGUAGE_KOREAN);

        Button btn = (Button)view.findViewById(R.id.cafe_list_location_search_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TMapPoint point = mapView.getCenterPoint();
                addMarker(point.getLatitude(), point.getLongitude(), "My Marker");

                //키워드에 맞는 카페를 찾아오는 리퀘스트
                AllCafeListRequest searchRequest = new AllCafeListRequest(getContext(), keyword ,"1", "10", 37, 126);
                NetworkManager.getInstance().getNetworkData(searchRequest, new NetworkManager.OnResultListener<NetworkResult<List<Cafe>>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<List<Cafe>>> request, NetworkResult<List<Cafe>> result) {
                        allCafeList = result.getResult();
                        mAdapter.clear();
                        mAdapter.addAll(allCafeList);
                        Toast.makeText(getContext(), "오예 성공이다", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<List<Cafe>>> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(getContext(), "network fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return view;
    }

    private void addMarker(double lat, double lng, String title) {
        TMapMarkerItem item = new TMapMarkerItem();
        TMapPoint point = new TMapPoint(lat, lng);
        item.setTMapPoint(point);
        Bitmap icon = ((BitmapDrawable) ContextCompat.getDrawable(getContext(), android.R.drawable.ic_input_add)).getBitmap();
        item.setIcon(icon);
        item.setPosition(0.5f, 1);
        item.setCalloutTitle(title);
        item.setCalloutSubTitle("sub " + title);
        Bitmap left = ((BitmapDrawable)ContextCompat.getDrawable(getContext(), android.R.drawable.ic_dialog_alert)).getBitmap();
        item.setCalloutLeftImage(left);
        Bitmap right = ((BitmapDrawable)ContextCompat.getDrawable(getContext(), android.R.drawable.ic_input_get)).getBitmap();
        item.setCalloutRightButtonImage(right);
        item.setCanShowCallout(true);
        mapView.addMarkerItem("m"+id,item);
        id++;
    }

    int id = 0;

    boolean isInitialized = false;

    private void setupMap() {
        isInitialized = true;
        mapView.setMapType(TMapView.MAPTYPE_STANDARD);
//        mapView.setSightVisible(true);
//        mapView.setCompassMode(true);
//        mapView.setTrafficInfo(true);
//        mapView.setTrackingMode(true);
        if (cacheLocation != null) {
            moveMap(cacheLocation.getLatitude(), cacheLocation.getLongitude());
            setMyLocation(cacheLocation.getLatitude(), cacheLocation.getLongitude());
        }
        mapView.setOnClickListenerCallBack(new TMapView.OnClickListenerCallback() {
            @Override
            public boolean onPressEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
                return false;
            }

            @Override
            public boolean onPressUpEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
                return false;
            }
        });
        mapView.setOnCalloutRightButtonClickListener(new TMapView.OnCalloutRightButtonClickCallback() {
            @Override
            public void onCalloutRightButton(TMapMarkerItem tMapMarkerItem) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = mLM.getLastKnownLocation(mProvider);
        if (location != null) {
            mListener.onLocationChanged(location);
        }
        mLM.requestSingleUpdate(mProvider, mListener, null);

        AllCafeListRequest ACLRequest = new AllCafeListRequest(getContext(), "1", "10", 37, 126);
        NetworkManager.getInstance().getNetworkData(ACLRequest, new NetworkManager.OnResultListener<NetworkResult<List<Cafe>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<Cafe>>> request, NetworkResult<List<Cafe>> result) {
                allCafeList = result.getResult();
                mAdapter.clear();
                mAdapter.addAll(allCafeList);
                Toast.makeText(getContext(), "yap", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<Cafe>>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "network fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLM.removeUpdates(mListener);
    }
    Location cacheLocation = null;
    private void moveMap(double lat, double lng) {
        mapView.setCenterPoint(lng, lat);
    }

    private void setMyLocation(double lat, double lng) {
        Bitmap icon = ((BitmapDrawable) ContextCompat.getDrawable(getContext(), android.R.drawable.ic_dialog_map)).getBitmap();
        mapView.setIcon(icon);
        mapView.setLocationPoint(lng, lat);
        mapView.setIconVisibility(true);
    }

    LocationListener mListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (isInitialized) {
                moveMap(location.getLatitude(), location.getLongitude());
                setMyLocation(location.getLatitude(), location.getLongitude());
            } else {
                cacheLocation = location;
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
}
