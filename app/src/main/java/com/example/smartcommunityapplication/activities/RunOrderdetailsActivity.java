package com.example.smartcommunityapplication.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcommunityapplication.R;

public class RunOrderdetailsActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_orderdetails);
        getViews();
    }

    private void getViews() {
        imageView = findViewById(R.id.RunOrder_back);
    }

    public void onClicked(View view) {
        switch (view.getId()){
            case R.id.RunOrder_back:
                finish();
        }
    }
}