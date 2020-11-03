package com.consultoraestrategia.ss_crmeducativo.services.importar.util;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.importar.ui.ImportarJobService;
//import com.consultoraestrategia.ss_crmeducativo.util.IdGenerator;
//import com.consultoraestrategia.ss_crmeducativo.utils.AppMessengetNotification;
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
        Log.d("LlamarImportarService", tipoImportacion.toString());
       if(beVariables==null)return;
        Data data = new Data.Builder()
                .putAll(beVariables.convertData())
                .putString(ImportarJobService.ENUM_TIPOIMPORTACION, tipoImportacion.toString())
                .build();

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(ImportarJobService.class)
                .setInputData(data)
                .build();

        WorkManager.getInstance().enqueue(oneTimeWorkRequest);

    }



}