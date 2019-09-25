package com.ghc.tdi_main.Schedule;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghc.tdi_main.Main.select_main;
import com.ghc.tdi_main.R;

public class schedule_list extends AppCompatActivity {
    RecyclerView list_recycle;
    RecyclerView.LayoutManager schedule_LayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_list);
        /*툴바*/
        Toolbar tb = (Toolbar) findViewById(R.id.schedule_list_app_toolbar);
        tb.setTitle("");
        setSupportActionBar(tb);
        /*//툴바*/
        ImageView backbtn;
        TextView tbackbtn;
        backbtn = (ImageView) findViewById(R.id.schedule_list_back_setting);
        /*뒤로가기 인텐트*/
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(schedule_list.this, select_main.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        tbackbtn = (TextView) findViewById(R.id.schedule_list_toolbar_title);
        tbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(schedule_list.this, select_main.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        /*//뒤로가기 인텐트*/
        /*fab버튼*/
        FloatingActionButton schedule_fab_btn = (FloatingActionButton) findViewById(R.id.schedule_fab_btn);
        schedule_fab_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(schedule_list.this, schedule_input.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        }); /*//fab버튼*/
     /*   list_recycle = (RecyclerView) findViewById(R.id.schedule_list_recycle);
        list_recycle.setHasFixedSize(true);
        schedule_LayoutManager = new LinearLayoutManager(this);
        list_recycle.setLayoutManager(schedule_LayoutManager);*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.abbbar_search_add, menu);
        return true;
    }
}