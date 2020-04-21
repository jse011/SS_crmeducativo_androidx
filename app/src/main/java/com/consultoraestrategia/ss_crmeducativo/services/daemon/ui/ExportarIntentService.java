package com.consultoraestrategia.ss_crmeducativo.services.daemon.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import androidx.core.app.NotificationCompat;
import android.util.Log;


import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.CRMEPresenter;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.CRMEPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.services.daemon.data.AdpaterEvalFormulaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.util.RepositoryInjector;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by SCIEV on 12/06/2018.
 */

public class ExportarIntentService extends JobService implements CRMEEvent {
    public static final int REQUEST_CODE = 12345;
    public final static String TAG = ExportarIntentService.class.getSimpleName();
    public static final String ENUM_TIPOEXPORTACION = "ExportarIntentService.TipoExportacion";
    private CRMEPresenterImpl presenter;
    private NotificationCompat.Builder builder;
    NotificationCompat.Builder notiCompat;
    public static final int NOTIF_ID = 68;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Servicio creado...");
        setupPresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        Log.d(TAG, "Servicio destruido...");
        presenter = null;
    }

    private void setupPresenter() {
        presenter = new CRMEPresenterImpl(RepositoryInjector.getBEDatosTareaRecursosRepositoryConRubros(),
                RepositoryInjector.getGEDatosEnvioAsistenciaRepositoryInjector(),
                RepositoryInjector.getGEDatosRubroEvaluacionProcesoRepositoryInjectorConTareas(),
                RepositoryInjector.getBEDatosSesionAprendizajeRepository(),
                RepositoryInjector.getBEbeDatosEnvioGrupoRepository(),
                new AdpaterEvalFormulaRepository(),
                RepositoryInjector.getBEDatosRubroEvaluacionProcesoRepositoryInjector(),
                RepositoryInjector.getBEDatosEnvioMensajeriaRepository());

        setPresenter(presenter);
    }


    @Override
    public void setPresenter(CRMEPresenter presenter) {
        presenter.attachView(this);
        presenter.onCreate();
    }
    @Override
    public void showNotificacionProgress(int icono, String titulo, String subtitulo, int max, int progress) {
        if(builder == null){
            builder = new NotificationCompat.Builder(this, CMRE.CHANNEL_ID);
            startForeground(1, builder.build());
        }
        builder.setSmallIcon(icono)
                .setContentTitle(titulo)
                .setContentText(subtitulo)
                .setProgress(max, progress, false);
    }

    @Override
    public void setNotificacionProgress(int max, int progress) {
        if( builder == null)return;
        builder.setProgress(max, progress, false);
        startForeground(1, builder.build());
    }

    @Override
    public void hideNotificacionProgress() {
        if( builder != null)stopForeground(true);
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

    @Override
    public void showNotificacion(int icono, String titulo, String subtitulo, String textoLargo, int usuarioId) {

        notiCompat = new NotificationCompat.Builder(this, CMRE.CHANNEL_ID)
                .setSmallIcon(R.drawable.logo_consultoraa)
                .setContentTitle(titulo)
                .setContentText(subtitulo)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(textoLargo));

        /*if(usuarioId!=-1){
            Intent intent = MainActivity.launchMainActivity(getBaseContext(),usuarioId);
            PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
            notiCompat.setContentIntent(pIntent);
        }*/

        Notification noti = notiCompat.build();

        // Hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(NOTIF_ID, noti);

    }

    @Override
    public void hideNotificacion() {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(NOTIF_ID);
    }


}
