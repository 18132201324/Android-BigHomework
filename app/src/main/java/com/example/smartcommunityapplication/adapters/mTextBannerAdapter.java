package com.example.smartcommunityapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.entities.InfoBean;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class mTextBannerAdapter extends BannerAdapter<InfoBean, mTextBannerAdapter.mbannerholder> {
    Context context;

    /**
     * 自定义构造方法
     * @param context  上下文对象
     * @param datas    传入数据
     */
    public mTextBannerAdapter(Context context, List<InfoBean> datas) {
        super(datas);
        this.context = context;
    }

    @Override
    public mbannerholder onCreateHolder(ViewGroup parent, int viewType) {
        return new mTextBannerAdapter.mbannerholder(LayoutInflater.from(context).inflate(R.layout.mytextbannerrecycler, parent, false));
    }

    @Override
    public void onBindView(mbannerholder holder, InfoBean data, final int position, int size) {
        holder.textView.setText(data.getText());
        //设置监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"第"+(position+1)+"张",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public class mbannerholder extends RecyclerView.ViewHolder {
        TextView textView;
        public mbannerholder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_banner);
        }
    }
}
