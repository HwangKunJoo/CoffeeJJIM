package com.coffeejjim.developers.reservation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.data.Proposal;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.CafeProposalListRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CafeReservationListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static final String AUCTION_FINISH_NOTI = "2";
    public static final String PROPOSAL_NOTI = "3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_reservation_list);
        ButterKnife.bind(this);
        setCustomActionbar();

        if (savedInstanceState == null) {
            CafeProposalListRequest cafeProposalListRequest=
                    new CafeProposalListRequest(this,"1","10");
            NetworkManager.getInstance().getNetworkData(cafeProposalListRequest, new NetworkManager.OnResultListener<NetworkResult<List<Proposal>>>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResult<List<Proposal>>> request, NetworkResult<List<Proposal>> result) {
                    List<Proposal> proposals = result.getResult();
                    if(proposals.size()==0){
                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.container, new CafeReservationListEmptyFragment())
                                .commit();
                    }else{
                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.container, new CafeReservationListFragment())
                                .commit();
                    }
                }
                @Override
                public void onFail(NetworkRequest<NetworkResult<List<Proposal>>> request, int errorCode, String errorMessage, Throwable e) {

                }
            });

        }
    }

    private void setCustomActionbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        //set custom view layout
//        View mCustomView = getLayoutInflater().inflate(R.layout.view_cafe_reservation_toolbar, null);
//        getSupportActionBar().setCustomView(mCustomView);

        toolbar.setContentInsetsAbsolute(0, 0);

        //getSupportActionBar().setCustomView(mCustomView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_cafe_reservation, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }else{
            //되는지 안되는지 티가 안남 고칠 필요가 있음, 새로고침
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new CafeReservationListFragment())
                    .commit();
        }
        return super.onOptionsItemSelected(item);
    }
}
