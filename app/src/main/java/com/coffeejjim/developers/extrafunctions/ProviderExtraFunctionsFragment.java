package com.coffeejjim.developers.extrafunctions;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeejjim.developers.ProviderInfoEditActivity;
import com.coffeejjim.developers.R;
import com.coffeejjim.developers.extrafunctions.inquiry.InquiryActivity;
import com.coffeejjim.developers.extrafunctions.notification.NotificationActivity;
import com.coffeejjim.developers.extrafunctions.settings.SettingsActivity;
import com.coffeejjim.developers.owner.auctionstatement.AuctionStatementActivity;
import com.coffeejjim.developers.owner.usermanagement.UserManagementActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProviderExtraFunctionsFragment extends Fragment {


    public ProviderExtraFunctionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_owner_extra_functions, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_provider_extra_functions_auction_statement)
    public void onAuctionStatement(){
        moveAuctionStatementActivity();
    }
    @OnClick(R.id.btn_provider_extra_functions_user_management)
    public void onUserManagement(){
        moveUserManagementActivity();
    }
    @OnClick(R.id.btn_provider_extra_functions_inquiry)
    public void onInquiry(){
        moveInquiryActivity();
    }
    @OnClick(R.id.btn_provider_extra_functions_notification)
    public void onNotification(){
        moveNotificationActivity();
    }
    @OnClick(R.id.btn_provider_extra_functions_settings)
    public void onSettings(){
        moveSettingsActivity();
    }
    @OnClick(R.id.btn_provider_extra_functions_user_info_edit)
    public void onUserInfoEdit(){
        moveProviderInfoEditActivity();
    }

    public void moveAuctionStatementActivity(){
        Intent AuctionListIntent = new Intent(getActivity(), AuctionStatementActivity.class);
        startActivity(AuctionListIntent);
    }

    public void moveUserManagementActivity(){
        Intent LikeListIntent = new Intent(getActivity(), UserManagementActivity.class);
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

    public void moveProviderInfoEditActivity(){
        Intent ProviderInfoEditIntent = new Intent(getActivity(), ProviderInfoEditActivity.class);
        startActivity(ProviderInfoEditIntent);
    }

}
