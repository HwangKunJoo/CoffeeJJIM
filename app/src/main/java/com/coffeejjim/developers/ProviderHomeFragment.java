package com.coffeejjim.developers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProviderHomeFragment extends Fragment {

    public ProviderHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_provider_home, container, false);
        ButterKnife.bind(this, view);

        ImageView icon = new ImageView(getContext());
        icon.setImageResource(R.drawable.floatsample);

        com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton actionButton
                = new com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.Builder(getActivity())
                .setContentView(icon).build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(getActivity());
        ImageView itemIcon = new ImageView(getContext());
        itemIcon.setImageResource(R.drawable.floatbutton);
        ImageView itemIcon2 = new ImageView(getContext());
        itemIcon2.setImageResource(R.drawable.floatbutton);
        ImageView itemIcon3 = new ImageView(getContext());
        itemIcon3.setImageResource(R.drawable.floatbutton);
        ImageView itemIcon4 = new ImageView(getContext());
        itemIcon4.setImageResource(R.drawable.floatbutton);
        SubActionButton button1 = itemBuilder.setContentView(itemIcon).build();
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();
        SubActionButton button4 = itemBuilder.setContentView(itemIcon4).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(getActivity())
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                .attachTo(actionButton)
                .build();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveProviderHomeActivity();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveAuctionProcessListActivity();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveUserManagementActivity();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveExtraFunctionsActivity();
            }
        });


        return view;
    }

    @OnClick(R.id.providerhome_modify_btn)
    public void onProvider() {
        ((ProviderHomeActivity) getActivity()).changeProviderHomeEdit();
    }

    public void moveProviderHomeActivity(){
        Intent intent = new Intent(getActivity(), ProviderHomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    public void moveAuctionProcessListActivity(){
        Intent intent = new Intent(getActivity(), AuctionProcessActivity.class);
        startActivity(intent);
    }

    public void moveUserManagementActivity(){
        Intent intent = new Intent(getActivity(), UserManagementActivity.class);
        startActivity(intent);
    }

    public void moveExtraFunctionsActivity(){
        Intent intent = new Intent(getActivity(), ExtraFunctionsActivity.class);
        startActivity(intent);
    }


}
