package com.coffeejjim.developers;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ExtraFunctionsActivity extends AppCompatActivity {

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_functions);
        Button acutionlistBtn = (Button) findViewById(R.id.extra_functions_estimate_btn);
        acutionlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AuctionListIntent = new Intent(ExtraFunctionsActivity.this, AuctionListActivity.class);
                startActivity(AuctionListIntent);
            }
        });

        Button likelistBtn = (Button) findViewById(R.id.extra_functions_likelist_btn);
        likelistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LikeListIntent = new Intent(ExtraFunctionsActivity.this, LikeListActivity.class);
                startActivity(LikeListIntent);
            }
        });

        Button inquiryBtn = (Button) findViewById(R.id.extra_functions_inguiry_btn);
        inquiryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent InquiryIntent = new Intent(ExtraFunctionsActivity.this, InquiryActivity.class);
                startActivity(InquiryIntent);
            }
        });

        Button notificationBtn = (Button) findViewById(R.id.extra_functions_notification_btn);
        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent NotificationIntent = new Intent(ExtraFunctionsActivity.this, NotificationActivity.class);
                startActivity(NotificationIntent);
            }
        });

        Button settingsBtn = (Button) findViewById(R.id.extra_functions_Settings_btn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SettingsIntent = new Intent(ExtraFunctionsActivity.this, SettingsActivity.class);
                startActivity(SettingsIntent);
            }
        });

        Button logoutBtn = (Button) findViewById(R.id.extra_functions_logout_btn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAlertDialog();
            }
        });
    }

    private void onAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Coffee JJIM");
        builder.setMessage("로그아웃 되었습니다.");
        builder.setCancelable(true);
        dialog = builder.create();
        dialog.show();
    }
}
