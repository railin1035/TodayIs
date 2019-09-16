package com.ghc.tdi_main.Settings;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghc.tdi_main.Main.select_main;
import com.ghc.tdi_main.R;

public class setting_main extends AppCompatActivity {

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
        /******뒤로가기 버튼******/
        backbtn = (ImageView)findViewById(R.id.setting_back_setting);
        backbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(setting_main.this, select_main.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
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
        lockbtn=(TextView)findViewById(R.id.lock_switch);
        lockbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(setting_main.this,setting_lock.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                }
        });
        /******잠금설정******/

        /******공지사항******/
        noticebtn=(TextView)findViewById(R.id.notice_setting);
        noticebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(setting_main.this,setting_notice.class);
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
                        final String[] notification_infor = new String[]{"날씨", "미세먼지","일정","D-Day","메모"};
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
                        infoupdate_dialog.setTitle("알림 정보");
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
