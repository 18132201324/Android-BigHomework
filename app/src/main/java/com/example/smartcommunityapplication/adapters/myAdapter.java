package com.example.smartcommunityapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.entities.RecyclerinfoBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder>{
    private final Context context;
    List<RecyclerinfoBean> data = new ArrayList<RecyclerinfoBean>();
    /*
    自定义的构造方法 ，传入上下文对象context 和data数据
     */
    public myAdapter(Context context, List<RecyclerinfoBean> data){
        this.context = context;
        this.data = data;
    }
    /*
    getview
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.myrecycler,null);
        return new MyViewHolder(view);
    }
    /*
    绑定数据
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final RecyclerinfoBean bean = data.get(position);
        holder.picture.setImageResource(bean.getPicture());
        holder.title.setText(bean.getTitle());

        //设置item的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(bean.getTitle());
            }
        });
    }

    /*
     *得到总条数，返回data.size() 就可以了
     */
    @Override
    public int getItemCount() {
        return data.size();
    }

    /*
    也是getview
     */
    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView picture;
        private TextView title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.img_picture);
            title = itemView.findViewById(R.id.tv_title);
        }
    }
}
