package com.ghc.tdi_main.Display;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.support.v4.app.FragmentManager;


import com.ghc.tdi_main.Main.select_main;
import com.ghc.tdi_main.R;

import java.util.ArrayList;

public class Display_main  extends AppCompatActivity {

    MenuItem wid,theme,color;

    private static String TAG = "recyclerview_example";
    private int count = -1;

    Display_theme display_theme;
    Display_color display_color;
    Display_widget display_widget;
    //FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_main);
        // 액션바를 삭제시키고 넣을 툴바를 생성, 배치
        Toolbar tb = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(tb);


       // pager = (ViewPager) findViewById(R.id.pager);

        wid = (MenuItem) findViewById(R.id.widgets);
        theme = (MenuItem) findViewById(R.id.them);
        color = (MenuItem) findViewById(R.id.colorpick);

        //fragemt 객체화, 트랙잭션 선언

        display_theme = new Display_theme();
        display_color = new Display_color();
        display_widget = new Display_widget();

        //themebackbtns = (ImageButton)findViewById(R.id.themebackbtn);
       // themebackbtns.setOnClickListener();

    }


    //메뉴바 디스플레이 인플레이트
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.display_appbar, menu );
        //getMenuInflater().inflate(R.menu.appbar_action, menu) ;

        wid = menu.findItem(R.id.widgets);
        theme = menu.findItem(R.id.them);
        color = menu.findItem(R.id.colorpick);

        return true ;
    }


    //아이콘 터치마다 발생할 이벤트 설정
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

                transaction.replace(R.id.framlayout, display_widget);
                transaction.commit();

                theme.setIcon(R.drawable.ic_theme);
                color.setIcon(R.drawable.ic_colorpicker);

                return true ;

            case R.id.them :

                theme.setIcon(R.drawable.ic_theme_on);

                transaction.replace(R.id.framlayout, display_theme);
                transaction.commit();

                color.setIcon(R.drawable.ic_colorpicker);
                wid.setIcon(R.drawable.ic_widget);

                return true ;

            case R.id.colorpick:

                color.setIcon(R.drawable.ic_colorpicker_on);

                transaction.replace(R.id.framlayout, display_color);
                transaction.commit();

                theme.setIcon(R.drawable.ic_theme);
                wid.setIcon(R.drawable.ic_widget);

                return true ;

            default :
                return super.onOptionsItemSelected(item) ;

          }
      }



    public void isclick(View view) {


       // transaction.replace(R.id.framlayout, display_theme);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(display_theme);
        transaction.commit();
        theme.setIcon(R.drawable.ic_theme);
        Toast.makeText(getApplicationContext(),"메인에서먹힘",Toast.LENGTH_SHORT).show();
    }

    public void widgetclick(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(display_widget);
        transaction.commit();
        wid.setIcon(R.drawable.ic_widget);
        Toast.makeText(getApplicationContext(),"위젯먹힘",Toast.LENGTH_SHORT).show();
}


    public void colorclick(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(display_color);
        transaction.commit();
        color.setIcon(R.drawable.ic_colorpicker);
        Toast.makeText(getApplicationContext(),"컬러에서먹힘",Toast.LENGTH_SHORT).show();
    }
}

