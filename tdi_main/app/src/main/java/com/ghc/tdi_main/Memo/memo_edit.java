package com.ghc.tdi_main.Memo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghc.tdi_main.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;

public class memo_edit extends AppCompatActivity {
        /*database*/
        private FirebaseDatabase mFirebase;
        private DatabaseReference mDatabase;

        EditText edit_Title;
        EditText edit_Content;
        TextView edit_Date;

        String ID;
        String title;
        String content;
        String date;
        String editdate;
        public void writeNewMeMo(String title, String content, String create_day, String update_day){
                String key = mDatabase.child("memo_list").push().getKey();
                memo_list_items memo = new memo_list_items(title, content, create_day, update_day);
                mDatabase.child("memo_list").child("key").setValue(memo);
        }
        /*database*/
        @BindView(R.id.edit_iconbar_id) View testcionbar;
        private ImageView texticonimage;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window win = getWindow();
        win.setContentView(R.layout.memo_edit_filed);
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

        mDatabase = FirebaseDatabase.getInstance().getReference();
        edit_Title = (EditText)findViewById(R.id.memo_edit_title);
        edit_Content = (EditText)findViewById(R.id.memo_edit_contents);
        edit_Date = (TextView)findViewById(R.id.memo_edit_day);

        /*texticonimage = testcionbar.findViewById(R.id.edit_bar_text);
        texticonimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
        });*/
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
                                title = edit_Title.getText().toString();
                                content = edit_Content.getText().toString();
                                date = edit_Date.getText().toString();
                                editdate = edit_Date.getText().toString();
                                writeNewMeMo(title, content, date, editdate);
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
