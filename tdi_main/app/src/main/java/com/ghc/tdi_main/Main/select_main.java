package com.ghc.tdi_main.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ghc.tdi_main.Display.Display_main;
import com.ghc.tdi_main.Help.help_main;
import com.ghc.tdi_main.Login.FirstMain;
import com.ghc.tdi_main.R;
import com.ghc.tdi_main.Memo.memo_list;
import com.ghc.tdi_main.Settings.setting_main;

public class select_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.select_main);
        ImageView displaybtn,settingbtn,memobtn,helpbtn,calendarbtn;
        /*화면 버튼*/
        displaybtn = findViewById(R.id.m_display_btn);
        displaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(select_main.this, Display_main.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        /*화면 버튼*/
        /*설정 버튼*/
        settingbtn = (ImageView)findViewById(R.id.m_setting_btn);
        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(select_main.this, setting_main.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        /*설정 버튼*/
        /*메모 버튼*/
        memobtn = (ImageView)findViewById(R.id.m_memo_btn);
        memobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(select_main.this, memo_list.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        /*메모 버튼*/
        /*도움말 버튼*/
        helpbtn = (ImageView)findViewById(R.id.m_help_btn);
        helpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(select_main.this, FirstMain.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        /*도움말 버튼*/
    }
}