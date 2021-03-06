package com.example.smartcommunityapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
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
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.utils.DistanceUtil;
import com.example.smartcommunityapplication.R;
import com.example.smartcommunityapplication.adapters.CommentAdapter;
import com.example.smartcommunityapplication.entities.Comment;
import com.example.smartcommunityapplication.entities.Second_shop;
import com.example.smartcommunityapplication.fragments.ShopPageFragment;
import com.example.smartcommunityapplication.mapapi.overlayutil.WalkingRouteOverlay;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BaiMapActivity extends AppCompatActivity {
    private MapView mapView;
    private LocationClient locationClient;
    private BaiduMap baiduMap;
    private RoutePlanSearch mSearch;
    private LatLng latLng1;
    private NestedScrollView nestedScrollView;
    private ListView mListView;
    private TextView minName;
    private TextView minCategory;
    private TextView minDistance;
    private Button minButtonroute;
    private ImageView secondButtonBack;
    private List<Comment> dataSource1 = new ArrayList<>();
    private BottomSheetDialog bottomSheetDialog;
    private double distance;
    private Second_shop shop;
    private int position;
    private LatLng latLng2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_map);
        Intent intent=getIntent();
        shop= (Second_shop)intent.getSerializableExtra("shop");
        position= (int) intent.getSerializableExtra("position");
        //获取视图控件对象
        findViews();
        //设置min控件文本信息

        mapView.showZoomControls(true);//设置是否显示缩放控件
        mapView.getChildAt(2).setPadding(0,0,-20,1980);//这是控制缩放控件的位置

        minName.setText(shop.getShopName());
        minCategory.setText(shop.getShopCategory());
        secondButtonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                finish();
            }
        });

        minButtonroute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlanNode stNode = PlanNode.withLocation(latLng1);
                PlanNode enNode = PlanNode.withLocation(latLng2);
                mSearch.walkingSearch((new WalkingRoutePlanOption())
                        .from(stNode)
                        .to(enNode));
            }
        });
        //修改比例尺
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(16.0f);
        baiduMap.setMapStatus(msu);
        locationClient = new LocationClient(getApplicationContext());
        //动态申请权限
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
            @Override
            public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
                //创建WalkingRouteOverlay实例
                if (walkingRouteResult == null || walkingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(BaiMapActivity.this, "抱歉，未找到结果"+walkingRouteResult.error, Toast.LENGTH_SHORT).show();
                }
                if (walkingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                    // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                    // result.getSuggestAddrInfo()
                    return;
                }
                if (walkingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                    WalkingRouteOverlay overlay = new WalkingRouteOverlay(baiduMap);
                    if (walkingRouteResult.getRouteLines().size() > 0) {
                        //获取路径规划数据,(以返回的第一条数据为例)
                        //为WalkingRouteOverlay实例设置路径数据
                        overlay.setData(walkingRouteResult.getRouteLines().get(0));
                        //在地图上绘制WalkingRouteOverlay
                        overlay.addToMap();
                    }
                }
            }

            @Override
            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

            }

            @Override
            public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

            }

            @Override
            public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

            }

            @Override
            public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

            }

            @Override
            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

            }

        };
        mSearch.setOnGetRoutePlanResultListener(listener);
        Comment comment = new Comment("123", "大力推荐非常好的一个地方，很适合放松心情陶冶情操", 5, "赵志强", "12月9日 20：26");
        dataSource1.add(comment);

        LinearLayout linearLayout=findViewById(R.id.displaydetails);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View inflate = View.inflate(BaiMapActivity.this, R.layout.two_fragment, null);
                TextView detailsName = inflate.findViewById(R.id.details_name);
                TextView detailsDistance = inflate.findViewById(R.id.details_distance);
                TextView detailsCategory = inflate.findViewById(R.id.details_category);
                TextView detailsAddress = inflate.findViewById(R.id.details_address);
                TextView detailsTel = inflate.findViewById(R.id.details_tel);
                ImageView shopsCenery1 = inflate.findViewById(R.id.shopscenery1);
                ImageView shopsCenery2 = inflate.findViewById(R.id.shopscenery2);
                detailsName.setText(shop.getShopName());
                detailsDistance.setText(distance+"");
                detailsCategory.setText(shop.getShopCategory());
                detailsAddress.setText(shop.getShopAddress());
                detailsTel.setText(shop.getShopTel());
                if(position==0) {
                    shopsCenery1.setImageDrawable(BaiMapActivity.this.getDrawable(R.drawable.beiguo2));
                    shopsCenery2.setImageDrawable(BaiMapActivity.this.getDrawable(R.drawable.beiguo3));
                }else if (position==1){
                    shopsCenery1.setImageDrawable(BaiMapActivity.this.getDrawable(R.drawable.wanda2));
                    shopsCenery2.setImageDrawable(BaiMapActivity.this.getDrawable(R.drawable.wanda3));
                }else if(position==2){
                    shopsCenery1.setImageDrawable(BaiMapActivity.this.getDrawable(R.drawable.huaite2));
                    shopsCenery2.setImageDrawable(BaiMapActivity.this.getDrawable(R.drawable.huaite3));
                }

                Button call = inflate.findViewById(R.id.call);
                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent();
                        intent.setAction(Intent.ACTION_DIAL);   //android.intent.action.DIAL
                        intent.setData(Uri.parse("tel:"+shop.getShopTel()));
                        startActivity(intent);
                    }
                });
                Button message = inflate.findViewById(R.id.message);
                message.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent0 = new Intent();
                        intent0.setAction(Intent.ACTION_SENDTO);
                        intent0.setData(Uri.parse("smsto:"+shop.getShopTel()));
                        startActivity(intent0);
                    }
                });
                Button share = inflate.findViewById(R.id.share);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent0 = new Intent();
                        intent0.setClass(BaiMapActivity.this,MyElemeActivity.class);
                        startActivity(intent0);
                    }
                });
                Button btn_Route=inflate.findViewById(R.id.button1);
                btn_Route.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PlanNode stNode = PlanNode.withLocation(latLng1);
                        PlanNode enNode = PlanNode.withLocation(latLng2);
                        mSearch.walkingSearch((new WalkingRoutePlanOption())
                                .from(stNode)
                                .to(enNode));
                        bottomSheetDialog.dismiss();
                    }
                });

                mListView = inflate.findViewById(R.id.iv1);
                mListView.setFocusable(false);
                CommentAdapter adapter = new CommentAdapter(BaiMapActivity.this, dataSource1, R.layout.listview_comment);
                mListView.setAdapter(adapter);
                bottomSheetDialog.setCancelable(false);
                bottomSheetDialog.setContentView(inflate);
                if (bottomSheetDialog.isShowing()) {
                    bottomSheetDialog.dismiss();
                } else {
                    bottomSheetDialog.show();
                    bottomSheetDialog.setCanceledOnTouchOutside(true);
                }
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(nestedScrollView);
                int peekHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
                bottomSheetBehavior.setPeekHeight(peekHeight);//设置最小高度
                bottomSheetBehavior.setHideable(true);//设置是否可隐藏q
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);//设置当前为隐藏状
            }
        });

 }

    private void findViews() {
        Log.e(""+Double.parseDouble(shop.getShopPointy()),""+Double.parseDouble(shop.getShopPointx()));
        latLng2=new LatLng(Double.parseDouble(shop.getShopPointy()), Double.parseDouble(shop.getShopPointx()));
        bottomSheetDialog = new BottomSheetDialog(BaiMapActivity.this);
        mapView = findViewById(R.id.map_view);
        minName = findViewById(R.id.min_name);
        minCategory= findViewById(R.id.min_shopCategory);
        minDistance = findViewById(R.id.min_shopdistance);
        minButtonroute = findViewById(R.id.min_buttonroute);
        nestedScrollView=findViewById(R.id.nestedScrollView);
        secondButtonBack=findViewById(R.id.secondback);
        baiduMap = mapView.getMap();
        mSearch = RoutePlanSearch.newInstance();
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
            option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
            //将定位参数应用到定位客户端
            locationClient.setLocOption(option);
            //设置定位成功监听器（实现异步定位操作，定位成功后自动调用接口中的方法）
            locationClient.registerLocationListener(new BDAbstractLocationListener() {
                @Override
                public void onReceiveLocation(BDLocation bdLocation) {
                    //定位成功后自动执行：定位成功后位置信息保存在BDLocation对象中
                    double latitude = bdLocation.getLatitude();//纬度
                    double longitude = bdLocation.getLongitude();//经度
                    latLng1=new LatLng(latitude,longitude);
                    int code = bdLocation.getLocType();
                    //移动地图界面显示到当前位置
                    LatLng point = new LatLng(latitude, longitude);
                    Double dtest1=DistanceUtil. getDistance(latLng1, latLng2)/1000;
                    distance= Double.valueOf(String.format("%.2f", dtest1 ));
                    minDistance.setText(distance+""); //获取之间的距离
                    addMarkerOverlay();
                    MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(point);
                    //对地图界面进行移动
                    baiduMap.animateMapStatus(update);
                    //在当前位置添加标注覆盖物
//                    BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.drawable.location);
//                    MarkerOptions options = new MarkerOptions().position(point).icon(descriptor);
//                    baiduMap.addOverlay(options);
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

    //添加标注覆盖物
    public void addMarkerOverlay() {
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.location);

        //1.创建OverlayOptions子类对象
        MarkerOptions options = new MarkerOptions()
                .position(latLng2)//位置
                .icon(icon)//指定图标
                .draggable(true);
        //2.将覆盖物显示到地图界面
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