package com.example.smartcommunityapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.adapters.CommunityListAdapter;
import com.example.smartcommunityapplication.entities.Community;

import java.util.ArrayList;
import java.util.List;

public class MySheQuFuWuActivity extends AppCompatActivity {
    private List<Community> communities = new ArrayList<> ();
    private ListView communityList;
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