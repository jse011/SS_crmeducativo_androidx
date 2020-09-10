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
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.services.data.local.ServiceLocalDataRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.remote.ServiceRemoteDataRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEGuardarEntidadesGlobal;

import java.util.List;

public class SimpleSyncIntenService extends SyncIntenService {

    String TAGSimple= SimpleSyncIntenService.class.getSimpleName();

    @Override
    int getMaxSecondsInMillis() {
        return 240;
    }

    @Override
    int getCountInterval() {
        return 1000;
    }

    public SimpleSyncIntenService(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    void sendFinish() {
        Intent bcIntent = new Intent();
        bcIntent.setAction(Core2.ACTION_SIMPLE_FIN);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(bcIntent);
    }

    @Override
    protected void onHandle(final Data data) {
        Log.d(TAGSimple, "ImportarCountDownTimer service ");
        List<String> tableChange = serviceLocalDataRepository.comprobrarCambiosBaseDatosDaocenteMentor();
        boolean isUpdateTable = tableChange.isEmpty();
        if(isUpdateTable){
            Log.d(TAG, "Sin cambios cancelar service");
            destruirTimer();
            sendFinish();
            return;
        }

        int programaEducativoId = data.getInt("programaEducativoId", 0);

        BEGuardarEntidadesGlobal beGuardarEntidadesGlobal = serviceLocalDataRepository.getDatosExportarGlobalSimple();
        beGuardarEntidadesGlobal.setProgramaEducativoId(programaEducativoId);

        serviceRemoteDataRepository.SendDatosGlobalSimple(beGuardarEntidadesGlobal, new ServiceRemoteDataRepository.RespuestaCallBack<BEGuardarEntidadesGlobal, BERespuesta>() {
            @Override
            public void onResponse(boolean success, BEGuardarEntidadesGlobal item, BERespuesta respuesta) {
                if(success){

                    if(respuesta == null){
                        showNotificacion(getApplicationContext(), NOTIFICATION_ID, "Error","Problema con los servidores");
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

                    initNotification(item, respuesta);

                    serviceLocalDataRepository.changeEstadoGlobal(item,respuesta, BaseEntity.FLAG_EXPORTED, new ServiceLocalDataRepository.SuccessCallBack() {
                        @Override
                        public void onResponse(boolean success) {
                            if(!success){
                                showNotificacion(getApplicationContext(),NOTIFICATION_IDESTADO,"Error interno","codigo del error E5t4Ad0");
                            }
                        }
                    });


                }
            }
        });

    }

    @Override
    public void onStopped() {
        super.onStopped();
    }

    public static void start(Context context, int programaEducativoId){
        /*Data data = new Data.Builder()
                .putInt("iteraciones", 10)
                .putInt("programaEducativoId", programaEducativoId)
                .build();

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(SimpleSyncIntenService.class)
                .setInputData(data)
                .build();

        WorkManager.getInstance().beginUniqueWork(
                NAME_SERVICE_SIMPLE,
                ExistingWorkPolicy.APPEND,
                oneTimeWorkRequest)
                .enqueue();*/

    }

}
