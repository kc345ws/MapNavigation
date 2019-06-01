package com.kc345ws.MapNavigation;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

//申请权限
public class RequestPermission {
    private String[] rPermission = null;
    Activity mActivity = null;
    public RequestPermission(String[] Permissions, Activity activity){
        rPermission = Permissions;
        mActivity = activity;
    }
    public void request(){
        ActivityCompat.requestPermissions(mActivity,rPermission,59804);
        /*if(ContextCompat.checkSelfPermission(mActivity.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(mActivity,Manifest.permission.ACCESS_FINE_LOCATION)){
            }else{
                ActivityCompat.requestPermissions(mActivity,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }*/
    }
}
