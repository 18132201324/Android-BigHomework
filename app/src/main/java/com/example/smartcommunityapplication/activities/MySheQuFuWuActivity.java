package com.example.smartcommunityapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.smartcommunityapplication.BottomDialog;
import com.example.smartcommunityapplication.CustomPopupWindow;
import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.adapters.CommunityListAdapter;
import com.example.smartcommunityapplication.entities.Community;

import java.util.ArrayList;
import java.util.List;

public class MySheQuFuWuActivity extends AppCompatActivity implements CustomPopupWindow.OnItemClickListener {
    private List<Community> communities = new ArrayList<> ();
    private ListView communityList;
    private CustomPopupWindow mPop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_my_she_qu_fu_wu);
        getViews();
        Community community1 = new Community ("国之花","河南洛阳","1");
        Community community2 = new Community ("大数据产业园","河南洛阳","1");
        Community community3 = new Community ("五洲大厦","河南洛阳","1");
        communities.add (community1);
        communities.add (community2);
        communities.add (community3);
        CommunityListAdapter communityListAdapter = new CommunityListAdapter (MySheQuFuWuActivity.this,communities,R.layout.xiaoqulistadapter);
        communityList.setAdapter (communityListAdapter);
        mPop=new CustomPopupWindow(this);
        mPop.setOnItemClickListener(this);
        communityList.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPop.backgroundAlpha (MySheQuFuWuActivity.this,0.5f);
                mPop.showAtLocation(MySheQuFuWuActivity.this.findViewById(R.id.mySheQuFuWu_communityList), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });

    }

    public void setOnItemClick(View v) {
        switch(v.getId()){
            case R.id.guanliyuan:
                startActivity (new Intent (MySheQuFuWuActivity.this,LoginActivity.class));
                mPop.backgroundAlpha (MySheQuFuWuActivity.this,1f);
                mPop.dismiss ();
            break;
            case R.id.yonghu:
                BottomDialog bottomDialogFr = new BottomDialog();
                bottomDialogFr.show(getSupportFragmentManager(), "DF");
                mPop.backgroundAlpha (MySheQuFuWuActivity.this,1f);
                mPop.dismiss ();
            break;
        }
    }

    private void getViews() {
        communityList = findViewById (R.id.mySheQuFuWu_communityList);
    }

    public void onClicked(View view) {
        switch (view.getId()){
            case R.id.RunOrder_back:
                finish();
        }
    }
}