package com.example.smartcommunityapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcommunityapplication.R;

public class ReleaseActivity extends AppCompatActivity {
    private TextView addPhoto;
    private RadioButton radiobutton1;
    private TextView fifth_tv_fabu;
    private EditText fifth_tv_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        addPhoto = findViewById(R.id.add_photo);
        radiobutton1=findViewById(R.id.radiobutton1);
        fifth_tv_price=findViewById(R.id.fifth_tv_price);
        fifth_tv_fabu=findViewById(R.id.fifth_tv_fabu);


        fifth_tv_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), fifth_tv_price.getText().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReleaseActivity.this,PhotoActivity.class);
                startActivity(intent);
            }
        });

        fifth_tv_fabu.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                // rel.setFocusable(true);
                // 如果xml文件里面没设置，就需要在这里设置
                // rel.setFocusableInTouchMode(true);
                fifth_tv_fabu.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(fifth_tv_price.getWindowToken(), 0);
                return false;
            }
        });

    }




}
