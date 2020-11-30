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
    private List<Second_shop> shops = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //加载内容页面布局（将内容页面的XML布局文件转化成View类型对象）
        View view = inflater.inflate(R.layout.shoppagefragment_layout, //内容页面的布局文件
                container,//根视图对象
                false);//false表示需要手动调用addView方法将view添加到contain方法
        //true表示不需要手动调用addView方法
        main_listview = view.findViewById(R.id.second_iv0);
        ShopAdapter shopAdapter = new ShopAdapter(ShopPageFragment.this.getActivity(), shops, R.layout.shoppagefragment_item_layout);
        main_listview.setAdapter(shopAdapter);
        initData();//获取数据源
        main_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ShopPageFragment.this.getActivity(), BaiMapActivity.class);
                intent.putExtra("shop", shops.get(i));
                startActivity(intent);
            }
        });

        return view;
    }
    private void initData() {
        for (int i = 0; i < 10; i++) {
            Second_shop second_shop = new Second_shop(i + "", "2", "北国商城", "12", "13", "123", "13", "66", "312", "66");
            shops.add(second_shop);
        }

    }


}