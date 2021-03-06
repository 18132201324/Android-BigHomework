package com.example.smartcommunityapplication.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.activities.ChattingHomeActivity;
import com.example.smartcommunityapplication.adapters.ChattingPageListAdapter;
import com.example.smartcommunityapplication.entities.ChattingPageItem;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zaaach.toprightmenu.MenuItem;
import com.zaaach.toprightmenu.TopRightMenu;

import java.util.ArrayList;
import java.util.List;

public class ChatPageFragment extends Fragment {
    private List<ChattingPageItem> chattingPageItems;
    private ListView chatListView;
    private ChattingPageListAdapter adapter;
    private ImageButton forth_btn_extend;
    private SmartRefreshLayout refreshLayout;
    private final int REFRESH = 100;
    private Handler myHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case REFRESH:
                    ChattingPageItem chattingPageItem = new ChattingPageItem("","旅游四人组","害","6月30日");
                    chattingPageItems.add(0,chattingPageItem);//修改数据源
                    adapter.notifyDataSetChanged();//刷新界面
                    //结束刷新动画
                    refreshLayout.finishRefresh();
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载内容页面布局（将内容页面的XML布局文件转化成View类型对象）
        View view = inflater.inflate(R.layout.chatpagefragment_layout, //内容页面的布局文件
                container,//根视图对象
                false);//false表示需要手动调用addView方法将view添加到contain方法
        //true表示不需要手动调用addView方法


        initData();

        //初始化ListView
        chatListView = view.findViewById(R.id.lv_chattingList);
        adapter = new ChattingPageListAdapter(getContext(),chattingPageItems,R.layout.chatpage_item_layout);
        chatListView.setAdapter(adapter);

        forth_btn_extend=view.findViewById(R.id.forth_btn_extend);
        forth_btn_extend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toprightmenu();
            }
        });
        //打开聊天详情
        chatListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ChattingHomeActivity.class);
                intent.putExtra("name",chattingPageItems.get(position).getName());
                startActivity(intent);
            }
        });
        
        //获取SmartRefreshLayout的引用
        refreshLayout = view.findViewById(R.id.chat);
        setListener();

        //设置刷新头和尾的样式
        refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));

        return view;
    }

    private void initData(){
        chattingPageItems = new ArrayList<>();
        //准备数据源
        for (int i=0;i<10;i++){
            ChattingPageItem chattingPageItem = new ChattingPageItem("","联系人"+i,"最近聊天内容"+i,"时间"+i);
            chattingPageItems.add(chattingPageItem);
        }
    }

    private void setListener(){
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //下拉刷新时调用
                new Thread(){
                    @Override
                    public void run() {
                        //通过网络访问服务器端
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //使用Handler发送一个Message
                        Message message = new Message();
                        message.what = REFRESH;
                        myHandler.sendMessage(message);
                    }
                }.start();
                //刷新界面

            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //上拉加载更多时调用
                new LoadMoreTask().execute();
            }
        });
    }

    private class LoadMoreTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            //刷新界面
            ChattingPageItem chattingPageItem = new ChattingPageItem("","旅游四人组","害","6月30日");
            chattingPageItems.add(0,chattingPageItem);
            adapter.notifyDataSetChanged();
            //结束加载更多的数据
            refreshLayout.finishLoadMore();
            super.onPostExecute(o);
        }
    }
    private void toprightmenu(){
        TopRightMenu topRightMenu = new TopRightMenu(getActivity());

        topRightMenu
                .setHeight(800)     //默认高度480
                .setWidth(500)      //默认宽度wrap_content
                .showIcon(true)     //显示菜单图标，默认为true
                .dimBackground(true)        //背景变暗，默认为true
                .needAnimationStyle(true)   //显示动画，默认为true
                .setAnimationStyle(R.style.TRM_ANIM_STYLE)
                .addMenuItem(new MenuItem(R.drawable.chuangjianqunzu1, "创建群聊"))
                .addMenuItem(new MenuItem(R.drawable.saoyisao1, "扫一扫"))
                .addMenuItem(new MenuItem(R.drawable.tianjiahaoyou1, "加好友/群"))
                .addMenuItem(new MenuItem(R.drawable.erweima, "我的二维码"))
                .addMenuItem(new MenuItem(R.drawable.shoufukuan1, "收付款"))
//                        .addMenuItem(new MenuItem(R.mipmap.coach_icon, "面对面建群"))
                .setOnMenuItemClickListener(new TopRightMenu.OnMenuItemClickListener() {
                    @Override
                    public void onMenuItemClick(int position) {
                        Toast.makeText(getActivity(), "点击菜单:" + position, Toast.LENGTH_SHORT).show();
                    }
                })
                .showAsDropDown(forth_btn_extend, -225, 0);    //带偏移量
    }
}
