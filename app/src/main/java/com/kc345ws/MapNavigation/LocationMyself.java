package com.kc345ws.MapNavigation;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import static java.lang.Thread.sleep;

//定位到自己当前的位置
public class LocationMyself extends BDAbstractLocationListener {
    private MapView mMapView = null;
    private BaiduMap mBaiduMap = null;
    private LocationClient mLocationClient = null;
    private LatLng latLng;
    public LocationMyself(MapView mapView , BaiduMap baiduMap , LocationClient locationClient){
        mMapView = mapView;
        mBaiduMap = baiduMap;
        mLocationClient = locationClient;
    }
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
        latLng = new LatLng(location.getLatitude(),location.getLongitude());
        MapStatus mapStatus = new MapStatus.Builder()
                    .target(latLng)//设置地图中心
                    .zoom(18)//设置缩放比例
                    .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mBaiduMap.setMapStatus(mapStatusUpdate);//将地图状态设置为新的状态
        mLocationClient.stop();
    }

    public LatLng getLatLng() {
        return latLng;
    }
}
