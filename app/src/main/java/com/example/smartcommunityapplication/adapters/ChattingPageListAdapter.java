package com.example.smartcommunityapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.entities.ChattingPageItem;

import java.util.List;

public class ChattingPageListAdapter extends BaseAdapter {
    private Context context;
    private List<ChattingPageItem> chattingPageItems;
    private int layoutResId;

    public ChattingPageListAdapter(Context context, List<ChattingPageItem> chattingPageItems, int chatting_item) {
        this.context = context;
        this.chattingPageItems = chattingPageItems;
        this.layoutResId = chatting_item;
    }

    @Override
    public int getCount() {
        if (chattingPageItems!=null){
            return chattingPageItems.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (chattingPageItems!=null){
            return chattingPageItems.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layoutResId,null);
            //创建ViewHolder对象
            viewHolder = new ViewHolder();
            //获取到item每一个视图对象的引用
            viewHolder.ivPhoto = convertView.findViewById(R.id.forth_img_chatPhoto);
            viewHolder.tvName = convertView.findViewById(R.id.forth_tv_chatName);
            viewHolder.tvContent = convertView.findViewById(R.id.forth_tv_chatContent);
            viewHolder.tvTime = convertView.findViewById(R.id.forth_tv_chatTime);
            //缓存ViewHolder对象
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.ivPhoto.setBackgroundResource(R.color.colorAccent);
        viewHolder.tvName.setText(chattingPageItems.get(position).getName());
        viewHolder.tvContent.setText(chattingPageItems.get(position).getContent());
        viewHolder.tvTime.setText(chattingPageItems.get(position).getTime());
        return convertView;
    }

    private class ViewHolder{
        //定义每个item布局中的视图对象作为ViewHolder成员属性
        ImageView ivPhoto;
        TextView tvName;
        TextView tvContent;
        TextView tvTime;

    }
}
