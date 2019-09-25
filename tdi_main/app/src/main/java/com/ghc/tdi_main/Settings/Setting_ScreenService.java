package com.ghc.tdi_main.Settings;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class Setting_ScreenService extends Service {

    private Setting_ScreenReceiver mReceiver = null;

    @Override

    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override

    public void onCreate() {

        super.onCreate();

        mReceiver = new Setting_ScreenReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mReceiver, filter);

    }



    @Override

    public int onStartCommand(Intent intent, int flags, int startId){

        super.onStartCommand(intent, flags, startId);

        if(intent != null){

            if(intent.getAction()==null){

                if(mReceiver==null){
                    mReceiver = new Setting_ScreenReceiver();
                    IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
                    registerReceiver(mReceiver, filter);
                }
            }
        }

        return START_REDELIVER_INTENT;
    }



    @Override

    public void onDestroy(){
        super.onDestroy();

        if(mReceiver != null){
            unregisterReceiver(mReceiver);
            //리시버 완료되면 실행 중지 시킴
        }

    }

}


