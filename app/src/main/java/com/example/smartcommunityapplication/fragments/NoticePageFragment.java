package com.example.smartcommunityapplication.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTabHost;

import com.example.smartcommunityapplication.R;

import java.util.HashMap;
import java.util.Map;

public class NoticePageFragment extends Fragment {
    private Map<String, TextView> textViewMap = new HashMap<>();
    private Map<String, ImageView> imgViewMap = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载内容页面布局（将内容页面的XML布局文件转化成View类型对象）
        View view = inflater.inflate(R.layout.noticepagefragment_layout, //内容页面的布局文件
                container,//根视图对象
                false);

        //获取FragmentTabHost的引用
        FragmentTabHost fragmentTabHost = view.findViewById(android.R.id.tabhost);
        //初始化
        fragmentTabHost.setup(getContext(),
                getChildFragmentManager(),//管理多个Fragment管理器
                android.R.id.tabcontent);//显示内容页面的控件id

        //创建内容TabSpec对象
        TabHost.TabSpec tab1 = fragmentTabHost.newTabSpec("first_tab")
                .setIndicator(getTabSpecView("first_tab","聊天",getResources().getDrawable(R.drawable.weixin1)));

        //Class参数：类名.class,对象.getClass()
        fragmentTabHost.addTab(tab1,
                ChatPageFragment.class,//FirstFragment类的字节码（Class）对象
                null);//传递数据时使用，不需要传递数据直接传null

        TabHost.TabSpec tab2 = fragmentTabHost.newTabSpec("second_tab")
                .setIndicator(getTabSpecView("second_tab","通讯录",getResources().getDrawable(R.drawable.information1)));

        fragmentTabHost.addTab(tab2,
                AddressListPageFragment.class,
                null);

        TabHost.TabSpec tab3 = fragmentTabHost.newTabSpec("third_tab")
                .setIndicator(getTabSpecView("third_tab","朋友圈",getResources().getDrawable(R.drawable.moment1)));

        fragmentTabHost.addTab(tab3,
                MomentPageFragment.class,
                null);

        //处理fragmentTabHost的选项切换事件
        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //修改图片和文字颜色
                switch(tabId){
                    case "first_tab":
                        textViewMap.get("first_tab").setTextColor(getResources().getColor(R.color.colorPrimary));
                        imgViewMap.get("first_tab").setImageDrawable(getResources().getDrawable(R.drawable.weixin));
                        textViewMap.get("second_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("second_tab").setImageDrawable(getResources().getDrawable(R.drawable.information1));
                        textViewMap.get("third_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("third_tab").setImageDrawable(getResources().getDrawable(R.drawable.moment1));
                        break;
                    case "second_tab":
                        textViewMap.get("first_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("first_tab").setImageDrawable(getResources().getDrawable(R.drawable.weixin1));
                        textViewMap.get("second_tab").setTextColor(getResources().getColor(R.color.colorPrimary));
                        imgViewMap.get("second_tab").setImageDrawable(getResources().getDrawable(R.drawable.information));
                        textViewMap.get("third_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("third_tab").setImageDrawable(getResources().getDrawable(R.drawable.moment1));
                        break;
                    case "third_tab":
                        textViewMap.get("first_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("first_tab").setImageDrawable(getResources().getDrawable(R.drawable.weixin1));
                        textViewMap.get("second_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("second_tab").setImageDrawable(getResources().getDrawable(R.drawable.information1));
                        textViewMap.get("third_tab").setTextColor(getResources().getColor(R.color.colorPrimary));
                        imgViewMap.get("third_tab").setImageDrawable(getResources().getDrawable(R.drawable.moment));
                        break;
                }
            }
        });

        //设置默认选中的标签页：参数是下标
        fragmentTabHost.setCurrentTab(0);
        //setTextColor不能直接传入color资源的id号
        textViewMap.get("first_tab").setTextColor(getResources().getColor(R.color.colorPrimary));
        imgViewMap.get("first_tab").setImageDrawable(getResources().getDrawable(R.drawable.weixin));
        return view;
    }

    public View getTabSpecView(String tag, String title,Drawable drawable){
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
