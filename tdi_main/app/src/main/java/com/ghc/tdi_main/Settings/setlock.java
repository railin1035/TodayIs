package com.ghc.tdi_main.Settings;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.andrognito.patternlockview.utils.ResourceUtils;
import com.andrognito.rxpatternlockview.RxPatternLockView;
import com.andrognito.rxpatternlockview.events.PatternLockCompleteEvent;
import com.andrognito.rxpatternlockview.events.PatternLockCompoundEvent;
import com.ghc.tdi_main.R;

import java.util.List;

import io.reactivex.functions.Consumer;

public class setlock extends AppCompatActivity {
    private PatternLockView mPatternLockView;
    ImageButton patbackbtn,patcheckbtn; /*뒤로가기, 확인*/
    String pattern_save;
    patternHelper patternHelper;
    SQLiteDatabase sqlDB;
    public static Context mContext;
    int finalpassint = 0;
    /*패턴 메소드*/
    private PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {
        @Override
        public void onStarted() {
            Log.d(getClass().getName(), "Pattern drawing started");
        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {
            Log.d(getClass().getName(), "Pattern progress: " +
                    PatternLockUtils.patternToString(mPatternLockView, progressPattern));

        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            Log.d(getClass().getName(), "Pattern complete: " +
                    PatternLockUtils.patternToString(mPatternLockView, pattern));
            pattern_save = PatternLockUtils.patternToString(mPatternLockView, pattern);
            Log.d(getClass().getName(), "패턴수집: " +
                    PatternLockUtils.patternToString(mPatternLockView, pattern));

            /*수집한거 데이터베이스에 테이블 만들고 그 테이블에 넣음 넣은 후
              테이블 컬럼의 숫자와 일치하면 화면을 넘겨줘야함.*/
        }

        @Override
        public void onCleared() {

            Log.d(getClass().getName(), "Pattern has been cleared");
        }
    };

    /*패턴 메소드*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lockscreen_view);


       patternHelper = new patternHelper(this);


        /*뒤로가기, 확인*/
        patbackbtn = (ImageButton)findViewById(R.id.patter_backbutton);
        patbackbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(setlock.this, setting_lock.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        patcheckbtn = (ImageButton)findViewById(R.id.patter_checkbutton);
        patcheckbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                sqlDB = patternHelper.getWritableDatabase();
                patternHelper.onUpgrade(sqlDB, 1, 2); // 인수는 아무거나 입력하면 됨.
                sqlDB.execSQL("INSERT INTO pass VALUES ( '"
                        + pattern_save +"');");
                sqlDB.close();
                /*setting_lock onChangedText에 int index값 1을 보내줌*/
                Toast.makeText(getApplicationContext(),"패턴이 저장되었습니다."+pattern_save,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(setlock.this,setting_lock.class);
                intent.putExtra("succesint",1);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        mPatternLockView = (PatternLockView) findViewById(R.id.patter_lock_view);
        mPatternLockView.setDotCount(3);
        mPatternLockView.setDotNormalSize((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_dot_size));
        mPatternLockView.setDotSelectedSize((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_dot_selected_size));
        mPatternLockView.setPathWidth((int) ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_path_width));
        mPatternLockView.setAspectRatioEnabled(true);
        mPatternLockView.setAspectRatio(PatternLockView.AspectRatio.ASPECT_RATIO_HEIGHT_BIAS);
        mPatternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
        mPatternLockView.setDotAnimationDuration(150);
        mPatternLockView.setPathEndAnimationDuration(100);
        mPatternLockView.setCorrectStateColor(ResourceUtils.getColor(this, R.color.colorWhite));
        mPatternLockView.setInStealthMode(false);
        mPatternLockView.setTactileFeedbackEnabled(true);
        mPatternLockView.setInputEnabled(true);
        mPatternLockView.addPatternLockListener(mPatternLockViewListener);



        RxPatternLockView.patternComplete(mPatternLockView)
                .subscribe(new Consumer<PatternLockCompleteEvent>() {
                    @Override
                    public void accept(PatternLockCompleteEvent patternLockCompleteEvent) throws Exception {
                        Log.d(getClass().getName(), "Completess2: " + patternLockCompleteEvent.getPattern().toString());
                        /*저장할 필요 없음.*/
                    }
                });

        RxPatternLockView.patternChanges(mPatternLockView)
                .subscribe(new Consumer<PatternLockCompoundEvent>() {
                    @Override
                    public void accept(PatternLockCompoundEvent event) throws Exception {
                        if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_STARTED) {
                            Log.d(getClass().getName(), "Pattern drawing startedssss22");
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_PROGRESS) {
                            Log.d(getClass().getName(), "Pattern progressesssss: " +
                                    PatternLockUtils.patternToString(mPatternLockView, event.getPattern()));
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_COMPLETE) {
                            Log.d(getClass().getName(), "Pattern completexssss: " +

                                    PatternLockUtils.patternToString(mPatternLockView, event.getPattern()));
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_CLEARED) {
                            Log.d(getClass().getName(), "Pattern has been clearedssses");


                        }
                    }
                });
    }


        public void serchPattern(){
            sqlDB = patternHelper.getReadableDatabase();
            Cursor cursor;

            cursor = sqlDB.rawQuery("SELECT * FROM pass;", null);
            String culName = "";

            while (cursor.moveToNext()) {
                culName += cursor.getString(0); // culName =>데이터베이스에서 패턴 값 가져와 저장하는String
            }
            if (culName.equals(pattern_save)) {
                Log.d(getClass().getName(),"패턴일치");
            } else if (!culName.equals(pattern_save)) {
                Log.d(getClass().getName(),"패턴불일치");
            }
            cursor.close();
            Log.d(getClass().getName(),"culName and pattern_save" + culName +" ---" + pattern_save);
        }

    /*-----------------------------------데이터베이스-------------------------------------------------------*/
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
    /*-------------------------------------------------------------------------------------------*/
}
