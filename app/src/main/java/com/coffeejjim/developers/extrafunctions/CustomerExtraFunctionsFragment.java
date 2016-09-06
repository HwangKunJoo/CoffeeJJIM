package com.coffeejjim.developers.extrafunctions;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.extrafunctions.auctionlist.AuctionListActivity;
import com.coffeejjim.developers.extrafunctions.inquiry.InquiryActivity;
import com.coffeejjim.developers.extrafunctions.likelist.LikeListActivity;
import com.coffeejjim.developers.extrafunctions.notification.NotificationActivity;
import com.coffeejjim.developers.extrafunctions.settings.SettingsActivity;
import com.coffeejjim.developers.login.LoginActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerExtraFunctionsFragment extends Fragment {

    AlertDialog dialog;

    public CustomerExtraFunctionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fr_customer_extra_functions, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_customer_extra_functions_auction_statement)
    public void onAuctionList(){
        moveAuctionListActivity();
    }
    @OnClick(R.id.btn_customer_extra_functions_like_list)
    public void onLikeList(){
        moveLikeListActivity();
    }
    @OnClick(R.id.btn_customer_extra_functions_inquiry)
    public void onInquiry(){
        moveInquiryActivity();
    }
    @OnClick(R.id.btn_customer_extra_functions_notification)
    public void onNotification(){
        moveNotificationActivity();
    }
    @OnClick(R.id.btn_customer_extra_functions_settings)
    public void onSettings(){
        moveSettingsActivity();
    }
    @OnClick(R.id.btn_customer_extra_functions_logout)
    public void onLogout(){
        onLogoutAlertDialog();
    }

    public void moveAuctionListActivity(){
        Intent AuctionListIntent = new Intent(getActivity(), AuctionListActivity.class);
        startActivity(AuctionListIntent);
    }

    public void moveLikeListActivity(){
        Intent LikeListIntent = new Intent(getActivity(), LikeListActivity.class);
        startActivity(LikeListIntent);
    }

    public void moveInquiryActivity(){
        Intent InquiryIntent = new Intent(getActivity(), InquiryActivity.class);
        startActivity(InquiryIntent);
    }

    public void moveNotificationActivity(){
        Intent NotificationIntent = new Intent(getActivity(), NotificationActivity.class);
        startActivity(NotificationIntent);
    }

    public void moveSettingsActivity(){
        Intent SettingsIntent = new Intent(getActivity(), SettingsActivity.class);
        startActivity(SettingsIntent);
    }


    private void onLogoutAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Coffee JJIM");
        builder.setMessage("로그아웃 하시겠습니까.");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(),"로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(loginIntent);
                getActivity().finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(),"취소 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
    }

}
