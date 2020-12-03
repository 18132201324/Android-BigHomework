package com.example.smartcommunityapplication.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.smartcommunityapplication.R;

import java.util.ArrayList;
import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<Uri> imgs = new ArrayList<> ();
    private int resid;

    public GridViewAdapter(Context mContext, List<Uri> imgs, int resid) {
        this.mContext = mContext;
        this.imgs = imgs;
        this.resid = resid;
    }

    @Override
    public int getCount() {
        if (imgs!=null){
            return imgs.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (imgs!=null){
            return imgs.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(resid,null);
        ImageView imageView = convertView.findViewById (R.id.image);
        imageView.setImageURI (imgs.get (position));
        return convertView;
    }
}
