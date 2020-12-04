package com.example.smartcommunityapplication.activities;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcommunityapplication.MyScrollView;
import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.UnScrollListView;
import com.example.smartcommunityapplication.adapters.Third_informationCommentAdapter;
import com.example.smartcommunityapplication.entities.Third_informationComment;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class OneInformationActivity extends Activity {
    public List<Third_informationComment> thirdInformationComments = new ArrayList<>();//一个全局的链表
    private UnScrollListView third_informationCommentListView;
    private MyScrollView third_informationCommentScrollView;
    private Third_informationCommentAdapter third_informationCommentAdapter;

    private String phoneNumber;
    private ImageView ivreturn_icon;
    private EditText third_et_comment;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    image1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final Dialog dialog = new Dialog(getApplicationContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                            ImageView imgView = getView();
                            dialog.setContentView(imgView);
                            dialog.show();
                            imgView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                }
                            });
                        }
                    });
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_information);

        findView();

        initData();

        Message msg=myHandler.obtainMessage();
        msg.what=1;
        myHandler.sendMessage(msg);

        ivreturn_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        third_et_comment.setBackground(getResources().getDrawable(R.drawable.btn_bottom_call));
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


    private void findView() {
        image1 = findViewById(R.id.third_iv_bigPicture1);
        image2 = findViewById(R.id.third_iv_bigPicture2);
        image3 = findViewById(R.id.third_iv_bigPicture3);
        third_et_comment=findViewById(R.id.third_et_comment);
        ivreturn_icon=findViewById(R.id.third_iv_return_icon);
        third_informationCommentListView=findViewById(R.id.third_lv_OneInformation);
        third_informationCommentScrollView=findViewById(R.id.third_sv_OneInformation);
    }

    public ImageView getView() {
        ImageView imgView = new ImageView(this);
        imgView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        @SuppressLint("ResourceType") InputStream is = getResources().openRawResource(R.drawable.test_picture);
        Drawable drawable = BitmapDrawable.createFromStream(is, null);
        imgView.setImageDrawable(drawable);
        return imgView;
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
