package com.coffeejjim.developers.estimate.provider;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.coffeejjim.developers.R;

public class EstimateSheetConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate_sheet_confirm);

    }

    public void onAlertDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(EstimateSheetConfirmActivity.this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Coffee JJIM");
        builder.setMessage("견적 요청이 들어왔습니다. 확인하시겠습니까?");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(EstimateSheetConfirmActivity.this, "확인", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(EstimateSheetConfirmActivity.this, "닫기", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setCancelable(false);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Toast.makeText(EstimateSheetConfirmActivity.this, "취소 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
