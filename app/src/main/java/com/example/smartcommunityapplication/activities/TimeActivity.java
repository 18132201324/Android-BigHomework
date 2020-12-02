package com.example.smartcommunityapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.smartcommunityapplication.R;

import java.util.ArrayList;
import java.util.List;

public class TimeActivity extends AppCompatActivity {
    private TextView tvtime1,tvtime2,tvtime3;
    private long time=500;

    List<CityItem> cityList;
    RelativeLayout itmel;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        tvtime1=(TextView)findViewById(R.id.tvtime1);
        tvtime2=(TextView) findViewById(R.id.tvtime2);
        tvtime3=(TextView) findViewById(R.id.tvtime3);
        handler.postDelayed(runnable, 1000);
        gridView = (GridView) findViewById(R.id.grid);
        setData();
        setGridView();
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time--;
            String formatLongToTimeStr = formatLongToTimeStr(time);
            String[] split = formatLongToTimeStr.split("：");
            for (int i = 0; i < split.length; i++) {
                if(i==0){
                    tvtime1.setText(split[0]+"小时");
                }
                if(i==1){
                    tvtime2.setText(split[1]+"分钟");
                }
                if(i==2){
                    tvtime3.setText(split[2]+"秒");
                }

            }
            if(time>0){
                handler.postDelayed(this, 1000);
            }
        }
    };
    public String formatLongToTimeStr(Long l) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        second = l.intValue() ;
        if (second > 60) {
            minute = second / 60;   //取整
            second = second % 60;   //取余
        }

        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        String strtime = hour+"："+minute+"："+second;
        return strtime;
    }


    /**设置数据*/
    private void setData() {
        cityList = new ArrayList<CityItem>();
        CityItem item = new CityItem();
        item.setCityName("可爱玩偶");
        item.setCityCode("998积分");
        item.setCityImg(getResources().getDrawable(R.drawable.wanou));
        cityList.add(item);
        item = new CityItem();
        item.setCityName("正品华为手机");
        item.setCityCode("1080积分");
        item.setCityImg(getResources().getDrawable(R.drawable.shouji));
        cityList.add(item);
        item = new CityItem();
        item.setCityName("可爱玩偶");
        item.setCityCode("998积分");
        item.setCityImg(getResources().getDrawable(R.drawable.wanou));
        cityList.add(item);
        item = new CityItem();
        item.setCityName("正品华为手机");
        item.setCityCode("1080积分");
        item.setCityImg(getResources().getDrawable(R.drawable.shouji));
        cityList.add(item);
        item = new CityItem();
        item.setCityName("荣事达电热锅");
        item.setCityCode("998积分");
        item.setCityImg(getResources().getDrawable(R.drawable.guo));
        cityList.add(item);
        item = new CityItem();
        item.setCityName("正品华为手机");
        item.setCityCode("1080积分");
        item.setCityImg(getResources().getDrawable(R.drawable.shouji));
        cityList.add(item);
        cityList.addAll(cityList);
    }

    /**设置GirdView参数，绑定数据*/
    private void setGridView() {
        int size = cityList.size();
        int length = 100;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.FILL_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        gridView.setHorizontalSpacing(8); // 设置列表项水平间距
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数

        GridViewAdapter adapter = new GridViewAdapter(getApplicationContext(),
                cityList);
        gridView.setAdapter(adapter);
    }

    /**GirdView 数据适配器*/
    public class GridViewAdapter extends BaseAdapter {
        Context context;
        List<CityItem> list;
        public GridViewAdapter(Context _context, List<CityItem> _list) {
            this.list = _list;
            this.context = _context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            ImageView img = (ImageView) convertView.findViewById(R.id.ItemImage);
            TextView tvCity = (TextView) convertView.findViewById(R.id.tvCity);
            TextView tvCode = (TextView) convertView.findViewById(R.id.tvCode);
            CityItem city = list.get(position);
            img.setImageDrawable(city.getCityImg());
            tvCity.setText(city.getCityName());
            tvCode.setText(city.getCityCode());
            return convertView;
        }
    }

    public class CityItem {
        private String cityName;
        private String cityCode;
        private Drawable cityImg;

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public Drawable getCityImg() {
            return cityImg;
        }

        public void setCityImg(Drawable cityImg) {
            this.cityImg = cityImg;
        }
    }
}
