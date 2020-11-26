package com.example.smartcommunityapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.activities.LoginActivity;
import com.example.smartcommunityapplication.activities.PhotoActivity;
import com.example.smartcommunityapplication.activities.ReleaseActivity;

public class MyselfPageFragment extends Fragment {
    private TextView textView;
    private TextView login;
    private ImageView imageView;
    private ImageView Issue;
    private final int LOGIN_REQUEST = 100;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //加载内容页面布局（将内容页面的XML布局文件转化成View类型对象）
        View view = inflater.inflate(R.layout.myselfpagefragment_layout, //内容页面的布局文件
                container,//根视图对象
                false);//false表示需要手动调用addView方法将view添加到contain方法
        Issue = view.findViewById(R.id.release);
        imageView = view.findViewById(R.id.touxiang);


        //登录跳转
        login = view.findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), LoginActivity.class);
                startActivityForResult(intent,LOGIN_REQUEST);
            }
        });

        //我的发布
        Issue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), PhotoActivity.class);
                startActivity(intent);
            }
        });


        //文字平移
        textView = view.findViewById(R.id.wenzi);
        String html = "据中国日报，当地时间6月23日，加拿大不列颠哥伦比亚省高等法院和<a href = 'https://baike.baidu.com/item/%E5%AD%9F%E6%99%9A%E8%88%9F/131792?fr=aladdin'>孟晚舟</a>引渡案的控、辩双方达成共识，对引渡案的全部日程进行确认，同意于8月17日恢复引渡听证会，对加拿大和美国当局提供的信息的证据可采纳性进行讨论；而关于美加是否滥用司法程序的辩论将于2021年2月16日开始。";
        CharSequence charSequence = Html.fromHtml(html);
        textView.setText(charSequence);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setSingleLine(true);
        textView.setSelected(true);
        textView.setFocusable(true);
        textView.setFocusableInTouchMode(true);
        return view;
    }

    //接受返回的响应数据，并且刷新界面
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //requestCode跳转时的请求码
        //resultCode返回响应的结果码
        //data返回响应的Intent对象
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==LOGIN_REQUEST && resultCode==1){
            //获得响应的数据
            String num = data.getStringExtra("number");
            //显示手机号
            login.setText(num);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.nvhai));
        }

    }
}
