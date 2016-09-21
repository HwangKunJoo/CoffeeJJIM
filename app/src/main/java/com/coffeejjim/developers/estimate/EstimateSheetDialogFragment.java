package com.coffeejjim.developers.estimate;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.coffeejjim.developers.R;
import com.coffeejjim.developers.extrafunctions.auctionlist.AuctionListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class EstimateSheetDialogFragment extends DialogFragment {

    @BindView(R.id.fr_estimate_sheet_dialog_plain_text)
    TextView sheetDescTopView;
    @BindView(R.id.fr_estimate_sheet_dialog_plain_text1)
    TextView sheetDescBottomView;
    @BindView(R.id.fr_estimate_sheet_dialog_cancle_btn)
    Button cancelBtn;
    @BindView(R.id.fr_estimate_sheet_dialog_present_btn)
    Button presentBtn;


    public EstimateSheetDialogFragment() {
        // Required empty public constructor
    }

    public static EstimateSheetDialogFragment newInstance(int type) {
        EstimateSheetDialogFragment f = new EstimateSheetDialogFragment();
        Bundle b = new Bundle();
        b.putInt("estimateSheet", type);
        f.setArguments(b);
        return f;
    }

    int type;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt("estimateSheet");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_estimate_sheet_dialog, container, false);
        ButterKnife.bind(this,view);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCancelable(false);
        if(type == AuctionListFragment.AUCTION_LIST_CALL){
            sheetDescTopView.setVisibility(View.GONE);
            sheetDescBottomView.setVisibility(View.GONE);
            cancelBtn.setVisibility(View.GONE);
            presentBtn.setVisibility(View.GONE);
        }else if(type == EstimateSheetFragment.ESTIMATE_CALL){
            sheetDescTopView.setVisibility(View.VISIBLE);
            sheetDescBottomView.setVisibility(View.VISIBLE);
            cancelBtn.setVisibility(View.VISIBLE);
            presentBtn.setVisibility(View.VISIBLE);
        }

        return view;
    }

}
