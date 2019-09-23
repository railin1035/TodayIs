package com.ghc.tdi_main.Settings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghc.tdi_main.R;

public class setting_appinfo extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_appinfo);
    /*툴바*/
    Toolbar tb = (Toolbar) findViewById(R.id.setting_appinfo_app_toolbar);
    tb.setTitle("");
    setSupportActionBar(tb);
    /*//툴바*/
    ImageView backbtn;
    TextView tbackbtn;
    backbtn = (ImageView)findViewById(R.id.setting_appinfo_back_setting);
    /*뒤로가기 인텐트*/
    backbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(setting_appinfo.this,setting_main.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        }
    });
    tbackbtn=(TextView)findViewById(R.id.setting_appinfo_toolbar_title);
    tbackbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(setting_appinfo.this,setting_main.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        }
    });
    /*//뒤로가기 인텐트*/
        }
}
