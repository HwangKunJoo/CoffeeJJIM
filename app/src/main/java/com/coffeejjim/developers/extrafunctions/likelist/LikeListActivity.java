package com.coffeejjim.developers.extrafunctions.likelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.coffeejjim.developers.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LikeListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_list);

        ButterKnife.bind(this);
        setCustomActionbar();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new LikeListFragment())
                    .commit();
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
        getMenuInflater().inflate(R.menu.activity_like_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    boolean isCompleted = false;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        } else if (id == R.id.like_list_edit) {
            if (!isCompleted) {
                item.setIcon(R.drawable.success);
                isCompleted = true;
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new DeleteLikeListFragment())
                        .commit();
            } else {
                item.setIcon(R.drawable.edit);
                isCompleted= false;
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new LikeListFragment())
                        .commit();
                Toast.makeText(this, "즐겨찾기 편집이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
