package com.consultoraestrategia.ss_crmeducativo.login2.service2.worker;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

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
import androidx.work.WorkerParameters;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.core2.application.Core2;
import com.consultoraestrategia.ss_crmeducativo.dao.sessionUser.SessionUserDao;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.login2.data.preferent.LoginPreferentRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepositoryImpl;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.Firebase.GetListaCambiosResultados;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.GetCurso;
import com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase.servidorData.GetDatosServidorTwo;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarTipoUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ProgramaEducativoUi;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ServiceEnvioFbUi;
import com.consultoraestrategia.ss_crmeducativo.login2.service2.Notify.NotifyImpl;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.util.InjectorUtils;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SincronizarFotoAlumnos extends ListenableWorker  {
    private final static String TAG = "SynckServiceResultTAG";
    public final static String NAME_SERVICE_FIREBASE = "SERVICIO_UPDATE_RESULTADO_ACADEMICO";
    private RetrofitCancel cancel;
    private int indentificador;
    private GetDatosServidorTwo getDatosServidorTwo;
    private long fechaModificacion;
    private LoginPreferentRepositoryImpl preferentRepository;
    private GetCurso getCurso;

    public SincronizarFotoAlumnos(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    public static void start(Context context) {
        Log.d(TAG, "service pre start");
        Data data = new Data.Builder()
                .putInt("iteraciones", 10)
                .build();

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(SincronizarFotoAlumnos.class)
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

    @NonNull
    @Override
    public ListenableFuture<Result> startWork() {
        return CallbackToFutureAdapter.getFuture(completer -> {
            Log.d(TAG, "service init");
            SessionUserDao sessionUserDao = InjectorUtils.provideSessionUserDao();
            SessionUser sessionUser = sessionUserDao.getCurrentUser();
            if(sessionUser==null|| !sessionUser.isDataImported()){
                Log.d(TAG, "Cancelar service ");
            }else {
                onMain(getInputData(), sessionUser, completer);
            }
            // This value is used only for debug purposes: it will be used in toString()
            // of returned future or error cases.
            return "SynckServiceResultadoFB.load operation";
        });
    }

    private void onMain(Data inputData, SessionUser sessionUser, CallbackToFutureAdapter.Completer<Result> completer) {
        Log.d(TAG, "comensar el service ");
        LoginDataRepository repositorio = new LoginDataRepositoryImpl(ApiRetrofit.getInstance(), InjectorUtils.provideSessionUserDao(), InjectorUtils.provideParametrosDisenioDao(), InjectorUtils.provideCursoDao(), InjectorUtils.provideAlumnoDao());
        preferentRepository = new LoginPreferentRepositoryImpl(getApplicationContext());
        GetListaCambiosResultados getListaCambiosResultados = new GetListaCambiosResultados(repositorio, preferentRepository, getApplicationContext());
        getDatosServidorTwo = new GetDatosServidorTwo(repositorio);
        getCurso = new GetCurso(repositorio);

        int usuarioId = sessionUser.getUserId();
        cancel = getListaCambiosResultados.execute(usuarioId, true, new GetListaCambiosResultados.Callback() {
            @Override
            public void onLoad(boolean success, List<ServiceEnvioFbUi> serviceEnvioUiList) {
                //Simular parte del logeo endonde se trae los resultados por el silabo y el calendario periodo
                HashMap<Integer, List<ActualizarUi>> hashMap = new HashMap<>();
                for (ServiceEnvioFbUi serviceEnvioFbUi : serviceEnvioUiList){
                    switch (serviceEnvioFbUi.getTipo()){
                        case TareaAlumno:
                            break;
                        case SessionAlumno:
                            break;
                        case ResultadosAcademico:
                            ActualizarUi actualizarUi = new ActualizarUi();
                            actualizarUi.setEncoloa(true);
                            actualizarUi.setTipo(ActualizarTipoUi.Resultado);
                            actualizarUi.setSilaboEventoId(serviceEnvioFbUi.getSilaboEventoId());
                            if(hashMap.containsKey(serviceEnvioFbUi.getCalendarioPeriodoId())){
                                List<ActualizarUi> actualizarUis = hashMap.get(serviceEnvioFbUi.getCalendarioPeriodoId());
                                actualizarUis.add(actualizarUi);
                            }else {
                                List<ActualizarUi> actualizarUis = new ArrayList<>();
                                actualizarUis.add(actualizarUi);
                                hashMap.put(serviceEnvioFbUi.getCalendarioPeriodoId(), actualizarUis);
                            }
                            break;
                    }
                }
                if(!hashMap.isEmpty()){
                    showNotifyProgress();
                    for (Map.Entry<Integer, List<ActualizarUi>> entry : hashMap.entrySet()){
                        ProgramaEducativoUi programaEducativoUi =new ProgramaEducativoUi();
                        programaEducativoUi.setCalendarioPeriodoId(entry.getKey());
                        programaEducativoUi.setActualizarUiList(entry.getValue());
                        programaEducativoUiList.add(programaEducativoUi);
                    }

                    fechaModificacion = serviceEnvioUiList.get(0).getFechaModificacion();
                    getDatosServidorTwo(completer);
                }else {
                    completer.set(Result.success());
                }
            }
        });

    }

    List<ProgramaEducativoUi> programaEducativoUiList = new ArrayList<>();
    void getDatosServidorTwo(CallbackToFutureAdapter.Completer<Result> completer){
        if(!programaEducativoUiList.isEmpty()){
            ProgramaEducativoUi programaEducativoUi = programaEducativoUiList.get(0);
            programaEducativoUiList.remove(programaEducativoUi);
            getDatosServidorTwo.execute(new GetDatosServidorTwo.Request(programaEducativoUi,ActualizarTipoUi.Resultado ), new UseCaseLoginSincrono.Callback<GetDatosServidorTwo.Response>() {
                @Override
                public void onResponse(boolean success, GetDatosServidorTwo.Response value) {
                    if(value instanceof GetDatosServidorTwo.ResponseDownloadProgress){

                    }else if(value instanceof GetDatosServidorTwo.ResponseUploadProgress){

                    }else {
                        if(success){
                            ArrayList<String> arrayList = new ArrayList<>();
                            for (ActualizarUi actualizarUi : programaEducativoUi.getActualizarUiList()){
                                arrayList.add(getCurso.execute(actualizarUi.getSilaboEventoId()));
                            }

                            NotifyImpl.Companion.ResultadosActualizados(getApplicationContext(), arrayList);
                        }
                        getDatosServidorTwo(completer);
                    }
                }
            });
        }else {
            Log.d(TAG, "Sin cambios cancelar service");
            if(fechaModificacion>0)preferentRepository.saveFechaCambiosResultados(fechaModificacion);
            completer.set(Result.success());
            sendFinish();
            hideNotifyProgress();
        }

    }

    private void showNotifyProgress() {
        indentificador = NotifyImpl.Companion.ServiceProgressResultadoAcademico(getApplicationContext());
    }

    private void hideNotifyProgress(){
        NotifyImpl.Companion.Cancel(indentificador, getApplicationContext());
    }

    private void sendFinish() {
        Intent bcIntent = new Intent();
        bcIntent.setAction(Core2.ACTION_FIN);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(bcIntent);
    }

}
