package com.coffeejjim.developers.estimate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;


public class EstimateComplementDialogFragment extends DialogFragment {

    public static EstimateComplementDialogFragment newInstance(String message) {
        EstimateComplementDialogFragment f = new EstimateComplementDialogFragment();
        Bundle b = new Bundle();
        b.putString("message", message);
        f.setArguments(b);
        return f;
    }

    String message;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            message = getArguments().getString("message");
        }
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("select item");
        builder.setMessage("item : " + message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "OK Click", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setCancelable(false);
        return builder.show();
    }

}
