package com.coffeejjim.developers.extrafunctions;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.extrafunctions.auctionlist.AuctionListActivity;
import com.coffeejjim.developers.extrafunctions.inquiry.InquiryActivity;
import com.coffeejjim.developers.extrafunctions.likelist.LikeListActivity;
import com.coffeejjim.developers.extrafunctions.notification.NotificationActivity;
import com.coffeejjim.developers.extrafunctions.settings.SettingsActivity;
import com.coffeejjim.developers.login.LoginActivity;

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
        ImageButton acutionlistBtn = (ImageButton)view.findViewById(R.id.extra_functions_estimate_btn);
        acutionlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AuctionListIntent = new Intent((ExtraFunctionsActivity)getActivity(), AuctionListActivity.class);
                startActivity(AuctionListIntent);
            }
        });

        ImageButton likelistBtn = (ImageButton) view.findViewById(R.id.extra_functions_likelist_btn);
        likelistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LikeListIntent = new Intent((ExtraFunctionsActivity)getActivity(), LikeListActivity.class);
                startActivity(LikeListIntent);
            }
        });

        ImageButton inquiryBtn = (ImageButton) view.findViewById(R.id.extra_functions_inguiry_btn);
        inquiryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent InquiryIntent = new Intent((ExtraFunctionsActivity)getActivity(), InquiryActivity.class);
                startActivity(InquiryIntent);
            }
        });

        ImageButton notificationBtn = (ImageButton) view.findViewById(R.id.extra_functions_notification_btn);
        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent NotificationIntent = new Intent((ExtraFunctionsActivity)getActivity(), NotificationActivity.class);
                startActivity(NotificationIntent);
            }
        });

        ImageButton settingsBtn = (ImageButton) view.findViewById(R.id.extra_functions_Settings_btn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SettingsIntent = new Intent((ExtraFunctionsActivity)getActivity(), SettingsActivity.class);
                startActivity(SettingsIntent);
            }
        });

        ImageButton logoutBtn = (ImageButton) view.findViewById(R.id.extra_functions_logout_btn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAlertDialog();
            }
        });
        return view;
    }

    private void onAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Coffee JJIM");
        builder.setMessage("로그아웃 되었습니다.");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent loginIntent = new Intent((ExtraFunctionsActivity)getActivity(), LoginActivity.class);
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(loginIntent);
                getActivity().finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText((ExtraFunctionsActivity)getActivity(),"취소 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
    }

}
