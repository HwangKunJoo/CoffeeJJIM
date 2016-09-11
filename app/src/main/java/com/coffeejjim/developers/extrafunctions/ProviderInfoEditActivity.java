package com.coffeejjim.developers.extrafunctions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.coffeejjim.developers.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProviderInfoEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_info_edit);
        ButterKnife.bind(this);

        String[] str = getResources().getStringArray(R.array.emailArray);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,str);
        Spinner spi = (Spinner)findViewById(R.id.activity_provider_info_edit_email_spinner);
        spi.setAdapter(adapter);
        spi.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );
    }

    @OnClick(R.id.activity_provider_info_edit_logout_btn)
    public void onLogout(){
        onLogoutAlertDialog();
    }

    private void onLogoutAlertDialog() {

        LogoutDialogFragment logoutDialogFragment = new LogoutDialogFragment();
        logoutDialogFragment.show(getSupportFragmentManager(), "LogoutDialog");
    }


}
