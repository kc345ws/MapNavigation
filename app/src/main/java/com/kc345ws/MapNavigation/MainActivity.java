package com.kc345ws.MapNavigation;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private MapView mMapView = null;
    private BaiduMap mBaiduMap = null;
    private LocationClient mLocationClient = null;
    private ImageButton locationButton = null;//定位按钮
    private LocationMyself locationMyself = null;
    private SearchInfo searchInfo = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //申请权限
        String []permission=new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
        RequestPermission requestPermission = new RequestPermission(permission,this);
        requestPermission.request();
       // LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //获取地图控件引用
        mMapView = findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();//获取百度地图对象
        mBaiduMap.setMyLocationEnabled(true);//开启定位图层
        mLocationClient = new LocationClient(this);//定位初始化

        locationButton = findViewById(R.id.locationButton);//获取定位按钮

        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//高精度模式
        locationClientOption.setOpenGps(true);//打开GPS
        locationClientOption.setCoorType("bd0911");//设置坐标类型
        locationClientOption.setScanSpan(1000);//设置间距
        locationClientOption.setIsNeedLocationDescribe(true);//设置需要描述位置信息

        //设置locationClientOption
        mLocationClient.setLocOption(locationClientOption);

        //注册locationClientOption
        locationMyself = new LocationMyself(mMapView,mBaiduMap,mLocationClient);
        //MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(locationMyself);
        //开启地图定位图层
        mLocationClient.start();

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLocationClient.start();
            }
        });

        searchInfo = new SearchInfo(this,locationMyself,mBaiduMap);
        /*ImageButton chaxunButton = findViewById(R.id.chacunButton);
        chaxunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchInfo.search();
            }
        });*/

        //SearchInfo searchInfo = new SearchInfo(this,locationMyself,mBaiduMap);
        //searchInfo.search();

        ImageButton findnearbyBTN = findViewById(R.id.findnearbyBTN);
        findnearbyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SelectPoiActivity.class);
                startActivityForResult(intent,0X141);//发现周边Activity
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0X141 && resultCode == 0X141){
            Bundle bundle = data.getExtras();//获取SelectPoiActivity的Extras
            String selectpoi = bundle.getString("selectPOI");//获取选择POI
            searchInfo.search(selectpoi);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView = null;
    }

    /*private void locationUpdate(Location location){//获取指定的查询信息
        if(location!=null){//如果location不为空
            LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());//获取经纬度信息
            Log.i("Location","纬度:"+location.getLatitude()+"经度:"+location.getLongitude());
        }else{
            Log.i("Location","没有获取到经纬度信息");
        }
    }*/

    /*public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null){
                return;
            }
            MyLocationData myLocationData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    //此处为方向信息,顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(myLocationData);
            //MyLatLng = LatLngBounds.Builder();
            LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
            MapStatus mapStatus = new MapStatus.Builder()
                    .target(latLng)//设置地图中心
                    .zoom(18)//设置缩放比例
                    .build();
            //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
            MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
            mBaiduMap.setMapStatus(mapStatusUpdate);
        }
    }*/
}





/*Debug调试版：
您的密钥库包含 1 个条目
别名: androiddebugkey
创建日期: 2019-4-10
条目类型: PrivateKeyEntry
证书链长度: 1
证书[1]:
所有者: C=US, O=Android, CN=Android Debug
发布者: C=US, O=Android, CN=Android Debug
序列号: 1
有效期为 Wed Apr 10 17:45:51 CST 2019 至 Fri Apr 02 17:45:51 CST 2049
证书指纹:
         MD5:  A3:DB:88:BC:6F:AE:88:DA:97:59:F6:C0:5C:B1:F9:A6
         SHA1: 2B:2C:1B:F6:97:A3:85:56:36:67:D1:C3:32:8C:47:E7:2E:8A:D4:DA
         SHA256: 90:55:40:6B:38:E3:C2:10:D3:F3:5D:E3:99:8F:82:7B:1A:7B:95:28:B1:FF                                                                                                           :91:01:25:20:87:04:29:69:7C:81
签名算法名称: SHA1withRSA
主体公共密钥算法: 1024 位 RSA 密钥
版本: 1
 */

/*发布版
别名: baidu_map
创建日期: 2019-5-30
条目类型: PrivateKeyEntry
证书链长度: 1
证书[1]:
所有者: CN=kc345ws
发布者: CN=kc345ws
序列号: 5fa256d0
有效期为 Thu May 30 20:40:09 CST 2019 至 Mon May 23 20:40:09 CST 2044
证书指纹:
         MD5:  3D:B7:CE:23:DC:37:A1:AA:F6:D3:F4:30:F4:54:9D:35
         SHA1: FF:F9:77:90:75:3F:58:BB:7C:17:C9:04:98:75:F4:AA:13:D4:5D:3B
         SHA256: 18:BC:97:8E:15:89:8A:17:1F:78:9D:A9:FE:AB:88:A6:E2:DD:C1:F2:BD:2B:77:36:21:04:B5:C2:C8:43:87:84
签名算法名称: SHA256withRSA
主体公共密钥算法: 2048 位 RSA 密钥
版本: 3
* */
