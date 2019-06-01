package com.kc345ws.MapNavigation;

import android.location.LocationManager;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

public class SearchInfo {
    PoiSearch mPoiSearch;//POI检索实例
    OnGetPoiSearchResultListener listener;//POI检索监听器
    LatLng mlatLng;
    LocationMyself mlocationMyself;
    public SearchInfo(LocationMyself locationMyself){
        mlatLng = locationMyself.getLatLng();
        mlocationMyself = locationMyself;
        mPoiSearch = PoiSearch.newInstance();
        listener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {

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

    public void search(){
        /**
         *  PoiCiySearchOption 设置检索属性
         *  city 检索城市
         *  keyword 检索内容关键字
         *  pageNum 分页页码
         */
        mlatLng = mlocationMyself.getLatLng();
        mPoiSearch.searchNearby(new PoiNearbySearchOption()
                .location(new LatLng(39.915446, 116.403869))
                .radius(100)
                .keyword("餐厅")
                .pageNum(10));
        mPoiSearch.destroy();
    }

}
