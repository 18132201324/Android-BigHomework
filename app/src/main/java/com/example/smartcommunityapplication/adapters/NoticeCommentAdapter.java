package com.example.smartcommunityapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.entities.InformationComment;
import com.example.smartcommunityapplication.entities.Notice;

import java.util.ArrayList;
import java.util.List;

public class NoticeCommentAdapter extends BaseAdapter {
    private ImageView headImg;
    private TextView userName;
    private TextView content;
    private TextView time;
    private Context mContext;
    private List<InformationComment> comments = new ArrayList<> ();
    private int resid;

    public NoticeCommentAdapter(Context mContext, List<InformationComment> comments, int resid) {
        this.mContext = mContext;
        this.comments = comments;
        this.resid = resid;
    }

    @Override
    public int getCount() {
       if (comments!=null){
           return comments.size ();
       }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (comments!=null){
            return comments.get(position);
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
        headImg = convertView.findViewById (R.id.comment_headImg);
        userName = convertView.findViewById (R.id.comment_userName);
        content = convertView.findViewById (R.id.comment_content);
        time = convertView.findViewById (R.id.comment_time);

        userName.setText (comments.get (position).getUserName ());
        content.setText (comments.get (position).getContent ());
        time.setText (comments.get (position).getTime ());
        return convertView;
    }
}
