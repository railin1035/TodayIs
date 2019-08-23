package com.ghc.tdi_main.Display;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ghc.tdi_main.Main.activity_main;
import com.ghc.tdi_main.R;

import java.util.ArrayList;

public class Display_main  extends AppCompatActivity {


    MenuItem wid,the,color;
    LinearLayout widli, widtheme, widback;
    ImageButton widgetButton,themeButton,backButton;

    private static String TAG = "recyclerview_example";

    private ArrayList<Dictionary> mArrayList, themeArrayList;
    private CustomAdapter mAdapter, themeAdapter;
    private RecyclerView mRecyclerView, themeRecyclerView;
    private LinearLayoutManager mLinearLayoutManager, themeLinearLayoutManager, backLinearLayoutManager;
    private ConstraintLayout backcon;



    private int count = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_main);
        // 액션바를 삭제시키고 넣을 툴바를 생성, 배치
        Toolbar tb = (Toolbar) findViewById(R.id.app_toolbar) ;
        setSupportActionBar(tb);


        widli = (LinearLayout) findViewById(R.id.widgetLayout);
        widtheme = (LinearLayout) findViewById(R.id.themeLayout);
        widback = (LinearLayout) findViewById(R.id.backLayout);

        widgetButton = (ImageButton) findViewById(R.id.widgetButton);
        widgetButton.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view){
                widli.setVisibility(View.GONE);
                ((TextView)findViewById(R.id.textView)).setText("먹혀부렸스") ;
            }
        });

        themeButton = (ImageButton) findViewById(R.id.themeButton);
        themeButton.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view){
                widtheme.setVisibility(View.GONE);
                ((TextView)findViewById(R.id.textView)).setText("테마먹혀부렸스") ;
            }
        });

        backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view){
                widback.setVisibility(View.GONE);
                ((TextView)findViewById(R.id.textView)).setText("배경먹혀부렸스") ;
            }
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_listwid);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        themeRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_listtheme);
        themeLinearLayoutManager = new LinearLayoutManager(this);
        themeRecyclerView.setLayoutManager(themeLinearLayoutManager);




        mArrayList = new ArrayList<>();
        themeArrayList = new ArrayList<>();




        // RecyclerView를 위해 CustomAdapter를 사용합니다.
        mAdapter = new CustomAdapter(mArrayList);
        mRecyclerView.setAdapter(mAdapter);


        themeAdapter = new CustomAdapter(themeArrayList);
        themeRecyclerView.setAdapter(themeAdapter);


        // RecyclerView의 줄(row) 사이에 수평선을 넣기위해 사용됩니다.
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        DividerItemDecoration dividerItemDecoration2 = new DividerItemDecoration(themeRecyclerView.getContext(),
                themeLinearLayoutManager.getOrientation());
        themeRecyclerView.addItemDecoration(dividerItemDecoration2);

    }


    //메뉴바 디스플레이 인플레이트
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.display_appbar, menu );
        //getMenuInflater().inflate(R.menu.appbar_action, menu) ;


        widli.setVisibility(View.GONE);
        widtheme.setVisibility(View.GONE);
        widback.setVisibility(View.GONE);
        /*
        layout.setVisibility(View.VISIBLE); 해당뷰를 보여줌
        layout.setVisibility(View.INVISIBLE); //공간은 존재
        */

        wid = menu.findItem(R.id.widgets);
        the = menu.findItem(R.id.them);
        color = menu.findItem(R.id.colorpick);

        // Dictionary 생성자를 사용하여 ArrayList에 삽입할 데이터를 만듭니다.
        Dictionary dict = new Dictionary(count+"",
                "apple" + count, "사과" + count);
        //mArrayList.add(0, dict); //RecyclerView의 첫 줄에 삽입
        mArrayList.add(dict); // RecyclerView의 마지막 줄에 삽입
        mAdapter.notifyDataSetChanged(); //변경된 데이터를 화면에 반영

        // Dictionary 생성자를 사용하여 ArrayList에 삽입할 데이터를 만듭니다.
        Dictionary dict2 = new Dictionary(count+"",
                "apple" + count, "사과" + count);
        //mArrayList.add(0, dict); //RecyclerView의 첫 줄에 삽입
        themeArrayList.add(dict2); // RecyclerView의 마지막 줄에 삽입
        themeAdapter.notifyDataSetChanged(); //변경된 데이터를 화면에 반영




        return true ;
    }


    //아이콘 터치마다 발생할 이벤트 설정
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.noalls :

                Intent intent = new Intent(Display_main.this, activity_main.class);
                setContentView(R.layout.activity_main);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();


                Toast.makeText(this, "화면 종료", Toast.LENGTH_SHORT).show();
                
                return true ;

            case R.id.widgets :

                wid.setIcon(R.drawable.ic_widget_on);
                widli.setVisibility(View.VISIBLE);


                widtheme.setVisibility(View.GONE);
                widback.setVisibility(View.GONE);

                the.setIcon(R.drawable.ic_theme);
                color.setIcon(R.drawable.ic_colorpicker);

                ((TextView)findViewById(R.id.textView)).setText("ACCOUNT") ;
                return true ;

            case R.id.them :

                the.setIcon(R.drawable.ic_theme_on);
                widtheme.setVisibility(View.VISIBLE);

                widli.setVisibility(View.GONE);
                widback.setVisibility(View.GONE);

                color.setIcon(R.drawable.ic_colorpicker);
                wid.setIcon(R.drawable.ic_widget);

                ((TextView)findViewById(R.id.textView)).setText("SETTINGS") ;
                return true ;

            case R.id.colorpick:

                color.setIcon(R.drawable.ic_colorpicker_on);
                widback.setVisibility(View.VISIBLE);

                widli.setVisibility(View.GONE);
                widtheme.setVisibility(View.GONE);

                the.setIcon(R.drawable.ic_theme);
                wid.setIcon(R.drawable.ic_widget);

                ((TextView)findViewById(R.id.textView)).setText("Background") ;
                return true ;

            default :

                return super.onOptionsItemSelected(item) ;

        }
    }
}