package com.example.smartcommunityapplication.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.classes.LoginAccountMessage;
import com.example.smartcommunityapplication.classes.LoginState;

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
        nickName.setText (LoginAccountMessage.user.getNick_name());
        phone.setText ("+86 "+ LoginAccountMessage.user.getPhone_number());
    }
    public void onClicked(View view) {
        switch (view.getId ()){
            case R.id.changeMyselfPage_back:
                finish ();
                break;
            case R.id.btn_logOut:
                LoginState.State=0;
                Toast.makeText(this,"注销成功",Toast.LENGTH_SHORT).show();
                finish ();
                break;
        }
    }
}
