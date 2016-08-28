package com.coffeejjim.developers.extrafunctions;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.extrafunctions.auctionlist.AuctionListActivity;
import com.coffeejjim.developers.extrafunctions.likelist.LikeListActivity;
import com.coffeejjim.developers.extrafunctions.notification.NotificationActivity;
import com.coffeejjim.developers.extrafunctions.settings.SettingsActivity;
import com.coffeejjim.developers.extrafunctions.inquiry.InquiryActivity;
import com.coffeejjim.developers.login.LoginActivity;

public class ExtraFunctionsActivity extends AppCompatActivity {

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_functions);
        ImageButton acutionlistBtn = (ImageButton) findViewById(R.id.extra_functions_estimate_btn);
        acutionlistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AuctionListIntent = new Intent(ExtraFunctionsActivity.this, AuctionListActivity.class);
                startActivity(AuctionListIntent);
            }
        });

        ImageButton likelistBtn = (ImageButton) findViewById(R.id.extra_functions_likelist_btn);
        likelistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LikeListIntent = new Intent(ExtraFunctionsActivity.this, LikeListActivity.class);
                startActivity(LikeListIntent);
            }
        });

        ImageButton inquiryBtn = (ImageButton) findViewById(R.id.extra_functions_inguiry_btn);
        inquiryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent InquiryIntent = new Intent(ExtraFunctionsActivity.this, InquiryActivity.class);
                startActivity(InquiryIntent);
            }
        });

        ImageButton notificationBtn = (ImageButton) findViewById(R.id.extra_functions_notification_btn);
        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent NotificationIntent = new Intent(ExtraFunctionsActivity.this, NotificationActivity.class);
                startActivity(NotificationIntent);
            }
        });

        ImageButton settingsBtn = (ImageButton) findViewById(R.id.extra_functions_Settings_btn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SettingsIntent = new Intent(ExtraFunctionsActivity.this, SettingsActivity.class);
                startActivity(SettingsIntent);
            }
        });

        ImageButton logoutBtn = (ImageButton) findViewById(R.id.extra_functions_logout_btn);
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
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent loginIntent = new Intent(ExtraFunctionsActivity.this, LoginActivity.class);
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(loginIntent);
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ExtraFunctionsActivity.this,"취소 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
    }
}
