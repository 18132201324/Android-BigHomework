package com.example.smartcommunityapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.entities.Community;
import com.giftedcat.picture.lib.selector.bean.Image;

import java.util.ArrayList;
import java.util.List;

public class CommunityListAdapter extends BaseAdapter {
    private ImageView communityImg;
    private TextView communityName;
    private TextView communityLocaiton;
    private Context mContext;
    private List<Community> communities = new ArrayList<> ();
    private int itemLayoutRes;

    public CommunityListAdapter(Context mContext, List<Community> communities, int itemLayoutRes) {
        this.mContext = mContext;
        this.communities = communities;
        this.itemLayoutRes = itemLayoutRes;
    }

    @Override
    public int getCount() {
        if (null != communities){
            return communities.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (null != communities){
            return communities.get(position);
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
        communityImg = convertView.findViewById (R.id.community_img);
        communityName = convertView.findViewById (R.id.community_name);
        communityLocaiton = convertView.findViewById (R.id.community_location);

        communityName.setText (communities.get (position).getName ());
        communityLocaiton.setText (communities.get (position).getLocation ());
        return convertView;
    }
}
