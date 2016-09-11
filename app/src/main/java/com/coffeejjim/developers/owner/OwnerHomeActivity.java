package com.coffeejjim.developers.owner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.extrafunctions.ExtraFunctionsActivity;
import com.coffeejjim.developers.owner.auctionprocess.AuctionProcessActivity;
import com.coffeejjim.developers.owner.auctionstatement.AuctionStatementActivity;
import com.coffeejjim.developers.owner.usermanagement.UserManagementActivity;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OwnerHomeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    FloatingActionButton actionButton;
    SubActionButton floatingOwnerHomeButton;
    SubActionButton floatingAuctionStatementButton;
    SubActionButton floatingUserManagementButton;
    SubActionButton floatingExtraFunctionsButton;


    public static final int PROVIDER = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_home);
        ButterKnife.bind(this);
        setCustomActionbar();
        setFloatingButton();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.provider_home_container, new OwnerHomeFragment())
                    .commit();
        }
    }

    private void setFloatingButton() {
        ImageView floatingOwnerMainView = new ImageView(this);
        floatingOwnerMainView.setImageResource(R.drawable.floating_main);
        int floatingWidth = getResources().getDimensionPixelSize(R.dimen.radius_small);
        int floatingHeight = getResources().getDimensionPixelSize(R.dimen.radius_small);
        int floatingMargin = 58;
        FloatingActionButton.LayoutParams floatingMainParams = new FloatingActionButton.LayoutParams
                (floatingWidth, floatingHeight);
        floatingMainParams.setMargins(floatingMargin, floatingMargin, floatingMargin, floatingMargin);

        actionButton = new FloatingActionButton.Builder(this).setContentView(floatingOwnerMainView).setBackgroundDrawable(R.drawable.floating_background).
                setLayoutParams(floatingMainParams).build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        ImageView floatingOwnerHomeView = new ImageView(this);
        floatingOwnerHomeView.setImageResource(R.drawable.floating_home);
        ImageView floatingAuctionStatementView = new ImageView(this);
        floatingAuctionStatementView.setImageResource(R.drawable.floating_auction_statement);
        ImageView floatingUserManagementView = new ImageView(this);
        floatingUserManagementView.setImageResource(R.drawable.floating_user_management);
        ImageView floatingExtraFunctionsView = new ImageView(this);
        floatingExtraFunctionsView.setImageResource(R.drawable.floating_extra_functions);

        floatingOwnerHomeButton = itemBuilder.setContentView(floatingOwnerHomeView)
                .setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.floating_background)).build();
        floatingAuctionStatementButton = itemBuilder.setContentView(floatingAuctionStatementView).build();
        floatingUserManagementButton = itemBuilder.setContentView(floatingUserManagementView).build();
        floatingExtraFunctionsButton = itemBuilder.setContentView(floatingExtraFunctionsView).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .setRadius(getResources().getDimensionPixelSize(R.dimen.radius_large))
                .addSubActionView(floatingOwnerHomeButton)
                .addSubActionView(floatingAuctionStatementButton)
                .addSubActionView(floatingUserManagementButton)
                .addSubActionView(floatingExtraFunctionsButton)
                .attachTo(actionButton)
                .build();

        floatingOwnerHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.provider_home_container, new OwnerHomeFragment())
                        .commit();
            }
        });
        floatingAuctionStatementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveAuctionStatementListActivity();
            }
        });
        floatingUserManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveUserManagementActivity();
            }
        });
        floatingExtraFunctionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveExtraFunctionsActivity();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_provider_home, menu);
        return true;


        // 뱃지 알림 처리 알고리즘 추가
//        //you can add some logic (hide it if the count == 0)
//        if (badgeCount > 0) {
//            ActionItemBadge.update(this, menu.findItem(R.id.item_samplebadge), FontAwesome.Icon.faw_bell, ActionItemBadge.BadgeStyles.RED, badgeCount);
//        } else {
//            ActionItemBadge.hide(menu.findItem(R.id.item_samplebadge));
//        }

        //If you want to add your ActionItem programmatically you can do this too. You do the following:

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.provider_home_item_badge) {
            Toast.makeText(this, "진행중인 경매 목록으로 이동합니다.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AuctionProcessActivity.class);
            startActivity(intent);
//            badgeCount--;
//            ActionItemBadge.update(item, badgeCount);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setCustomActionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void changeProviderHome() {
        if (actionButton.getVisibility() == View.GONE) {
            actionButton.setVisibility(View.VISIBLE);
            floatingOwnerHomeButton.setVisibility(View.VISIBLE);
            floatingAuctionStatementButton.setVisibility(View.VISIBLE);
            floatingUserManagementButton.setVisibility(View.VISIBLE);
            floatingExtraFunctionsButton.setVisibility(View.VISIBLE);
            Intent intent = new Intent(this, OwnerHomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void changeProviderHomeEdit() {
        if (actionButton.getVisibility() == View.VISIBLE) {
            actionButton.setVisibility(View.GONE);
            floatingOwnerHomeButton.setVisibility(View.GONE);
            floatingAuctionStatementButton.setVisibility(View.GONE);
            floatingUserManagementButton.setVisibility(View.GONE);
            floatingExtraFunctionsButton.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.provider_home_container, new OwnerHomeEditFragment())
                    .commit();
        }
    }

    public void moveAuctionStatementListActivity() {
        Intent intent = new Intent(this, AuctionStatementActivity.class);
        startActivity(intent);
    }

    public void moveUserManagementActivity() {
        Intent intent = new Intent(this, UserManagementActivity.class);
        startActivity(intent);
    }

    public void moveExtraFunctionsActivity() {
        Intent intent = new Intent(this, ExtraFunctionsActivity.class);
        intent.putExtra("user", PROVIDER);
        startActivity(intent);
    }


}
