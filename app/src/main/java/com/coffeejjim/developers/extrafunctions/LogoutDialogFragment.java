package com.coffeejjim.developers.extrafunctions;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.data.Owner;
import com.coffeejjim.developers.login.LoginActivity;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.OwnerLogoutRequest;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class LogoutDialogFragment extends DialogFragment {

    AlertDialog dialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Coffee JJIM");
        builder.setMessage("로그아웃 하시겠습니까.");
        builder.setCancelable(true);
        getFragmentManager().findFragmentByTag("LogoutDialog");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                OwnerLogoutRequest OWRequest = new OwnerLogoutRequest(getContext());
                NetworkManager.getInstance().getNetworkData(OWRequest, new NetworkManager.OnResultListener<NetworkResult<Owner>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<Owner>> request, NetworkResult<Owner> result) {
                        Toast.makeText(getActivity(),"로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(loginIntent);
                        getActivity().finish();
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<Owner>> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(getActivity(),"로그아웃에 실패하였습니다..", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(),"취소 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setCancelable(false);

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
