package com.consultoraestrategia.ss_crmeducativo.services.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.importar.util.LlamarImportarService;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by irvinmarin on 20/08/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FirebaseMessagingSere";
    //private NotificacionesGrupoBuilder notificationGrupoBuilder;
    //private NotificacionesBuilder notificationBuilder;


    @Override
    public void onCreate() {
        super.onCreate();
        //notificationGrupoBuilder = NotificacionesGrupoBuilder.newInstance(getApplicationContext());
        //notificationBuilder = NotificacionesBuilder.newInstance(getApplicationContext());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public int parseInt(String o){
        try {
            return Integer.parseInt(o);
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        sendNotification("Notificaciones eliminadas");
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        Map<String, String> data = remoteMessage.getData();
        Log.d(TAG, "onMessageReceived: " + data.toString());

        if (data.size() > 0) {
            Log.d(TAG, "Message data payload: " + data);
            scheduleJob(remoteMessage);
        }

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        if(data.containsKey("messageGrupo")){
            //notificationGrupoBuilder.sendBundledNotification(data);
        }else if(data.containsKey("message")){
            //notificationBuilder.sendBundledNotification(data);
        }

    }

    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);
        sendRegistrationToServer(token);
    }

    private void scheduleJob(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        if (data.containsKey("user_id") && data.containsKey("package_id")) {
            String userId = data.get("user_id");
            String packageId = data.get("package_id");

            String cargaCursoId = data.get("carga_curso_id");
            String calendarioPeriodoId = data.get("calendario_periodo_id");
            String docenteId = data.get("docente_id");
            String silaboEventoId = data.get("silabo_evento_id");
            String sesionAprendizajeId = data.get("sesion_aprendizaje_id");
            String rubroEvalProcesoId = data.get("rubro_proceso_id");
            String unidadAprendizajeId = data.get("unidad_aprendizaje_id");
            String programaEducativoId = data.get("programa_educativo_id");
            String anioAcademicoId = data.get("anio_academico_id");

            Log.d(TAG, "handleImport: " + userId + ", " + packageId);
            Log.d(TAG, "params: " +
                    " carga_curso_id: " + cargaCursoId +
                    ", calendario_periodo_id: " + calendarioPeriodoId +
                    ", docente_id: " + docenteId +
                    ", silabo_evento_id: " + silaboEventoId +
                    ", sesion_aprendizaje_id: " + sesionAprendizajeId +
                    ", rubro_proceso_id: " + rubroEvalProcesoId +
                    ", unidad_aprendizaje_id: " + unidadAprendizajeId +
                    ", programa_educativo_id: " + programaEducativoId +
                    ", anio_academico_id: " + anioAcademicoId

            );

            SessionUser sessionUser = SessionUser.getCurrentUser();
            if(sessionUser == null)return;
            if(parseInt(userId)== sessionUser.getUserId()){

                BEVariables beVariables = new BEVariables();
                beVariables.setCalendarioPeriodoId(parseInt(calendarioPeriodoId));
                beVariables.setCargaCursoId(parseInt(cargaCursoId));
                beVariables.setDocenteId(parseInt(docenteId));
                beVariables.setUsuarioId(parseInt(userId));
                beVariables.setSilavoEventoId(parseInt(silaboEventoId));
                beVariables.setSesionEventoId(parseInt(sesionAprendizajeId));
                beVariables.setRubroEvaluacionId(rubroEvalProcesoId);
                beVariables.setUnidadAprendizajeId(parseInt(unidadAprendizajeId));
                beVariables.setProgramaEducativoId(parseInt(programaEducativoId));
                beVariables.setAnioAcademicoId(parseInt(anioAcademicoId));
                try {
                    switch (packageId){
                        case "bedatostarearecursos":
                            //LlamarImportarService.jobServiceExportarTipos(getBaseContext(),TipoImportacion.TAREA, beVariables);
                            break;
                        case "gedatosrubroevaluacionproceso":
                            //LlamarImportarService.jobServiceExportarTipos(getBaseContext(),TipoImportacion.RUBROEVALUACION, beVariables);
                            break;
                        case "gedatosenvioasistencia":
                            //LlamarImportarService.jobServiceExportarTipos(getBaseContext(),TipoImportacion.ASISTENCIA, beVariables);
                            break;
                        case "bedatossesionaprendizaje":
                            LlamarImportarService.jobServiceExportarTipos(getBaseContext(),TipoImportacion.SESIONES, beVariables);
                            break;
                        case "strategylogin":
                            //LoginImportarService.jobServiceLogin(getBaseContext());
                            break;
                        case "bedatosunidadprendizaje":
                            //LlamarImportarService.jobServiceExportarTipos(getBaseContext(),TipoImportacion.UNIDAD, beVariables);
                            break;
                        case "bedatostiponota":
                            ///LlamarImportarService.jobServiceExportarTipos(getBaseContext(),TipoImportacion.TIPONOTA, beVariables);
                            break;
                        case "bedatoscalendarioperiodo":
                            LlamarImportarService.jobServiceExportarTipos(getBaseContext(),TipoImportacion.CALENDARIO_PERIODO, beVariables);
                            break;
                        case "bedatosgrupoequipo":
                            //LlamarImportarService.jobServiceExportarTipos(getBaseContext(),TipoImportacion.CALENDARIO_PERIODO, beVariables);
                            break;
                        default:
                            break;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }


        }
    }


    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }


    private void sendRegistrationToServer(String token) {
        //  Implement this method to send token to your app server.
    }

    private void sendNotification2(RemoteMessage remoteMessage){
        String notification_title= remoteMessage.getNotification().getTitle();
        String notification_message=remoteMessage.getNotification().getBody();
        String click_action=remoteMessage.getNotification().getClickAction();

        String from_user_id=remoteMessage.getData().get("from_user_id");
        //Log.e("from_user_id in FMS is:",from_user_id);
        String channelId = getString(R.string.default_notification_channel_id);
        //----BUILDING NOTIFICATION LAYOUT----
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this,channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(notification_title)
                .setContentText(notification_message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        //--CLICK ACTION IS PROVIDED---
        Intent resultIntent = new Intent(click_action);
        resultIntent.putExtra("user_id",from_user_id);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,0,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);


        int mNotificationId=(int)System.currentTimeMillis();
        NotificationManager mNotifyManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mNotifyManager.notify(mNotificationId,mBuilder.build());
    }

    private void sendNotification(String messageBody) {
        //Intent intent = new Intent(this, MainActivity.class);
       // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
       // PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
         //       PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_messenger)
                        .setContentTitle("FCM Message")
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri);
                        //.setContentIntent(pendingIntent);




        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        int mNotificationId=(int)System.currentTimeMillis();
        notificationManager.notify(mNotificationId /* ID of notification */, notificationBuilder.build());
    }

}
