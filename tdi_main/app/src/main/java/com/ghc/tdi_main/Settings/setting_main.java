package com.ghc.tdi_main.Settings;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ghc.tdi_main.Main.select_main;
import com.ghc.tdi_main.R;
import com.ghc.tdi_main.Schedule.schedule_input;

public class setting_main extends AppCompatActivity {

        int booleanint = 1;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_main);
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(setting_main.this,R.style.AlertDialog);
        Toolbar tb = (Toolbar) findViewById(R.id.setting_app_toolbar);
        tb.setTitle("");
        setSupportActionBar(tb);
        ImageView backbtn;
        final TextView tbackbtn,lockbtn,noticebtn,appinfobtn,notification_btn,infoupdate_btn;
        final int lockprimary = ContextCompat.getColor(this,R.color.colorPrimary);
        /******뒤로가기 버튼******/
        lockbtn=(TextView)findViewById(R.id.lock_switch);
        backbtn = (ImageView)findViewById(R.id.setting_back_setting);
        backbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        if (lockbtn.getText().toString().equals("설정")) {
                                Intent intent = new Intent(setting_main.this, select_main.class);
                                intent.putExtra("selectmainint", 1);
                                startActivity(intent);
                                overridePendingTransition(0, 0);
                                finish();
                                Log.d(getClass().getName(),"이것은 setting_main에서 selectmain");
                        }
                        else{
                                Intent intent = new Intent(setting_main.this, select_main.class);
                                startActivity(intent);
                                overridePendingTransition(0, 0);
                                finish();
                        }
                }
        });
        tbackbtn=(TextView)findViewById(R.id.setting_toolbar_title); // 뒤로가기 버튼 텍스트
        tbackbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(setting_main.this,select_main.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                }
        });
        /******뒤로가기 버튼******/

        /******잠금설정******/
        /*select_main에서 인텐트한 값*/
        Intent intentback = getIntent();
        int backset = intentback.getIntExtra("backsettingmain",2);
        Log.d(getClass().getName(), "select_main에서 인텐트한거 설정!!:"+backset);
        if(backset == 1 ){
                lockbtn.setText("설정");
                lockbtn.setTextColor(lockprimary);
                booleanint = 2;
                Log.d(getClass().getName(), "select_main에서 인텐트한거 설정!!bool:" + booleanint);
        }
        else if(backset != 1){
                Toast.makeText(getApplicationContext(),"backset이 1이아님",Toast.LENGTH_SHORT).show();
        }

        /*select_main에서 인텐트한 값*/

        /*setting_lock에서 인텐트한 값*/
        Intent intentone = getIntent();
        int allset = intentone.getIntExtra("settingsucces", 2);
        if(allset == 1){
                lockbtn.setText("설정");
                lockbtn.setTextColor(lockprimary);
                Log.d(getClass().getName(), "메인잠금설정 설정완료!:"+allset);
        }
        /*setting_lock에서 인텐트한 값*/

        lockbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        /*setting_lock에서 온 값*/
                        Intent intentone = getIntent();
                        int allset = intentone.getIntExtra("settingsucces", 2);
                        /*setting_lock에서 온 값*/

                        /*select_main에서 온 값*/
                        Intent intentback = getIntent();
                        int backset2 = intentback.getIntExtra("backsettingmain2",2);
                        /*select_main에서 온 값*/
                        //&& lockbtn.getText().toString().equals("설정")
                        /*select_main에서 온 값으로 setting_lock에 이벤트*/
                        Log.d(getClass().getName(),"backset2:"+backset2);
                        Log.d(getClass().getName(),"allset1:"+allset);
                        if(backset2 == 1 && lockbtn.getText().toString().equals("설정")){
                                Intent intent = new Intent(setting_main.this,setting_lock.class);
                                intent.putExtra("backsettinglock", 1);
                                startActivity(intent);
                                overridePendingTransition(0, 0);
                                finish();
                                Log.d(getClass().getName(),"select_main에서 온값setting_lock으로"+backset2);
                        }
                        /*select_main에서 온 값으로 setting_lock에 이벤트*/

                        /*setting_lock에 값 전달*/
                        else if(allset == 1 && lockbtn.getText().toString().equals("설정")){
                                Intent intent = new Intent(setting_main.this,setting_lock.class);
                                intent.putExtra("setstart", 1);
                                startActivity(intent);
                                overridePendingTransition(0, 0);
                                finish();
                                Log.d(getClass().getName(), "setting_lock에서 온값으로 실행");
                        }
                        /*setting_lock에 값 전달*/

                        else {
                                Intent intent = new Intent(setting_main.this, setting_lock.class);
                                startActivity(intent);
                                overridePendingTransition(0, 0);
                                finish();
                        }
                }
        });
        /******잠금설정******/

        /******공지사항******/
        noticebtn=(TextView)findViewById(R.id.notice_setting);
        noticebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                      /*  Intent intent = new Intent(setting_main.this,setting_notice.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();*/
                        Intent intent = new Intent(setting_main.this, setting_notice.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                }
        });
        /******공지사항******/

        /******어플리케이션 정보******/
        appinfobtn=(TextView)findViewById(R.id.appinfo_setting);
        appinfobtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(setting_main.this,setting_appinfo.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                }
        });
        /******어플리케이션 정보******/

        /******알림 설정******/
        final int[] selected_notification_infor ={0};
        final TextView notification_text;
        notification_text = (TextView)findViewById(R.id.Notification_text);
        notification_btn=(TextView)findViewById(R.id.Notification_switch);
        notification_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        final String[] notification_infor = new String[]{"날씨, 미세먼지","메모","일정","D-Day"};
                       android.app.AlertDialog.Builder notification_dialog = new android.app.AlertDialog.Builder(setting_main.this,R.style.AlertDialog);
                        notification_dialog.setTitle("알림 정보");
                        notification_dialog.setSingleChoiceItems(notification_infor, 0, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                        selected_notification_infor[0] = which;
                                }
                        })
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                                notification_btn.setText(notification_infor[selected_notification_infor[0]]);
                                                notification_btn.setTextColor(getResources().getColorStateList(R.color.colorPrimary));
                                                notification_text.setTextColor(getResources().getColorStateList(R.color.colorBlack));
                                        }
                                })
                                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                        }
                                });
                        notification_dialog.create();
                        notification_dialog.show();
                }
        });
        /******알림 설정******/

        /******정보 업데이트******/
        final int[] selected_infoupdate_infor ={0};
        infoupdate_btn=(TextView)findViewById(R.id.infoupdate_switch);
        infoupdate_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        final String[] infoupdate_infor = new String[]{"켤때마다", "정보 클릭 시","30분","1시간","2시간","4시간","6시간","12시간","24시간"};
                        android.app.AlertDialog.Builder infoupdate_dialog = new android.app.AlertDialog.Builder(setting_main.this,R.style.AlertDialog);
                        infoupdate_dialog.setTitle("정보 업데이트");
                        infoupdate_dialog.setSingleChoiceItems(infoupdate_infor, 0, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                        selected_infoupdate_infor[0] = which;
                                }
                        })
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                                infoupdate_btn.setText(infoupdate_infor[selected_infoupdate_infor[0]]);
                                                infoupdate_btn.setTextColor(getResources().getColorStateList(R.color.colorPrimary));
                                        }
                                })
                                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                });
                        infoupdate_dialog.create();
                        infoupdate_dialog.show();
                }
        });
        /******정보 업데이트******/
        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate(R.menu.abbbar_action, menu);

                return true;
        }
        }
