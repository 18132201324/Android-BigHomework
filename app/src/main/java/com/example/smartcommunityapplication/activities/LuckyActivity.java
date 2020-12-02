package com.example.smartcommunityapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.view.LuckyView;

public class LuckyActivity extends AppCompatActivity {
    private LuckyView luckyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        luckyView = findViewById(R.id.lucky_view);
    }

    private void initData() {

    }

    private void initListener() {
        luckyView.setLuckAnimationEndListener(new LuckyView.OnLuckAnimationEndListener() {
            @Override
            public void onLuckAnimationEnd(int pos, String msg) {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
