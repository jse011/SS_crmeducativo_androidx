package com.consultoraestrategia.ss_crmeducativo.login2.service2.worker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.concurrent.futures.ResolvableFuture;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerFactory;
import androidx.work.WorkerParameters;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
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
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.servidorData.UpdateRubroEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ServiceEnvioFbUi;
import com.consultoraestrategia.ss_crmeducativo.login2.service2.Notify.NotifyImpl;
import com.consultoraestrategia.ss_crmeducativo.login2.service2.ServicesPresenter;
import com.consultoraestrategia.ss_crmeducativo.login2.service2.ServicesPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class SynckServiceFB extends ListenableWorker implements ImportarCountThread.ImportarCountDownTimerCallback {
    private final static String TAG = "SynckServiceFBTAG";
    public final static String NAME_SERVICE_FIREBASE = "SERVICIO_UPDATE_PORTAL_ALUMNO";
    public final static String NAME_SERVICE_TAG_FIREBASE = "SERVICIO_UPDATE_PORTAL_ALUMNO_TAG";
    private ImportarCountThread importarCountDownTimer;
    private boolean esperando = true;
    private RetrofitCancel cancel;
    private int indentificador;

    public SynckServiceFB(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public ListenableFuture<Result> startWork() {
        return CallbackToFutureAdapter.getFuture(completer -> {

            esperando = true;
            startTimer();

            Log.d(TAG, "service init");
            SessionUserDao sessionUserDao = InjectorUtils.provideSessionUserDao();
            SessionUser sessionUser = sessionUserDao.getCurrentUser();
            if(sessionUser==null|| !sessionUser.isDataImported()){
                Log.d(TAG, "Cancelar service ");
                sendFinish();
                destruirTimer();
            }else {
                onMain(getInputData(), sessionUser, completer);
            }
            // This value is used only for debug purposes: it will be used in toString()
            // of returned future or error cases.
            return "SynckServiceFB.load operation";
        });
    }

    private void onMain(Data inputData, SessionUser sessionUser, CallbackToFutureAdapter.Completer<Result> completer) {
        Log.d(TAG, "comensar el service ");
        LoginDataRepository repositorio = new LoginDataRepositoryImpl(ApiRetrofit.getInstance(), InjectorUtils.provideSessionUserDao(), InjectorUtils.provideParametrosDisenioDao(), InjectorUtils.provideCursoDao(), InjectorUtils.provideAlumnoDao());
        LoginPreferentRepository preferentRepository = new LoginPreferentRepositoryImpl(getApplicationContext());
        GetListaCambiosFB getListaCambiosFB = new GetListaCambiosFB(repositorio, preferentRepository, getApplicationContext());
        UpdateRubroEvaluacion updateRubroEvaluacion = new UpdateRubroEvaluacion(repositorio, preferentRepository);
        int usuarioId = sessionUser.getUserId();

        cancel = getListaCambiosFB.execute(usuarioId, true, new GetListaCambiosFB.Callback() {
            @Override
            public void onLoad(boolean success, List<ServiceEnvioFbUi> response) {

                if(!response.isEmpty()){
                    Log.d(TAG, "traer los cambios " + response.size());
                    esperando = false;
                    showNotifyProgress();
                    cancel = updateRubroEvaluacion.execute(response, new UseCaseLoginSincrono.Callback<Void>() {
                        @Override
                        public void onResponse(boolean success, Void value) {
                            Log.d(TAG, "service success " + success);
                            Log.d(TAG, "service finish");
                            completer.set(Result.success());

                            if(success){
                                Set<String> nombreSesionesHechas = new LinkedHashSet<>();
                                Set<String> nombreRubros = new LinkedHashSet<>();
                                for (ServiceEnvioFbUi serviceEnvioFbUi : response){
                                    List<ServiceEnvioFbUi.Detalle> detalles = serviceEnvioFbUi.getDetalles();
                                    if(!TextUtils.isEmpty(serviceEnvioFbUi.getNombreSesionDocenteId())){
                                        nombreSesionesHechas.add(serviceEnvioFbUi.getNombreSesionDocenteId());
                                    }
                                    if(detalles!=null){
                                        for (ServiceEnvioFbUi.Detalle detalle: detalles) {
                                            if(!TextUtils.isEmpty(detalle.getRubroEvalProcesoId()))
                                                nombreRubros.add(detalle.getNombre());
                                        }
                                    }
                                    if(!TextUtils.isEmpty(serviceEnvioFbUi.getRubroEvaluacionId()))nombreRubros.add(serviceEnvioFbUi.getNombre());
                                }

                                if(!nombreSesionesHechas.isEmpty()){

                                    NotifyImpl.Companion.Sesioneshechas(getApplicationContext(), new ArrayList<>(nombreSesionesHechas));
                                }

                                if(!nombreRubros.isEmpty()){
                                    NotifyImpl.Companion.EvaluacionesActualizada(getApplicationContext(), new ArrayList<>(nombreRubros));
                                }

                            }
                            hideNotifyProgress();
                            sendFinish();
                            destruirTimer();

                        }
                    });
                }else{
                    Log.d(TAG, "Sin cambios cancelar service");
                    completer.set(Result.success());
                    sendFinish();
                    destruirTimer();
                }
            }
        });


    }

    private void showNotifyProgress() {
        indentificador = NotifyImpl.Companion.ServiceProgressFB(getApplicationContext());
    }

    private void hideNotifyProgress(){
        NotifyImpl.Companion.Cancel(indentificador, getApplicationContext());
    }

    private void sendFinish() {
        Intent bcIntent = new Intent();
        bcIntent.setAction(Core2.ACTION_FIN_FIREBASE);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(bcIntent);
    }

    public static void start(Context context){
        Log.d(TAG, "service pre start");
        Data data = new Data.Builder()
                .putInt("iteraciones", 10)
                .build();

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(SynckServiceFB.class)
                .setInputData(data)
                //.addTag(NAME_SERVICE_TAG_FIREBASE)
                //.setConstraints(constraints)
                .build();

        WorkManager.getInstance().beginUniqueWork(
                NAME_SERVICE_FIREBASE,
                ExistingWorkPolicy.REPLACE,
                oneTimeWorkRequest)
                .enqueue();
    }

    @Override
    public void onImportarCountDownTimerCount(int counter) {

    }

    @Override
    public void onImportarCountDownTimerFinish() {
        //WorkManager.getInstance().cancelUniqueWork(NAME_SERVICE_FIREBASE);
    }

    @Override
    public void onImportarCountDownProgress(int progress) {
        //Comunicamos el progreso
        Intent bcIntent = new Intent();
        bcIntent.setAction(Core2.ACTION_PROGRESO_FIREBASE);
        bcIntent.putExtra("progreso", esperando?-1:progress);
        Log.d(TAG, "progreso service : "+ progress);
        //sendBroadcast(bcIntent);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(bcIntent);
    }

    @Override
    public void onStopped() {
        Log.d(TAG, "ponStopped");
        super.onStopped();
        if(cancel!=null)cancel.cancel();
        hideNotifyProgress();
        destruirTimer();
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

}
