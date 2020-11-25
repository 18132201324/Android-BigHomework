package com.example.smartcommunityapplication.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.entities.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends BaseAdapter {
    private List<Comment> dataSource1 = new ArrayList<>();
    //上下文环境
    private Context context = null;
    // item对应的布局文件
    private int item_layout_id;
    private TextView score;
    private TextView evaluatorName;
    private TextView time;
    private TextView content;
    private ImageView img;

    public CommentAdapter(Context context,
                          List<Comment> dataSource,
                          int item_layout_id
    ) {
        this.context = context;
        this.dataSource1 = dataSource;
        this.item_layout_id = item_layout_id;
    }

    public int getCount() {
        return dataSource1.size();
    }

    /**
     * 根据对应位置，返回item的原始数据
     *
     * @param position item在ListView中的位置
     * @return 对应位置的item的原始数据
     */
    @Override
    public Object getItem(int position) {
        return dataSource1.get(position);
    }

    /**
     * 根据item位置返回item对应的ID，ID在ListView的监听器响应方法中
     * 被使用
     *
     * @param position item在ListView中的位置，从0开始
     * @return 返回当前item的ID
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            Log.e("test", position + "位置创建新的view");
            // 布局填充器，可以根据布局文件生成相应View对象
            LayoutInflater inflater = LayoutInflater.from(context);
            // 使用布局填充器根据布局文件资源ID生成View视图对象
            convertView = inflater.inflate(item_layout_id, null);
        }
        img = convertView.findViewById(R.id.comment_image);
        content = convertView.findViewById(R.id.comment_content);
        score = convertView.findViewById(R.id.score);
        evaluatorName = convertView.findViewById(R.id.comment_name);
        time = convertView.findViewById(R.id.comment_time);

        Comment comment = dataSource1.get(position);
        evaluatorName.setText(comment.getEvaluatorName().toString());
        time.setText(comment.getTime().toString());
        //score.setText(comment.getScore());
        content.setText(comment.getContent());
        return convertView;
    }

}

