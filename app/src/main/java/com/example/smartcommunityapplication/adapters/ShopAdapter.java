package com.example.smartcommunityapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.entities.Second_shop;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends BaseAdapter {
    //环境上下文
    private Context context = null;

    //数据源
    private List<Second_shop> shops=new ArrayList<>();

    //item对应的布局文件
    private int item_layout_id;

    public ShopAdapter(Context context, List<Second_shop> shops, int item_layout_id) {
        this.context = context;
        this.shops = shops;
        this.item_layout_id = item_layout_id;
    }

    @Override
    public int getCount() {
        return shops.size();
    }

    @Override
    public Object getItem(int i) {
        return shops.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null){
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            convertView= layoutInflater.inflate(item_layout_id,null);
            //创建viewHolder对象
            viewHolder=new ViewHolder();
            //获取到item每一个视图对象的引用
            viewHolder.shopPhoto=convertView.findViewById(R.id.iv_shop_image);
            viewHolder.shopName=convertView.findViewById(R.id.tv_shopname);
            viewHolder.shopScore=convertView.findViewById(R.id.tv_score);
            viewHolder.shopsCenery1=convertView.findViewById(R.id.iv_shopsCenery1);
            viewHolder.shopsCenery2=convertView.findViewById(R.id.iv_shopsCenery2);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ShopAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.shopName.setText(shops.get(position).getShopName());
        if(position==0) {
            viewHolder.shopPhoto.setImageDrawable(context.getDrawable(R.drawable.beiguo1));
            viewHolder.shopsCenery1.setImageDrawable(context.getDrawable(R.drawable.beiguo2));
            viewHolder.shopsCenery2.setImageDrawable(context.getDrawable(R.drawable.beiguo3));
        }else if (position==1){
            viewHolder.shopPhoto.setImageDrawable(context.getDrawable(R.drawable.wanda1));
            viewHolder.shopsCenery1.setImageDrawable(context.getDrawable(R.drawable.wanda2));
            viewHolder.shopsCenery2.setImageDrawable(context.getDrawable(R.drawable.wanda3));
        }else if(position==2){
        viewHolder.shopPhoto.setImageDrawable(context.getDrawable(R.drawable.huaite1));
        viewHolder.shopsCenery1.setImageDrawable(context.getDrawable(R.drawable.huaite2));
        viewHolder.shopsCenery2.setImageDrawable(context.getDrawable(R.drawable.huaite3));
    }
        return convertView;
    }

    private class ViewHolder{
        //定义每个item布局中的视图对象作为ViewHolder成员属性
        ImageView shopPhoto;
        TextView shopName;
        TextView shopScore;
        ImageView shopsCenery1;
        ImageView shopsCenery2;
    }
}
