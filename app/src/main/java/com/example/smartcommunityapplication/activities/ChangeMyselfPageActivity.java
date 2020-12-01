package com.example.smartcommunityapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.classes.LoginAccountMessage;

public class ChangeMyselfpageActivity extends AppCompatActivity {
    private ImageView back;
    private TextView phone;
    private TextView nickName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_change_myself_page);
        getViews();
        init();
    }

    private void getViews() {
        phone = findViewById (R.id.changeMyselfPage_phone);
        nickName = findViewById (R.id.changeMyselfPage_phone);
    }

    private void init() {
        nickName.setText ("-");
        phone.setText ("+86 "+ LoginAccountMessage.Account);
    }
    public void onClicked(View view) {
        switch (view.getId ()){
            case R.id.changeMyselfPage_back:
                finish ();
                break;
        }
    }
}