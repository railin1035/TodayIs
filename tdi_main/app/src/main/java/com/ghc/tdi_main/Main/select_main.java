package com.ghc.tdi_main.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ghc.tdi_main.Display.Display_main;
import com.ghc.tdi_main.Login.FirstMain;
import com.ghc.tdi_main.R;
import com.ghc.tdi_main.Memo.memo_list;
import com.ghc.tdi_main.Schedule.schedule_list;
import com.ghc.tdi_main.Settings.setting_main;

public class select_main extends AppCompatActivity implements View.OnClickListener {

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
        ImageView displaybtn,settingbtn,memobtn,schedule_listbtn,mypagebtn,helpbtn; // 각 이미지별 버튼

        displaybtn = findViewById(R.id.m_display_btn); // 화면
        displaybtn.setOnClickListener(this);

        settingbtn = findViewById(R.id.m_setting_btn); // 설정
        settingbtn.setOnClickListener(this);

        memobtn = findViewById(R.id.m_memo_btn); // 메모
        memobtn.setOnClickListener(this);

        schedule_listbtn = findViewById(R.id.m_schedule_btn); // 일정
        schedule_listbtn.setOnClickListener(this);

        mypagebtn = findViewById(R.id.m_mypage_btn); // 마이페이지
        helpbtn = findViewById(R.id.m_help_btn); //도움말
        helpbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.m_display_btn){
            Intent intent = new Intent(select_main.this, Display_main.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        }
        if(v.getId()==R.id.m_setting_btn){
            Intent intent = new Intent(select_main.this, setting_main.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        }
        if(v.getId()==R.id.m_memo_btn){
            Intent intent = new Intent(select_main.this, memo_list.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        }
        if(v.getId()==R.id.m_schedule_btn){
            Intent intent = new Intent(select_main.this, schedule_list.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        }
        if(v.getId()==R.id.m_mypage_btn){

        }
        if(v.getId()==R.id.m_help_btn){
            Intent intent = new Intent(select_main.this, FirstMain.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        }
    }
}