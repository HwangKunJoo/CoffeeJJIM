package com.coffeejjim.developers.cafedetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.data.Cafe;
import com.coffeejjim.developers.data.CafeImage;
import com.coffeejjim.developers.data.NetworkResult;
import com.coffeejjim.developers.event.EventDetailFragment;
import com.coffeejjim.developers.manager.NetworkManager;
import com.coffeejjim.developers.manager.NetworkRequest;
import com.coffeejjim.developers.request.AddLikeListRequest;
import com.coffeejjim.developers.request.DeleteLikeListRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CafeDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_detail);
        ButterKnife.bind(this);
        setCustomActionbar();
        Intent intent = getIntent();
        int cafeId = intent.getIntExtra("cafeId", 50);
        if (savedInstanceState == null) {
            CafeDetailFragment cafeDetailFragment = CafeDetailFragment.newInstance(cafeId);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container , cafeDetailFragment).commit();
        }
    }

    private void setCustomActionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_cafe_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    boolean isLiked = false;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        } else if(id == R.id.cafe_detail_like){
            if(!isLiked){
                item.setIcon(R.drawable.btn_likefull);
                isLiked = true;
                AddLikeListRequest ALLRequest = new AddLikeListRequest(this, "50");
                NetworkManager.getInstance().getNetworkData(ALLRequest, new NetworkManager.OnResultListener<NetworkResult<Cafe>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<Cafe>> request, NetworkResult<Cafe> result) {
                        Toast.makeText(CafeDetailActivity.this, "즐겨찾기 성공" , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<Cafe>> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(CafeDetailActivity.this, "즐겨찾기 실패" , Toast.LENGTH_SHORT).show();
                    }
                });
                //Toast.makeText(this, "즐겨찾기에 추가 되었습니다." , Toast.LENGTH_SHORT).show();
            }else{
                item.setIcon(R.drawable.btn_likeempty);
                isLiked = false;
                DeleteLikeListRequest DLLRequest = new DeleteLikeListRequest(this, "50");
                NetworkManager.getInstance().getNetworkData(DLLRequest, new NetworkManager.OnResultListener<NetworkResult<Cafe>>() {
                    @Override
                    public void onSuccess(NetworkRequest<NetworkResult<Cafe>> request, NetworkResult<Cafe> result) {
                        Toast.makeText(CafeDetailActivity.this, "즐겨찾기 해제 성공" , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(NetworkRequest<NetworkResult<Cafe>> request, int errorCode, String errorMessage, Throwable e) {
                        Toast.makeText(CafeDetailActivity.this, "즐겨찾기 해제 실패" , Toast.LENGTH_SHORT).show();
                    }
                });
                //Toast.makeText(this, "즐겨찾기가 해제 되었습니다." , Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
