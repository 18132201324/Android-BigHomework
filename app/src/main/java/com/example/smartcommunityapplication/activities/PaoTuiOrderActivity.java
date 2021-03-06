package com.example.smartcommunityapplication.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.adapters.RunOrderAdapter;
import com.example.smartcommunityapplication.entities.OrderRun;
import com.example.smartcommunityapplication.entities.Runorder;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class PaoTuiOrderActivity extends AppCompatActivity {
    private View view1, view2, view3;
    private ViewPager viewPager;  //对应的viewPager
    private List<View> viewList;//view数组
    private TabLayout tabLayout;
    private ImageView back;
    private List<String> titles = new ArrayList<> ();
    private List<OrderRun> RunOrderDataList = new ArrayList<> ();
    private ListView yiFaBuList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_pao_tui_order);
        getViews();
        LayoutInflater inflater=getLayoutInflater();
        view1 = inflater.inflate(R.layout.paotui_layout1, null);
        view2 = inflater.inflate(R.layout.paotui_layout2,null);
        view3 = inflater.inflate(R.layout.paotui_layout3, null);
        yiFaBuList = (ListView) view1.findViewById (R.id.paoTuiLayout1_yiFaBu);
        viewList = new ArrayList<View> ();// 将要分页显示的View装入数组中
        back.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        });
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        for (int i=0;i<5;i++){
            OrderRun orderRun = new OrderRun ();
            orderRun.setId (""+i);
            orderRun.setTitle ("title"+i);
            orderRun.setType ("type"+i);
            orderRun.setTime_start ("strat"+i);
            orderRun.setTime_stop ("stop"+i);
            orderRun.setMoney (i);
            RunOrderDataList.add (orderRun);
        }
        RunOrderAdapter runOrderAdapter = new RunOrderAdapter (this,RunOrderDataList,R.layout.order_run_list_item);
        yiFaBuList.setAdapter (runOrderAdapter);
        titles.add ("已发布");
        titles.add ("未消费");
        titles.add ("待评价");
        tabLayout.setupWithViewPager(viewPager);

        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size ();
            }


            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }


            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList.get(position));
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        };
        viewPager.setAdapter(pagerAdapter);
    }

    private void getViews() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = findViewById (R.id.about_my_tab);
        back = findViewById (R.id.shangChengOrder_back);
    }
}