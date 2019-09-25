package com.ghc.tdi_main.Login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ghc.tdi_main.Main.select_main;
import com.ghc.tdi_main.R;

public class FirstMain extends AppCompatActivity {

    LinearLayout first, id, pass;
    EditText idText, passText;
    ImageView idicon,passicon;

    Button loginbtns, googlebtn, erunbtn;
    ImageButton googlebtns;
    boolean firstcheck = true;
    int firstint = 0;
    int passint = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        //레이아웃
        first = (LinearLayout)findViewById(R.id.fristmain);
        id = (LinearLayout)findViewById(R.id.idbtn);
        pass = (LinearLayout)findViewById(R.id.passbtn);


        //에딧 테스트
        idText = (EditText)findViewById(R.id.Id_Text);
        passText = (EditText)findViewById(R.id.Pass_Text);

        //이미지 뷰
        idicon = (ImageView)findViewById(R.id.Id_iconView);
        passicon = (ImageView)findViewById(R.id.Pass_iconView);

        //버튼
        loginbtns = (Button)findViewById(R.id.mainloginbtn);
        erunbtn = (Button)findViewById(R.id.loginbtn);
        googlebtns = (ImageButton)findViewById(R.id.googlebtn);


        //이미지 뷰 컬러
        final int idpasscolor = ContextCompat.getColor(this,R.color.colorPrimary);
        final int idpasscolor2 = ContextCompat.getColor(this,R.color.colorWhite);
        final int loginclick = ContextCompat.getColor(this,R.color.loginClick);
        //----------이벤트----------

        //아이디
        idText.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                FirstMain.super.onTouchEvent(event);

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        idicon.setColorFilter(idpasscolor);
                        idText.setTextColor(idpasscolor);
                        id.setBackgroundResource(R.drawable.login_layout_customborder);
                        idText.requestFocus();
                        return true;
                    case MotionEvent.ACTION_UP:
                        idicon.setColorFilter(idpasscolor);
                        idText.setTextColor(idpasscolor);
                        id.setBackgroundResource(R.drawable.login_layout_customborder);


                        passicon.setColorFilter(idpasscolor2);
                        passText.setTextColor(idpasscolor2);
                        pass.setBackgroundResource(R.drawable.first_layout_customborder);
                        idText.requestFocus();
                        return true;
                }
                return false;
            }
        });
        //아이디

        //비밀번호
        passText.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                FirstMain.super.onTouchEvent(event);{
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            passicon.setColorFilter(idpasscolor);
                            passText.setTextColor(idpasscolor);
                            pass.setBackgroundResource(R.drawable.login_layout_customborder);

                            idicon.setColorFilter(idpasscolor2);
                            idText.setTextColor(idpasscolor2);
                            id.setBackgroundResource(R.drawable.first_layout_customborder);
                            passText.requestFocus();
                            return true;
                        case MotionEvent.ACTION_UP:

                            idicon.setColorFilter(idpasscolor2);
                            idText.setTextColor(idpasscolor2);
                            id.setBackgroundResource(R.drawable.first_layout_customborder);
                            passText.requestFocus();
                            return true;

                    }
                    return false;
                    }
            }
        });

        //비밀번호

        //로그인 화면전환
        loginbtns = (Button) findViewById(R.id.mainloginbtn);
        loginbtns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FirstMain.this, select_main.class);
                setContentView(R.layout.select_main);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        //로그인 화면전환

        //이런 회원가입
        erunbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstMain.this, EurnSignUp.class);
                setContentView(R.layout.erun_signup_layout);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        //이런 회원가입


    }
}



