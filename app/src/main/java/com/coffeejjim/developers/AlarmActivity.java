package com.coffeejjim.developers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.coffeejjim.developers.owner.BookingInfoActivity;

public class AlarmActivity extends AppCompatActivity {

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        onAlertDialog();
    }

    private void onAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Coffee JJIM");
        builder.setMessage("입찰이 성공하였습니다.");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent BookingInfoIntent = new Intent(AlarmActivity.this, BookingInfoActivity.class);
                startActivity(BookingInfoIntent);
            }
        });

        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();

    }
}
