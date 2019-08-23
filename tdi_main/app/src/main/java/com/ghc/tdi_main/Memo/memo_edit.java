package com.ghc.tdi_main.Memo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghc.tdi_main.Main.activity_main;
import com.ghc.tdi_main.R;

public class memo_edit extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_edit_filed);
        Toolbar tb = (Toolbar) findViewById(R.id.app_toolbar);
        tb.setTitle("");
        setSupportActionBar(tb);
        /*//툴바*/
        ImageView backbtn;
        TextView tbackbtn;
        backbtn = (ImageView)findViewById(R.id.back_setting);


        /*뒤로가기 인텐트*/
        backbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(memo_edit.this, memo_list.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                }
        });
        tbackbtn=(TextView)findViewById(R.id.toolbar_title);
        tbackbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(memo_edit.this,memo_list.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                }
        });
        /*//뒤로가기 인텐트*/
}
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                getMenuInflater().inflate(R.menu.abbbar_memo_edit, menu);

                return true;
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item){
                switch(item.getItemId()){
                        case R.id.memo_storage:
                                break;
                        case R.id.memo_close:
                                Intent intent = new Intent(memo_edit.this,memo_list.class);
                                startActivity(intent);
                                overridePendingTransition(0, 0);
                                finish();
                                break;
                }
                return true;
        }
}
