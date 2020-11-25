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

import java.util.ArrayList;
import java.util.List;

public class Third_informationAdapter extends BaseAdapter {
    private Context mcontext;
    private List<Third_information> informations=new ArrayList<>();
    private int itemLayoutResId;

    public Third_informationAdapter(Context mcontext, List<Third_information> informations, int itemLayoutResId) {
        this.mcontext = mcontext;
        this.informations = informations;
        this.itemLayoutResId = itemLayoutResId;
    }

    @Override
    public int getCount() {
        if (null!=informations){
            return informations.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (null!=informations){
            return informations.get(position);
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
        ImageView third_tx_image=convertView.findViewById(R.id.third_tx_image);
        TextView third_tx_name=convertView.findViewById(R.id.third_tx_name);


//        String imagePath= ConfigUitl.SERVER_ADDR+"images/"+cakes.get(position).getPhoto();
//        Glide.with(mcontext)
//                .load(imagePath)
//                .into(cakePhoto);
        third_tx_name.setText(informations.get(position).getMain());

        return convertView;
    }
}