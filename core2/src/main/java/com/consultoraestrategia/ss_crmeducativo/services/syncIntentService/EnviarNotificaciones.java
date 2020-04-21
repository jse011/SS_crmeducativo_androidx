package com.consultoraestrategia.ss_crmeducativo.services.syncIntentService;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.android.gms.common.util.ArrayUtils;

import java.util.ArrayList;

public class EnviarNotificaciones extends Worker {

    private static final String TAG = EnviarNotificaciones.class.getSimpleName();
    public static final String COUNT_TAREA = "listaTarea";
    public static final String LISTA_EVALUACION = "listaEvaluacion";
    public static final String LISTA_CASOS = "listaCasos";
    public static final String LISTA_ASISTENCIA = "listaAsistencia";
    public static final String PROGRAMAEDUCATIVOID = "programaEducativoId";



    public EnviarNotificaciones(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    public static void start(Context context,
                             int programaEducativoId,
                             ArrayList<Integer> evaluacionIdList,
                             ArrayList<Integer> casoList,
                             int tareaCount,
                             ArrayList<Integer> asistenciaList){


        Data data = new Data.Builder()
                .putInt(COUNT_TAREA, tareaCount)
                .putInt(PROGRAMAEDUCATIVOID, programaEducativoId)
                .putIntArray(LISTA_EVALUACION, ArrayUtils.toPrimitiveArray(evaluacionIdList))
                .putIntArray(LISTA_CASOS, ArrayUtils.toPrimitiveArray(casoList))
                .putIntArray(LISTA_ASISTENCIA, ArrayUtils.toPrimitiveArray(asistenciaList))
                .build();

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(EnviarNotificaciones.class)
                .setInputData(data)
                .build();

        WorkManager.getInstance().enqueue(oneTimeWorkRequest);

    }



    @NonNull
    @Override
    public Result doWork() {
        try {
            codeYouWantToRun(getInputData());
            return  Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure();
        }

    }

    @Override
    public void onStopped() {
        super.onStopped();
        Log.d(TAG, "Servicio JOBSERVICE_EXPORTAR onStopped...");
    }



    private void codeYouWantToRun(Data parameters){
        Log.d(TAG,"codeYouWantToRun" );




    }


    /*@Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Servicio JOBSERVICE_EXPORTAR Creado...");
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.d(TAG,"onStartCommand" );
        if(workerThread == null || !workerThread.isAlive()){
            workerThread = new Thread(new Runnable(){
                public void run(){
                    //Aquí se realiza el trabajo del hilo secundario
                    codeYouWantToRun(intent);
                }
            });

            workerThread.start();
        }

        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Servicio JOBSERVICE_EXPORTAR destruido...");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
*/



}
