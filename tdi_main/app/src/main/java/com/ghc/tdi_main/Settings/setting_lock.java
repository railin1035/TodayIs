package com.ghc.tdi_main.Settings;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ghc.tdi_main.Main.select_main;
import com.ghc.tdi_main.R;
import com.google.android.gms.common.annotation.KeepForSdkWithFieldsAndMethods;

public class setting_lock extends AppCompatActivity {


    Switch lockonoff;
    TextView draglock,passwordlock,patternlock;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_lock);
    /*텍스트 컬러 변수*/
    final int passprimary = ContextCompat.getColor(this, R.color.colorPrimary);
    final int switchoffcolor = ContextCompat.getColor(this,R.color.colorGrayoff);
    /*텍스트 컬러 변수*/

        /*툴바*/
    Toolbar tb = (Toolbar) findViewById(R.id.setting_lock_app_toolbar);
    tb.setTitle("");
    setSupportActionBar(tb);
    /*//툴바*/
    /*드래그,비밀번호,패턴 설정 텍스트뷰*/
    draglock = (TextView)findViewById(R.id.Drag_text);
    passwordlock = (TextView)findViewById(R.id.Password_text);
    patternlock = (TextView)findViewById(R.id.Pattern_text);
    /*설정 전엔 터치 못하게 만듬*/
    draglock.setEnabled(false);passwordlock.setEnabled(false);patternlock.setEnabled(false);
    /*설정 전엔 터치 못하게 만듬*/
    /*드래그,비밀번호,패턴 설정 텍스트뷰*/

    /*setting_main(거꾸로)에서 값 전달에 의한 이벤트(잠금화면 '설정'일경우)*/
    lockonoff = (Switch)findViewById(R.id.lockview_switch);
    Intent intent4 = getIntent();
    int set5 = intent4.getIntExtra("backsettinglock", 0);
    Log.d(getClass().getName(), "setting_main(거꾸로)!!:" + set5);
    if(set5 == 1){
        lockonoff.setChecked(true);
        patternlock.setText("설정");patternlock.setTextColor(passprimary);
        Log.d(getClass().getName(), "setting_main(거꾸로)!!: ");
    }
    /*setting_main(거꾸로)에서 값 전달에 의한 이벤트(잠금화면 '설정'일경우)*/


    /*setlock에서 인텐트한 값*/
    Intent intent2 = getIntent();
    int set = intent2.getIntExtra("succesint",2);
    if(set == 1){
        lockonoff.setChecked(true);
        patternlock.setText("설정");patternlock.setTextColor(passprimary);
        Log.d(getClass().getName(), "설정!!: " + set);
    }
    /*setlock에서 인텐트한 값*/

    /*setting_main에서 값 전달에 의한 이벤트*/
    Intent intent3 = getIntent();
    int set2 = intent3.getIntExtra("setstart", 2);
    if(set2 == 1){
        lockonoff.setChecked(true);
        patternlock.setText("설정");patternlock.setTextColor(passprimary);
        Log.d(getClass().getName(), "두번째 설정!!: " + set);
    }
    /*setting_main에서 값 전달에 의한 이벤트*/

    /*잠금설정 스위치*/
    lockonoff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            /*Switch ON*/
            if(isChecked) {
                /*체크 했을때 텍스트뷰를 터치 가능하게함(설정바꾸기 가능하게 열어줌)*/
                draglock.setEnabled(true);
                passwordlock.setEnabled(true);
                patternlock.setEnabled(true);
                /*체크 했을때 텍스트뷰를 터치 가능하게함(설정바꾸기 가능하게 열어줌)*/


                /* 각 텍스트뷰 별 이벤트*/
                draglock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        draglock.setText("설정");
                        draglock.setTextColor(passprimary);
                        /*잠금화면 설정중 취소를 누를경우 다시 설정 안함으로 setText*/
                        /*설정 했을때 */
                        if (draglock.getText().equals("수정")) {
                            /*잠금화면 인텐트*/
                            /*잠금화면 설정 완료했을경우 setText("수정")*, 설정 첫화면에 잠금 설정 -> 설정 안함 에서 setText("설정")*/
                            /*잠금화면 설정 부분은 어플 자체에 데이터 베이스 생성해서 값 주입, 참조 */


                        }
                    }
                });
                passwordlock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        passwordlock.setText("설정");
                        passwordlock.setTextColor(passprimary);
                        /*잠금화면 설정중 취소를 누를경우 다시 설정 안함으로 setText*/
                        /*설정 했을때 */
                        if (passwordlock.getText().equals("설정")) {
                            /*잠금화면 인텐트*/
                            /*잠금화면 설정 완료했을경우 setText("수정")*, 설정 첫화면에 잠금 설정 -> 설정 안함 에서 setText("설정")*/
                            /*잠금화면 설정 부분은 어플 자체에 데이터 베이스 생성해서 값 주입, 참조 */
                        }
                    }
                });

                patternlock.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        patternlock.setText("설정");
                        patternlock.setTextColor(passprimary);
                        /*설정한후 터치 이벤트 리스너를 따로 만들어서 그거에 따라 잠금화면 이벤트*/
                        if(patternlock.getText().equals("설정")){
                            Intent intent = new Intent(setting_lock.this, setlock.class);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                            finish();
                            /*잠금화면 인텐트*/

                            /*잠금화면 설정 부분은 어플 자체에 데이터 베이스 생성해서 값 주입, 참조 */
                        }

                    }
                });
            }
            /* 각 텍스트뷰 별 이벤트*/
            /*Switch Off*/
            else{
                draglock.setText("설정 안함"); draglock.setTextColor(switchoffcolor);
                passwordlock.setText("설정 안함");passwordlock.setTextColor(switchoffcolor);
                patternlock.setText("설정 안함");patternlock.setTextColor(switchoffcolor);
                draglock.setEnabled(false);passwordlock.setEnabled(false);patternlock.setEnabled(false);
                /*여기까지 한 후 사용자가 드래그, 비밀번호, 패턴 설정 해놨던 정보들을 데이터베이스에서 비워줌 의견 취합 후 반대로 방향잡기*/
                /*비워주지 않고 새로 들어온 정보를 replace 시켜주는 것 으로 방향 잡힘*/
            }
        }
    });

    /*잠금설정 스위치*/
    ImageView backbtn;
    TextView tbackbtn;

    backbtn = (ImageView)findViewById(R.id.setting_lock_back_setting);
    /*뒤로가기 인텐트*/
    backbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*setting_main에 값 전달(패턴설정 모두 완료되었을때(set)*/
            if(lockonoff.isChecked() && patternlock.getText().toString().equals("설정")) {
                Intent intent = new Intent(setting_lock.this, setting_main.class);
                intent.putExtra("settingsucces",1);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
            /*setting_main에 값 전달(패턴설정 모두 완료되었을때(set)*/
            else {
                Intent intent = new Intent(setting_lock.this, setting_main.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }

        }
    });
    tbackbtn=(TextView)findViewById(R.id.setting_lock_toolbar_title);
    tbackbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(setting_lock.this,setting_main.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        }
    });
    /*//뒤로가기 인텐트*/
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_action, menu);
        return true;
    }

    /*패턴 설정시 텍스트뷰 저장(설정스위치 on, 설정안함 -> 설정)*/
    /*Intent intent2 = getIntent();
    public void  patternonChangedText(intent2){
        if(index == 1) {
            lockonoff.setChecked(true);
            Log.d(getClass().getName(), "체크 되었음 !:");
        }
        else if(index != 1){
        }
    }*/
}