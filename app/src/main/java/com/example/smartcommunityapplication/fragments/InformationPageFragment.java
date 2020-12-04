package com.example.smartcommunityapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartcommunityapplication.MyScrollView;
import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.UnScrollListView;
import com.example.smartcommunityapplication.activities.MainActivity;
import com.example.smartcommunityapplication.activities.OneInformationActivity;
import com.example.smartcommunityapplication.adapters.Third_informationAdapter;
import com.example.smartcommunityapplication.entities.Third_information;
import com.zaaach.toprightmenu.MenuItem;
import com.zaaach.toprightmenu.TopRightMenu;

import java.util.ArrayList;
import java.util.List;

public class InformationPageFragment extends Fragment {
    public List<Third_information> thirdinformations;//一个全局的链表
    private UnScrollListView third_informationListView;
    private MyScrollView third_informationScrollView;
    private Third_informationAdapter third_informationAdapter;
    private ImageView iv_moreIcon;
    private TextView Text1;
    private LinearLayout third_ll_information1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //加载内容页面布局（将内容页面的XML布局文件转化成View类型对象）
        View view = inflater.inflate(R.layout.informationpagefragment_layout, //内容页面的布局文件
                container,//根视图对象
                false);//false表示需要手动调用addView方法将view添加到contain方法
        //true表示不需要手动调用addView方法
        initData();

        third_informationListView=view.findViewById(R.id.third_lv_information);
        third_informationScrollView=view.findViewById(R.id.third_sv_information);
        iv_moreIcon=view.findViewById(R.id.third_iv_moreIcon);
        Text1=view.findViewById(R.id.third_tv_text1);
        third_ll_information1=view.findViewById(R.id.third_ll_information1);
        third_ll_information1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),
                        OneInformationActivity.class);
                startActivity(intent);
            }
        });

        iv_moreIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toprightmenu();
            }
        });



        third_informationAdapter=new Third_informationAdapter(view.getContext(),thirdinformations,R.layout.informationpage_item);
        third_informationListView.setAdapter(third_informationAdapter);
        //处理ListView每一项的点击事件
        third_informationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), OneInformationActivity.class);
                intent.putExtra("title", thirdinformations.get(position).getTitle());
                intent.putExtra("main", thirdinformations.get(position).getMain());

                startActivity(intent);
            }
        });
        return view;
    }

    private void initData() {
        thirdinformations = new ArrayList<>();
        Third_information information=new Third_information();
        for (int i=0;i<10;i++){
            information.setMain("     测试信息:我时常想起过去。独自一人为了梦想拼搏的我，多么想拥有一些陪伴、鼓励和榜样。上篇说了3件法师神话装备得到了很多人的认可，这次把剩余的三件神话也一起说了。");
            information.setTitle("");
            thirdinformations.add(information);
        }

    }
    private void toprightmenu(){
        TopRightMenu topRightMenu = new TopRightMenu(getActivity());

        topRightMenu
                .setHeight(630)     //默认高度480
                .setWidth(500)      //默认宽度wrap_content
                .showIcon(true)     //显示菜单图标，默认为true
                .dimBackground(true)        //背景变暗，默认为true
                .needAnimationStyle(true)   //显示动画，默认为true
                .setAnimationStyle(R.style.TRM_ANIM_STYLE)
                .addMenuItem(new MenuItem(R.drawable.dianzan, "我的点赞"))
                .addMenuItem(new MenuItem(R.drawable.pinglun, "我的评论"))
                .addMenuItem(new MenuItem(R.drawable.zhuanfa, "我的转发"))
                .addMenuItem(new MenuItem(R.drawable.gerenzhongxin2, "个人中心"))
//                        .addMenuItem(new MenuItem(R.mipmap.coach_icon, "面对面建群"))
                .setOnMenuItemClickListener(new TopRightMenu.OnMenuItemClickListener() {
                    @Override
                    public void onMenuItemClick(int position) {
                        Toast.makeText(getActivity(), "点击菜单:" + position, Toast.LENGTH_SHORT).show();
                    }
                })
                .showAsDropDown(iv_moreIcon, -225, 0);    //带偏移量
    }

}
