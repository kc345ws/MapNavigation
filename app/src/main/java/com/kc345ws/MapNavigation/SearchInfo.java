package com.kc345ws.MapNavigation;


import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.kc345ws.MapNavigation.overlayutil.*;
import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;


import java.util.List;

public class SearchInfo{
    private PoiSearch mPoiSearch;//POI检索实例
    private OnGetPoiSearchResultListener listener;//POI检索监听器
    private LatLng mlatLng;
    private LocationMyself mlocationMyself;
    private BaiduMap mBaiduMap;
    private Activity mActivity;
    public SearchInfo(Activity activity ,LocationMyself locationMyself , BaiduMap baiduMap){
        mActivity = activity;
        mBaiduMap = baiduMap;
        mlatLng = locationMyself.getLatLng();
        mlocationMyself = locationMyself;
        mPoiSearch = PoiSearch.newInstance();

        listener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                    mBaiduMap.clear();

                    /*List<PoiInfo> allAddr = poiResult.getAllPoi();
                    for (PoiInfo p: allAddr) {
                    Log.d("MainActivity", "p.name--->" + p.name +"p.phoneNum" + p.phoneNum +" -->p.address:" + p.address + "p.location" + p.location);
                    }*/
                    //创建PoiOverlay对象
                    PoiOverlay poiOverlay = new PoiOverlay(mBaiduMap);

                    //设置Poi检索数据
                    poiOverlay.setData(poiResult);

                    List<PoiInfo> allAddr = poiResult.getAllPoi();
                    for (PoiInfo p: allAddr) {
                    Log.d("MainActivity", "p.name--->" + p.name +"p.phoneNum" + p.phoneNum +" -->p.address:" + p.address + "p.location" + p.location);
                    }
                    //将poiOverlay添加至地图并缩放至合适级别
                    poiOverlay.addToMap();
                    poiOverlay.zoomToSpan();

                    int TotalPoi = poiResult.getTotalPoiNum();
                    Toast.makeText(mActivity.getApplicationContext(),"共找到"+TotalPoi+"个POI点",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(mActivity.getApplicationContext(),"没有找到结果",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }
            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
            //废弃
            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }
        };

        mPoiSearch.setOnGetPoiSearchResultListener(listener);//设置检索监听器
    }

    public void search(String selectpoi){
        /**
         *  PoiCiySearchOption 设置检索属性
         *  city 检索城市
         *  keyword 检索内容关键字
         *  pageNum 分页页码
         */
        //获取POI检索结果

        /*mPoiSearch.searchInCity(new PoiCitySearchOption()
                .city("长春") //必填
                .keyword("美食") //必填
                .pageNum(10));*/

        mPoiSearch.searchNearby(new PoiNearbySearchOption()
                .location(new LatLng(43.825380,125.273634))
                .radius(3000)
                .keyword(selectpoi)
                .pageNum(10));

        /*LatLngBounds searchBounds = new LatLngBounds.Builder()
                .include(new LatLng( 43.825380, 125.273634 ))
                .include(new LatLng( 43.855380, 125.303634))
                .build();
        mPoiSearch.searchInBound(new PoiBoundSearchOption()
                .bound(searchBounds)
                .keyword("餐厅"));*/
        // 发起附近检索请求

        mPoiSearch.destroy();
    }

}


