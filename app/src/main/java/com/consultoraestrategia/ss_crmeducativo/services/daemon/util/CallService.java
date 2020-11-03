package com.consultoraestrategia.ss_crmeducativo.services.daemon.util;

import android.content.Context;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoExportacion;

/**
 * Created by SCIEV on 13/06/2018.
 */

public class CallService {

    public static final String JOBSERVICE_EXPORTAR = "jobServiceExportar";
    public static final String JOBSERVICE_EXPORTAR_TIPOS = "jobServiceExportarTipos";

    public static void jobServiceExportarForever(Context context){

    }

    public static void jobServiceExportarTipos(Context context, TipoExportacion tipoExportacion){
        /*Bundle jobParameters = new Bundle();
        jobParameters.putSerializable(ExportarIntentService.ENUM_TIPOEXPORTACION, tipoExportacion.toString());
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
        dispatcher.cancel(tipoExportacion.toString());
        Job job = dispatcher.newJobBuilder().
                setService(ExportarIntentService.class).
                setLifetime(Lifetime.FOREVER).
                setExtras(jobParameters).
                setRecurring(false).
                setTag(JOBSERVICE_EXPORTAR_TIPOS).
                setRetryStrategy(RetryStrategy.DEFAULT_LINEAR).
                setReplaceCurrent(false)
                .setTrigger (Trigger. executionWindow(2, 5))
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .build();
        dispatcher.mustSchedule(job);*/






    }

}
