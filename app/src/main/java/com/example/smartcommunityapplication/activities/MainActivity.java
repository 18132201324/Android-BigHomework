package com.example.smartcommunityapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTabHost;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.fragments.HomePageFragment;
import com.example.smartcommunityapplication.fragments.InformationPageFragment;
import com.example.smartcommunityapplication.fragments.MyselfPageFragment;
import com.example.smartcommunityapplication.fragments.NoticePageFragment;
import com.example.smartcommunityapplication.fragments.ShopPageFragment;


import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private Map<String,TextView> textViewMap = new HashMap<>();
    private Map<String, ImageView> imgViewMap = new HashMap<>();
    private FragmentTabHost fragmentTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //获取FragmentTabHost的引用
        fragmentTabHost = findViewById(android.R.id.tabhost);
        //初始化
        fragmentTabHost.setup(this,
                getSupportFragmentManager(),//管理多个Fragment管理器
                android.R.id.tabcontent);//显示内容页面的控件id

        //创建内容TabSpec对象
        TabHost.TabSpec tab1 = fragmentTabHost.newTabSpec("first_tab")
                .setIndicator(getTabSpecView("first_tab","首页",getResources().getDrawable(R.drawable.home1)));

        //Class参数：类名.class,对象.getClass()
        fragmentTabHost.addTab(tab1,
                HomePageFragment.class,//FirstFragment类的字节码（Class）对象
                null);//传递数据时使用，不需要传递数据直接传null

        TabHost.TabSpec tab2 = fragmentTabHost.newTabSpec("second_tab")
                .setIndicator(getTabSpecView("second_tab","商城",getResources().getDrawable(R.drawable.shop1)));

        fragmentTabHost.addTab(tab2,
                ShopPageFragment.class,
                null);

        TabHost.TabSpec tab3 = fragmentTabHost.newTabSpec("third_tab")
                .setIndicator(getTabSpecView("third_tab","资讯",getResources().getDrawable(R.drawable.information1)));

        fragmentTabHost.addTab(tab3,
                InformationPageFragment.class,
                null);

        TabHost.TabSpec tab4 = fragmentTabHost.newTabSpec("forth_tab")
                .setIndicator(getTabSpecView("forth_tab","通知",getResources().getDrawable(R.drawable.chat1)));

        fragmentTabHost.addTab(tab4,
                NoticePageFragment.class,
                null);

        TabHost.TabSpec tab5 = fragmentTabHost.newTabSpec("fifth_tab")
                .setIndicator(getTabSpecView("fifth_tab","我的",getResources().getDrawable(R.drawable.mine1)));

        fragmentTabHost.addTab(tab5,
                MyselfPageFragment.class,
                null);


        //处理fragmentTabHost的选项切换事件
        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //修改图片和文字颜色
                switch(tabId){
                    case "first_tab":
                        textViewMap.get("first_tab").setTextColor(getResources().getColor(R.color.colo_blue_green));
                        imgViewMap.get("first_tab").setImageDrawable(getResources().getDrawable(R.drawable.home));
                        textViewMap.get("second_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("second_tab").setImageDrawable(getResources().getDrawable(R.drawable.shop1));
                        textViewMap.get("third_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("third_tab").setImageDrawable(getResources().getDrawable(R.drawable.information1));
                        textViewMap.get("forth_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("forth_tab").setImageDrawable(getResources().getDrawable(R.drawable.chat1));
                        textViewMap.get("fifth_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("fifth_tab").setImageDrawable(getResources().getDrawable(R.drawable.mine1));
                        break;
                    case "second_tab":
                        textViewMap.get("first_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("first_tab").setImageDrawable(getResources().getDrawable(R.drawable.home1));
                        textViewMap.get("second_tab").setTextColor(getResources().getColor(R.color.colo_blue_green));
                        imgViewMap.get("second_tab").setImageDrawable(getResources().getDrawable(R.drawable.shop));
                        textViewMap.get("third_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("third_tab").setImageDrawable(getResources().getDrawable(R.drawable.information1));
                        textViewMap.get("forth_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("forth_tab").setImageDrawable(getResources().getDrawable(R.drawable.chat1));
                        textViewMap.get("fifth_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("fifth_tab").setImageDrawable(getResources().getDrawable(R.drawable.mine1));
                        break;
                    case "third_tab":
                        textViewMap.get("first_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("first_tab").setImageDrawable(getResources().getDrawable(R.drawable.home1));
                        textViewMap.get("second_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("second_tab").setImageDrawable(getResources().getDrawable(R.drawable.shop1));
                        textViewMap.get("third_tab").setTextColor(getResources().getColor(R.color.colo_blue_green));
                        imgViewMap.get("third_tab").setImageDrawable(getResources().getDrawable(R.drawable.information));
                        textViewMap.get("forth_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("forth_tab").setImageDrawable(getResources().getDrawable(R.drawable.chat1));
                        textViewMap.get("fifth_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("fifth_tab").setImageDrawable(getResources().getDrawable(R.drawable.mine1));
                        break;
                    case "forth_tab":
                        textViewMap.get("first_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("first_tab").setImageDrawable(getResources().getDrawable(R.drawable.home1));
                        textViewMap.get("second_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("second_tab").setImageDrawable(getResources().getDrawable(R.drawable.shop1));
                        textViewMap.get("third_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("third_tab").setImageDrawable(getResources().getDrawable(R.drawable.information1));
                        textViewMap.get("forth_tab").setTextColor(getResources().getColor(R.color.colo_blue_green));
                        imgViewMap.get("forth_tab").setImageDrawable(getResources().getDrawable(R.drawable.chat));
                        textViewMap.get("fifth_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("fifth_tab").setImageDrawable(getResources().getDrawable(R.drawable.mine1));
                        break;
                    case "fifth_tab":
                        textViewMap.get("first_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("first_tab").setImageDrawable(getResources().getDrawable(R.drawable.home1));
                        textViewMap.get("second_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("second_tab").setImageDrawable(getResources().getDrawable(R.drawable.shop1));
                        textViewMap.get("third_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("third_tab").setImageDrawable(getResources().getDrawable(R.drawable.information1));
                        textViewMap.get("forth_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("forth_tab").setImageDrawable(getResources().getDrawable(R.drawable.chat1));
                        textViewMap.get("fifth_tab").setTextColor(getResources().getColor(R.color.colo_blue_green));
                        imgViewMap.get("fifth_tab").setImageDrawable(getResources().getDrawable(R.drawable.mine));
                        break;
                }
            }
        });

        //设置默认选中的标签页：参数是下标
        fragmentTabHost.setCurrentTab(0);
        //setTextColor不能直接传入color资源的id号
        textViewMap.get("first_tab").setTextColor(getResources().getColor(R.color.colo_blue_green));
        imgViewMap.get("first_tab").setImageDrawable(getResources().getDrawable(R.drawable.home));
    }



    public View getTabSpecView(String tag, String title, Drawable drawable){
        View view = getLayoutInflater().inflate(R.layout.tab_spec_layout,null);

        TextView tv_title = view.findViewById(R.id.title);
        ImageView iv_photo = view.findViewById(R.id.photo);
        //将ImageView对象存储到Map中
        textViewMap.put(tag,tv_title);
        imgViewMap.put(tag,iv_photo);

        TextView tvTitle = view.findViewById(R.id.title);
        ImageView ivPhoto = view.findViewById(R.id.photo);
        tvTitle.setText(title);
        ivPhoto.setImageDrawable(drawable);
        return view;
    }
}
