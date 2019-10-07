package com.github.furkanozalp.colorpickerdialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class ColorPicker {

    private ImageView imgViewList[];
    private GridLayout.LayoutParams glp;
    private GridLayout gridLayout;
    private int itemCount;
    private Activity activity;
    private Random rnd;
    private Dialog dialog;
    private String title;
    private ClickListener colorClickListener;

    public static class Builder {

        private ImageView imgViewList[];
        private GridLayout gridLayout;
        private int itemCount = 9;
        private Activity activity;
        private Dialog dialog;
        private String title = "색";


        public Builder(Activity activity) {
            this.activity = activity;
            this.imgViewList = new ImageView[itemCount];
            this.dialog = new Dialog(activity);
            this.dialog.setContentView(R.layout.dialog_view);
            this.gridLayout = dialog.findViewById(R.id.gridLayout);

        }

        public Builder setTitle(String t) {
            title = t;
            return this;
        }

        public ColorPicker build() {
            return new ColorPicker(this);
        }
    }

    private ColorPicker(Builder builder) {
        activity = builder.activity;
        title = builder.title;
        imgViewList = builder.imgViewList;
        gridLayout = builder.gridLayout;
        itemCount = builder.itemCount;
        dialog = builder.dialog;
        setup();
    }

    private void setup(){

        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        TextView titleView = dialog.findViewById(R.id.title);
        titleView.setText(title);



        rnd = new Random();
        for (int i = 0; i < itemCount; i++)
        {
            int color=0;
            if(i==0) {
            color = Color.argb(255, 0, 0, 0);
        }
            if(i==1) {
            color = Color.argb(255, 250, 40, 40);
            }
            if(i==2) {
            color = Color.argb(255, 255, 136, 0);
            }
            if(i==3) {
             color = Color.argb(255, 250, 229, 40);
            }
            if(i==4) {
                color = Color.argb(255, 0, 181, 48);
            }
            if(i==5) {
                color = Color.argb(255, 46, 185, 255);
            }
            if(i==6) {
            color = Color.argb(255, 53, 96, 252);
            }
            if(i==7) {
             color = Color.argb(255, 163, 91, 235);
            }
            if(i==8) {
             color = Color.argb(255, 230, 62, 123);
            }

            ImageView imageView = new ImageView(activity);
            imageView.setImageDrawable(activity.getResources().getDrawable(R.drawable.bg));
            imageView.setBackgroundColor(color);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);


            glp = new GridLayout.LayoutParams();
            glp.setMargins(dpToPx(5), dpToPx(5), dpToPx(5), dpToPx(5));
            imageView.setLayoutParams(glp);
            imageView.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, activity.getResources().getDisplayMetrics());//dpToPx(70);
            imageView.getLayoutParams().width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, activity.getResources().getDisplayMetrics());//dpToPx(70);

            imgViewList[i] = imageView;
            gridLayout.addView(imageView);
            imgViewList[i].setOnClickListener(listener);

        }

        activity.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    private int dpToPx(int dp) {
        float density = this.activity.getResources()
                .getDisplayMetrics()
                .density;
        System.out.println(Math.round((float) dp * density)+"");
        return Math.round((float) dp * density);
    }

    public void show(){
        dialog.show();
    }

    private View.OnClickListener listener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            v.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.anim_item));
            ColorDrawable viewColor = (ColorDrawable) v.getBackground();
            int colorId = viewColor.getColor();

            if (colorClickListener != null)
                colorClickListener.onClick(colorId);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                   dialog.dismiss();
                }
            },300);


        }
    };

    public void setClickListener(ClickListener listener){
        this.colorClickListener = listener;
    }
}