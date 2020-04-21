package com.consultoraestrategia.ss_crmeducativo.main.dialogAlarma;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.services.syncIntentService.ComplejoSyncIntenService;

public class AlertReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int programaEducativoId = intent.getIntExtra("programaEducativoId", 0);
        ComplejoSyncIntenService.start(context, programaEducativoId);
        CMRE.saveNotifyChangeDataBase(context);
       // Toast.makeText(context, "Sincronizando..." , Toast.LENGTH_SHORT).show();
    }
}
