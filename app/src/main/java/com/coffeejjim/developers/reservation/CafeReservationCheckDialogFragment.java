package com.coffeejjim.developers.reservation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;


public class CafeReservationCheckDialogFragment extends DialogFragment{
    String[] items = {"item 1", "item 2", "item 3", "item 4", "item 5", "item 6"};

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Dialog Fragment");
//        builder.setMessage("Show Dialog Fragment");
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String message = items[i];
                CafeReservationComplementDialogFragment f = CafeReservationComplementDialogFragment.newInstance(message);
                Fragment old = getFragmentManager().findFragmentByTag("dialog");
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                if (old != null) {
                    ft.remove(old);
                }
                ft.addToBackStack(null);
                f.show(ft, "childfragment");
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "OK click", Toast.LENGTH_SHORT).show();
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
