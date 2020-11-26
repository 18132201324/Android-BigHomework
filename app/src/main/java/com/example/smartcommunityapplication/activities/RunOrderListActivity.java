package com.example.smartcommunityapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.adapters.mRunOrderListAdapter;
import com.example.smartcommunityapplication.entities.Runorder;

import java.util.ArrayList;
import java.util.List;

public class RunOrderListActivity extends AppCompatActivity {
    private List<Runorder> Runorders = new ArrayList<>();
    private ListView RunOrderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_order_list);
        getViews();
        Runorder runorder1 = new Runorder("123","有偿家教","本人在石家庄居住，我儿子上5年级，现在需要一位家教老师，有意愿者联系。联系方式......","发布于2020-11-25");
        Runorder runorder2 = new Runorder("123","有偿家教","本人在石家庄居住，我儿子上5年级，现在需要一位家教老师，有意愿者联系。联系方式......","发布于2020-11-25");
        Runorder runorder3 = new Runorder("123","有偿家教","本人在石家庄居住，我儿子上5年级，现在需要一位家教老师，有意愿者联系。联系方式......","发布于2020-11-25");
        Runorder runorder4 = new Runorder("123","有偿家教","本人在石家庄居住，我儿子上5年级，现在需要一位家教老师，有意愿者联系。联系方式......","发布于2020-11-25");
        Runorders.add(runorder1);
        Runorders.add(runorder2);
        Runorders.add(runorder3);
        Runorders.add(runorder4);
        mRunOrderListAdapter mRunOrderListAdapter = new mRunOrderListAdapter(RunOrderListActivity.this, Runorders, R.layout.mrunorderlist_adapter);
        RunOrderList.setAdapter(mRunOrderListAdapter);

        RunOrderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(RunOrderListActivity.this,RunOrderdetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getViews() {
        RunOrderList = findViewById(R.id.RunOrder_List);
    }

    public void onClicked(View view) {
        switch (view.getId()){
            case R.id.RunOrder_back:
                finish();
        }
    }
}