package com.consultoraestrategia.ss_crmeducativo.services.daemon.util;

import android.content.Context;
import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.services.daemon.ui.ExportarIntentService;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoExportacion;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

/**
 * Created by SCIEV on 13/06/2018.
 */

public class CallService {

    public static final String JOBSERVICE_EXPORTAR = "jobServiceExportar";
    public static final String JOBSERVICE_EXPORTAR_TIPOS = "jobServiceExportarTipos";

    public static void jobServiceExportarForever(Context context){
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));

        for (TipoExportacion tipoExportacion : TipoExportacion.values()) {
            dispatcher.cancel(tipoExportacion.toString());
        }

        Job job = dispatcher.newJobBuilder ()
                // persistir la tarea entre boots
                .setLifetime (Lifetime.FOREVER )
                //.setLifetime(Lifetime.UNTIL_NEXT_BOOT)
                // llamar a este servicio cuando se cumplan los criterios.
                .setService (ExportarIntentService.class )
                // id. único de la tarea
                .setTag(JOBSERVICE_EXPORTAR)
                // no sobrescribe un trabajo existente con la misma etiqueta
                .setReplaceCurrent(false)
                //Mencionamos que el trabajo es periódico.
                .setRecurring (true)
                // Ejecutar entre 30 y 60 segundos a partir de ahora.
                .setTrigger (Trigger. executionWindow(3600, 5400))
                // reintento con retroceso exponencial
                .setRetryStrategy (RetryStrategy.DEFAULT_LINEAR )
                //.setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                // Ejecutar este trabajo solo cuando la red está disponible.
                .setConstraints(
                        // only run on an unmetered network
                        Constraint.ON_UNMETERED_NETWORK
                        // only run when the device is charging
                        //Constraint.DEVICE_CHARGING
                )
                .build ();
        dispatcher.mustSchedule(job);
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
