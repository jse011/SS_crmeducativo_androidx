package com.consultoraestrategia.ss_crmeducativo.login2.service2.worker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.core2.application.Core2;
import com.consultoraestrategia.ss_crmeducativo.dao.sessionUser.SessionUserDao;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.login2.data.preferent.LoginPreferentRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.data.preferent.LoginPreferentRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.Firebase.GetListaCambiosFB;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.GetCalendarioPeridoList;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.GetCalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.GetListActualizar;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.GetListServicioEnvio;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.GetPlanificarSinck;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.SavePlanificarSinck;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.servidorData.GetDatosServidor;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.servidorData.SaveDatosServidor;
import com.consultoraestrategia.ss_crmeducativo.login2.service2.Notify.NotifyImpl;
import com.consultoraestrategia.ss_crmeducativo.login2.service2.ServicesPresenter;
import com.consultoraestrategia.ss_crmeducativo.login2.service2.ServicesPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;

import java.util.List;

public class SynckService extends Worker implements ImportarCountThread.ImportarCountDownTimerCallback {
    private final static String TAG = "SynckServiceTAG";
    private final static String NAME_SERVICE_SIMPLE = "SERVICIO_SINCK_DOCENTE_MENTOR";
    private ImportarCountThread importarCountDownTimer;
    private int indentificador;

    public SynckService(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        startTimer();
        Result result = Result.failure();
        try {
            Log.d(TAG, "service init");
            SessionUserDao sessionUserDao = InjectorUtils.provideSessionUserDao();
            SessionUser sessionUser = sessionUserDao.getCurrentUser();
            if(sessionUser==null|| !sessionUser.isDataImported()){
                Log.d(TAG, "Cancelar service ");
            }else {
                onMain(getInputData());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        result = Result.success();
        sendFinish();
        destruirTimer();
        Log.d(TAG, "service finish");
        return result;
    }

    private void onMain(Data inputData) {
        Log.d(TAG, "comensar el service ");
        LoginDataRepository repositorio = new LoginDataRepositoryImpl(ApiRetrofit.getInstance(), InjectorUtils.provideSessionUserDao(), InjectorUtils.provideParametrosDisenioDao(), InjectorUtils.provideCursoDao(), InjectorUtils.provideAlumnoDao());
        LoginPreferentRepository preferentRepository = new LoginPreferentRepositoryImpl(getApplicationContext());
        List<String> tableChange = repositorio.comprobrarCambiosBaseDatosDaocenteMentor();
        boolean isUpdateTable = tableChange.isEmpty();
        if(isUpdateTable){
            Log.d(TAG, "Sin cambios cancelar service");
            sendFinish();
            return;
        }

        int programaEducativoId = inputData.getInt("programaEducativoId", 0);

        ServicesPresenter servicesPresenter = new ServicesPresenterImpl(null, getApplicationContext().getResources(),
                new GetListActualizar(repositorio),
                new GetListServicioEnvio(repositorio),
                new GetCalendarioPeriodo(repositorio),
                new GetDatosServidor(repositorio),
                new SaveDatosServidor(repositorio),
                new SavePlanificarSinck(repositorio),
                new GetPlanificarSinck(repositorio),
                new GetCalendarioPeridoList(repositorio),
                new GetListaCambiosFB(repositorio, preferentRepository, getApplicationContext()));


        //*** Enviado el Programa educativo al presenter
        CRMBundle crmBundle = new CRMBundle();
        crmBundle.setProgramaEducativoId(programaEducativoId);
        Bundle bundle = crmBundle.instanceBundle();
        bundle.putBoolean("IsServices", true);
        servicesPresenter.setExtras(bundle);
        showNotifyProgress();
        servicesPresenter.onCreate();
        servicesPresenter.onStartSynckService();
        hideNotifyProgress();
        Log.d(TAG, "service finish");
    }

    private void sendFinish() {
        Intent bcIntent = new Intent();
        bcIntent.setAction(Core2.ACTION_FIN);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(bcIntent);
    }

    public static void start(Context context, int programaEducativoId){
        Log.d(TAG, "service pre start ");
        Data data = new Data.Builder()
                .putInt("iteraciones", 10)
                .putInt("programaEducativoId", programaEducativoId)
                .build();

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(SynckService.class)
                .setInputData(data)
                .build();

        WorkManager.getInstance().beginUniqueWork(
                NAME_SERVICE_SIMPLE,
                ExistingWorkPolicy.APPEND,
                oneTimeWorkRequest)
                .enqueue();
    }

    @Override
    public void onImportarCountDownTimerCount(int counter) {

    }

    @Override
    public void onImportarCountDownTimerFinish() {

    }

    @Override
    public void onImportarCountDownProgress(int progress) {
        //Comunicamos el progreso
        Intent bcIntent = new Intent();
        bcIntent.setAction(Core2.ACTION_PROGRESO);
        bcIntent.putExtra("progreso", progress);
        Log.d(TAG, "progreso service : "+ progress);
        //sendBroadcast(bcIntent);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(bcIntent);
    }

    private void destruirTimer(){
        if(importarCountDownTimer != null)importarCountDownTimer.onDestroy();
        importarCountDownTimer = null;
    }

    private void startTimer(){
        if(importarCountDownTimer != null)importarCountDownTimer.onDestroy();
        importarCountDownTimer = new ImportarCountThread(240, 1000, this);
        importarCountDownTimer.start();
    }
    private void showNotifyProgress() {
        indentificador = NotifyImpl.Companion.ServiceProgress(getApplicationContext());
    }

    private void hideNotifyProgress(){
        NotifyImpl.Companion.Cancel(indentificador, getApplicationContext());
    }

    @Override
    public void onStopped() {
        super.onStopped();
        hideNotifyProgress();
        destruirTimer();
    }
}
