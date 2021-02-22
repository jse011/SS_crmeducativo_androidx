package com.consultoraestrategia.ss_crmeducativo.login2.service2.worker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.core2.application.Core2;
import com.consultoraestrategia.ss_crmeducativo.dao.sessionUser.SessionUserDao;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionData2;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionData2_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.servidorData.GetDatosServidor;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarTipoUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarUi;
import com.consultoraestrategia.ss_crmeducativo.login2.service2.Notify.NotifyImpl;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.google.common.util.concurrent.ListenableFuture;
import com.raizlabs.android.dbflow.sql.language.SQLite;

public class SincronizarCentroAprendizaje extends ListenableWorker implements ImportarCountThread.ImportarCountDownTimerCallback {
    private final static String TAG = "SincCentroAprendizaje";
    public final static String NAME_SERVICE_CENTRO_APRENDIZAJE = "SERVICIO_CENTRO_APRENDIZAJE";
    private ImportarCountThread importarCountDownTimer;
    private int indentificador;
    private RetrofitCancel cancel;

    public SincronizarCentroAprendizaje(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public ListenableFuture<Result> startWork() {
        return CallbackToFutureAdapter.getFuture(completer -> {
            Log.d(TAG, "service init");
            SessionUserDao sessionUserDao = InjectorUtils.provideSessionUserDao();
            SessionUser sessionUser = sessionUserDao.getCurrentUser();
            if (sessionUser == null || !sessionUser.isDataImported()) {
                Log.d(TAG, "Cancelar service ");
            } else {
                onMain(getInputData(), completer);
            }
            // This value is used only for debug purposes: it will be used in toString()
            // of returned future or error cases.
            return "SincronizarCentroAprendizaje.load operation";
        });
    }

    private void onMain(Data inputData, CallbackToFutureAdapter.Completer<Result> completer) {
        Log.d(TAG, "comensar el service ");
        LoginDataRepository repositorio = new LoginDataRepositoryImpl(ApiRetrofit.getInstance(), InjectorUtils.provideSessionUserDao(), InjectorUtils.provideParametrosDisenioDao(), InjectorUtils.provideCursoDao(), InjectorUtils.provideAlumnoDao());
        GetDatosServidor getDatosServidor = new GetDatosServidor(repositorio);
        showNotifyProgress();
        ActualizarUi actualizarUi = new ActualizarUi();
        actualizarUi.setEncoloa(true);
        actualizarUi.setTipo(ActualizarTipoUi.Rubros);
        actualizarUi.setSilaboEventoId(inputData.getInt("silaboEventoId",0));
        actualizarUi.setCalendarioPeriodoId(inputData.getInt("calendarioPeriodoId",0));

        Log.d(TAG, inputData.toString());
        startTimer();
        cancel = getDatosServidor.execute(actualizarUi, new UseCaseLoginSincrono.Callback<GetDatosServidor.Response>() {
            @Override
            public void onResponse(boolean success, GetDatosServidor.Response value) {

                if(value instanceof GetDatosServidor.ResponseDownloadProgress){

                }else if(value instanceof GetDatosServidor.ResponseUploadProgress){

                }else {
                    if(success){
                        SQLite.delete()
                                .from(SessionData2.class)
                                .where(SessionData2_Table.id.eq(SessionData2.Key_CentroProcesamiento))
                                .and(SessionData2_Table.calendarioPeriodoId.eq(inputData.getInt("calendarioPeriodoId",0)))
                                .and(SessionData2_Table.cargaCursoId.eq(inputData.getInt("cargaCursoId",0)))
                                .execute();
                    }
                    //else {
                      //  Toast.makeText(getApplicationContext(), "Tiempo de conexión agotado", Toast.LENGTH_SHORT).show();
                    //}

                    completer.set(Result.success());
                    Log.d(TAG, "success 4: " +success);
                    sendFinish();
                    hideNotifyProgress();
                    sendFinish();
                    destruirTimer();
                }

            }
        });

    }

    private void sendFinish() {
        Intent bcIntent = new Intent();
        bcIntent.setAction(Core2.ACTION_FIN);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(bcIntent);
    }

    public static void start(Context context, int silaboEventoId, int calendarioPeriodoId, int cargaCursoId) {
        Log.d(TAG, "service pre start");
        Data data = new Data.Builder()
                .putInt("silaboEventoId", silaboEventoId)
                .putInt("calendarioPeriodoId", calendarioPeriodoId)
                .putInt("cargaCursoId", cargaCursoId)
                .build();

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(SincronizarCentroAprendizaje.class)
                .setInputData(data)
                //.addTag(NAME_SERVICE_TAG_FIREBASE)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance().beginUniqueWork(
                NAME_SERVICE_CENTRO_APRENDIZAJE+"_"+ silaboEventoId+"_"+calendarioPeriodoId,
                ExistingWorkPolicy.REPLACE,
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
        if(cancel!=null)cancel.cancel();
        hideNotifyProgress();
        destruirTimer();
    }

}
