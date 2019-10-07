package com.ghc.tdi_main.Settings;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ghc.tdi_main.Login.EurnSignUp;
import com.ghc.tdi_main.Main.select_main;

public class Setting_ScreenReceiver extends BroadcastReceiver {


    private KeyguardManager km = null;

    private KeyguardManager.KeyguardLock keyLock = null;


    @Override

    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {

            if (km == null)

                km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);

            if (keyLock == null)

                keyLock = km.newKeyguardLock(Context.KEYGUARD_SERVICE);
                keyLock.disableKeyguard();
                Intent i = new Intent(context, select_main.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                reenableKeyguard();
            if(keyLock != null)

                keyLock = km.newKeyguardLock(Context.KEYGUARD_SERVICE);
                disableKeyguard();
                Intent s = new Intent(context , EurnSignUp.class);
                s.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(s);
                reenableKeyguard();

        }
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            if (km == null)
                km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);

            if (keyLock == null)
                disableKeyguard();
                keyLock = km.newKeyguardLock(Context.KEYGUARD_SERVICE);
                Intent i = new Intent(context, select_main.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                reenableKeyguard();

        }
    }


    public void reenableKeyguard() {
        keyLock.reenableKeyguard();

    }


    public void disableKeyguard() {
        keyLock.disableKeyguard();
    }

}





