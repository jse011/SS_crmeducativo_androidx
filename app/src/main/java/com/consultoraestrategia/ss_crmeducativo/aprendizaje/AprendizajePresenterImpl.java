package com.consultoraestrategia.ss_crmeducativo.aprendizaje;

import android.os.Handler;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.domain.usecase.GetCompetencias;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.domain.usecase.GetEvidencia;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.domain.usecase.GetRecursosDidacticos;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.domain.usecase.GetSesion;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.domain.usecase.UpdateSuccesDowloadArchivo;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CampotematicoUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CarCampoAccion;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CarEvidenciaUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CardCompeteciasUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CardRecursosDidacticosUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CardSesionUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.DesempenioUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.Extras;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.entities.CampoTematico;
import com.consultoraestrategia.ss_crmeducativo.entities.Competencia;
import com.consultoraestrategia.ss_crmeducativo.entities.DesempenioIcd;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.DowloadImageUseCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 15/01/2018.
 */

public class AprendizajePresenterImpl implements AprendizajePresenter {
    private String TAG = "AprendizajePresenterImpl";
    private AprendizajeView view;
    private UseCaseHandler handler;
    private GetSesion getSesion;
    private GetCompetencias getCompetencias;
    private GetRecursosDidacticos getRecursosDidacticos;
    private int sesionAprendizajeId;
    private CardSesionUi sesionUi;
    private CardCompeteciasUi cardCompeteciasUi;
    private CardRecursosDidacticosUi cardRecursosDidacticosUi;
    private CarCampoAccion carCampoAccion;
    private DowloadImageUseCase dowloadImageUseCase;
    private UpdateSuccesDowloadArchivo updateSuccesDowloadArchivo;
    private GetEvidencia getEvidencia;
    List<Object> items;

    public AprendizajePresenterImpl(UseCaseHandler handler, GetSesion getSesion, GetCompetencias getCompetencias, GetRecursosDidacticos getRecursosDidacticos, DowloadImageUseCase dowloadImageUseCase, UpdateSuccesDowloadArchivo updateSuccesDowloadArchivo, GetEvidencia getEvidencia) {
        this.handler = handler;
        this.getSesion = getSesion;
        this.getCompetencias = getCompetencias;
        this.getRecursosDidacticos = getRecursosDidacticos;
        this.dowloadImageUseCase = dowloadImageUseCase;
        this.items = new ArrayList<>();
        this.updateSuccesDowloadArchivo = updateSuccesDowloadArchivo;
        this.getEvidencia = getEvidencia;
    }

    @Override
    public void attachView(AprendizajeView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onAttach() {

    }

    @Override
    public void onCreateView() {

    }

    @Override
    public void onViewCreated() {
        if(view!=null)view.showProgress();
        Log.d(TAG, "onViewCreated");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(view!=null)view.hideProgress();
                iniciarListaAprendizaje();
            }
        },500);
    }

    @Override
    public void onActivityCreated() {

    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void onDetach() {

    }


    @Override
    public void setExtras(Extras extras) {
        if (extras != null) {
            sesionAprendizajeId = extras.getSesionAprendizajeId();
        }
    }

    @Override
    public void onResumeFragment() {
        Log.d(TAG, "onResumeFragment");
        iniciarListaAprendizaje();
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
        repositorioFileUi.setCancel(true);
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
                if (repositorioFileUi.getEstadoFileU() == RepositorioEstadoFileU.DESCARGA_COMPLETA) {
                    if (view != null) view.leerArchivo(repositorioFileUi.getPath());
                }
                break;
        }


    }

    private void iniciarListaAprendizaje() {
        Log.d(TAG, "iniciarListaAprendizaje");
            getSesion(sesionAprendizajeId);
            getCompetencias(sesionAprendizajeId);
            getRecursosDidacticos(sesionAprendizajeId);
            CarEvidenciaUi carEvidenciaUi = new CarEvidenciaUi();
            carEvidenciaUi.setEvidenciaUiList(getEvidencia.excute(sesionAprendizajeId));

            items.clear();
            items.add(sesionUi);
            items.add(cardCompeteciasUi);
            items.add(carCampoAccion);
            items.add(carEvidenciaUi);
            items.add(cardRecursosDidacticosUi);

            if (view != null) view.setAprendizaje(items);


    }

    private void getSesion(final int sesionAprendizajeId) {
        getSesion.execute(new GetSesion.RequestValues(sesionAprendizajeId), new UseCaseSincrono.Callback<GetSesion.ResponseValues>() {
            @Override
            public void onResponse(boolean success, GetSesion.ResponseValues response) {
                if(success){
                    sesionUi = response.getSesionUi();
                }else {

                }

            }
        });

    }

    private void getCompetencias(final int sesionAprendizajeId) {
        getCompetencias.execute(new GetCompetencias.RequestValues(0, sesionAprendizajeId), new UseCaseSincrono.Callback<GetCompetencias.ResponseValues>() {
            @Override
            public void onResponse(boolean success, GetCompetencias.ResponseValues response) {
                if(success){
                    cardCompeteciasUi = new CardCompeteciasUi(response.getCompetenciaUis());
                    carCampoAccion = new CarCampoAccion();


                    List<CampotematicoUi> competenciaUiListCompetenciaTransversales = new ArrayList<>();

                    List<CampotematicoUi> competenciaUiListEnfoqueTransversal = new ArrayList<>();

                    List<CampotematicoUi> competenciaUiListCompetenciaBase = new ArrayList<>();

                    for (Object o : response.getCompetenciaUis()) {
                        if (o instanceof CompetenciaUi) {
                            CompetenciaUi competenciaUi = (CompetenciaUi) o;


                            switch (competenciaUi.getTipoCompetencia()) {

                                case BASE:
                                    competenciaUiListCompetenciaBase.addAll(competenciaUi.getCampotematicoUis());
                                    break;
                                case EFOQUE:
                                    competenciaUiListEnfoqueTransversal.addAll(competenciaUi.getCampotematicoUis());
                                    break;

                                case TRASVERSAL:
                                    competenciaUiListCompetenciaTransversales.addAll(competenciaUi.getCampotematicoUis());
                                    break;
                            }
                        }
                    }

                    carCampoAccion.setCompetenciaUiListCompetenciaBase(competenciaUiListCompetenciaBase);
                    carCampoAccion.setCompetenciaUiListCompetenciaTransversales(competenciaUiListCompetenciaTransversales);
                    carCampoAccion.setCompetenciaUiListEnfoqueTransversal(competenciaUiListEnfoqueTransversal);
                }else {

                }
            }
        });

    }


    private void getRecursosDidacticos(int sesionAprendizajeId) {
        getRecursosDidacticos.execute(new GetRecursosDidacticos.RequestValues(sesionAprendizajeId), new UseCaseSincrono.Callback<GetRecursosDidacticos.ResponseValue>() {
            @Override
            public void onResponse(boolean success, GetRecursosDidacticos.ResponseValue response) {
                if(success){
                    cardRecursosDidacticosUi = new CardRecursosDidacticosUi(response.getRecursosDidacticoUis());
                }
            }
        });
    }

}
