package com.ghc.tdi_main.Help;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghc.tdi_main.Main.select_main;
import com.ghc.tdi_main.R;

public class help_main extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_main);
        Toolbar tb = (Toolbar) findViewById(R.id.app_toolbar);
        tb.setTitle("");
        setSupportActionBar(tb);
        /*//툴바*/
        ImageView backbtn;
        TextView tbackbtn;
        backbtn = (ImageView) findViewById(R.id.back_setting);
        /*뒤로가기 인텐트*/
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(help_main.this, select_main.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        tbackbtn = (TextView) findViewById(R.id.toolbar_title);
        tbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(help_main.this, select_main.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        /*//뒤로가기 인텐트*/
    }
}