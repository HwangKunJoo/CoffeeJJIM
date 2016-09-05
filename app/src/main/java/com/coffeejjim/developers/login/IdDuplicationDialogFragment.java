package com.coffeejjim.developers.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.widget.Toast;

public class IdDuplicationDialogFragment extends DialogFragment {


    public static IdDuplicationDialogFragment newInstance(String ownerId) {
        IdDuplicationDialogFragment f = new IdDuplicationDialogFragment();
        Bundle b = new Bundle();
        b.putString("ownerId", ownerId);
        f.setArguments(b);
        return f;
    }

    String ownerId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ownerId = getArguments().getString("ownerId");
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Coffee JJIM");
        getFragmentManager().findFragmentByTag("IdDuplicationDialog");
        if(TextUtils.isEmpty(ownerId))
        {
            builder.setMessage("아이디를 입력해 주세요.");
        }else {
            builder.setMessage("이미 존재하는 아이디 입니다.");
        }
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "Cancel click", Toast.LENGTH_SHORT).show();
            }
        });

        return builder.create();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }
}
