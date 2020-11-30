package com.example.smartcommunityapplication.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.entities.AddressPeopleItem;
import com.example.smartcommunityapplication.entities.Chatting;

import java.util.List;
import java.util.Map;

public class ChattingHomeListAdapter extends RecyclerView.Adapter<ChattingHomeListAdapter.MyViewHolder> {
    private Context context;
    private List<Chatting> chattingList;

    public ChattingHomeListAdapter(Context context,List<Chatting> chattingList){
        this.context = context;
        this.chattingList = chattingList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.chattinghomecontent_item,null);
        return new ChattingHomeListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Chatting bean = chattingList.get(position);
        if (bean.getFromName().equals("宋凌锐")){
            holder.tvChattingHomeTime.setVisibility(View.VISIBLE);
            holder.tvChattingHomeTime.setText(bean.getTime());
            holder.ivToPhoto.setVisibility(View.VISIBLE);
            holder.tvToContent.setVisibility(View.VISIBLE);
            holder.tvToContent.setText(bean.getContent());
        }else{
            holder.tvChattingHomeTime.setVisibility(View.VISIBLE);
            holder.tvChattingHomeTime.setText(bean.getTime());
            holder.ivFromPhoto.setVisibility(View.VISIBLE);
            holder.tvFromContent.setVisibility(View.VISIBLE);
            holder.tvFromContent.setText(bean.getContent());
        }

        //设置item的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,bean.getContent(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return chattingList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tvChattingHomeTime;
        private ImageView ivFromPhoto;
        private TextView tvFromContent;
        private ImageView ivToPhoto;
        private TextView tvToContent;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChattingHomeTime = itemView.findViewById(R.id.forth_tv_chattingHomeTime);
            ivFromPhoto = itemView.findViewById(R.id.forth_iv_chattingHomePhoto);
            tvFromContent = itemView.findViewById(R.id.forth_tv_chattingHomeContent);
            ivToPhoto = itemView.findViewById(R.id.forth_iv_chattingHomePhoto1);
            tvToContent = itemView.findViewById(R.id.forth_tv_chattingHomeContent1);
        }
    }
}
