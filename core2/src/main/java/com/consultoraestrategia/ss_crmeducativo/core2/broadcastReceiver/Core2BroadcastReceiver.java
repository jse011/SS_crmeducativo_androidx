package com.consultoraestrategia.ss_crmeducativo.core2.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.preference.PreferenceManager;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.SyncIntenService;

/**
 * Created by Jse on 27/12/2018.
 */

public class Core2BroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = Core2BroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        //SyncIntenService.start(context);
    }



}
