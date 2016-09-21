package com.coffeejjim.developers.owner.auctionprocess;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Estimate;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.AuctionProcessRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuctionProcessActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static final String AUCTION_PROCESS_NOTI = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction_process);
        ButterKnife.bind(this);
        setCustomActionbar();
        if (savedInstanceState == null) {
            AuctionProcessRequest auctionProcessRequest = new AuctionProcessRequest(this,"1","10");
            NetworkManager.getInstance().getNetworkData(auctionProcessRequest, new NetworkManager.OnResultListener<NetworkResult<List<Estimate>>>() {
                @Override
                public void onSuccess(NetworkRequest<NetworkResult<List<Estimate>>> request, NetworkResult<List<Estimate>> result) {
                    List<Estimate> estimates = result.getResult();
                    if(estimates.size()==0){
                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.container, new AuctionProcessEmptyFragment())
                                .commit();
                    }else{
                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.container, new AuctionProcessFragment())
                                .commit();
                    }
                }

                @Override
                public void onFail(NetworkRequest<NetworkResult<List<Estimate>>> request, int errorCode, String errorMessage, Throwable e) {

                }
            });
        }
    }
    private void setCustomActionbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
