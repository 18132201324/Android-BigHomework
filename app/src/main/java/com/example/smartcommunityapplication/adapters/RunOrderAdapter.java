package com.example.smartcommunityapplication.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.entities.OrderRun;

import java.util.ArrayList;
import java.util.List;

public class RunOrderAdapter extends BaseAdapter {
    private TextView runOrderNo;
    private TextView runOrderTitle;
    private ImageView runOrderImage;
    private TextView runOrderType;
    private TextView runOrderStartTime;
    private TextView runOrderStopTime;
    private TextView runOrderMoney;
    private Button delete;
    private Context mContext;
    private List<OrderRun> orders = new ArrayList<> ();
    private int resid;

    public RunOrderAdapter(Context mContext, List<OrderRun> orders, int resid) {
        this.mContext = mContext;
        this.orders = orders;
        this.resid = resid;
    }

    @Override
    public int getCount() {
        if (orders!=null){
            return orders.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (orders!=null){
            return orders.get(position);
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
        runOrderNo = convertView.findViewById (R.id.orderRun_no);
        runOrderTitle = convertView.findViewById (R.id.orderRun_title);
        runOrderImage = convertView.findViewById (R.id.orderRun_image);
        runOrderType = convertView.findViewById (R.id.orderRun_orderType);
        runOrderStartTime = convertView.findViewById (R.id.orderRun_orderStartTime);
        runOrderStopTime= convertView.findViewById (R.id.orderRun_orderStopTime);
        runOrderMoney = convertView.findViewById (R.id.orderRun_money);
        delete = convertView.findViewById (R.id.orderRun_delete);

        runOrderNo.setText (orders.get (position).getId ());
        runOrderTitle.setText (orders.get (position).getTitle ());
        runOrderType.setText (orders.get (position).getType ());
        runOrderStartTime.setText (orders.get (position).getTime_start ());
        runOrderStopTime.setText (orders.get (position).getTime_stop ());
        runOrderMoney.setText (orders.get (position).getMoney ()+"");
        return convertView;
    }
}
