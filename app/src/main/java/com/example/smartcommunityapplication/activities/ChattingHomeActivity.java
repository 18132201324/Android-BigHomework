package com.example.smartcommunityapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.adapters.ChattingHomeListAdapter;
import com.example.smartcommunityapplication.entities.Chatting;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChattingHomeActivity extends AppCompatActivity {
    private RecyclerView mrecycler;
    private List<Chatting> chattingList;
    private TextView tvChattingHomeName;
    private TextView etChattingHomeContent;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_home);
        initData();

        mrecycler = findViewById(R.id.chattingHomeRecyclerView);
        mrecycler.setAdapter(new ChattingHomeListAdapter(this,chattingList));
        mrecycler.setLayoutManager(new GridLayoutManager(this,1));
        mrecycler.scrollToPosition(chattingList.size()-1);
    }

    private void initData() {
        chattingList = new ArrayList<>();
        chattingList.add(new Chatting(0,"孙金岩","宋凌锐","你好啊","2020-11-28 10:15:55"));
        chattingList.add(new Chatting(1,"宋凌锐","孙金岩","你好啊","2020-11-28 10:15:55"));
        chattingList.add(new Chatting(2,"孙金岩","宋凌锐","你好啊","2020-11-28 10:15:55"));
        chattingList.add(new Chatting(3,"宋凌锐","孙金岩","你好啊","2020-11-28 10:15:55"));
        Intent intent = getIntent();
        tvChattingHomeName = findViewById(R.id.forth_tv_chattingHomeName);
        tvChattingHomeName.setText(intent.getStringExtra("name"));
        etChattingHomeContent = findViewById(R.id.forth_et_chattingHomeContent);
    }

    public void onClicked(View view){
        switch (view.getId()){
            case R.id.forth_imgBtn_back:
                finish();
                break;
            case R.id.forth_imgBtn_chattingHomeMore:
                break;
            case R.id.forth_btn_chattingHomeSend:
                if (etChattingHomeContent.getText()!=null&&etChattingHomeContent.getText().toString().length()!=0) {
                    //获得时间字符串
                    Date date = new Date();
                    long times = date.getTime();//时间戳
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String DateStr = formatter.format(date);
                    chattingList.add(new Chatting(4, "宋凌锐", tvChattingHomeName.getText().toString(), etChattingHomeContent.getText().toString(), DateStr));

                    mrecycler.setAdapter(new ChattingHomeListAdapter(this,chattingList));
                    mrecycler.setLayoutManager(new GridLayoutManager(this,1));
                    etChattingHomeContent.setText("");
                    mrecycler.scrollToPosition(chattingList.size()-1);
                }else{
                    Toast.makeText(this,"发送消息不能为空",Toast.LENGTH_SHORT).show();
                }
                Log.e("adsdas",chattingList.size()+"");
                break;
        }
    }
}
