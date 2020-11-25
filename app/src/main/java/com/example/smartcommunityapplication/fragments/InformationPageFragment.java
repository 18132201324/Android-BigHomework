package com.example.smartcommunityapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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

import java.util.ArrayList;
import java.util.List;

public class InformationPageFragment extends Fragment {
    public List<Third_information> thirdinformations = new ArrayList<>();//一个全局的链表
    private UnScrollListView third_informationListView;
    private MyScrollView third_informationScrollView;
    private Third_informationAdapter third_informationAdapter;

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
        Third_information information=new Third_information();
        for (int i=0;i<10;i++){
            information.setMain("     测试信息:我时常想起过去。独自一人为了梦想拼搏的我，多么想拥有一些陪伴、鼓励和榜样。上篇说了3件法师神话装备得到了很多人的认可，这次把剩余的三件神话也一起说了。");
            information.setTitle("");
            thirdinformations.add(information);
        }

    }


}
