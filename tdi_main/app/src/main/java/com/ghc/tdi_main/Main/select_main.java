package com.ghc.tdi_main.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.ghc.tdi_main.Display.Display_main;
import com.ghc.tdi_main.Login.EurnSignUp;
import com.ghc.tdi_main.Schedule.schedule_input;
import com.ghc.tdi_main.R;
import com.ghc.tdi_main.Memo.memo_list;
import com.ghc.tdi_main.Schedule.schedule_list;
import com.ghc.tdi_main.Settings.setting_frontlockview;
import com.ghc.tdi_main.Settings.setting_main;

public class select_main extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
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
        mypagebtn.setOnClickListener(this);

        helpbtn = findViewById(R.id.m_help_btn); //도움말
        helpbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent2 = getIntent();
        int mainset = intent2.getIntExtra("selectmainint", 2);

        if(v.getId()==R.id.m_display_btn){
            Intent intent = new Intent(select_main.this, Display_main.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        }
        if(v.getId()==R.id.m_setting_btn) {
            /*setting_main으로 값 전달*/
            if (mainset == 1) {
                Intent intent = new Intent(select_main.this, setting_main.class);
                intent.putExtra("backsettingmain",1);
                intent.putExtra("backsettingmain2",1);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                Log.d(getClass().getName(),"backsettingmain:");
            }
            /*setting_main으로 값 전달*/
            else {
                Intent intent = new Intent(select_main.this, setting_main.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                Log.d(getClass().getName(),"조건해당없음!:");
            }
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
            Intent intent = new Intent(select_main.this, EurnSignUp.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        }
        if(v.getId()==R.id.m_help_btn){
                Log.d(getClass().getName(), "도움말 클릭 되었음");
                Intent intent = new Intent(select_main.this, setting_frontlockview.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
        }
    }
/*
    @Override
    protected void onUserLeaveHint() {

        Toast.makeText(this,"재실행완료 홈키막힘",Toast.LENGTH_SHORT).show();

        finish();

        Intent i = new Intent(this, select_main.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        super.onUserLeaveHint();

    }*/

    /*private void DismissKeyGuard(){
        PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG);
        }
    }*/
}