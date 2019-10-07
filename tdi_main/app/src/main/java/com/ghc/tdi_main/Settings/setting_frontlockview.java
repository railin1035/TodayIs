package com.ghc.tdi_main.Settings;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.andrognito.patternlockview.utils.ResourceUtils;
import com.andrognito.rxpatternlockview.RxPatternLockView;
import com.andrognito.rxpatternlockview.events.PatternLockCompleteEvent;
import com.andrognito.rxpatternlockview.events.PatternLockCompoundEvent;
import com.ghc.tdi_main.Main.select_main;
import com.ghc.tdi_main.R;

import java.util.List;

import io.reactivex.functions.Consumer;

public class setting_frontlockview extends AppCompatActivity {
    private PatternLockView mPatternLockView;
    setting_frontlockview.patternHelper patternHelper;
    String pattern_save2;
    SQLiteDatabase sqlDB;
    int menubtnint = 0;



    private PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {
        @Override
        public void onStarted() {
            Log.d(getClass().getName(), "Pattern drawing started");
        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {
            Log.d(getClass().getName(), "Front Pattern progress: " +
                    PatternLockUtils.patternToString(mPatternLockView, progressPattern));
        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            Log.d(getClass().getName(), "Front Pattern complete: " +
                    PatternLockUtils.patternToString(mPatternLockView, pattern));

            pattern_save2 = PatternLockUtils.patternToString(mPatternLockView, pattern);
            Log.d(getClass().getName(), "pattern_save2: " + pattern_save2);

            sqlDB = patternHelper.getReadableDatabase();
            Cursor cursor;

            cursor = sqlDB.rawQuery("SELECT * FROM pass;", null);
            String culName = "";

            while (cursor.moveToNext()) {
                culName += cursor.getString(0); // culName =>데이터베이스에서 패턴 값 가져와 저장하는String
            }

            if (culName.equals(pattern_save2)) {
                Toast.makeText(getApplicationContext(), "패턴 일치.", Toast.LENGTH_SHORT).show();
                mPatternLockView.setCorrectStateColor(ResourceUtils.getColor(setting_frontlockview.this, R.color.colorWhite));

                menubtnint = 1;
                sqlDB.close();
                cursor.close();
                Log.d(getClass().getName(), "culName and pattern_save" + culName + " ---" + pattern_save2);
                Intent intent = new Intent(setting_frontlockview.this, select_main.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();


            } else if (!culName.equals(pattern_save2)) {
                Toast.makeText(getApplicationContext(), "패턴 불일치.", Toast.LENGTH_SHORT).show();
                mPatternLockView.setCorrectStateColor(ResourceUtils.getColor(setting_frontlockview.this, R.color.wrong_lock));

                sqlDB.close();
                cursor.close();
                Log.d(getClass().getName(), "culName and pattern_save" + culName + " ---" + pattern_save2);
            }
        }

        @Override
        public void onCleared() {
            Log.d(getClass().getName(), "Front Pattern has been cleared");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        setContentView(R.layout.lockscreen_frontview);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setFullScreen();

        patternHelper = new patternHelper(this);

        mPatternLockView = (PatternLockView) findViewById(R.id.patter_lock_view2);
        mPatternLockView.setDotCount(3);
        mPatternLockView.setDotNormalSize((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_dot_size));
        mPatternLockView.setDotSelectedSize((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_dot_selected_size));
        mPatternLockView.setPathWidth((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_path_width));
        mPatternLockView.setAspectRatioEnabled(true);
        mPatternLockView.setAspectRatio(PatternLockView.AspectRatio.ASPECT_RATIO_HEIGHT_BIAS);
        mPatternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
        mPatternLockView.setDotAnimationDuration(150);
        mPatternLockView.setPathEndAnimationDuration(100);
        mPatternLockView.setCorrectStateColor(ResourceUtils.getColor(this, R.color.colorPrimary));
        mPatternLockView.setInStealthMode(false);
        mPatternLockView.setTactileFeedbackEnabled(true);
        mPatternLockView.setInputEnabled(true);
        mPatternLockView.addPatternLockListener(mPatternLockViewListener);

        RxPatternLockView.patternComplete(mPatternLockView)
                .subscribe(new Consumer<PatternLockCompleteEvent>() {
                    @Override
                    public void accept(PatternLockCompleteEvent patternLockCompleteEvent) throws Exception {
                        Log.d(getClass().getName(), "Complete: " + patternLockCompleteEvent.getPattern().toString());
                    }

                });

        RxPatternLockView.patternChanges(mPatternLockView)
                .subscribe(new Consumer<PatternLockCompoundEvent>() {
                    @Override
                    public void accept(PatternLockCompoundEvent event) throws Exception {
                        if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_STARTED) {
                            Log.d(getClass().getName(), "Front Pattern drawing started2");
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_PROGRESS) {
                            Log.d(getClass().getName(), "Front Pattern progress2: " +
                                    PatternLockUtils.patternToString(mPatternLockView, event.getPattern()));
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_COMPLETE) {
                            Log.d(getClass().getName(), "Front Pattern complete: " +
                                    PatternLockUtils.patternToString(mPatternLockView, event.getPattern()));
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_CLEARED) {
                            Log.d(getClass().getName(), "Front Pattern has been cleared");
                        }
                    }
                });
    }


    public class patternHelper extends SQLiteOpenHelper {
        public patternHelper(Context context) {
            super(context, "pDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE pass ( patternNum TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS pass");
            onCreate(db);
        }
    }

    private void setFullScreen(){
        View view;
        view = findViewById(R.id.patter_lock_view2);
                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
        |View.SYSTEM_UI_FLAG_FULLSCREEN
        |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    public void onClikc_Main(View view){
        setFullScreen();
    }

    private void DismissKeyGuard() {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            if (pm.isInteractive()) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
            }
        } else{
            if(pm.isScreenOn()){
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
            }
        }
    }

   /*---------------홈키제어---------------*/
    @Override
    protected void onUserLeaveHint() {

            super.onUserLeaveHint();
    }

    /*---------------홈키제어---------------*/

    /*---------------뒤로가기, 메뉴 버튼 제어---------------*/
   @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
      // switch (keyCode)
            if(event.getKeyCode() == KeyEvent.KEYCODE_APP_SWITCH || event.getKeyCode() == KeyEvent.KEYCODE_BACK ) {
            // 로직 처리
                Toast.makeText(this, "뒤로가기 불가", Toast.LENGTH_SHORT).show();
                Log.d(getClass().getName(),"back!!!!!!!!!!!!!!!!!!!!!!");
                return true;
            }
            /*case KeyEvent.KEYCODE_BACK:
                Toast.makeText(this, "뒤로가기 눌림", Toast.LENGTH_SHORT).show();
                return false;*/
        return super.onKeyDown(keyCode, event);
    }

    /*---------------뒤로가기, 메뉴 버튼 제어---------------*/
    protected void onPause() {
        super.onPause();
        if(menubtnint != 1) {
            ActivityManager activityManager = (ActivityManager) getApplicationContext()
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.moveTaskToFront(getTaskId(), 0);
            Toast.makeText(this, "메뉴키 사용불가.", Toast.LENGTH_SHORT).show();
           //Log.d(getClass().getName(), "메뉴키 사용불가라고!!!!!!!!!!!!!!!!!!!!!!");
        }
    }

    @Override
   public void onStop() {
      //  Toast.makeText(this,"홈키",Toast.LENGTH_SHORT).show();

        super.onStop();

         }

    }
