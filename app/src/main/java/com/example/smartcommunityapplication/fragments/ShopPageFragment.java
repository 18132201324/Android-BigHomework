package com.example.smartcommunityapplication.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.UnScrollListView;
import com.example.smartcommunityapplication.activities.BaiMapActivity;
import com.example.smartcommunityapplication.adapters.CommentAdapter;
import com.example.smartcommunityapplication.adapters.ShopAdapter;
import com.example.smartcommunityapplication.entities.Comment;
import com.example.smartcommunityapplication.entities.Second_shop;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class ShopPageFragment extends Fragment {
    private ListView main_listview;
    private List<Second_shop> shops ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //加载内容页面布局（将内容页面的XML布局文件转化成View类型对象）
        View view = inflater.inflate(R.layout.shoppagefragment_layout, //内容页面的布局文件
                container,//根视图对象
                false);//false表示需要手动调用addView方法将view添加到contain方法
        //true表示不需要手动调用addView方法

        initData();//获取数据源
        main_listview = view.findViewById(R.id.second_iv0);
        ShopAdapter shopAdapter = new ShopAdapter(ShopPageFragment.this.getActivity(), shops, R.layout.shoppagefragment_item_layout);
        main_listview.setAdapter(shopAdapter);

        main_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ShopPageFragment.this.getActivity(), BaiMapActivity.class);
                intent.putExtra("shop", shops.get(i));
                intent.putExtra("position", i);
                startActivity(intent);
            }
        });
        return view;
    }
    private void initData() {
        shops= new ArrayList<>();

        Second_shop second_shop = new Second_shop(  "1", "beiguo1.jpg", "北国商城", "114.516653", "38.048554", "综合广场", "15203023480", "中国河北省石家庄市桥西区中山东路188号", "beiguo2.jpg", "beiguo3.jpg");
        Second_shop second_shop1 = new Second_shop(  "2", "wanda1.jpg", "万达广场", "114.545285", "38.024235", "综合商业圈", "15303023488", "中国河北省石家庄市裕华区建华南大街136号万达广场", "wanda2.jpg", "wanda3.jpg");
        Second_shop second_shop2 = new Second_shop(  "2", "huaite1.jpg", "怀特广场", "114.525996", "38.022734", "综合商业圈", "15303023488", "中国河北省石家庄市裕华区槐安东路105号", "huaite2.jpg", "huaite3.jpg");
        shops.add(second_shop);
        shops.add(second_shop1);
        shops.add(second_shop2);


    }


}