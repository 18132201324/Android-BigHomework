package com.example.smartcommunityapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.util.Util;
import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.classes.LoginAccountMessage;
import com.example.smartcommunityapplication.classes.LoginState;
import com.example.smartcommunityapplication.entities.User;
import com.example.smartcommunityapplication.fragments.MyselfPageFragment;
import com.mob.MobSDK;


import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.OkHttpClient;

import static com.tencent.connect.common.Constants.PACKAGE_QQ;

public class LoginActivity extends AppCompatActivity {
    private EditText iphone;
    private EditText password;
    private EditText yanzheng;
    private TextView loginByyanZheng;
    private TextView loginBymiMa;
    private Button login;
    private Button getYanZheng;
    private EditText number;
    private String phone_number;
    private String cord_number;
    private boolean flag=true;
    public EventHandler eh; //事件接收器
    private TimeCount mTimeCount;//计时器
    private Tencent mTencent;
    private IUiListener listener;
    private JSONObject json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("登录");
        getViews();
        initEvent();
        MobSDK.init(this);

        eh = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                Message msg=new Message();
                msg.arg1=event;
                msg.arg2=result;
                msg.obj=data;
                handler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh);


        //QQ第三方登录
        mTencent = Tencent.createInstance("1111183393",getApplicationContext());//将123123123改为自己的AppID
        ImageView QQlogin = (ImageView) findViewById(R.id.login_qqLogin);
        QQlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注意：此段非必要，如果手机未安装应用则会跳转网页进行授权
                if (!hasApp(LoginActivity.this, PACKAGE_QQ)) {
                    Toast.makeText(LoginActivity.this, "未安装QQ应用",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                //如果session无效，就开始做登录操作
                if (!mTencent.isSessionValid()) {
                    loginQQ();
                }
            }
        });
    }

    /**
     * 回调必不可少,官方文档未说明
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //腾讯QQ回调
        Tencent.onActivityResultData(requestCode, resultCode, data, listener);
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_LOGIN) {
                Tencent.handleResultData(data, listener);
            }
        }
        Log.e ("——————————————————————","onActivityResult");
        finish ();
    }
    private void loginQQ() {
        listener = new IUiListener() {
            @Override
            public void onComplete(Object object) {
                Log.e("TAG", "登录成功: " + object.toString() );
                JSONObject jsonObject = (JSONObject) object;
                try {
                    //得到token、expires、openId等参数
                    String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
                    String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
                    String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
                    mTencent.setAccessToken(token, expires);
                    mTencent.setOpenId(openId);
                    Log.e("TAG", "token: " + token);
                    Log.e("TAG", "expires: " + expires);
                    Log.e("TAG", "openId: " + openId);
                    //获取个人信息
                    getQQinfo();
                } catch (Exception e) {
                }
            }
            @Override
            public void onError(UiError uiError) {
                //登录失败
                Log.e("TAG", "登录失败" + uiError.errorDetail);
                Log.e("TAG", "登录失败" + uiError.errorMessage);
                Log.e("TAG", "登录失败" + uiError.errorCode + "");
            }
            @Override
            public void onCancel() {
                //登录取消
                Log.e("TAG", "登录取消");
            }
            @Override
            public void onWarning(int i) {

            }
        };
        //context上下文、第二个参数SCOPO 是一个String类型的字符串，表示一些权限
        //应用需要获得权限，由“,”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
        //第三个参数事件监听器
        mTencent.login(this, "all", listener);
        //注销登录
        //mTencent.logout(this);
    }


    private void getQQinfo() {
        QQToken qqToken = mTencent.getQQToken();
        UserInfo info = new UserInfo(getApplicationContext(), qqToken);
        info.getUserInfo(new IUiListener() {
            @Override
            public void onComplete(Object obj) {
                //用户信息获取到了
                Log.e("----TAG----", "个人信息：" + obj.toString());
                json = (JSONObject)obj;
                try {
                    Log.e ("——————————————————————","执行getQQinfo");
                    String nickname = json.getString ("nickname");
                    Log.e ("------",nickname);
                    LoginAccountMessage.user = new User();
                    LoginAccountMessage.user.setNick_name(nickname);
                    EventBus.getDefault ().post ("");
                } catch (JSONException e) {
                    e.printStackTrace ();
                }
            }

            @Override
            public void onError(UiError uiError) {
                Log.v("UserInfo","onError");
            }

            @Override
            public void onCancel() {
                Log.v("UserInfo","onCancel");
            }

            @Override
            public void onWarning(int i) {

            }
        });
    }


    /**
     * true 安装了相应包名的app
     */
    private boolean hasApp(Context context, String packName) {
        boolean is = false;
        List<PackageInfo> packages = context.getPackageManager()
                .getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            String packageName = packageInfo.packageName;
            if (packageName.equals(packName)) {
                is = true;
            }
        }
        return is;
    }

    /**
     * 使用Handler来分发Message对象到主线程中，处理事件
     */
    Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event=msg.arg1;
            int result=msg.arg2;
            Object data=msg.obj;
            if(result==SMSSDK.RESULT_COMPLETE)
            {
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Toast.makeText(getApplicationContext(), "验证码输入正确",
                            Toast.LENGTH_LONG).show();
                    LoginAccountMessage.user = new User();
                    LoginAccountMessage.user.setPhone_number(iphone.getText ().toString ());
                    LoginAccountMessage.user.setNick_name(iphone.getText ().toString ());
                    EventBus.getDefault ().post ("");
                    finish ();
                }
            }
            else
            {
                if(flag)
                {
                    getYanZheng.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"验证码获取失败请重新获取", Toast.LENGTH_LONG).show();
                    getYanZheng.requestFocus();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"验证码输入错误", Toast.LENGTH_LONG).show();
                }
            }

        }

    };

    private void initEvent() {
        mTimeCount = new TimeCount(60000, 1000);
    }

    private void getViews() {
        iphone = findViewById (R.id.login_iphone);
        loginByyanZheng = findViewById (R.id.login_yanZheng);
        loginBymiMa = findViewById (R.id.login_miMa);
        password = findViewById (R.id.login_etPassword);
        yanzheng = findViewById (R.id.login_etYanZheng);
        login = findViewById (R.id.login_login);
        getYanZheng = findViewById (R.id.login_getYanZheng);
    }

    public void onClicked(View view) {
        switch (view.getId ()){
            case R.id.login_yanZheng:
                loginByyanZheng.setVisibility (View.GONE);
                loginBymiMa.setVisibility (View.VISIBLE);
                getYanZheng.setVisibility (View.VISIBLE);
                password.setVisibility (View.GONE);
                yanzheng.setVisibility (View.VISIBLE);
                break;
            case R.id.login_miMa:
                loginByyanZheng.setVisibility (View.VISIBLE);
                loginBymiMa.setVisibility (View.GONE);
                getYanZheng.setVisibility (View.GONE);
                password.setVisibility (View.VISIBLE);
                yanzheng.setVisibility (View.GONE);
                break;
            case R.id.login_getYanZheng:
                //SMSSDK.getSupportedCountries();//获取短信目前支持的国家列表
                if (!iphone.getText ().toString ().trim ().equals ("")){
                    if (checkTel(iphone.getText().toString().trim())) {
                        SMSSDK.getVerificationCode("+86",iphone.getText().toString());//获取验证码
                        yanzheng.requestFocus ();
                        mTimeCount.start();
                    }else {
                        Toast.makeText(LoginActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_back:
                finish ();
                break;
            case R.id.login_login:
                //获得用户输入的验证码
                    if(judCord())
                        SMSSDK.submitVerificationCode("86",phone_number,cord_number);
                    flag=false;
                break;

        }

    }

    /**
     * 正则匹配手机号码
     * @param tel
     * @return
     */
    public boolean checkTel(String tel){
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
        Matcher matcher = p.matcher(tel);
        return matcher.matches();
    }

    private boolean judPhone()
    {
        if(TextUtils.isEmpty(iphone.getText().toString().trim()))
        {
            Toast.makeText(LoginActivity.this,"请输入您的电话号码",Toast.LENGTH_LONG).show();
            iphone.requestFocus();
            return false;
        }
        else if(iphone.getText().toString().trim().length()!=11)
        {
            Toast.makeText(LoginActivity.this,"您的电话号码位数不正确",Toast.LENGTH_LONG).show();
            iphone.requestFocus();
            return false;
        }
        else
        {
            phone_number=iphone.getText().toString().trim();
            String num="[1][358]\\d{9}";
            if(phone_number.matches(num))
                return true;
            else
            {
                Toast.makeText(LoginActivity.this,"请输入正确的手机号码",Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }
    private boolean judCord()
    {
        judPhone();
        if(TextUtils.isEmpty(yanzheng.getText().toString().trim()))
        {
            Toast.makeText(LoginActivity.this,"请输入您的验证码",Toast.LENGTH_LONG).show();
            yanzheng.requestFocus();
            return false;
        }
        else if(yanzheng.getText().toString().trim().length()!=6)
        {
            Toast.makeText(LoginActivity.this,"您的验证码位数不正确",Toast.LENGTH_LONG).show();
            yanzheng.requestFocus();

            return false;
        }
        else
        {
            cord_number=yanzheng.getText().toString().trim();
            return true;
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }

    /**
     * 计时器
     */
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super (millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            getYanZheng.setClickable (false);
            getYanZheng.setText (l / 1000 + "秒后重新获取");
        }

        @Override
        public void onFinish() {
            getYanZheng.setClickable (true);
            getYanZheng.setText ("获取验证码");
        }

    }
}
