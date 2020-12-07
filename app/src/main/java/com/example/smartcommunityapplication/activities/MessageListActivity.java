package com.example.smartcommunityapplication.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.adapters.MessageListAdapter;
import com.example.smartcommunityapplication.adapters.NoticeSendAdapter;
import com.example.smartcommunityapplication.entities.Notice;
import com.example.smartcommunityapplication.entities.OrderRun;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class MessageListActivity extends AppCompatActivity {
    private View view1, view2, view3;
    private ViewPager viewPager;  //对应的viewPager
    private List<View> viewList;//view数组
    private TabLayout tabLayout;
    private ImageView back;
    private ImageView publish;
    private List<String> titles = new ArrayList<> ();
    private List<Notice> allNotices = new ArrayList<> ();
    private List<Notice> weiDuNotices = new ArrayList<> ();
    private List<Notice> yiFaBuNotices = new ArrayList<> ();
    private ListView allList;
    private ListView weiDu;
    private ListView yiFaChu;
    private SmartRefreshLayout refreshLayout1;
    private SmartRefreshLayout refreshLayout2;
    private SmartRefreshLayout refreshLayout3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_message_list);
        getViews ();
        LayoutInflater inflater=getLayoutInflater();
        view1 = inflater.inflate(R.layout.messagelayout1, null);
        view2 = inflater.inflate(R.layout.messagelayout2,null);
        view3 = inflater.inflate(R.layout.messagelayout3, null);
        allList = (ListView) view1.findViewById (R.id.messageLayout1_quanBu);
        weiDu = (ListView) view2.findViewById (R.id.messageLayout2_weiDu);
        yiFaChu = (ListView) view3.findViewById (R.id.messageLayout3_yiFaChu);
        refreshLayout1 = view1.findViewById (R.id.srl);
        refreshLayout1.setOnRefreshListener (new OnRefreshListener () {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //下拉刷新
                //结束刷新动画
                refreshLayout.finishRefresh ();
            }
        });
        refreshLayout2 = view2.findViewById (R.id.srl);
        refreshLayout2.setOnRefreshListener (new OnRefreshListener () {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //下拉刷新
                //结束刷新动画
                refreshLayout.finishRefresh ();
            }
        });
        refreshLayout3 = view3.findViewById (R.id.srl);
        refreshLayout3.setOnRefreshListener (new OnRefreshListener () {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //下拉刷新
                //结束刷新动画
                refreshLayout.finishRefresh ();
            }
        });
        viewList = new ArrayList<View> ();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        for (int i=0;i<3;i++){
            Notice notice = new Notice ();
            notice.setUserName ("张三"+i);
            notice.setTitle (i+"您的软件18级5-8班午报还没有提交呦，赶快去提交吧！");
            notice.setTime ("2020-12-0"+i);
            allNotices.add (notice);
        }
        for (int i=0;i<3;i++){
            Notice notice = new Notice ();
            notice.setUserName ("张三"+i);
            notice.setTitle (i+"您的软件18级5-8班午报还没有提交呦，赶快去提交吧！");
            notice.setTime ("2020-12-0"+i);
            weiDuNotices.add (notice);
        }

        for (int i=0;i<3;i++){
            Notice notice = new Notice ();
            notice.setUserName ("张三"+i);
            notice.setTitle (i+"您的软件18级5-8班午报还没有提交呦，赶快去提交吧！");
            notice.setTime ("2020-12-0"+i);
            yiFaBuNotices.add (notice);
        }

        allList.setAdapter (new MessageListAdapter (MessageListActivity.this,allNotices,R.layout.item_message_list_layout));
        allList.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity (new Intent (MessageListActivity.this,NoticeDetailsActivity.class));
            }
        });
        weiDu.setAdapter (new MessageListAdapter (MessageListActivity.this,weiDuNotices,R.layout.item_message_list_layout));
        weiDu.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity (new Intent (MessageListActivity.this,NoticeDetailsActivity.class));
            }
        });
        yiFaChu.setAdapter (new NoticeSendAdapter (MessageListActivity.this,yiFaBuNotices,R.layout.item_notice_send_layout));
        yiFaChu.setOnItemClickListener (new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity (new Intent (MessageListActivity.this,NoticeDetailsActivity.class));
            }
        });

        titles.add ("全部");
        titles.add ("未读");
        titles.add ("已发出");
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
        publish.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity (new Intent (MessageListActivity.this,PublishiNoticeActivity.class));
            }
        });
        back.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        });
    }
    private void getViews() {
        back = findViewById (R.id.notice_back);
        publish = findViewById (R.id.publish);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = findViewById (R.id.about_my_tab);
    }
}