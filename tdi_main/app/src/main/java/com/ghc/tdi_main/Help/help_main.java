package com.ghc.tdi_main.Help;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ghc.tdi_main.Main.select_main;
import com.ghc.tdi_main.R;
import com.github.furkanozalp.colorpickerdialog.ClickListener;
import com.github.furkanozalp.colorpickerdialog.ColorPicker;

import java.util.ArrayList;
import java.util.Locale;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.defaults.colorpicker.ColorPickerPopup;

public class help_main extends AppCompatActivity {
    private static final String SAVED_STATE_KEY_COLOR = "saved_state_key_color";
    private static final int INITIAL_COLOR = 0xFF2785D0;
    @BindView(R.id.in_memo_edit_textsetting) View testView;
    @BindView(R.id.edit_text_primarycolor) Button testbtn;
    private TextView text;
    private ColorPicker colorPicker;
    private Button fontsizebtn;
    private ColorDrawable colorDrawable;
    private int bggcolor;
    private ImageView imageView,imageView2,imageView3;
    @OnClick({R.id.colorpickertest})
    void popup(View v) {
        colorDrawable =(ColorDrawable)testbtn.getBackground();
        bggcolor= colorDrawable.getColor();
        new ColorPickerPopup.Builder(this)
                .initialColor(bggcolor)
                .enableAlpha(true)
                .onTitle("텍스트 색")
                .okTitle("확인")
                .cancelTitle("취소")
                .showIndicator(true)
                .showValue(true)
                .onlyUpdateOnTouchEventUp(true)
                .build()
                .show(new ColorPickerPopup.ColorPickerObserver() {
                    @Override
                    public void onColorPicked(int color) {
                        testbtn.setBackgroundColor(color);
                    }
                });
    }
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_main);
    ButterKnife.bind(this);
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
            Intent intent = new Intent(help_main.this, select_main.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        }
    });
    tbackbtn=(TextView)findViewById(R.id.toolbar_title);
    tbackbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(help_main.this,select_main.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        }
    });
    /*//뒤로가기 인텐트*/
    text=findViewById(R.id.text_test);
    colorPicker= new ColorPicker.Builder(this).setTitle("텍스트 색").build();
    colorPicker.setClickListener(new ClickListener() {
        @Override
        public void onClick(int color) {
            text.setTextColor(color);
        }
    });
    findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            colorPicker.show();
        }
    });

    imageView2= testView.findViewById(R.id.edit_text_left);
    imageView2.setColorFilter(Color.argb(33,0,0,0));
    imageView2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            imageView2.setColorFilter(Color.argb(255,39,132,208));
        }
    });

    imageView=testView.findViewById(R.id.edit_text_center);
    imageView.setColorFilter(Color.argb(33,0,0,0));
    imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            imageView.setColorFilter(Color.argb(255,39,132,208));
        }
    });

    imageView3=testView.findViewById(R.id.edit_text_right);
    imageView3.setColorFilter(Color.argb(33,0,0,0));
    imageView3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            imageView3.setColorFilter(Color.argb(255,39,132,208));
        }
    });
    int color = INITIAL_COLOR;
    if (savedInstanceState != null) {
        color = savedInstanceState.getInt(SAVED_STATE_KEY_COLOR, INITIAL_COLOR);
    }
    testbtn.setBackgroundColor(color);
    final Button testdialog;
    testdialog=findViewById(R.id.testdialog);
    testdialog.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showAlertDialog();

        }
    });
}
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_STATE_KEY_COLOR, testbtn.getSolidColor());
    }
    private void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(help_main.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.fontsize_dialog,null);
        builder.setView(view);
        final ListView listView = view.findViewById(R.id.font_dialog_list);
        final TextView dialog_close = view.findViewById(R.id.font_dialog_close);
        final AlertDialog dialog = builder.create();
        final ArrayList<String> Data = new ArrayList<String>();
        String font_num;
        for(int i=1; i<=50; i++){
            if(i<10){
                font_num = String.valueOf(" "+i);
            }
            else {
                font_num = String.valueOf(i);
            }
            Data.add(font_num);
        }
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Data);
        listView.setAdapter(adapter);
        fontsizebtn = testView.findViewById(R.id.edit_text_size);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    fontsizebtn.setText(position+1+"");
                    dialog.cancel();
            }
        });
        dialog_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width=600;
        lp.height=870;
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.show();
        Window window = dialog.getWindow();
        window.setAttributes(lp);
    }
    /*요기부터*/
    private String colorHex(int color) {
        int a = Color.alpha(color);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        return String.format(Locale.getDefault(), "0x%02X%02X%02X%02X", a, r, g, b);
    }
    //db 컬러 넣어야 하는부분
        }