package com.ghc.tdi_main.Memo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.ghc.tdi_main.R;
import com.github.furkanozalp.colorpickerdialog.ClickListener;
import com.github.furkanozalp.colorpickerdialog.ColorPicker;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;
import top.defaults.colorpicker.ColorPickerPopup;

import static android.view.KeyEvent.KEYCODE_BACK;
import static android.view.KeyEvent.KEYCODE_ENTER;

public class memo_edit extends AppCompatActivity implements View.OnClickListener {
        /*컬러 세팅*/
        private static final String SAVED_STATE_KEY_COLOR = "saved_state_key_color";
        private static final int INITIAL_COLOR = 0xFF2785D0;
        private static final String DEFAULT_TEXT_COLOR = "#FF000000";
        private static final String DEFAULT_BG_COLOR = "#FFFFFFFF";
        private static final String DEFAULT_BORDER_COLOR = "#00FFFFFF";
        /*//컬러 세팅*/
        /*database*/
        private FirebaseDatabase mFirebase;
        private DatabaseReference databaseReference;

        EditText edit_Title;
        EditText edit_Content;
        TextView edit_Createdate;
        TextView edit_Editdate;
        LinearLayout layout;
        GradientDrawable noRoundBox;

        String title;
        String content;
        String date;
        String editdate;
        String targb = DEFAULT_TEXT_COLOR;
        String bargb = DEFAULT_BG_COLOR;
        String boargb = DEFAULT_BORDER_COLOR;
        String align;


        // 메모작성 함수
        public void writeNewMeMo(String title, String content, String create_day, String update_day, String targb, String bargb, String boargb, String align) {
                memo_list_items memo = new memo_list_items(title, content, create_day, update_day, targb, bargb, boargb, align);
                databaseReference.child("memo_list").push().setValue(memo);
                noRoundBox.setColor(Color.rgb(255,255,255));
        }

        // 색 넣기 함수
        public String colorChange(int color){
                int a = Color.alpha(color);
                int r = Color.red(color);
                int g = Color.green(color);
                int b = Color.blue(color);
                return String.format(Locale.getDefault(), "#%02X%02X%02X%02X",a, r, g, b);
        }
        /*database*/

        EditText contentsmemo, titlememo; // 제목 , 글
        View textsettingview, backgroundsettingview; // 텍스트 설정 박스, 배경 설정 박스
        ImageButton edit_bar_textbtn, edit_bar_backgroundbtn; // iconbar 버튼
        boolean textsettingbox = false, backgroundsettingbox = false; //text, background box on,off값

        /*select 컬러피커*/
        @BindView(R.id.edit_text_primarycolor)
        Button text_color_btn; // 텍스트 기본 색상
        @BindView(R.id.edit_background_primarycolor)
        Button background_color_btn; // 배경 기본 색상
        @BindView(R.id.edit_background_border_primarycolor)
        Button background_border_color_btn; // 테두리 기본 색상
        private ColorPicker text_select_colorpicker, background_select_colorpicker, background_border_select_colorpicker; // select 컬러피커
        /*//select 컬러피커*/
        @BindView(R.id.edit_text_size)
        Button text_size_btn; // 폰트 사이즈 버튼
        @BindView(R.id.edit_text_left)
        ImageView text_left_view; // 왼쪽 정렬 버튼
        @BindView(R.id.edit_text_center)
        ImageView text_center_view; // 중앙 정렬 버튼
        @BindView(R.id.edit_text_right)
        ImageView text_right_view; // 오른쪽 정렬 버튼
        @BindView(R.id.edit_text_colorpicker)
        ImageView text_colorpicker; // 텍스트 색상
        @BindView(R.id.edit_background_colorpicker)
        ImageView background_colorpicker; // 배경 색상
        @BindView(R.id.edit_background_border_colorpicker)
        ImageView border_colorpicker; // 테두리 색상

        private int edit_color; // 버튼 색상 가져오기1
        private boolean textpopup = false, backgroundpopup = false, borderpopup = false;
        private ColorDrawable colorDrawable; // 버튼 색상 가져오기2
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                Window win = getWindow();
                win.setContentView(R.layout.memo_edit_filed);
                ButterKnife.bind(this); //버터나이프 바인드
                Toolbar tb = (Toolbar) findViewById(R.id.memo_edit_app_toolbar);
                tb.setTitle("");
                setSupportActionBar(tb);
                /*//툴바*/
                ImageView backbtn;
                TextView tbackbtn;
                backbtn = (ImageView) findViewById(R.id.memo_edit_back_setting);
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

                tbackbtn = (TextView) findViewById(R.id.memo_edit_toolbar_title);
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

                databaseReference = FirebaseDatabase.getInstance().getReference();
                edit_Title = (EditText) findViewById(R.id.memo_edit_title);
                edit_Content = (EditText) findViewById(R.id.memo_edit_contents);
                edit_Createdate = (TextView) findViewById(R.id.memo_edit_day);
                edit_Editdate = (TextView) findViewById(R.id.memo_edit_day);

                layout = findViewById(R.id.edit_filled);
                noRoundBox = (GradientDrawable)layout.getBackground();

                // 시간 설정
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String getTime = sdf.format(date);
                edit_Createdate.setText(getTime);
                edit_Editdate.setText(getTime);

                /*iconbar 버튼 클릭 선언 및 오류처리*/
                textsettingview = findViewById(R.id.edit_text_setting);
                textsettingview.setVisibility(View.GONE);

                edit_bar_textbtn = findViewById(R.id.edit_bar_text);
                edit_bar_textbtn.setOnClickListener(this);
                backgroundsettingview = findViewById(R.id.edit_background_setting);
                backgroundsettingview.setVisibility(View.GONE);
                edit_bar_backgroundbtn = findViewById(R.id.edit_bar_background);
                edit_bar_backgroundbtn.setOnClickListener(this);
                /*//iconbar 버튼 클릭 선언 및 오류처리*/

                /*EditText 이벤트*/
                titlememo = findViewById(R.id.memo_edit_title);
                titlememo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                                HideBox();
                        }
                });
                contentsmemo = findViewById(R.id.memo_edit_contents);
                contentsmemo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                                HideBox();
                        }
                });
                /*//EditText 이벤트*/

                /*select 컬러피커*/
                text_select_colorpicker = new ColorPicker.Builder(this).setTitle("텍스트 색상").build();
                text_select_colorpicker.setClickListener(new ClickListener() {
                        @Override
                        public void onClick(int color) {
                                text_color_btn.setBackgroundColor(color);
                                int a = Color.alpha(color);
                                int r = Color.red(color);
                                int g = Color.green(color);
                                int b = Color.blue(color);
                                targb = String.format(Locale.getDefault(), "#%02X%02X%02X%02X",a, r, g, b);
                                edit_Content.setTextColor(Color.parseColor(targb));
                        }
                });
                findViewById(R.id.edit_text_primarycolor).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                text_select_colorpicker.show();
                        }
                });

                background_select_colorpicker = new ColorPicker.Builder(this).setTitle("배경 색상").build();
                background_select_colorpicker.setClickListener(new ClickListener() {
                        @Override
                        public void onClick(int color) {
                                background_color_btn.setBackgroundColor(color);
                                text_color_btn.setBackgroundColor(color);
                                int a = Color.alpha(color);
                                int r = Color.red(color);
                                int g = Color.green(color);
                                int b = Color.blue(color);
                                bargb = String.format(Locale.getDefault(), "#%02X%02X%02X%02X", a, r, g, b);
                                noRoundBox.setColor(Color.parseColor(bargb));
                        }
                });
                findViewById(R.id.edit_background_primarycolor).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                background_select_colorpicker.show();
                        }
                });
                background_border_select_colorpicker = new ColorPicker.Builder(this).setTitle("테두리 색상").build();
                background_border_select_colorpicker.setClickListener(new ClickListener() {
                        @Override
                        public void onClick(int color) {
                                background_border_color_btn.setBackgroundColor(color);
                                text_color_btn.setBackgroundColor(color);
                                int a = Color.alpha(color);
                                int r = Color.red(color);
                                int g = Color.green(color);
                                int b = Color.blue(color);
                                boargb = String.format(Locale.getDefault(), "#%02X%02X%02X%02X", a, r, g, b);
                        }
                });
                findViewById(R.id.edit_background_border_primarycolor).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                background_border_select_colorpicker.show();
                        }
                });
                /*//select 컬러피커*/
                /*폰트 크기 설정*/
                text_size_btn = findViewById(R.id.edit_text_size);
                text_size_btn.setOnClickListener(this);
                /*//폰트 크기 설정*/

                /*정렬 버튼*/
                text_left_view.setColorFilter(Color.argb(33, 0, 0, 0));
                text_left_view.setOnClickListener(this);

                text_center_view.setColorFilter(Color.argb(33, 0, 0, 0));
                text_center_view.setOnClickListener(this);

                text_right_view.setColorFilter(Color.argb(33, 0, 0, 0));
                text_right_view.setOnClickListener(this);
                /*//정렬 버튼*/
                /*커스텀 컬러*/
                text_colorpicker.setOnClickListener(this);
                background_colorpicker.setOnClickListener(this);
                border_colorpicker.setOnClickListener(this);
                int color = INITIAL_COLOR;
                if (savedInstanceState != null) {
                        color = savedInstanceState.getInt(SAVED_STATE_KEY_COLOR, INITIAL_COLOR);
                }
                text_color_btn.setBackgroundColor(color);
                background_color_btn.setBackgroundColor(color);
                background_border_color_btn.setBackgroundColor(color);
                /*//커스텀 컬러*/
        }  //class end

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
                                editdate = edit_Editdate.getText().toString();
                                writeNewMeMo(title, content, date, editdate, targb, bargb, boargb, align);

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
        /*요기부터*/
        private String colorHex(int color) {
                int a = Color.alpha(color);
                int r = Color.red(color);
                int g = Color.green(color);
                int b = Color.blue(color);
                return String.format(Locale.getDefault(), "0x%02X%02X%02X%02X", a, r, g, b);
        } // 사용자가 선택한 컬러 리턴

        //db 컬러 넣어야 하는부분

        public void HideKeyborad() {
                View view = getCurrentFocus();
                if (view != null) {
                        InputMethodManager inputManager = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
        } // 키보드 내리기

        public void HideBox() {
                if (textsettingbox == true) {
                        textsettingview.setVisibility(View.GONE);
                        edit_bar_textbtn.setBackgroundResource(R.drawable.default_text);
                        textsettingbox = false;
                } else if (backgroundsettingbox == true) {
                        backgroundsettingview.setVisibility(View.GONE);
                        edit_bar_backgroundbtn.setBackgroundResource(R.drawable.ic_colorpicker);
                        backgroundsettingbox = false;
                }
        } // EditText 포커스 오류처리 함수
        private void showAlertDialog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(memo_edit.this);
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.fontsize_dialog, null);
                builder.setView(view); // view 객체 선언
                final ListView listView = view.findViewById(R.id.font_dialog_list);
                final TextView dialog_close = view.findViewById(R.id.font_dialog_close);
                final AlertDialog dialog = builder.create(); // 다이얼로그 생성
                final ArrayList<String> Data = new ArrayList<String>();
                String font_num;
                for (int i = 1; i <= 50; i++) {
                        if (i < 10) {
                                font_num = String.valueOf(" " + i);
                        } else {
                                font_num = String.valueOf(i);
                        }
                        Data.add(font_num);
                } // 다이얼로그 데이터 리스트
                ArrayAdapter<String> adapter;
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Data);
                listView.setAdapter(adapter); // 데이터 받기
                text_size_btn = textsettingview.findViewById(R.id.edit_text_size);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                text_size_btn.setText(position + 1 + "");
                                dialog.cancel(); // 아이템 선택
                        }
                });
                dialog_close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                dialog.cancel();
                        }
                }); // 닫기
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = 600;
                lp.height = 870;
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                dialog.show();
                Window window = dialog.getWindow();
                window.setAttributes(lp);
        } // 다이얼로그 크기 설정

        @Override
        public void onClick(View v) {
                if(v.getId() == R.id.edit_text_left){
                        text_left_view.setColorFilter(Color.argb(255,39,132,208));
                }
                if(v.getId() == R.id.edit_text_center){
                        text_center_view.setColorFilter(Color.argb(255,39,132,208));
                }
                if(v.getId() == R.id.edit_text_right){
                        text_right_view.setColorFilter(Color.argb(255,39,132,208));
                }
                if(v.getId() == R.id.edit_bar_text){
                        HideKeyborad();
                        HideBox();
                        textsettingview.setVisibility(View.VISIBLE);
                        edit_bar_textbtn.setBackgroundResource(R.drawable.click_text);
                        textsettingbox=true;
                }
                if(v.getId() == R.id.edit_bar_background) {
                        HideKeyborad();
                        HideBox();
                        backgroundsettingview.setVisibility(View.VISIBLE);
                        edit_bar_backgroundbtn.setBackgroundResource(R.drawable.ic_colorpicker_on);
                        backgroundsettingbox=true;
                }
                if(v.getId() == R.id.edit_text_size){
                        showAlertDialog();
                }
                if(v.getId() == R.id.edit_text_colorpicker){
                        textpopup=true;
                        popup(v);
                }// custom 컬러피커
                if(v.getId() == R.id.edit_background_colorpicker){
                        backgroundpopup=true;
                        popup(v);
                }
                if(v.getId() == R.id.edit_background_border_colorpicker){
                        borderpopup=true;
                        popup(v);
                }
        } // Onclick
        public void popup(View v) {
                if(textpopup==true) {
                        colorDrawable = (ColorDrawable) text_color_btn.getBackground();
                        edit_color = colorDrawable.getColor();
                        new ColorPickerPopup.Builder(this)
                                .initialColor(edit_color)
                                .enableAlpha(true)
                                .onTitle("텍스트 색상")
                                .okTitle("확인")
                                .cancelTitle("취소")
                                .showIndicator(true)
                                .showValue(true)
                                .onlyUpdateOnTouchEventUp(true)
                                .build()
                                .show(new ColorPickerPopup.ColorPickerObserver() {
                                        @Override
                                        public void onColorPicked(int color) {
                                                text_color_btn.setBackgroundColor(color);
                                                int a = Color.alpha(color);
                                                int r = Color.red(color);
                                                int g = Color.green(color);
                                                int b = Color.blue(color);
                                                targb = String.format(Locale.getDefault(), "#%02X%02X%02X%02X",a, r, g, b);
                                                edit_Content.setTextColor(Color.parseColor(targb));
                                        }
                                });
                        textpopup=false;
                }
                else if(backgroundpopup==true){
                        colorDrawable = (ColorDrawable) background_color_btn.getBackground();
                        edit_color = colorDrawable.getColor();
                        new ColorPickerPopup.Builder(this)
                                .initialColor(edit_color)
                                .enableAlpha(true)
                                .onTitle("배경 색상")
                                .okTitle("확인")
                                .cancelTitle("취소")
                                .showIndicator(true)
                                .showValue(true)
                                .onlyUpdateOnTouchEventUp(true)
                                .build()
                                .show(new ColorPickerPopup.ColorPickerObserver() {
                                        @Override
                                        public void onColorPicked(int color) {
                                                background_color_btn.setBackgroundColor(color);
                                                int a = Color.alpha(color);
                                                int r = Color.red(color);
                                                int g = Color.green(color);
                                                int b = Color.blue(color);
                                                bargb = String.format(Locale.getDefault(), "#%02X%02X%02X%02X", a, r, g, b);
                                                noRoundBox.setColor(Color.parseColor(bargb));
                                        }
                                });
                        backgroundpopup=false;
                }
                else if(borderpopup==true){
                        colorDrawable = (ColorDrawable) background_border_color_btn.getBackground();
                        edit_color = colorDrawable.getColor();
                        new ColorPickerPopup.Builder(this)
                                .initialColor(edit_color)
                                .enableAlpha(true)
                                .onTitle("테두리 색상")
                                .okTitle("확인")
                                .cancelTitle("취소")
                                .showIndicator(true)
                                .showValue(true)
                                .onlyUpdateOnTouchEventUp(true)
                                .build()
                                .show(new ColorPickerPopup.ColorPickerObserver() {
                                        @Override
                                        public void onColorPicked(int color) {
                                                background_border_color_btn.setBackgroundColor(color);
                                                int a = Color.alpha(color);
                                                int r = Color.red(color);
                                                int g = Color.green(color);
                                                int b = Color.blue(color);
                                                boargb = String.format(Locale.getDefault(), "#%02X%02X%02X%02X", a, r, g, b);
                                        }
                                });

                        borderpopup=false;
                }
        }
}


