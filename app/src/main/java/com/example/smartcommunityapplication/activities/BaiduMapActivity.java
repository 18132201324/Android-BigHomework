package com.example.smartcommunityapplication.activities;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.entities.Second_shop;

public class BaiduMapActivity extends AppCompatActivity {
    private MapView mapView;
    private LocationClient locationClient;
    private BaiduMap baiduMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidumap);
        //Intent intent=getIntent();
        //Second_shop shop= (Second_shop)intent.getSerializableExtra("shop");
        //Log.e("得到的对象是",shop.toString());
        mapView = findViewById(R.id.map_view);
        baiduMap = mapView.getMap();
        //修改比例尺
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(16.0f);
        baiduMap.setMapStatus(msu);
        //设置比例尺缩放范围20m ~ 2KM
        //baiduMap.setMaxAndMinZoomLevel(19, 13);
        //实现定位功能
        //创建定位客户端对象：参数要求是应用程序级别的环境上下文对象
        locationClient = new LocationClient(getApplicationContext());
        //动态申请权限
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //表示用户允许了这个权限,这个数组当中存储的是用户选择的结果，0表示允许，-1表示拒绝
        if (requestCode == 100 && grantResults[0] == 0) {
            //3.创建LocationClientOption对象趋于无穷【】
            LocationClientOption option = new LocationClientOption();
            //配置定位参数
            //设置打开GPS
            option.setOpenGps(true);
            //设置坐标系类型
            option.setCoorType("bd09ll");
            //设置定位模式（低功耗定位模式）
            option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
            //将定位参数应用到定位客户端
            locationClient.setLocOption(option);
            //设置定位成功监听器（实现异步定位操作，定位成功后自动调用接口中的方法）
            locationClient.registerLocationListener(new BDAbstractLocationListener() {
                @Override
                public void onReceiveLocation(BDLocation bdLocation) {
                    //定位成功后自动执行：定位成功后位置信息保存在BDLocation对象中
                    double latitude = bdLocation.getLatitude();//纬度
                    double longitude = bdLocation.getLongitude();//经度
                    int code = bdLocation.getLocType();
                    //移动地图界面显示到当前位置
                    LatLng point = new LatLng(latitude, longitude);
                    MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(point);
                    //对地图界面进行移动
                    baiduMap.animateMapStatus(update);
                    //在当前位置添加标注覆盖物
                    BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.drawable.fangzi);
                    MarkerOptions options = new MarkerOptions().position(point).icon(descriptor);
                    baiduMap.addOverlay(options);
                    baiduMap.setMyLocationEnabled(true);
                    //配置定位数据
                    MyLocationData data = new MyLocationData.Builder().latitude(latitude).longitude(longitude).build();
                    //将定位数据设置到地图
                    baiduMap.setMyLocationData(data);
                }
            });
            //开启定位
            locationClient.start();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单资源文件
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //处理选项菜单的点击事件
        switch (item.getItemId()) {
            case R.id.normal:
                baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.satellite:
                baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.none:
                baiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
                break;
            case R.id.trafic:
                if (baiduMap.isTrafficEnabled()) {
                    baiduMap.setTrafficEnabled(false);
                    item.setTitle("交通图层on");
                } else {
                    baiduMap.setTrafficEnabled(true);
                    item.setTitle("交通图层off");
                }

                break;
            case R.id.heat:
                if (baiduMap.isBaiduHeatMapEnabled()) {
                    baiduMap.setBaiduHeatMapEnabled(false);
                    item.setTitle("热力图层on");
                } else {
                    baiduMap.setBaiduHeatMapEnabled(true);
                    item.setTitle("热力图层off");
                }
                break;
            case R.id.overlay:
                //添加覆盖物
                addMarkerOverlay();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //添加标注覆盖物
    public void addMarkerOverlay() {
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.fangzi);
        //1.定义坐标点
        LatLng point = new LatLng(38.048997, 114.524521);
        //2.创建OverlayOptions子类对象
        MarkerOptions options = new MarkerOptions()
                .position(point)//位置
                .icon(icon)//指定图标
                .draggable(true);
        //3.将覆盖物显示到地图界面
        Marker marker = (Marker) baiduMap.addOverlay(options);
        Bundle bundle = new Bundle();
        bundle.putString("title", "店铺");
        marker.setExtraInfo(bundle);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }


}