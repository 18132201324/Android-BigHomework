package com.example.smartcommunityapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.activities.LocationTagsTableActivity;
import com.example.smartcommunityapplication.activities.LuckyActivity;
import com.example.smartcommunityapplication.activities.RunOrderListActivity;
import com.example.smartcommunityapplication.activities.ServiceActivity;
import com.example.smartcommunityapplication.activities.TagTableActivity;
import com.example.smartcommunityapplication.activities.TimeActivity;
import com.example.smartcommunityapplication.adapters.mBannerAdapter;
import com.example.smartcommunityapplication.adapters.mTextBannerAdapter;
import com.example.smartcommunityapplication.adapters.myAdapter;
import com.example.smartcommunityapplication.adapters.myRunOrderAdapter;
import com.example.smartcommunityapplication.entities.InfoBean;
import com.example.smartcommunityapplication.entities.RecyclerinfoBean;
import com.example.smartcommunityapplication.entities.RunorderinfoBean;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment {
    private View view;
    private TextView getMoreOrder;
    private ImageView getMoreLocation;
    private LinearLayout linearLayoutSearch;
    private RecyclerView mrecycler;
    private RecyclerView mrunoder;
    private Banner mbanner;
    private Banner mTextbanner;
    private TextView tvFirst_location;
    private List<InfoBean> Imgbannerdata = new ArrayList<>();
    private List<InfoBean> Textbannerdata = new ArrayList<>();
    private List<RecyclerinfoBean> recyclerdata = new ArrayList<>();
    private List<RunorderinfoBean> runorderdata = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载内容页面布局（将内容页面的XML布局文件转化成View类型对象）
        view = inflater.inflate(R.layout.homepagefragment_layout, //内容页面的布局文件
                container,//根视图对象
                false);//false表示需要手动调用addView方法将view添加到contain方法
        //true表示不需要手动调用addView方法
            Imgbannerdata = new ArrayList<>();
            Textbannerdata = new ArrayList<>();
            recyclerdata = new ArrayList<>();
            runorderdata = new ArrayList<>();
            initDate();
            getMoreLocation = view.findViewById(R.id.first_moreLocation);
            linearLayoutSearch = view.findViewById(R.id.first_search);
            mTextbanner = view.findViewById(R.id.first_textbanner);
            mbanner = view.findViewById(R.id.banner);
            tvFirst_location = view.findViewById(R.id.first_location);
            //设置mbanner设配器
            mbanner.setAdapter(new mBannerAdapter(getActivity(),Imgbannerdata));
            mTextbanner.setAdapter(new mTextBannerAdapter(getActivity(),Textbannerdata));
            //是否允许自动轮播
            mbanner.isAutoLoop(true);
            mbanner.setLoopTime(3000);
            mTextbanner.isAutoLoop(true);
            mTextbanner.setLoopTime(6000);
            //设置指示器， CircleIndicator为已经定义好的类，直接用就好
            mbanner.setIndicator(new CircleIndicator(getContext()));
//            mbanner.setBannerGalleryEffect(20,20,10);
            //开始轮播
            mbanner.start();
            mTextbanner.start();
            mrunoder = view.findViewById(R.id.first_recyclerrunOrder);
            mrecycler = view.findViewById(R.id.recycler_test);
            //设置适配器
            mrunoder.setAdapter(new myRunOrderAdapter(getActivity(),runorderdata));
            mrecycler.setAdapter(new myAdapter(getActivity(),recyclerdata));
            //设置样式 ,有如下:
            // LinearLayoutManager(Context context, @RecyclerView.Orientation int orientation,boolean reverseLayout)
            //GridLayoutManager(Context context, int spanCount)
            //StaggeredGridLayoutManager(int spanCount, int orientation)
            mrecycler.setLayoutManager(new GridLayoutManager(getActivity(),5));
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            mrunoder.setLayoutManager(layoutManager);
            getMoreOrder = view.findViewById(R.id.first_getMoreOrder);
            getMoreOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), RunOrderListActivity.class);
                    startActivity(intent);
                }
            });
            linearLayoutSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), TagTableActivity.class);
                        startActivity(intent);
                }
            });
            getMoreLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LocationTagsTableActivity.class);
                    startActivity(intent);
                }
            });
        return view;

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

    @Subscribe
    public void handleEvent(String event){
        String GongNeng = event;
        switch (GongNeng){
            case "限时兑换":
                Intent intent2 = new Intent();
                intent2.setClass(getActivity(), TimeActivity.class);
                startActivity(intent2);
                break;
            case "积分抽奖":
                Intent intent1 = new Intent();
                intent1.setClass(getActivity(), LuckyActivity.class);
                startActivity(intent1);
                break;
            case "设备维修":
                Intent intent = new Intent();
                intent.setClass(getActivity(),ServiceActivity.class);
                startActivity(intent);
                break;
            case "东城区":
                tvFirst_location.setText("东城区");
                break;
        }
    }
    private void initDate() {
        Imgbannerdata.add(new InfoBean(R.drawable.a));
        Imgbannerdata.add(new InfoBean(R.drawable.b));
        Imgbannerdata.add(new InfoBean(R.drawable.c));
        Imgbannerdata.add(new InfoBean(R.drawable.d));
        Textbannerdata.add(new InfoBean("招商公告"));
        Textbannerdata.add(new InfoBean("外卖配送"));
        Textbannerdata.add(new InfoBean("社区管理"));
        recyclerdata.add(new RecyclerinfoBean(R.drawable.ditu,"社区服务"));
        recyclerdata.add(new RecyclerinfoBean(R.drawable.fuwu,"上门维修"));
        recyclerdata.add(new RecyclerinfoBean(R.drawable.shebei,"积分抽奖"));
        recyclerdata.add(new RecyclerinfoBean(R.drawable.weixiu,"设备维修"));
        recyclerdata.add(new RecyclerinfoBean(R.drawable.anzhuang,"设备回收"));
        recyclerdata.add(new RecyclerinfoBean(R.drawable.xiaoxi,"限时兑换"));
        recyclerdata.add(new RecyclerinfoBean(R.drawable.kehu,"跑腿代购"));
        recyclerdata.add(new RecyclerinfoBean(R.drawable.tongji,"商家服务"));
        recyclerdata.add(new RecyclerinfoBean(R.drawable.qiyong,"同区信息"));
        recyclerdata.add(new RecyclerinfoBean(R.drawable.more,"全部"));
        runorderdata.add(new RunorderinfoBean(R.drawable.f,"2020-11-15 12:00","有偿接孩子放学","10"));
        runorderdata.add(new RunorderinfoBean(R.drawable.g,"2020-11-13 15:00","有偿批改作业","20"));
        runorderdata.add(new RunorderinfoBean(R.drawable.shequrun,"2020-11-10 6:00","兴趣社区约跑","0"));
        runorderdata.add(new RunorderinfoBean(R.drawable.shequread,"2020-11-9 15:00","社区兴趣读书","0"));
    }
}
