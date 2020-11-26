package com.example.smartcommunityapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.entities.runorder;

import java.util.ArrayList;
import java.util.List;

public class mRunOrderListAdapter extends BaseAdapter {
    private ImageView runOrderImg;
    private TextView runOrderTheme;
    private TextView runOrderContent;
    private TextView runOrderTime;
    private Context mContext;
    private List<runorder> runorders = new ArrayList<>();
    private int itemLayoutRes;

    public mRunOrderListAdapter(Context mContext, List<runorder> runorders, int itemLayoutRes) {
        this.mContext = mContext;
        this.runorders = runorders;
        this.itemLayoutRes = itemLayoutRes;
    }

    @Override
    public int getCount() {
        if (null != runorders){
            return runorders.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (null != runorders){
            return runorders.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载item的布局文件
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(itemLayoutRes, null);
        runOrderImg = convertView.findViewById(R.id.runOrderList_Img);
        runOrderTheme = convertView.findViewById(R.id.runOrderList_theme);
        runOrderContent = convertView.findViewById(R.id.runOrderList_Content);
        runOrderTime = convertView.findViewById(R.id.runOrderList_Time);

        runOrderTheme.setText(runorders.get(position).getTheme());
        runOrderContent.setText(runorders.get(position).getContent());
        runOrderTime.setText(runorders.get(position).getTime());
        return convertView;
    }
}
