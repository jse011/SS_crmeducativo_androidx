package com.consultoraestrategia.ss_crmeducativo.services.importar.ui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.main.ui.MainActivity;
import com.consultoraestrategia.ss_crmeducativo.services.cloudDataBase.crmeNotification.CrmeNotificationServiceImpl;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.util.RepositoryInjector;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.TipoImportacion;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.importar.ImportarPresenter;
import com.consultoraestrategia.ss_crmeducativo.services.importar.ImportarPresenterImpl;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by SCIEV on 12/06/2018.
 */

public class ImportarJobService extends JobService implements ImportarEvent {

    public static final String REQUEST_STRING = "myRequest";
    public static final String RESPONSE_STRING = "myResponse";
    public static final String RESPONSE_BOOLEAN = "myResponse.boolean";
    public static final String RESPONSE_MESSAGE = "myResponseMessage";



    public final static String TAG = ImportarJobService.class.getSimpleName();
    public static final String ENUM_TIPOIMPORTACION = "ImportarJobService.TipoImportacion";
    public static final String ENUM_IMPORTARWRAPPER = "ImportarJobService.ImportarWrapper";
    public static final int NOTIF_ID = 56;

    private ImportarPresenterImpl presenter;
    private NotificationCompat.Builder builder;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Servicio creado...");
        setupPresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Servicio destruido...");
        presenter = null;
    }

    private void setupPresenter() {
        presenter = new ImportarPresenterImpl(RepositoryInjector.getBEDatosTareaRecursosRepositoryConRubros(),
                RepositoryInjector.getGEDatosEnvioAsistenciaRepositoryInjector(),
                RepositoryInjector.getGEDatosRubroEvaluacionProcesoRepositoryInjectorConTareas(),
                RepositoryInjector.getBEDatosSesionAprendizajeRepository(),
                RepositoryInjector.getBEDatosEnvioTipoNotaRepository(),
                RepositoryInjector.getBEDatosEnvioMensajeriaRepository(),
                RepositoryInjector.getBEDatosCargaAcademicaRepository()
        );

        setPresenter(presenter);
    }


    @Override
    public void setPresenter(ImportarPresenter presenter) {
        presenter.attachView(this);
        presenter.onCreate();
    }
    @Override
    public void showNotificacionProgress(int icono, String titulo, String subtitulo, String textoLargo, int usuarioId) {

        Intent intent = MainActivity.launchMainActivity(getBaseContext(),usuarioId);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification noti =
                new NotificationCompat.Builder(this, CMRE.CHANNEL_ID)
                        .setSmallIcon(R.drawable.logo_consultoraa)
                        .setContentTitle(titulo)
                        .setContentText(subtitulo)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(textoLargo))
                        .setContentIntent(pIntent)
                        .setColor(Color.parseColor("#2980b9"))
        //                .setVibrate(new long[] { 1000, 1000})
                        .build();

        noti.flags |= Notification.FLAG_AUTO_CANCEL;
        noti.defaults |= Notification.DEFAULT_LIGHTS;
        noti.sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CMRE.CHANNEL_ID;// The user-visible name of the channel.
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CMRE.CHANNEL_ID, name, importance);
            if(mNotificationManager!=null) mNotificationManager.createNotificationChannel(mChannel);
        }

        mNotificationManager.notify(NOTIF_ID, noti);
    }

    @Override
    public void llamarDefaultService() {

    }

    @Override
    public void onFinishMessaje(TipoImportacion tipoImportacion, boolean success, BEVariables importarWrapper) {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(CrmeNotificationServiceImpl.MyWebRequestReceiver.PROCESS_RESPONSE);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra(RESPONSE_BOOLEAN, success);
        broadcastIntent.putExtra(RESPONSE_MESSAGE, tipoImportacion.toString());
        Bundle bundle = new Bundle();
        importarWrapper.convertBundle(bundle);
        broadcastIntent.putExtras(bundle);
        sendBroadcast(broadcastIntent);
    }

    @Override
    public boolean onStartJob(final JobParameters job) {
        if(presenter!=null)presenter.setExtra(job.getExtras());
        new Thread(new Runnable() {
            @Override
            public void run() {
                codeYouWantToRun(job);
            }
        }).start();
        return true;
    }

    public void codeYouWantToRun(JobParameters parameters) {
           try {
               if(presenter!=null)presenter.onHandleIntent();
           }finally {
               jobFinished(parameters, false);
           }
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }


}
