package com.kc345ws.MapNavigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SelectPoiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_poi);

        ImageButton TEST = findViewById(R.id.imageButton3);
        TEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle =new Bundle();
                bundle.putString("selectPOI","超市");
                Intent intent = getIntent();
                intent.putExtras(bundle);
                setResult(0X141,intent);
                finish();
            }
        });
    }
}
