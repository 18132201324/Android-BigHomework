package com.example.smartcommunityapplication.activities;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcommunityapplication.MyScrollView;
import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.UnScrollListView;
import com.example.smartcommunityapplication.adapters.Third_informationCommentAdapter;
import com.example.smartcommunityapplication.entities.Third_informationComment;

import java.util.ArrayList;
import java.util.List;

public class OneInformationActivity extends AppCompatActivity {
    public List<Third_informationComment> thirdInformationComments = new ArrayList<>();//一个全局的链表
    private UnScrollListView third_informationCommentListView;
    private MyScrollView third_informationCommentScrollView;
    private Third_informationCommentAdapter third_informationCommentAdapter;

    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_information);

        initData();
        third_informationCommentListView=findViewById(R.id.third_lv_OneInformation);
        third_informationCommentScrollView=findViewById(R.id.third_sv_OneInformation);

        third_informationCommentAdapter=new Third_informationCommentAdapter(getBaseContext(),thirdInformationComments,R.layout.informationcomment_item);
        third_informationCommentListView.setAdapter(third_informationCommentAdapter);
        //处理ListView每一项的点击事件
        third_informationCommentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(OneInformationActivity.this, OneInformationActivity.class);
//                intent.putExtra("title", thirdinformations.get(position).getTitle());
//                intent.putExtra("main", thirdinformations.get(position).getMain());
//
//                startActivity(intent);
            }
        });

    }
    public void buttonClicked(View view) {
        switch (view.getId()){
            case R.id.third_btn_phone:

//                携带电话号码跳转到拨号界面
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_DIAL);   //android.intent.action.DIAL
                intent.setData(Uri.parse("tel:114"));
                startActivity(intent);
                break;
            case R.id.third_btn_message:
                Intent intent0 = new Intent();
                intent0.setAction(Intent.ACTION_SENDTO);
                intent0.setData(Uri.parse("smsto:114"));
                startActivity(intent0);

                break;
        }
    }


    private void initData() {
        Third_informationComment informationComment=new Third_informationComment();
        for (int i=0;i<10;i++){
            informationComment.setObserver("快乐键盘侠");
            informationComment.setComment("我玩杰斯打不过石头人呜呜呜。");
            informationComment.setLikes(5);
            thirdInformationComments.add(informationComment);
        }

    }
}
