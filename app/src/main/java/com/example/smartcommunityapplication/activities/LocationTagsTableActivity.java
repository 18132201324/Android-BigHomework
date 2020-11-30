package com.example.smartcommunityapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcommunityapplication.R;

public class LocationTagsTableActivity extends AppCompatActivity {
    private TextView getMoreLocation;
    private LinearLayout moreLocation;
    private LinearLayout beiJinggetMore;
    private LinearLayout beiJingMoreLocation;
    private LinearLayout dongGuangetMore;
    private LinearLayout dongGuanMoreLocation;
    private LinearLayout luoYanggetMore;
    private LinearLayout luoYangMoreLocation;
    private LinearLayout quanZhougetMore;
    private LinearLayout quanZhouMoreLocation;
    private int isShowHeader = 0;
    private int isShowbelow = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_tags_table);
        getViews();
    }

    private void getViews() {
        getMoreLocation = findViewById (R.id.locationTagTable_getMorelocation);
        moreLocation = findViewById (R.id.locationTagTable_moreLocation);
        beiJinggetMore = findViewById (R.id.locationTagTable_beiJinggetMore);
        beiJingMoreLocation = findViewById (R.id.locationTagTable_beiJingMoreLocation);
        dongGuangetMore = findViewById (R.id.locationTagTable_dongGuangetMore);
        dongGuanMoreLocation = findViewById (R.id.locationTagTable_dongGuanMoreLocation);
        luoYanggetMore = findViewById (R.id.locationTagTable_luoYanggetMore);
        luoYangMoreLocation = findViewById (R.id.locationTagTable_luoYangMoreLocation);
        quanZhougetMore = findViewById (R.id.locationTagTable_quanZhougetMore);
        quanZhouMoreLocation = findViewById (R.id.locationTagTable_quanZhouMoreLocation);
    }

    public void onClicked(View view) {
        switch (view.getId ()){
            case R.id.locationTags_back:
                finish ();
                break;
            case R.id.locationTagTable_getMorelocation:
                if (isShowHeader==1){
                   moreLocation.setVisibility (View.GONE);
                    isShowHeader = 0;
               }else {
                   moreLocation.setVisibility (View.VISIBLE);
                    isShowHeader = 1;
               }
                break;
            case R.id.locationTagTable_beiJinggetMore:
                if (isShowbelow==1){
                    beiJingMoreLocation.setVisibility (View.GONE);
                    dongGuanMoreLocation.setVisibility (View.GONE);
                    luoYangMoreLocation.setVisibility (View.GONE);
                    quanZhouMoreLocation.setVisibility (View.GONE);
                    isShowbelow = 0;
                }else {
                    beiJingMoreLocation.setVisibility (View.VISIBLE);
                    dongGuanMoreLocation.setVisibility (View.GONE);
                    luoYangMoreLocation.setVisibility (View.GONE);
                    quanZhouMoreLocation.setVisibility (View.GONE);
                    isShowbelow = 1;
                }
                break;
            case R.id.locationTagTable_dongGuangetMore:
                if (isShowbelow==1){
                    dongGuanMoreLocation.setVisibility (View.GONE);
                    beiJingMoreLocation.setVisibility (View.GONE);
                    dongGuanMoreLocation.setVisibility (View.GONE);
                    quanZhouMoreLocation.setVisibility (View.GONE);
                    isShowbelow = 0;
                }else {
                    dongGuanMoreLocation.setVisibility (View.VISIBLE);
                    beiJingMoreLocation.setVisibility (View.GONE);
                    luoYangMoreLocation.setVisibility (View.GONE);
                    quanZhouMoreLocation.setVisibility (View.GONE);
                    isShowbelow = 1;
                }
                break;
            case R.id.locationTagTable_luoYanggetMore:
                if (isShowbelow==1){
                    luoYangMoreLocation.setVisibility (View.GONE);
                    beiJingMoreLocation.setVisibility (View.GONE);
                    dongGuanMoreLocation.setVisibility (View.GONE);
                    quanZhouMoreLocation.setVisibility (View.GONE);
                    isShowbelow = 0;
                }else {
                    luoYangMoreLocation.setVisibility (View.VISIBLE);
                    beiJingMoreLocation.setVisibility (View.GONE);
                    dongGuanMoreLocation.setVisibility (View.GONE);
                    quanZhouMoreLocation.setVisibility (View.GONE);
                    isShowbelow = 1;
                }
                break;
            case R.id.locationTagTable_quanZhougetMore:
                if (isShowbelow==1){
                    quanZhouMoreLocation.setVisibility (View.GONE);
                    beiJingMoreLocation.setVisibility (View.GONE);
                    dongGuanMoreLocation.setVisibility (View.GONE);
                    quanZhouMoreLocation.setVisibility (View.GONE);
                    isShowbelow = 0;
                }else {
                    quanZhouMoreLocation.setVisibility (View.VISIBLE);
                    luoYangMoreLocation.setVisibility (View.GONE);
                    dongGuanMoreLocation.setVisibility (View.GONE);
                    beiJingMoreLocation.setVisibility (View.GONE);
                    isShowbelow = 1;
                }
                break;
        }
    }
}