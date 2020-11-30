package com.example.smartcommunityapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcommunityapplication.R;

public class ReleaseActivity extends AppCompatActivity {
    private TextView addPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        addPhoto = findViewById(R.id.add_photo);

        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReleaseActivity.this,PhotoActivity.class);
                startActivity(intent);
            }
        });
    }
}
