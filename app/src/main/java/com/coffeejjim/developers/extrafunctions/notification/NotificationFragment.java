package com.coffeejjim.developers.extrafunctions.notification;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.data.NotiContent;
import com.coffeejjim.developers.data.Notification;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.NotiContentRequest;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationFragment extends Fragment {

    @BindView(R.id.notification_rv_list)
    RecyclerView notiRecyclerView;

    NotificationRecyclerAdapter mAdapter;
    List<Notification> notices;


    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_notification, container, false);
        ButterKnife.bind(this, view);


//        NotiContent notiContent1 = new NotiContent("서버 점검 안내입니다.");
//        NotiContent notiContent2 = new NotiContent("서버 점검 안내입니다2.");
//        NotiContent notiContent3 = new NotiContent("서버 점검 안내입니다3.");
//
//        Notification noti1 = new Notification("서버 점검", "2016-08-17", Arrays.asList(notiContent1));
//        Notification noti2 = new Notification("서버 점검2", "2016-08-18", Arrays.asList(notiContent1));
//        Notification noti3 = new Notification("서버 점검3", "2016-08-19", Arrays.asList(notiContent1));
//        final List<Notification> notis = Arrays.asList(noti1, noti2, noti3);


        NotiContentRequest NCRequest = new NotiContentRequest(getContext(),"1","10");
        NetworkManager.getInstance().getNetworkData(NCRequest, new NetworkManager.OnResultListener<NetworkResult<List<Notification>>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<List<Notification>>> request,
                                  NetworkResult<List<Notification>> result) {
                notices = result.getResult();
                mAdapter = new NotificationRecyclerAdapter(getContext(), notices);
                mAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
                    @Override
                    public void onListItemExpanded(int position) {
                        Notification noti = notices.get(position);
                        Toast.makeText(getActivity(), noti.getTitle(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onListItemCollapsed(int position) {
                        Notification cnoti = notices.get(position);
                        Toast.makeText(getActivity(), cnoti.getTitle(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                });
                notiRecyclerView.setAdapter(mAdapter);
                notiRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                Toast.makeText(getContext(), "asdad1123", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(NetworkRequest<NetworkResult<List<Notification>>> request, int errorCode, String errorMessage, Throwable e) {
                Toast.makeText(getContext(), "asdad112355555555", Toast.LENGTH_SHORT).show();
            }
        });

        Toast.makeText(getContext(), "asdad1123", Toast.LENGTH_SHORT).show();




        return view;
    }

}
