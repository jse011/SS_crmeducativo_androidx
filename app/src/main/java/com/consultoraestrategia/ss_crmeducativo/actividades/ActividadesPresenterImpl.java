package com.consultoraestrategia.ss_crmeducativo.actividades;

import android.os.Handler;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.actividades.domain.usecase.GetActividadesList;
import com.consultoraestrategia.ss_crmeducativo.actividades.domain.usecase.UpdateActividad;
import com.consultoraestrategia.ss_crmeducativo.actividades.domain.usecase.UpdateSuccesDowloadArchivo;
import com.consultoraestrategia.ss_crmeducativo.actividades.entidades.ActividadesUi;
import com.consultoraestrategia.ss_crmeducativo.actividades.ui.ActividadesView;
import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.DowloadImageUseCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kike on 08/02/2018.
 */

public class ActividadesPresenterImpl implements ActividadesPresenter {
    private static String TAG = ActividadesPresenterImpl.class.getSimpleName();
    private UseCaseHandler handler;
    private GetActividadesList getActividadesList;
    private UpdateActividad updateActividad;
    private ActividadesView view;
    private DowloadImageUseCase dowloadImageUseCase;
    private UpdateSuccesDowloadArchivo updateSuccesDowloadArchivo;

    public ActividadesPresenterImpl(UseCaseHandler handler, GetActividadesList getActividadesList, UpdateActividad updateActividad, DowloadImageUseCase dowloadImageUseCase, UpdateSuccesDowloadArchivo updateSuccesDowloadArchivo) {
        this.handler = handler;
        this.getActividadesList = getActividadesList;
        this.updateActividad = updateActividad;
        this.dowloadImageUseCase = dowloadImageUseCase;
        this.updateSuccesDowloadArchivo = updateSuccesDowloadArchivo;
    }

    @Override
    public void attachView(ActividadesView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
    }

    private void getActividadesList() {
        Log.d(TAG, "getActividadesList");
        if (view != null) view.hideMessage();
        handler.execute(getActividadesList,
                new GetActividadesList.RequestValues(cargaCursoId, sesionAprendizajeId, backgroundColor, personaId),
                new UseCase.UseCaseCallback<GetActividadesList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetActividadesList.ResponseValue response) {
                        Log.d(TAG, "onSuccess");
                        if (response.getActividadesUiList().size() != 0) {
                            List<Object> objects = new ArrayList<Object>(response.getActividadesUiList());
                            if (view != null) view.showListObject(objects);
                        } else view.showMessage();

                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
    }

    int sesionAprendizajeId, backgroundColor, personaId, cargaCursoId;


    @Override
    public void setExtras(int cargaCursoId, int sesionAprendizajeId, int backgroundColor, int personaId) {
        Log.d(TAG, "setExtrasImpn : " + sesionAprendizajeId + " / " + backgroundColor + " /  " + personaId);
        this.sesionAprendizajeId = sesionAprendizajeId;
        this.backgroundColor = backgroundColor;
        this.personaId = personaId;
        this.cargaCursoId = cargaCursoId;
    }

    @Override
    public void onResumeFragment() {
        initViewActividades();
    }

    @Override
    public void onSelectActividad(ActividadesUi actividadesUi) {
        updateActividad(actividadesUi);
    }

    @Override
    public void onClickDownload(final RepositorioFileUi repositorioFileUi) {
        handler.execute(dowloadImageUseCase, new DowloadImageUseCase.RequestValues(repositorioFileUi),
                new UseCase.UseCaseCallback<UseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(UseCase.ResponseValue response) {
                        if (response instanceof DowloadImageUseCase.ResponseProgressValue) {
                            DowloadImageUseCase.ResponseProgressValue responseProgressValue = (DowloadImageUseCase.ResponseProgressValue) response;
                            if (view != null)
                                view.setUpdateProgress(responseProgressValue.getRepositorioFileUi(), responseProgressValue.getCount());
                            Log.d(TAG, ":( :" + repositorioFileUi.getNombreArchivo() + " = " + responseProgressValue.getRepositorioFileUi().getNombreArchivo());
                        }
                        if (response instanceof DowloadImageUseCase.ResponseSuccessValue) {
                            final DowloadImageUseCase.ResponseSuccessValue responseValue = (DowloadImageUseCase.ResponseSuccessValue) response;
                            saveRegistorRecursos(repositorioFileUi, new UseCaseSincrono.Callback<Boolean>() {
                                @Override
                                public void onResponse(boolean success, Boolean value) {
                                    if(success){
                                        if(view!=null)view.setUpdate(responseValue.getRepositorioFileUi());
                                        if(view!=null)view.leerArchivo(repositorioFileUi.getPath());
                                    }else{
                                        responseValue.getRepositorioFileUi().setEstadoFileU(RepositorioEstadoFileU.ERROR_DESCARGA);
                                        Log.d(TAG,"error al actualizar archivoId: " + repositorioFileUi.getArchivoId()+ " con el pathLocal:" + responseValue.getRepositorioFileUi().getPath());
                                        if(view!=null)view.setUpdate(responseValue.getRepositorioFileUi());
                                    }
                                }
                            });
                            Log.d(TAG, "pathLocal:" + responseValue.getRepositorioFileUi().getPath());
                        }
                        if (response instanceof DowloadImageUseCase.ResponseErrorValue) {
                            DowloadImageUseCase.ResponseErrorValue responseErrorValue = (DowloadImageUseCase.ResponseErrorValue) response;
                            if (view != null)
                                view.setUpdate(responseErrorValue.getRepositorioFileUi());
                        }
                    }

                    @Override
                    public void onError() {

                    }
                }
        );
    }

    private void saveRegistorRecursos(RepositorioFileUi repositorioFileUi, UseCaseSincrono.Callback<Boolean> callback) {
        updateSuccesDowloadArchivo.execute(new UpdateSuccesDowloadArchivo.Request(repositorioFileUi.getArchivoId(), repositorioFileUi.getPath()), callback);
    }

    @Override
    public void onClickClose(RepositorioFileUi repositorioFileUi) {
        if (view != null) repositorioFileUi.setCancel(true);
    }

    @Override
    public void onClickArchivo(RepositorioFileUi repositorioFileUi) {
        switch (repositorioFileUi.getTipoFileU()) {
            case VINCULO:
                if (view != null) view.showVinculo(repositorioFileUi.getUrl());
                break;
            case YOUTUBE:
                if (view != null) view.showYoutube(repositorioFileUi.getUrl());
                break;
            case MATERIALES:
                break;
            default:
                if (repositorioFileUi.getEstadoFileU() == RepositorioEstadoFileU.DESCARGA_COMPLETA)
                    if (view != null) view.leerArchivo(repositorioFileUi.getPath());
                break;
        }


    }

    @Override
    public void onAttach() {
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onCreateView() {
        Log.d(TAG, "onCreateView");
    }

    @Override
    public void onViewCreated() {
        Log.d(TAG, "onViewCreated");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initViewActividades();
            }
        }, 600);
    }

    @Override
    public void onActivityCreated() {
        Log.d(TAG, "onActivityCreated");

    }

    private void initViewActividades() {
        if (view != null) {
            view.clearActividades();
            getActividadesList();
        }
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach");
    }

    private void updateActividad(ActividadesUi actividadesUi) {
        handler.execute(updateActividad, new UpdateActividad.RequestValues(actividadesUi),
                new UseCase.UseCaseCallback<UpdateActividad.ResponseValue>() {
                    @Override
                    public void onSuccess(UpdateActividad.ResponseValue response) {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
