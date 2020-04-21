package com.consultoraestrategia.ss_crmeducativo.services.loginService.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.CMRE;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.main.ui.MainActivity;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.util.RepositoryInjector;
import com.consultoraestrategia.ss_crmeducativo.services.loginService.LoginPresenter;
import com.consultoraestrategia.ss_crmeducativo.services.loginService.LoginPresenterImpl;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by SCIEV on 12/06/2018.
 */

public class LoginJobService extends JobService implements LoginEvent {
    public final static String TAG = LoginJobService.class.getSimpleName();
    public static final int NOTIF_ID = 56;

    private LoginPresenterImpl presenter;

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
        presenter = new LoginPresenterImpl(RepositoryInjector.getBEDatosEnvioMensajeriaRepository(),
                RepositoryInjector.getBEDatosEnvioTipoNotaRepositoryInjector(),
                RepositoryInjector.getBEDatosRubroEvaluacionProcesoRepositoryInjector(),
                RepositoryInjector.getBEDatosSilaboEventoEnvioRepository(),
                RepositoryInjector.getSEDatosCompletosLoginRepository(),
                RepositoryInjector.getBEDatosEnvioAsistenciaRepository(),
                RepositoryInjector.getBEbeDatosEnvioGrupoRepository());


        setPresenter(presenter);
    }


    @Override
    public void setPresenter(LoginPresenter presenter) {
        presenter.attachView(this);
        presenter.onCreate();
    }
    @Override
    public void showNotificacionProgress(int icono, String titulo, String subtitulo, String textoLargo, int usuarioId) {

        NotificationCompat.Builder notiCompat = new NotificationCompat.Builder(this, CMRE.CHANNEL_ID)
                        .setSmallIcon(R.drawable.logo_consultoraa)
                        .setContentTitle(titulo)
                        .setContentText(subtitulo)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(textoLargo));
        if(usuarioId!=-1){
            Intent intent = MainActivity.launchMainActivity(getBaseContext(),usuarioId);
            PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
            notiCompat.setContentIntent(pIntent);
        }

        Notification noti = notiCompat.build();

        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIF_ID, noti);
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
