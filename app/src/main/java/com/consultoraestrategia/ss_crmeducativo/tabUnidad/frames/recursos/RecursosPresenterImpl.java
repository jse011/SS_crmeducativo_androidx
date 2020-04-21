package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.recursos;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.DowloadImageUseCase;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadParametros;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase.GetRecursoDidactico;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase.UpdateSuccesDowloadArchivo;

public class RecursosPresenterImpl extends BaseFragmentPresenterImpl<RecursosView> implements RecursosPresenter {

    private UnidadParametros unidadParametros;
    private int unidadAprendizaje;
    private GetRecursoDidactico getRecursoDidactico;
    private DowloadImageUseCase dowloadImageUseCase;
    private UpdateSuccesDowloadArchivo updateSuccesDowloadArchivo;

    public RecursosPresenterImpl(UseCaseHandler handler, Resources res, GetRecursoDidactico getRecursoDidactico, DowloadImageUseCase dowloadImageUseCase, UpdateSuccesDowloadArchivo updateSuccesDowloadArchivo) {
        super(handler, res);
        this.getRecursoDidactico = getRecursoDidactico;
        this.dowloadImageUseCase = dowloadImageUseCase;
        this.updateSuccesDowloadArchivo = updateSuccesDowloadArchivo;
    }

    @Override
    protected String getTag() {
        return RecursosPresenterImpl.class.getSimpleName();
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        unidadParametros = UnidadParametros.clone(extras);
        if (unidadParametros!=null){
            unidadAprendizaje = unidadParametros.getUnidadAprendizajeId();
        }
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {

    }

    @Override
    public void onStart() {
        super.onStart();
        setGetRecursoDidactico();
    }

    public void setGetRecursoDidactico() {
        if (view!=null)view.showProgresBar();
        handler.execute(getRecursoDidactico, new GetRecursoDidactico.RequestValue(unidadAprendizaje), new UseCase.UseCaseCallback<GetRecursoDidactico.ResponseValue>() {
            @Override
            public void onSuccess(GetRecursoDidactico.ResponseValue response) {
                if (view!=null)view.hideProgressBar();
                if (view!=null)view.showRecursoDidactico(response.getRecursosDidacticoUis());
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void onCLickAcceptButtom() {

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
                                    if (!success)responseValue.getRepositorioFileUi().setEstadoFileU(RepositorioEstadoFileU.ERROR_DESCARGA);

                                    if (view != null)
                                        view.setUpdate(responseValue.getRepositorioFileUi());
                                }
                            });
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
}
