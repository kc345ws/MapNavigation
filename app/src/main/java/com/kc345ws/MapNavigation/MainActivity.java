package com.kc345ws.MapNavigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.mapapi.map.MapView;

public class MainActivity extends AppCompatActivity {
    private MapView mMapView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
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
    }
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
