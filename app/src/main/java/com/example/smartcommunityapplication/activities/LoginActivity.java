package com.example.smartcommunityapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcommunityapplication.R;

public class LoginActivity extends AppCompatActivity {

    private EditText number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("登录");

        number = findViewById(R.id.number);
        Button login = findViewById(R.id.saved);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent response = new Intent();

                response.putExtra("number",number.getText().toString());
                //响应
                setResult(1,response);
                //结束当前的NewActivity
                finish();
            }
        });
    }
}
