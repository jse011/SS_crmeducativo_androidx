package com.consultoraestrategia.ss_crmeducativo.services.broadcastReciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.util.CallService;


/**
 * Created by SCIEV on 24/05/2018.
 */

public class Monitor extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        //CallService.jobServiceExportarForever(context);
    }
}
