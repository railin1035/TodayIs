package com.ghc.tdi_main.Memo;

import android.content.Context;
import android.content.Intent;
<<<<<<< HEAD
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
=======
import android.graphics.Color;
>>>>>>> develop
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
<<<<<<< HEAD
import android.util.Log;
import android.view.ContextMenu;
=======
import android.view.LayoutInflater;
>>>>>>> develop
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghc.tdi_main.Main.select_main;
import com.ghc.tdi_main.R;
import com.ghc.tdi_main.Memo.memo_list_items;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class memo_list extends AppCompatActivity {
    RecyclerView list_recycle;
    RecyclerView.LayoutManager myLayoutManager;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    memo_list_adapter list_adapter;
    ArrayList<memo_list_items> memo_arraylist;
    public static Context mContext;


<<<<<<< HEAD
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_list);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        mContext = this;
        /*리사이클뷰*/
        list_recycle = (RecyclerView) findViewById(R.id.list_recycle);
        list_recycle.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this);
        list_recycle.setLayoutManager(myLayoutManager);

        /*툴바*/
        Toolbar tb = (Toolbar) findViewById(R.id.app_toolbar);
        tb.setTitle("");
        setSupportActionBar(tb);
        /*//툴바*/
        ImageView backbtn;
        TextView tbackbtn;
        backbtn = (ImageView) findViewById(R.id.back_setting);
        /*뒤로가기 인텐트*/
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(memo_list.this, activity_main.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        tbackbtn = (TextView) findViewById(R.id.toolbar_title);
        tbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(memo_list.this, activity_main.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        /*//뒤로가기 인텐트*/
        /*fab버튼*/
        FloatingActionButton memo_fab_btn = (FloatingActionButton) findViewById(R.id.memo_fab_btn);
        memo_fab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(memo_list.this, memo_edit.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        /*fab버튼*/

        /*리사이클뷰*/
        memo_arraylist = new ArrayList<>();
        list_adapter = new memo_list_adapter(memo_arraylist);
        list_recycle.setAdapter(list_adapter);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("memo_list");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    memo_list_items data_memo = data.getValue(memo_list_items.class);
                    memo_arraylist.add(data_memo);
                    list_adapter.notifyItemInserted(memo_arraylist.size()-1);
                }
                list_adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    /*@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0,1,100,"즐겨찾기");
        menu.add(0,2,100,"삭제");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case 1 ://즐겨찾기
                Toast.makeText(getApplicationContext(), "즐겨찾기 추가",
                        Toast.LENGTH_SHORT).show();
                break;
            case 2 ://삭제
                memo_arraylist.remove(list_adapter);
                list_adapter.notifyItemRemoved(memo_arraylist.size()-1);
                list_adapter.notifyItemRangeChanged(,memo_arraylist.size());
                break;
        }
        return true;
    }*/

=======
    boolean bookmarkbool=false;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_list);
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    /*툴바*/
    Toolbar tb = (Toolbar) findViewById(R.id.memo_list_app_toolbar);
    tb.setTitle("");
    setSupportActionBar(tb);
    /*//툴바*/
    ImageView backbtn;
    TextView tbackbtn;
    backbtn = (ImageView)findViewById(R.id.memo_list_back_setting);
    /*뒤로가기 인텐트*/
    backbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(memo_list.this, select_main.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        }
    });
    tbackbtn=(TextView)findViewById(R.id.memo_list_toolbar_title);
    tbackbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(memo_list.this,select_main.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        }
    });
    /*//뒤로가기 인텐트*/
    /*fab버튼*/
    FloatingActionButton memo_fab_btn = (FloatingActionButton)findViewById(R.id.memo_fab_btn);
    memo_fab_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(memo_list.this, memo_edit.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        }
    });
    /*fab버튼*/
    /*리사이클뷰*/
    list_recycle=(RecyclerView)findViewById(R.id.list_recycle);
    list_recycle.setHasFixedSize(true);
    myLayoutManager = new LinearLayoutManager(this);
    list_recycle.setLayoutManager(myLayoutManager);


    ArrayList<memo_list_items> memo_items = new ArrayList<>();
    memo_items.add(new memo_list_items("롸?","루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?루?","2019.08.04","2019.08.08"));
    memo_items.add(new memo_list_items("롸?","루?","2019.08.04","2019.08.08"));
    memo_items.add(new memo_list_items("롸?","루?","2019.08.04","2019.08.08"));

    memo_list_adapter list_adapter = new memo_list_adapter(memo_items);
    list_recycle.setAdapter(list_adapter);
    /*리사이클뷰*/
}
>>>>>>> develop
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.abbbar_search_add, menu);

        return true;
    }

    @Override
<<<<<<< HEAD
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.created_alignment:
                break;
            case R.id.updated_alignment:
                break;
            case R.id.help:
                break;
            case R.id.delete:
                /*databaseReference.child("memo1").removeValue();
                list_adapter.notifyItemRemoved();*/
                return true;
        }
        return true;
=======
    public boolean onOptionsItemSelected(MenuItem item){
    switch(item.getItemId()){
        case R.id.created_alignment:
            /*팝업 레이아웃 */
            final ImageButton bookmark_btn,remove_btn;
            LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout li =(LinearLayout)inflater.inflate(R.layout.memo_list_popmenu,null);
            li.setBackgroundColor(Color.parseColor("#99000000"));
            LinearLayout.LayoutParams layparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            addContentView(li,layparams);
            /*//팝업 레이아웃 */
            /*즐겨찾기 버튼*/
            bookmark_btn = findViewById(R.id.memo_bookmark_btn);
            bookmark_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(bookmarkbool == false) {
                        bookmark_btn.setBackgroundResource(R.drawable.click_star);
                        bookmarkbool = true;
                    }
                    else if(bookmarkbool == true){
                        bookmark_btn.setBackgroundResource(R.drawable.default_star);
                        bookmarkbool = false;
                    }
                }
            });
            /*//즐겨찾기 버튼*/
            /*삭제 버튼*/
            remove_btn = findViewById(R.id.memo_remove_btn);
            remove_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LinearLayout deleteli = findViewById(R.id.list_popmenu);
                    ((ViewManager)deleteli.getParent()).removeView(deleteli);
                }
            });
            /*//삭제 버튼*/
            break;
        case R.id.updated_alignment:
            break;
        case R.id.help:
            break;
    }
    return true;
>>>>>>> develop
    }
}