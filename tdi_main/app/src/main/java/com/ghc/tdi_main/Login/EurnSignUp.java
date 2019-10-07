package com.ghc.tdi_main.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ghc.tdi_main.R;

public class EurnSignUp extends AppCompatActivity {

    Button checkboxbtn,finalsignup;
    ImageButton signback;
    TextView passsixcheck, passcheckhint;
    EditText passsix, passcheck, idtext, emailtext;

    public boolean btncheck = false;
    public boolean passcheckbool = true;
    public boolean passifcheckbool = true;

    int checkcount = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erun_signup_layout);

        //체크박스 모양 btn, 회원가입 btn
        checkboxbtn = (Button)findViewById(R.id.Checked);
        finalsignup = (Button)findViewById(R.id.signbtn);
        //체크박스 모양 btn, 회원가입 btn

        //뒤로가기 버튼
        signback = (ImageButton)findViewById(R.id.SignBackbtn);
        //뒤로가기 버튼

        //비밀번호 입력 텍스트, 아이디, 이메일
        passsix = (EditText)findViewById(R.id.Pass_six);
        passcheck = (EditText)findViewById(R.id.Pass_textCheck);
        idtext = (EditText)findViewById(R.id.Sign_id);
        emailtext = (EditText)findViewById(R.id.Email_Text);
        //비밀번호 입력 텍스트 아이디, 이메일

        //비밀번호 숫자확인, 일치확인 텍스트
        passsixcheck = (TextView)findViewById(R.id.Pass_sixCheck);
        passcheckhint = (TextView)findViewById(R.id.Pass_CheckHint);
        //비밀번호 숫자확인, 일치확인 텍스트

        final int passcolorRed = ContextCompat.getColor(this,R.color.colorRed);
        final int passprimary = ContextCompat.getColor(this, R.color.colorPrimary);


        //뒤로가기
        signback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EurnSignUp.this, FirstMain.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        //뒤로가기

        //체크박스
        checkboxbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                checkcount = 1 - checkcount;

                if (checkcount == 0) {
                    checkboxbtn.setBackgroundResource(R.drawable.signtruecheck);
                    btncheck = true;
                }
                else{
                    checkboxbtn.setBackgroundResource(R.drawable.signupcheckbox);
                    btncheck = false;

                }
            }
        });
        //체크박스

        //비밀번호 6자리 밑에 텍스트
        passsix.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(passsix.getText().toString().length() < 2) {
                    passsixcheck.setTextColor(passcolorRed);
                }
            }
        });
        //비밀번호 6자리 밑에 텍스트

        //비밀번호
        passsix.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(1 < s.length() || s.length() <= 6 ){
                    passsix.setTextColor(passcolorRed);
                    passsixcheck.setTextColor(passcolorRed);
                    passsixcheck.setText("비밀번호가 6자리 이하입니다.");
                    passsixcheck.setVisibility(View.VISIBLE);
                    passifcheckbool = false;
                }

                if(1 < s.length() || s.length() <= 6 && !passcheck.getText().toString().equals(passcheck.getText().toString()) && passcheckbool){
                    passsix.setTextColor(passcolorRed);
                    passsixcheck.setTextColor(passcolorRed);
                    passsixcheck.setText("비밀번호가 6자리 이하입니다.");
                    passsixcheck.setVisibility(View.VISIBLE);

                    passcheck.setTextColor(passcolorRed);
                    passcheckhint.setTextColor(passcolorRed);
                    passcheckhint.setText("비밀번호 불일치");
                    passcheckhint.setVisibility(View.VISIBLE);

                    passifcheckbool = false;
                }

                if(1 < s.length() || s.length() <= 6 && passcheck.getText().toString().equals(passcheck.getText().toString())){
                    passsix.setTextColor(passcolorRed);
                    passsixcheck.setTextColor(passcolorRed);
                    passsixcheck.setText("비밀번호가 6자리 이하입니다.");
                    passsixcheck.setVisibility(View.VISIBLE);

                    passcheck.setTextColor(passcolorRed);
                    passcheckhint.setTextColor(passcolorRed);
                    passcheckhint.setText("비밀번호 일치하지만 6자리 이하입니다.");
                    passcheckhint.setVisibility(View.VISIBLE);

                    passifcheckbool = false;
                }

                if(s.length() > 6){
                    passsix.setTextColor(passprimary);
                    passsixcheck.setVisibility(View.INVISIBLE);
                }
                else if(s.length() == 0){
                    passifcheckbool = false;
                }

                 if(s.length() > 6  && passsix.getText().toString().equals(passcheck.getText().toString()) ){
                 passsix.setTextColor(passprimary);
                 passsixcheck.setVisibility(View.INVISIBLE);


                  passcheck.setTextColor(passprimary);
                  passcheckhint.setText("비밀번호 일치");
                  passcheckhint.setTextColor(passprimary);

                  passifcheckbool = true;

                }
                else if(s.length() > 6 && !passsix.getText().toString().equals(passcheck.getText().toString())){
                    passcheck.setTextColor(passcolorRed);
                    passcheckhint.setTextColor(passcolorRed);
                    passcheckhint.setText("비밀번호 불일치");
                    passifcheckbool = false;
                    }

                else if(!passsix.getText().toString().equals(passcheck.getText().toString())){
                    passcheck.setTextColor(passcolorRed);
                    passcheckhint.setTextColor(passcolorRed);
                    passcheckhint.setText("비밀번호 불일치");
                    passifcheckbool = false;
                }


            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        //비밀번호

        //비밀번호 확인 영역
        passcheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(passsix.getText().length() < 2 && passsixcheck.getText().length() < 2){
                    passsixcheck.setVisibility(View.INVISIBLE);
                    passcheckhint.setVisibility(View.INVISIBLE);
                }

                if(1 < s.length() || s.length() <= 6 && passcheck.getText().toString().equals(passcheck.getText().toString())){
                  /*  passsix.setTextColor(passcolorRed);
                    passsixcheck.setTextColor(passcolorRed);
                    passsixcheck.setText("비밀번호가 6자리 이하입니다.");
                    passsixcheck.setVisibility(View.VISIBLE);*/

                    passcheck.setTextColor(passcolorRed);
                    passcheckhint.setTextColor(passcolorRed);
                    passcheckhint.setText("비밀번호 일치하지만 6자리 이하입니다.");
                    passcheckhint.setVisibility(View.VISIBLE);

                    passifcheckbool = false;
                }

                if(!passsix.getText().toString().equals(passcheck.getText().toString())){
                    passcheck.setTextColor(passcolorRed);
                    passcheckhint.setTextColor(passcolorRed);
                    passcheckhint.setText("비밀번호 불일치");
                    passcheckhint.setVisibility(View.VISIBLE);
                    passcheckbool = false;
             }

                 if(s.length() > 6 && passsix.getText().toString().equals(passcheck.getText().toString()) && !passsix.getText().toString().equals("")){
                     passsix.setTextColor(passprimary);
                     passcheckhint.setVisibility(View.INVISIBLE);

                     passcheck.setTextColor(passprimary);
                     passcheckhint.setText("비밀번호 일치");
                     passcheckhint.setTextColor(passprimary);
                     passcheckhint.setVisibility(View.VISIBLE);
                    passcheckbool = true;

               }
               if(passcheck.getText().toString().equals("")){
                   passcheckbool = false;
               }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //비밀번호 확인 영역

        finalsignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //아이디 텍스트 조건
                if(idtext.getText().toString().equals("") && passsix.getText().toString().length() > 6 && !emailtext.getText().toString().equals("") && btncheck && passcheckbool){
                    Toast.makeText(EurnSignUp.this, "아이디 입력칸이 공백입니다.", Toast.LENGTH_SHORT).show();
                    idtext.requestFocus();
                }else if(!idtext.getText().toString().equals("") && passsix.getText().toString().length() < 7 && emailtext.getText().toString().equals("") && !btncheck){
                    Toast.makeText(EurnSignUp.this,"비밀번호가 6자리 이하입니다.", Toast.LENGTH_SHORT).show();
                    passsix.requestFocus();
                }else if(!idtext.getText().toString().equals("") && passsix.getText().toString().length() > 6 &&  !passcheckbool && emailtext.getText().toString().equals("") && !btncheck){
                    Toast.makeText(EurnSignUp.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    passcheck.requestFocus();
                }else if(!idtext.getText().toString().equals("") && passsix.getText().toString().length() > 6 && passcheckbool && emailtext.getText().toString().equals("") && !btncheck){
                    Toast.makeText(EurnSignUp.this,"이메일 입력칸이 공백입니다.",Toast.LENGTH_SHORT).show();
                    emailtext.requestFocus();
                }
                //아이디 텍스트 조건

                //비밀번호 텍스트 조건
                if(passsix.getText().toString().length() < 7 && btncheck && !idtext.getText().toString().equals("") && !emailtext.getText().toString().equals("") && passcheck.getText().toString().equals("")){
                    Toast.makeText(EurnSignUp.this, "비밀번호가 6자리 이하입니다.", Toast.LENGTH_SHORT).show();
                    passsix.requestFocus();
                }else if(passsix.getText().toString().length() > 6 && idtext.getText().toString().equals("") && !passifcheckbool && emailtext.getText().toString().equals("") && !btncheck){
                    Toast.makeText(EurnSignUp.this,"아이디 입력칸이 공백입니다.", Toast.LENGTH_SHORT).show();
                    idtext.requestFocus();
                }
                else if(passsix.getText().toString().length() < 7 && idtext.getText().toString().equals("") && !passifcheckbool && emailtext.getText().toString().equals("") && !btncheck){
                    Toast.makeText(EurnSignUp.this,"아이디 입력칸이 공백입니다.", Toast.LENGTH_SHORT).show();
                    idtext.requestFocus();
                }
                //비밀번호 텍스트 조건


                //비밀번호 확인 조건
                if(!passcheckbool && passsix.getText().toString().length() > 6 && !idtext.getText().toString().equals("") && !emailtext.getText().toString().equals("")){
                    Toast.makeText(EurnSignUp.this,"비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
                    passcheck.requestFocus();
                }
                else if(passcheckbool && idtext.getText().toString().equals("") && passsix.getText().toString().length() > 6 && emailtext.getText().toString().equals("") && !btncheck){
                    Toast.makeText(EurnSignUp.this,"아이디 입력칸이 공백입니다.",Toast.LENGTH_SHORT).show();
                    idtext.requestFocus();
                }else if(!passifcheckbool && passcheck.getText().toString().equals("") && passsix.getText().toString().length() > 6 && idtext.getText().toString().equals("")){
                    Toast.makeText(EurnSignUp.this,"아이디 입력칸이 공백입니다.",Toast.LENGTH_SHORT).show();
                    idtext.requestFocus();
                }
                else if(passcheck.getText().toString().equals("") && passsix.getText().toString().length() > 6){
                    Toast.makeText(EurnSignUp.this,"비밀번호 확인 입력칸이 공백입니다.",Toast.LENGTH_SHORT).show();
                    passcheck.requestFocus();
                }
               /* else if(passcheckbool && !idtext.getText().toString().equals("") && passsix.getText().toString().length() < 7 && emailtext.getText().toString().equals("") && !btncheck){
                    Toast.makeText(EurnSignUp.this,"비밀번호가 6자리 이하입니다.", Toast.LENGTH_SHORT).show();
                    passsix.requestFocus();}*/

                //비밀번호 확인 조건


                //이메일 확인 조건
                if(emailtext.getText().toString().equals("")&& passsix.getText().toString().length() > 6 && btncheck && passcheckbool){
                    Toast.makeText(EurnSignUp.this,"이메일 입력칸이 공백입니다.",Toast.LENGTH_SHORT).show();
                    emailtext.requestFocus();
                }else if(!emailtext.getText().toString().equals("") && idtext.getText().toString().equals("") && passsix.getText().toString().length() < 7 && !passcheckbool && !btncheck){
                    Toast.makeText(EurnSignUp.this,"아이디 입력칸이 공백입니다.",Toast.LENGTH_SHORT).show();
                    idtext.requestFocus();
                }
                //이메일 확인 조건


                //이용약관 동의 확인 조건
                if(!btncheck && passsix.getText().toString().length() > 6 && !idtext.getText().toString().equals("") && !emailtext.getText().toString().equals("") && passcheckbool) {
                    Toast.makeText(EurnSignUp.this, "이용약관 동의가 필요합니다.", Toast.LENGTH_SHORT).show();
                }else if(btncheck && idtext.getText().toString().equals("") && passsix.getText().toString().length() < 7 && !passcheckbool && emailtext.getText().toString().equals("")){
                    Toast.makeText(EurnSignUp.this, "아이디 입력칸이 공백입니다.", Toast.LENGTH_SHORT).show();
                    idtext.requestFocus();
                }
                //이용약관 동의 확인 조건

                //전체
                if(idtext.getText().toString().equals("") && passsix.getText().toString().equals("") && passcheck.getText().toString().equals("") && emailtext.getText().toString().equals("") && !btncheck){
                    Toast.makeText(EurnSignUp.this,"전체 빈칸입니다. 모두 바르게 입력해주세요.", Toast.LENGTH_SHORT).show();
                    idtext.requestFocus();
                }
                else if(passsix.getText().toString().length() < 7 && !passcheckbool && idtext.getText().toString().equals("") && emailtext.getText().toString().equals("") && !btncheck){
                    Toast.makeText(EurnSignUp.this,"비밀번호 입력 형식이 옳바르지 않습니다.", Toast.LENGTH_SHORT).show();
                    passsix.requestFocus();
                }
                else if(passsix.getText().toString().length() < 7 && !passcheckbool && !idtext.getText().toString().equals("") && !emailtext.getText().toString().equals("") && btncheck){
                    Toast.makeText(EurnSignUp.this,"비밀번호 입력 형식이 옳바르지 않습니다.", Toast.LENGTH_SHORT).show();
                    passsix.requestFocus();
                }

                //전체

                //회원가입 조건 모두 충족
                else if(passsix.getText().toString().length() > 6 && btncheck && !idtext.getText().toString().equals("") && !emailtext.getText().toString().equals("") && passcheckbool){
                    Toast.makeText(EurnSignUp.this, "회원가입을 축하드립니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EurnSignUp.this, FirstMain.class);
                    setContentView(R.layout.login_layout);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    finish();
                }
            }
        });




    }
}
