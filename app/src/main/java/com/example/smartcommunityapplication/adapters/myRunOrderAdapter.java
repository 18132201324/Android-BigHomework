package com.example.smartcommunityapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.activities.RunOrderdetailsActivity;
import com.example.smartcommunityapplication.entities.RunorderinfoBean;

import java.util.ArrayList;
import java.util.List;


public class myRunOrderAdapter extends RecyclerView.Adapter<myRunOrderAdapter.MyViewHolder> {
    private final Context context;
   List<RunorderinfoBean> data = new ArrayList<>();
    /*
    自定义的构造方法 ，传入上下文对象context 和data数据
     */
    public myRunOrderAdapter(Context context, List<RunorderinfoBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.myrunorderrecycler,null);
        return new myRunOrderAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RunorderinfoBean bean = data.get(position);
        holder.imageView.setImageResource(bean.getPicture());
        holder.time.setText(bean.getTime());
        holder.content.setText(bean.getContent());
        holder.price.setText(bean.getPrice());

        //设置item的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), RunOrderdetailsActivity.class);
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView time;
        private TextView content;
        private TextView price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.first_runorderimg);
            time = itemView.findViewById(R.id.first_runordertime);
            content = itemView.findViewById(R.id.first_runordercontent);
            price = itemView.findViewById(R.id.first_runorderprice);
        }
    }
}
