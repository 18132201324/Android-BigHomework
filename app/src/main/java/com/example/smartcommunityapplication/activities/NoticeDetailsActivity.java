package com.example.smartcommunityapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.adapters.NoticeCommentAdapter;
import com.example.smartcommunityapplication.entities.InformationComment;
import com.example.smartcommunityapplication.entities.Notice;

import java.util.ArrayList;
import java.util.List;

public class NoticeDetailsActivity extends AppCompatActivity {
    private ListView NoticePl;
    private ImageView back;
    private ImageView headImg;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private TextView name;
    private TextView content;
    private TextView time;
    private LinearLayout faBuZhe;
    private List<InformationComment> commentList = new ArrayList <>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_notice_details);
        getViews();

        for (int i=0;i<3;i++){
            InformationComment informationComment = new InformationComment ();
            informationComment.setUserName ("张三"+i);
            informationComment.setContent ("好的，没问题"+i);
            informationComment.setTime ("2020-12-01 13:11");
            commentList.add (informationComment);
        }
        Log.e ("123",commentList.size ()+"");
        NoticeCommentAdapter noticeCommentAdapter = new NoticeCommentAdapter (NoticeDetailsActivity.this,commentList,R.layout.item_notice_comment_layout);
        NoticePl.setAdapter (noticeCommentAdapter);
        back.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        });
        faBuZhe.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity (new Intent (NoticeDetailsActivity.this,DataCardActivity.class));
            }
        });
    }
    private void getViews() {
        faBuZhe = findViewById (R.id.messageLayout1_faBuZhe);
        NoticePl = findViewById (R.id.noticDetails_pinglun);
        back = findViewById (R.id.noticeDetails_back);
        headImg = findViewById (R.id.messageLayout1_img1);
        img2 = findViewById (R.id.messageLayout1_img2);
        img3 = findViewById (R.id.messageLayout1_img3);
        img4 = findViewById (R.id.messageLayout1_img4);
        name = findViewById (R.id.messageLayout1_Name);
        content = findViewById (R.id.messageLayout1_title);
        time = findViewById (R.id.messageLayout1_time);
    }

}