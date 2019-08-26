package com.ghc.tdi_main.Memo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghc.tdi_main.R;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class memo_edit extends AppCompatActivity {

    private DatabaseReference databaseReference;

    EditText edit_Title;
    EditText edit_Content;
    TextView edit_Createdate;
    TextView edit_Editdate;

    String title;
    String content;
    String date;
    String editdate;

    public  static Context context;

    //데이터 추가
    public void writeNewMeMo(String title, String content, String create_day, String update_day) {
        memo_list_items memo = new memo_list_items(title, content, create_day, update_day);
        databaseReference.child("memo_list").push().setValue(memo);
    }

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
        backbtn = (ImageView) findViewById(R.id.back_setting);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        edit_Title = (EditText) findViewById(R.id.memo_edit_title);
        edit_Content = (EditText) findViewById(R.id.memo_edit_contents);
        edit_Createdate = (TextView) findViewById(R.id.memo_edit_day);
        edit_Editdate = (TextView)findViewById(R.id.memo_edit_day);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = sdf.format(date);
        edit_Createdate.setText(getTime);
        edit_Editdate.setText(getTime);

        context=this;

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
        tbackbtn = (TextView) findViewById(R.id.toolbar_title);
        tbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(memo_edit.this, memo_list.class);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.memo_storage:

                title = edit_Title.getText().toString();
                content = edit_Content.getText().toString();
                date = edit_Createdate.getText().toString();
                editdate =  edit_Editdate.getText().toString();
                writeNewMeMo(title, content, date, editdate);

                Intent intent2 = new Intent(memo_edit.this, memo_list.class);
                startActivity(intent2);
                overridePendingTransition(0, 0);
                finish();
                break;
            case R.id.memo_close:
                Intent intent = new Intent(memo_edit.this, memo_list.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                break;
        }
        return true;
    }


}
