package com.consultoraestrategia.ss_crmeducativo.services.importar.util;

import android.content.Context;
import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.importar.ui.ImportarJobService;
import com.consultoraestrategia.ss_crmeducativo.util.IdGenerator;
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

public class LlamarImportarService {

    public static final String JOBSERVICE_IMPORTAR_TIPOS = "jobServiceExportarTipos";

    public static void jobServiceExportarTipos(Context context, TipoImportacion tipoImportacion, BEVariables beVariables){

        Bundle jobParameters = new Bundle();
       if(beVariables==null)return;
        jobParameters.putSerializable(ImportarJobService.ENUM_TIPOIMPORTACION, tipoImportacion.toString());
        beVariables.convertBundle(jobParameters);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
        Job job = dispatcher.newJobBuilder().
                setService(ImportarJobService.class).
                setLifetime(Lifetime.FOREVER).
                setExtras(jobParameters).
                setRecurring(false).
                setTag(IdGenerator.generateId()).
                setRetryStrategy(RetryStrategy.DEFAULT_LINEAR).
                setReplaceCurrent(false)
                //.setTrigger (Trigger. executionWindow(0, 1))
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .build();
        dispatcher.mustSchedule(job);
    }



}