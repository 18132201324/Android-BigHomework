package com.example.smartcommunityapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.UnScrollListView;
import com.example.smartcommunityapplication.adapters.CommentAdapter;
import com.example.smartcommunityapplication.adapters.ShopAdapter;
import com.example.smartcommunityapplication.entities.Comment;
import com.example.smartcommunityapplication.entities.Second_shop;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class ShopPageFragment extends Fragment {
    private UnScrollListView mListView;
    private Button button;
    private ListView main_listview;
    private Context context;
    private List<Comment> dataSource1 = new ArrayList<>();
    private List<Second_shop> shops = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //加载内容页面布局（将内容页面的XML布局文件转化成View类型对象）
        View view = inflater.inflate(R.layout.shoppagefragment_layout, //内容页面的布局文件
                container,//根视图对象
                false);//false表示需要手动调用addView方法将view添加到contain方法
        //true表示不需要手动调用addView方法
        context = ShopPageFragment.this.getActivity();
        button = view.findViewById(R.id.btn_oppen);
        main_listview=view.findViewById(R.id.second_iv0);
        ShopAdapter shopAdapter=new ShopAdapter(ShopPageFragment.this.getActivity(),shops,R.layout.shoppagefragment_item_layout);
        main_listview.setAdapter(shopAdapter);
        initData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                View inflate = View.inflate(context, R.layout.two_fragment, null);
                View qq = inflate.findViewById(R.id.call);
                View wx = inflate.findViewById(R.id.message);
                View sina = inflate.findViewById(R.id.share);
                mListView = inflate.findViewById(R.id.iv1);
                mListView.setFocusable(false);
                CommentAdapter adapter = new CommentAdapter(ShopPageFragment.this.getActivity(), dataSource1, R.layout.listview_comment);
                mListView.setAdapter(adapter);
                bottomSheetDialog.setCancelable(false);
                bottomSheetDialog.setContentView(inflate);
                if (bottomSheetDialog.isShowing()) {
                    bottomSheetDialog.dismiss();
                } else {
                    bottomSheetDialog.show();
                    bottomSheetDialog.setCanceledOnTouchOutside(true);
                }

            }
        });

        return view;
    }

    private void initData() {
        Comment comment = new Comment("123", "456", 5, "55", "333");
        for (int i = 0; i < 10; i++) {
            dataSource1.add(comment);
        }
        Second_shop second_shop = new Second_shop("1","2","北国商城","12","13","123","13","66","312","66");
        for (int i=0;i<10;i++){
            shops.add(second_shop);
        }

    }


}