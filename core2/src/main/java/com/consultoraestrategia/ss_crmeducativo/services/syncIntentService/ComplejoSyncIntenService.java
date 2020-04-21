package com.consultoraestrategia.ss_crmeducativo.services.syncIntentService;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.util.Log;

import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkerParameters;

import com.consultoraestrategia.ss_crmeducativo.core2.application.Core2;

public class ComplejoSyncIntenService extends SyncIntenService {

    @Override
    int getMaxSecondsInMillis() {
        return 240;
    }

    @Override
    int getCountInterval() {
        return 1000;
    }

    public ComplejoSyncIntenService(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    void sendFinish() {
        Intent bcIntent = new Intent();
        bcIntent.setAction(Core2.ACTION_FIN);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(bcIntent);
    }

    @Override
    public void onHandle(final Data data) {
        Log.d(TAG, "ImportarCountDownTimer service ");
/*
        serviceLocalDataRepository.getDatosExportarGlobal(new ServiceLocalDataRepository.CallBack<BEGuardarEntidadesGlobal>() {
            @Override
            public void onResponse(boolean success, BEGuardarEntidadesGlobal item) {
                if(success){
                    item.setProgramaEducativoId(data.getInt("programaEducativoId", 0));
                    serviceRemoteDataRepository.SendDatosGlobal(item, new ServiceRemoteDataRepository.RespuestaCallBack<BEGuardarEntidadesGlobal, BERespuesta>() {
                        @Override
                        public void onResponse(boolean success, BEGuardarEntidadesGlobal item, BERespuesta respuesta) {
                            if(success){

                                if(respuesta == null){
                                    showNotificacion(getApplicationContext(), NOTIFICATION_ID, "Error de conectividad","Los servidores han dectado un problema con los datos del CRMEducativo");
                                    return;
                                }
                                if(!respuesta.isCommit_Asistencia()){
                                    showNotificacionConectividad("De los registros de asistencia.");
                                }

                                if(!respuesta.isCommit_Grupo()){
                                    showNotificacionConectividad("De los registros de grupos.");
                                }

                                if(!respuesta.isCommit_Mensajes()){
                                    showNotificacionConectividad("De los registros de mensajes.");
                                }

                                if(!respuesta.isCommit_RubroEvaluacionProceso()){
                                    showNotificacionConectividad("De los registros de rubros.");
                                }

                                if(!respuesta.isCommit_Sesion()){
                                    showNotificacionConectividad("De los registros de sesión.");
                                }

                                if(!respuesta.isCommit_TareaRecurso()){
                                    showNotificacionConectividad("De los registros de tareas.");
                                }


                                saveCacheImage(respuesta);


                                initNotification(item, respuesta);

                                serviceLocalDataRepository.changeEstadoGlobal(item,respuesta, BaseEntity.FLAG_EXPORTED, new ServiceLocalDataRepository.SuccessCallBack() {
                                    @Override
                                    public void onResponse(boolean success) {
                                        if(!success){showNotificacion(getApplicationContext(),NOTIFICATION_IDESTADO,"Error interno","codigo del error E5t4Ad0");

                                        }
                                    }
                                });


                                serviceLocalDataRepository.saveDatosGlobal(respuesta, new ServiceDataSource.SuccessCallBack() {
                                    @Override
                                    public void onResponse(boolean success) {
                                        if(!success){
                                            showNotificacion(getApplicationContext(), NOTIFICATION_IDSAVE, "Error interno","codigo del error S4v3");
                                        }
                                    }
                                });




                            }else {
                                showNotificacion(getApplicationContext(), NOTIFICATION_ID, "Error de conectividad","CRMEducativo ha dectado un problema con los servidores");
                            }
                        }
                    });
                }else {
                    showNotificacion(getApplicationContext(), NOTIFICATION_ID, "Error de Conectividad","CRMEducativo ha dectado un problema con la red");
                }
            }
        });

        /*int iter = intent.getIntExtra("iteraciones", 0);
        Log.d(TAG, "Emprocreso...");
        for(int i=1; i<=iter; i++) {
            tareaLarga();

            //Comunicamos el progreso
            Intent bcIntent = new Intent();
            bcIntent.setAction(ACTION_PROGRESO);
            bcIntent.putExtra("progreso", i*10);
            Log.d(TAG, "progreso service : "+ i*10);
            sendBroadcast(bcIntent);
        }*/
    }


    public static void start(Context context, int programaEducativoId){


        Data data = new Data.Builder()
                .putInt("iteraciones", 10)
                .putInt("programaEducativoId", programaEducativoId)
                .build();

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(ComplejoSyncIntenService.class)
                .setInputData(data)
                .build();

        WorkManager.getInstance().beginUniqueWork(
                NAME_SERVICE_COMPLEJO,
                ExistingWorkPolicy.KEEP,
                oneTimeWorkRequest)
                .enqueue();
    }

}
