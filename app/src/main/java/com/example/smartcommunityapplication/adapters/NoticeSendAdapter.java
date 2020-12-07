package com.example.smartcommunityapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.entities.Notice;

import java.util.ArrayList;
import java.util.List;

public class NoticeSendAdapter extends BaseAdapter {
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private TextView name;
    private TextView content;
    private TextView time;
    private ImageView bubble;
    private ImageView menu;
    private Context mContext;
    private List<Notice> notices = new ArrayList<> ();
    private int resid;

    public NoticeSendAdapter(Context mContext, List<Notice> notices, int resid) {
        this.mContext = mContext;
        this.notices = notices;
        this.resid = resid;
    }

    @Override
    public int getCount() {
        if (notices!=null){
            return notices.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (notices!=null){
            return notices.get(position);
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
        img1 = convertView.findViewById (R.id.noticeSend_img1);
        img2 = convertView.findViewById (R.id.noticeSend_img2);
        img3 = convertView.findViewById (R.id.noticeSend_img3);
        name = convertView.findViewById (R.id.noticeSend_userName);
        content = convertView.findViewById (R.id.noticeSend_content);
        time = convertView.findViewById (R.id.noticeSend_time);
        bubble = convertView.findViewById (R.id.messageLayout1_bubble);
        menu = convertView.findViewById (R.id.messageLayout1_menu);

        name.setText (notices.get (position).getUserName ());
        content.setText (notices.get (position).getTitle ());
        time.setText (notices.get (position).getTime ());
        bubble.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText (mContext,"气泡",Toast.LENGTH_LONG).show ();
            }
        });
        menu.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Toast.makeText (mContext,"菜单",Toast.LENGTH_LONG).show ();
            }
        });
        return convertView;
    }
}
