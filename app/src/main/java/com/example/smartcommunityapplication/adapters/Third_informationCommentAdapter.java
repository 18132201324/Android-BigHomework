package com.example.smartcommunityapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.entities.Third_information;
import com.example.smartcommunityapplication.entities.Third_informationComment;

import java.util.ArrayList;
import java.util.List;

public class Third_informationCommentAdapter extends BaseAdapter {
    private Context mcontext;
    private List<Third_informationComment> informationComments=new ArrayList<>();
    private int itemLayoutResId;

    public Third_informationCommentAdapter(Context mcontext, List<Third_informationComment> informationComments, int itemLayoutResId) {
        this.mcontext = mcontext;
        this.informationComments = informationComments;
        this.itemLayoutResId = itemLayoutResId;
    }
    @Override
    public int getCount() {
        if (null!=informationComments){
            return informationComments.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (null!=informationComments){
            return informationComments.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =LayoutInflater.from(mcontext);
        convertView=inflater.inflate(itemLayoutResId,null);

        //设置视图对象显示的内容
        TextView third_Comment_Observer=convertView.findViewById(R.id.third_tv_Observer);
        TextView third_Comment_comment=convertView.findViewById(R.id.third_tv_comment);
        TextView third_Comment_likes=convertView.findViewById(R.id.third_tv_likes);

//        String imagePath= ConfigUitl.SERVER_ADDR+"images/"+cakes.get(position).getPhoto();
//        Glide.with(mcontext)
//                .load(imagePath)
//                .into(cakePhoto);
        third_Comment_Observer.setText(informationComments.get(position).getObserver());
        third_Comment_comment.setText(informationComments.get(position).getComment());
        third_Comment_likes.setText(informationComments.get(position).getLikes()+"");

        return convertView;
    }
}
