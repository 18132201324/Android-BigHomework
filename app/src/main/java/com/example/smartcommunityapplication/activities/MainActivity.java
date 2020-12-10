package com.example.smartcommunityapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTabHost;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.fragments.HomePageFragment;
import com.example.smartcommunityapplication.fragments.InformationPageFragment;
import com.example.smartcommunityapplication.fragments.MyselfPageFragment;
import com.example.smartcommunityapplication.fragments.NoticePageFragment;
import com.example.smartcommunityapplication.fragments.ShopPageFragment;


import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private Map<String, TextView> textViewMap = new HashMap<>();
    private Map<String, ImageView> imgViewMap = new HashMap<>();
    private FragmentTabHost fragmentTabHost;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();


    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息
            float radius = location.getRadius();    //获取定位精度，默认值为0.0f
            //Log.e("这个经度是", "" + longitude);
            //Log.e("这个维度是", "" + latitude);
            String coorType = location.getCoorType();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准

            int errorCode = location.getLocType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);

        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);

        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，设置定位模式，默认高精度
//LocationMode.Hight_Accuracy：高精度；
//LocationMode. Battery_Saving：低功耗；
//LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("bd09ll");
//可选，设置返回经纬度坐标类型，默认GCJ02
//GCJ02：国测局坐标；
//BD09ll：百度经纬度坐标；
//BD09：百度墨卡托坐标；
//海外地区定位，无需设置坐标类型，统一返回WGS84类型坐标

        option.setScanSpan(1000);
//可选，设置发起定位请求的间隔，int类型，单位ms
//如果设置为0，则代表单次定位，即仅定位一次，默认为0
//如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
//可选，设置是否使用gps，默认false
//使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
//可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
//可选，定位SDK内部是一个service，并放到了独立进程。
//设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
//可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5 * 60 * 1000);
//可选，V7.2版本新增能力
//如果设置了该接口，首次启动定位时，会先判断当前Wi-Fi是否超出有效期，若超出有效期，会先重新扫描Wi-Fi，然后定位

        option.setEnableSimulateGps(false);
//可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        option.setNeedNewVersionRgc(true);
//可选，设置是否需要最新版本的地址信息。默认需要，即参数为true

        mLocationClient.setLocOption(option);
//mLocationClient为第二步初始化过的LocationClient对象
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        mLocationClient.start();


        //获取FragmentTabHost的引用
        fragmentTabHost = findViewById(android.R.id.tabhost);
        //初始化
        fragmentTabHost.setup(this,
                getSupportFragmentManager(),//管理多个Fragment管理器
                android.R.id.tabcontent);//显示内容页面的控件id

        //创建内容TabSpec对象
        TabHost.TabSpec tab1 = fragmentTabHost.newTabSpec("first_tab")
                .setIndicator(getTabSpecView("first_tab", "首页", getResources().getDrawable(R.drawable.home1)));

        //Class参数：类名.class,对象.getClass()
        fragmentTabHost.addTab(tab1,
                HomePageFragment.class,//FirstFragment类的字节码（Class）对象
                null);//传递数据时使用，不需要传递数据直接传null

        TabHost.TabSpec tab2 = fragmentTabHost.newTabSpec("second_tab")
                .setIndicator(getTabSpecView("second_tab", "商城", getResources().getDrawable(R.drawable.shop1)));

        fragmentTabHost.addTab(tab2,
                ShopPageFragment.class,
                null);

        TabHost.TabSpec tab3 = fragmentTabHost.newTabSpec("third_tab")
                .setIndicator(getTabSpecView("third_tab", "资讯", getResources().getDrawable(R.drawable.information1)));

        fragmentTabHost.addTab(tab3,
                InformationPageFragment.class,
                null);

        TabHost.TabSpec tab4 = fragmentTabHost.newTabSpec("forth_tab")
                .setIndicator(getTabSpecView("forth_tab", "通知", getResources().getDrawable(R.drawable.chat1)));

        fragmentTabHost.addTab(tab4,
                NoticePageFragment.class,
                null);

        TabHost.TabSpec tab5 = fragmentTabHost.newTabSpec("fifth_tab")
                .setIndicator(getTabSpecView("fifth_tab", "我的", getResources().getDrawable(R.drawable.mine1)));

        fragmentTabHost.addTab(tab5,
                MyselfPageFragment.class,
                null);


        //处理fragmentTabHost的选项切换事件
        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //修改图片和文字颜色
                switch (tabId) {
                    case "first_tab":
                        textViewMap.get("first_tab").setTextColor(getResources().getColor(R.color.colo_blue_green));
                        imgViewMap.get("first_tab").setImageDrawable(getResources().getDrawable(R.drawable.home));
                        textViewMap.get("second_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("second_tab").setImageDrawable(getResources().getDrawable(R.drawable.shop1));
                        textViewMap.get("third_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("third_tab").setImageDrawable(getResources().getDrawable(R.drawable.information1));
                        textViewMap.get("forth_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("forth_tab").setImageDrawable(getResources().getDrawable(R.drawable.chat1));
                        textViewMap.get("fifth_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("fifth_tab").setImageDrawable(getResources().getDrawable(R.drawable.mine1));
                        break;
                    case "second_tab":
                        textViewMap.get("first_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("first_tab").setImageDrawable(getResources().getDrawable(R.drawable.home1));
                        textViewMap.get("second_tab").setTextColor(getResources().getColor(R.color.colo_blue_green));
                        imgViewMap.get("second_tab").setImageDrawable(getResources().getDrawable(R.drawable.shop));
                        textViewMap.get("third_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("third_tab").setImageDrawable(getResources().getDrawable(R.drawable.information1));
                        textViewMap.get("forth_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("forth_tab").setImageDrawable(getResources().getDrawable(R.drawable.chat1));
                        textViewMap.get("fifth_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("fifth_tab").setImageDrawable(getResources().getDrawable(R.drawable.mine1));
                        break;
                    case "third_tab":
                        textViewMap.get("first_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("first_tab").setImageDrawable(getResources().getDrawable(R.drawable.home1));
                        textViewMap.get("second_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("second_tab").setImageDrawable(getResources().getDrawable(R.drawable.shop1));
                        textViewMap.get("third_tab").setTextColor(getResources().getColor(R.color.colo_blue_green));
                        imgViewMap.get("third_tab").setImageDrawable(getResources().getDrawable(R.drawable.information));
                        textViewMap.get("forth_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("forth_tab").setImageDrawable(getResources().getDrawable(R.drawable.chat1));
                        textViewMap.get("fifth_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("fifth_tab").setImageDrawable(getResources().getDrawable(R.drawable.mine1));
                        break;
                    case "forth_tab":
                        textViewMap.get("first_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("first_tab").setImageDrawable(getResources().getDrawable(R.drawable.home1));
                        textViewMap.get("second_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("second_tab").setImageDrawable(getResources().getDrawable(R.drawable.shop1));
                        textViewMap.get("third_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("third_tab").setImageDrawable(getResources().getDrawable(R.drawable.information1));
                        textViewMap.get("forth_tab").setTextColor(getResources().getColor(R.color.colo_blue_green));
                        imgViewMap.get("forth_tab").setImageDrawable(getResources().getDrawable(R.drawable.chat));
                        textViewMap.get("fifth_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("fifth_tab").setImageDrawable(getResources().getDrawable(R.drawable.mine1));
                        break;
                    case "fifth_tab":
                        textViewMap.get("first_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("first_tab").setImageDrawable(getResources().getDrawable(R.drawable.home1));
                        textViewMap.get("second_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("second_tab").setImageDrawable(getResources().getDrawable(R.drawable.shop1));
                        textViewMap.get("third_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("third_tab").setImageDrawable(getResources().getDrawable(R.drawable.information1));
                        textViewMap.get("forth_tab").setTextColor(getResources().getColor(android.R.color.black));
                        imgViewMap.get("forth_tab").setImageDrawable(getResources().getDrawable(R.drawable.chat1));
                        textViewMap.get("fifth_tab").setTextColor(getResources().getColor(R.color.colo_blue_green));
                        imgViewMap.get("fifth_tab").setImageDrawable(getResources().getDrawable(R.drawable.mine));
                        break;
                }
            }
        });

        //设置默认选中的标签页：参数是下标
        fragmentTabHost.setCurrentTab(0);
        //setTextColor不能直接传入color资源的id号
        textViewMap.get("first_tab").setTextColor(getResources().getColor(R.color.colo_blue_green));
        imgViewMap.get("first_tab").setImageDrawable(getResources().getDrawable(R.drawable.home));
    }


    public View getTabSpecView(String tag, String title, Drawable drawable) {
        View view = getLayoutInflater().inflate(R.layout.tab_spec_layout, null);

        TextView tv_title = view.findViewById(R.id.title);
        ImageView iv_photo = view.findViewById(R.id.photo);
        //将ImageView对象存储到Map中
        textViewMap.put(tag, tv_title);
        imgViewMap.put(tag, iv_photo);

        TextView tvTitle = view.findViewById(R.id.title);
        ImageView ivPhoto = view.findViewById(R.id.photo);
        tvTitle.setText(title);
        ivPhoto.setImageDrawable(drawable);
        return view;
    }
}
