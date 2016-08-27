package com.coffeejjim.developers.cafedetail;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.estimate.EstimateSheetFragment;

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

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new CafeDetailFragment())
                    .commit();
        }
    }

    private void setCustomActionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                item.setIcon(R.drawable.likefull);
                isLiked = true;
                Toast.makeText(this, "즐겨찾기에 추가 되었습니다." , Toast.LENGTH_SHORT).show();
            }else{
                item.setIcon(R.drawable.likeempty);
                isLiked = false;
                Toast.makeText(this, "즐겨찾기가 해제 되었습니다." , Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
