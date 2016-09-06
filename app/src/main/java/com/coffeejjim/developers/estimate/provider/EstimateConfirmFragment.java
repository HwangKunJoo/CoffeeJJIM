package com.coffeejjim.developers.estimate.provider;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.coffeejjim.developers.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstimateConfirmFragment extends Fragment {

    @BindView(R.id.btn_estimate_sheet_confirm_present)
    Button btn_confirm_present;


    public EstimateConfirmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fr_estimate_confirm, container, false);
        ButterKnife.bind(this, view);
        btn_confirm_present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAlertDialog(view);
            }
        });
        return view;
    }


    public void onAlertDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder((EstimateSheetConfirmActivity)getActivity());
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Coffee JJIM");
        builder.setMessage("견적 요청이 들어왔습니다. 확인하시겠습니까?");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText((EstimateSheetConfirmActivity)getActivity(), "확인", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText((EstimateSheetConfirmActivity)getActivity(), "닫기", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setCancelable(false);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Toast.makeText((EstimateSheetConfirmActivity)getActivity(), "취소 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
