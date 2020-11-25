package com.example.smartcommunityapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.adapters.AddressListAdapter;
import com.example.smartcommunityapplication.entities.AddressPeopleItem;
import com.example.smartcommunityapplication.utils.Pinyin4jUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddressListPageFragment extends Fragment {
    private RecyclerView mrecycler;
    private List<AddressPeopleItem> data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //加载内容页面布局（将内容页面的XML布局文件转化成View类型对象）
        View view = inflater.inflate(R.layout.addresslistpagefragment_layout, //内容页面的布局文件
                container,//根视图对象
                false);//false表示需要手动调用addView方法将view添加到contain方法
        //true表示不需要手动调用addView方法

        initDate();
        mrecycler = view.findViewById(R.id.forth_addressPeopleRecycler_view);
        //设置适配器
        List<String> lists = new ArrayList<>();
        for (int i = 0;i<data.size();i++){
            lists.add(data.get(i).getName());
        }
        Map<String,Object> addressNameList = Pinyin4jUtil.px(lists);
        List<AddressPeopleItem> data = new ArrayList<>();
        mrecycler.setAdapter(new AddressListAdapter(getContext(),addressNameList,data));
        //设置样式 ,有如下:
        // LinearLayoutManager(Context context, @RecyclerView.Orientation int orientation,boolean reverseLayout)
        //GridLayoutManager(Context context, int spanCount)
        //StaggeredGridLayoutManager(int spanCount, int orientation)
        mrecycler.setLayoutManager(new GridLayoutManager(getContext(),1));
        return view;
    }

    private void initDate() {
        data.add(new AddressPeopleItem("","张三"));
        data.add(new AddressPeopleItem("","李四"));
        data.add(new AddressPeopleItem("","王五"));
        data.add(new AddressPeopleItem("","赵六"));
    }
}
