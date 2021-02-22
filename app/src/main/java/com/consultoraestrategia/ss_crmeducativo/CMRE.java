package com.consultoraestrategia.ss_crmeducativo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.multidex.MultiDex;

import com.consultoraestrategia.ss_crmeducativo.calendarioPeriodo.CalendarioPeridoService;
import com.consultoraestrategia.ss_crmeducativo.core2.application.Core2;
import com.consultoraestrategia.ss_crmeducativo.main.ui.MainActivity;
import com.consultoraestrategia.ss_crmeducativo.services.cloudDataBase.crmeNotification.CrmeNotificationServiceImpl;

/**
 * Created by kelvi on 22/02/2017.
 */

public class CMRE extends Core2 {

    public static String CHANNEL_ID = "ChannelID";
    private CrmeNotificationServiceImpl crmeNotification;
    private final static String PREFERENCIA = "SyncIntenService";
    private final static String PREFERENCIA_KEY = "changeDataBase";
    private CalendarioPeridoService calendarioPeridoService;

    @Override
    public void onCreate() {
        super.onCreate();
        if(crmeNotification!=null)crmeNotification.onDestroy();
        crmeNotification = CrmeNotificationServiceImpl.init(this);
        calendarioPeridoService = CalendarioPeridoService.getInstance();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        super.onActivitySaveInstanceState(activity, bundle);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        super.onActivityStarted(activity);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        super.onActivityCreated(activity, bundle);
        if(activity instanceof CrmeNotificationServiceImpl.EventNotificacion){
            crmeNotification.setEventNotificacion((CrmeNotificationServiceImpl.EventNotificacion)activity);
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        super.onActivityResumed(activity);
        if(activity instanceof MainActivity){
            try {
                calendarioPeridoService.destroy();
                calendarioPeridoService.refresh();
                calendarioPeridoService.execute();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    //guardar la una intencion de cambio en el servicio
    public static void saveNotifyChangeDataBase(Context context){
        SharedPreferences prefs = context.
                getSharedPreferences(PREFERENCIA,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(PREFERENCIA_KEY, true);
        editor.apply();
    }

    //verificar intencion de cambio en el servicio
    public static boolean isChangeDataBase(Context context){
        SharedPreferences prefs =
                context.getSharedPreferences(PREFERENCIA,Context.MODE_PRIVATE);
        return prefs.getBoolean(PREFERENCIA_KEY, false);
    }
    //borrar la intencion de cambio de los servicios
    public static void succesNotifyChangeDataBase(Context context){
        SharedPreferences prefs = context.
                getSharedPreferences(PREFERENCIA,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(PREFERENCIA_KEY, false);
        editor.apply();
    }


}

