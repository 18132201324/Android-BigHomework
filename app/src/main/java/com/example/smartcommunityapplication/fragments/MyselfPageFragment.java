package com.example.smartcommunityapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.activities.ChangeMyselfpageActivity;
import com.example.smartcommunityapplication.activities.LoginActivity;
import com.example.smartcommunityapplication.activities.MerchantLoginActivity;
import com.example.smartcommunityapplication.activities.MySheQuFuWuActivity;
import com.example.smartcommunityapplication.activities.PaoTuiOrderActivity;
import com.example.smartcommunityapplication.activities.PhotoActivity;
import com.example.smartcommunityapplication.activities.ShangchengOrderActivity;
import com.example.smartcommunityapplication.activities.TuanGouOrderActivity;
import com.example.smartcommunityapplication.activities.WaiMaiOrderActivity;
import com.example.smartcommunityapplication.classes.LoginAccountMessage;
import com.example.smartcommunityapplication.classes.LoginState;
import com.example.smartcommunityapplication.entities.User;
import com.example.smartcommunityapplication.utils.ConfigUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyselfPageFragment extends Fragment {
    private TextView tv_balance;
    private TextView tv_score;
    private TextView tv_creditworthiness;
    private TextView textView;
    private TextView login;
    private ImageView touXinag;
    private RelativeLayout mySheQuFuWu;
    private RelativeLayout Issue;
    private RelativeLayout merchantCenter;
    private LinearLayout commodity;
    private LinearLayout tuangou;
    private LinearLayout paotui;
    private LinearLayout waimai;
    private final int LOGIN_REQUEST = 100;
    private OkHttpClient okHttpClient;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    String data = (String) msg.obj;
                    String jsonStr = data.substring(data.indexOf("{"));
                    Log.e("qwe",jsonStr);
                    LoginAccountMessage.user = new Gson().fromJson(jsonStr,User.class);

                    login.setText(LoginAccountMessage.user.getNick_name());

                    RequestOptions options =new RequestOptions()
                            .circleCrop();
                    Glide.with(getContext())
                            .load(ConfigUtil.BASE_URL+"imgs/"+LoginAccountMessage.user.getHead_photo())
                            .apply(options)
                            .into(touXinag);
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载内容页面布局（将内容页面的XML布局文件转化成View类型对象）
        View view = inflater.inflate(R.layout.myselfpagefragment_layout, //内容页面的布局文件
                container,//根视图对象
                false);//false表示需要手动调用addView方法将view添加到contain方法
        Issue = view.findViewById(R.id.mySelfpage_myRelease);
        touXinag = view.findViewById(R.id.touxiang);
        mySheQuFuWu = view.findViewById (R.id.mySelfpage_fuwu);
        merchantCenter = view.findViewById (R.id.merchantCenter);
        commodity = view.findViewById (R.id.mySelfpage_commodity);
        tuangou = view.findViewById (R.id.mySelfpage_tuangou);
        paotui = view.findViewById (R.id.mySelfpage_paoTui);
        waimai = view.findViewById (R.id.mySelfpage_waimai);
        tv_balance = view.findViewById (R.id.tv_balance);
        tv_score = view.findViewById (R.id.tv_score);
        tv_creditworthiness = view.findViewById (R.id.tv_creditworthiness);


        //1.创建OkHttpClient对象
        okHttpClient = new OkHttpClient();

        //登录跳转
        Log.e ("123",LoginState.State+"");
        login = view.findViewById(R.id.login);
        if (LoginState.State == 0 ){
            login.setText ("立即登录");
        }else if(LoginState.State == 1 ){
            login.setText (LoginAccountMessage.user.getPhone_number());
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), LoginActivity.class);
                startActivity (intent);
            }
        });

        touXinag.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ();
                intent.setClass (getActivity (), ChangeMyselfpageActivity.class);
                startActivity (intent);
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
        //我的社区服务
        mySheQuFuWu.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity (), MySheQuFuWuActivity.class);
                startActivity (intent);
            }
        });
        //商家中心
        merchantCenter.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity (), MerchantLoginActivity.class);
                startActivity (intent);
            }
        });
        //商品订单
        commodity.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity (), ShangchengOrderActivity.class);
                startActivity (intent);
            }
        });
        //团购订单
        tuangou.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity (), TuanGouOrderActivity.class);
                startActivity (intent);
            }
        });
        //跑腿订单
        paotui.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity (), PaoTuiOrderActivity.class);
                startActivity (intent);
            }
        });
        //外卖订单
        waimai.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getActivity (), WaiMaiOrderActivity.class);
                startActivity (intent);
            }
        });
        tv_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(LoginState.State+"",tv_balance.getText().toString().equals("- -")+"");
                if(LoginState.State==1&&tv_balance.getText().toString().equals("- -")){
                    tv_balance.setText(LoginAccountMessage.user.getBalance()+"");
                }else{
                    tv_balance.setText("- -");
                }
            }
        });
        tv_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LoginState.State==1&&tv_score.getText().toString().equals("- -")){
                    tv_score.setText(LoginAccountMessage.user.getScore()+"");
                }else{
                    tv_score.setText("- -");
                }
            }
        });
        tv_creditworthiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LoginState.State==1&&tv_creditworthiness.getText().toString().equals("- -")){
                    tv_creditworthiness.setText(LoginAccountMessage.user.getCreditworthiness()+"");
                }else{
                    tv_creditworthiness.setText("- -");
                }
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
        }

    }

    //异步使用Post请求
    public void getPostAsync(){
        //2.创建RequestBody(请求体)对象
        RequestBody requestBody = RequestBody.create (MediaType.parse
                ("text/plain;charset=utf-8"),new Gson().toJson (new User("",LoginAccountMessage.user.getPhone_number(),"","",LoginAccountMessage.user.getNick_name(),"","","",0,"","",0,0)));
        //3.创建请求对象
        Request request = new Request.Builder ()
                .post (requestBody)
                .url (ConfigUtil.BASE_URL+"LoginServlet")
                .build();
        //4.创建Call对象，发送请求，并接受响应
        final Call call = okHttpClient.newCall (request);
        //异步网络请求（无需创建子线程）
        call.enqueue (new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败时回调
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //请求成功时回调
                Message message = new Message();
                message.what=1;
                message.obj=response.body ().string ();
                handler.sendMessage(message);
            }
        });
    }

    @Subscribe
    public void dorRefresh(String event){
        LoginState.State = 1;
        getPostAsync();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this))//加上判断
            EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onResume() {
        Log.e ("124",LoginState.State+"");
        super.onResume ();
        if (LoginState.State == 0 ){
            login.setText ("立即登录");
            touXinag.setImageDrawable(getResources().getDrawable(R.drawable.yuan));
        }else if(LoginState.State == 1 ){
            login.setText (LoginAccountMessage.user.getNick_name());
            RequestOptions options =new RequestOptions()
                    .circleCrop();
            Glide.with(getContext())
                    .load(ConfigUtil.BASE_URL+"imgs/"+LoginAccountMessage.user.getHead_photo())
                    .apply(options)
                    .into(touXinag);
        }
    }
}
