package com.ghc.tdi_main.Settings;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghc.tdi_main.R;

public class setting_notice extends AppCompatActivity {
    private LinearLayout parentLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_notice);
        Toolbar tb = (Toolbar) findViewById(R.id.setting_notice_app_toolbar);
        tb.setTitle("");
        setSupportActionBar(tb);
        ImageView backbtn;
        TextView tbackbtn;
        backbtn = (ImageView) findViewById(R.id.setting_notice_back_setting);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(setting_notice.this, setting_main.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        tbackbtn = (TextView) findViewById(R.id.setting_notice_toolbar_title);
        tbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(setting_notice.this, setting_main.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_action, menu);

        return true;
    }

    public void onAddField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.setting_notice_filed, null);
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
    }
}