package com.ghc.tdi_main.Display;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.ghc.tdi_main.Main.select_main;
import com.ghc.tdi_main.R;


public class Display_main  extends AppCompatActivity {

    //메뉴아이템 선언
    MenuItem wid,theme,color;

    //프라그먼트 클래스 객체화
    Display_theme display_theme;
    Display_color display_color;
    Display_widget display_widget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_main);
        // 액션바를 삭제시키고 넣을 툴바를 생성, 배치
        Toolbar tb = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(tb);


        wid = (MenuItem) findViewById(R.id.widgets);
        theme = (MenuItem) findViewById(R.id.them);
        color = (MenuItem) findViewById(R.id.colorpick);

        //fragemt 객체화
        display_theme = new Display_theme();
        display_color = new Display_color();
        display_widget = new Display_widget();


    }


    //메뉴바 디스플레이 인플레이트
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.display_appbar, menu );

        wid = menu.findItem(R.id.widgets);
        theme = menu.findItem(R.id.them);
        color = menu.findItem(R.id.colorpick);

        return true ;
    }


    //아이콘 터치마다 발생할 이벤트 설정
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //프라그먼트를 리플레이스 시키기 위한 프라그먼트 트렌잭션 선언
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (item.getItemId()) {

            case R.id.noalls :

                Intent intent = new Intent(Display_main.this, select_main.class);
                setContentView(R.layout.select_main);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();

                Toast.makeText(this, "화면 종료", Toast.LENGTH_SHORT).show();
                return true ;

            case R.id.widgets :

                wid.setIcon(R.drawable.ic_widget_on);

                //프레임 레이아웃에 display_widget를 리플레이스
                transaction.replace(R.id.framlayout, display_widget);
                transaction.commit();

                theme.setIcon(R.drawable.ic_theme);
                color.setIcon(R.drawable.ic_colorpicker);

                return true ;

            case R.id.them :

                theme.setIcon(R.drawable.ic_theme_on);

                //프레임 레이아웃에 display_theme를 리플레이스
                transaction.replace(R.id.framlayout, display_theme);
                transaction.commit();

                color.setIcon(R.drawable.ic_colorpicker);
                wid.setIcon(R.drawable.ic_widget);

                return true ;

            case R.id.colorpick:

                color.setIcon(R.drawable.ic_colorpicker_on);

                //프레임 레이아웃에 display_color를 리플레이스
                transaction.replace(R.id.framlayout, display_color);
                transaction.commit();

                theme.setIcon(R.drawable.ic_theme);
                wid.setIcon(R.drawable.ic_widget);

                return true ;

            default :
                return super.onOptionsItemSelected(item) ;

          }
      }


  //테마에서 닫기버튼 눌럯을때 display_theme를 삭제
    public void isclick(View view) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(display_theme);
        transaction.commit();
        theme.setIcon(R.drawable.ic_theme);
    }

    //테마에서 닫기버튼 눌럯을때 display_widget을 삭제
    public void widgetclick(View view) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(display_widget);
        transaction.commit();
        wid.setIcon(R.drawable.ic_widget);
}

    //테마에서 닫기버튼 눌럯을때 display_color를 삭제
    public void colorclick(View view) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(display_color);
        transaction.commit();
        color.setIcon(R.drawable.ic_colorpicker);
    }
}

