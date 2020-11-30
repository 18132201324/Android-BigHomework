package com.example.smartcommunityapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.classes.LoginAccountMessage;
import com.example.smartcommunityapplication.classes.LoginState;
import com.mob.MobSDK;

import org.greenrobot.eventbus.EventBus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

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
                    LoginAccountMessage.Account = iphone.getText ().toString ();
                    LoginAccountMessage.sign = 1;
                    LoginState.State = 1;
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
