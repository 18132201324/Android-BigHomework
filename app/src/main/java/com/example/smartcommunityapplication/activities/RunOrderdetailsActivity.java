package com.example.smartcommunityapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.entities.OrderRun;

public class RunOrderdetailsActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView theme;
    private TextView stopTime;
    private TextView price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_orderdetails);
        getViews();
        Intent intent = getIntent ();
        OrderRun orderRun = (OrderRun) intent.getSerializableExtra ("runOrder");
        theme.setText (orderRun.getTitle ());
        stopTime.setText ("截止时间："+orderRun.getTime_stop ());
        price.setText ("￥"+orderRun.getMoney ()+"");
    }

    private void getViews() {
        imageView = findViewById(R.id.RunOrder_back);
        theme = findViewById (R.id.runorder_theme);
        stopTime = findViewById (R.id.runorder_stopTime);
        price = findViewById (R.id.runorder_price);
    }

    public void onClicked(View view) {
        switch (view.getId()){
            case R.id.RunOrder_back:
                finish();
        }
    }
}