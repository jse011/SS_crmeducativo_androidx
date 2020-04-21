package com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoC.presenter;


import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.comportamientoAlumno.tabs.comportAlumnoC.ui.ComportAlumnoCview;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.DeleteComportamiento;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.GetComportAlumnoList;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase.UpdateSuccesDowloadCasoArchivo;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.DowloadImageUseCase;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.domain_usecase.UpdateSuccesDowloadArchivo;

import java.util.List;

public class ComportAlumnoCPresenterImpl extends BaseFragmentPresenterImpl<ComportAlumnoCview> implements ComportAlumnoCPresenter {

    private GetComportAlumnoList getComportAlumnoList;
    int programaEducativoId;
    int cargaAcademicaId;
    int cargaCursoId;
    int docenteId;
    int alumnoId;
    int calendarioPeriodoId;
    int entidadId;
    int georeferenciaId;
    private DeleteComportamiento deleteComportamiento;
    private List<ComportamientoUi> comportamientoUiList;
    private AlumnoUi alumnoUi;
    private String TAG=ComportAlumnoCPresenterImpl.class.getSimpleName();
    private ComportamientoUi comportamientoUiselected;
    private DowloadImageUseCase dowloadImageUseCase;
    private UpdateSuccesDowloadCasoArchivo updateSuccesDowloadCasoArchivo;

    public ComportAlumnoCPresenterImpl(UseCaseHandler handler, Resources res, GetComportAlumnoList getComportAlumnoList, DeleteComportamiento deleteComportamiento,
                                       DowloadImageUseCase dowloadImageUseCase,
                                       UpdateSuccesDowloadCasoArchivo updateSuccesDowloadCasoArchivo) {
        super(handler, res);
        this.getComportAlumnoList=getComportAlumnoList;
        this.deleteComportamiento=deleteComportamiento;
        this.dowloadImageUseCase = dowloadImageUseCase;
        this.updateSuccesDowloadCasoArchivo = updateSuccesDowloadCasoArchivo;
    }

    @Override
    protected String getTag() {
        return null;
    }

    @Override
    public void onSingleItemSelected(Object singleItem, int selectedPosition) {
    }

    @Override
    public void onCLickAcceptButtom() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void getComportamientoA() {
        handler.execute(getComportAlumnoList, new GetComportAlumnoList.RequestValues(programaEducativoId, cargaAcademicaId, cargaCursoId, docenteId,calendarioPeriodoId, alumnoId), new UseCase.UseCaseCallback<GetComportAlumnoList.ResponseValue>() {
            @Override
            public void onSuccess(GetComportAlumnoList.ResponseValue response) {
                Log.d(TAG, "onSuccess "+ response.getAlumnoUi().getComportamientoUiList().size());
                comportamientoUiList= response.getAlumnoUi().getComportamientoUiList();
                alumnoUi=response.getAlumnoUi();
                showList();
            }
            @Override
            public void onError() {

            }
        });
    }

    private void showList() {
        hideProgress();
        Log.d(TAG, "showList " +comportamientoUiList.size());
        if(comportamientoUiList.size()>0)if(view!=null)view.showListComportamiento(comportamientoUiList);
        else {if(view!=null)view.showEmptyText();}
    }


    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        CRMBundle crmBundle= new CRMBundle(extras);
        this.programaEducativoId= crmBundle.getProgramaEducativoId();
        this.cargaAcademicaId=crmBundle.getCargaAcademicaId();
        this.cargaCursoId= crmBundle.getCargaCursoId();
        this.docenteId= crmBundle.getEmpleadoId();
        this.alumnoId= extras.getInt("alumnoId");
        this.calendarioPeriodoId= crmBundle.getCalendarioPeriodoId();
        this.entidadId= crmBundle.getEntidadId();
        this.georeferenciaId= crmBundle.getGeoreferenciaId();
        getComportamientoA();
    }

    @Override
    public void onSelectOpcionMenuComportamiento(ComportamientoUi comportamientoUi, int positionMenu) {
        this.comportamientoUiselected=comportamientoUi;
        comportamientoUiselected.setEntidadId(entidadId);
        comportamientoUiselected.setGeoreferenciaId(georeferenciaId);
        switch (positionMenu) {
            case 0:
                if (view != null) view.lauchEditComportamiento(comportamientoUiselected);
                break;
            case 1:
                if (view != null) view.dialogDeleteComportamiento(res.getString(R.string.title_delete_comportamiento));
                break;
        }
    }

    @Override
    public void deleteComportamiento(){
        handler.execute(deleteComportamiento, new DeleteComportamiento.RequestValues(comportamientoUiselected.getId()), new UseCase.UseCaseCallback<DeleteComportamiento.ResponseValue>() {
            @Override
            public void onSuccess(DeleteComportamiento.ResponseValue response) {
                Log.d(TAG ,"onSuccess "+ response.isSuccces());
                if(view!=null)view.deleteComportamiento(comportamientoUiselected, programaEducativoId);

            }

            @Override
            public void onError() {
                Log.d(TAG ,"Error : No se pudo eliminar Caso ");
            }
        });
    }

    @Override
    public void onClickDownload(final RepositorioFileUi repositorioFileUi) {
        handler.execute(dowloadImageUseCase, new DowloadImageUseCase.RequestValues(repositorioFileUi),
                new UseCase.UseCaseCallback<UseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(UseCase.ResponseValue response) {
                        if(response instanceof DowloadImageUseCase.ResponseProgressValue){
                            DowloadImageUseCase.ResponseProgressValue responseProgressValue = (DowloadImageUseCase.ResponseProgressValue) response;
                            view.setUpdateProgress(responseProgressValue.getRepositorioFileUi(), responseProgressValue.getCount());
                            Log.d(TAG,":( :" + repositorioFileUi.getNombreArchivo() +" = " + responseProgressValue.getRepositorioFileUi().getNombreArchivo());
                        }
                        if(response instanceof DowloadImageUseCase.ResponseSuccessValue){
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
                            Log.d(TAG,"pathLocal:" + responseValue.getRepositorioFileUi().getPath());
                        }
                        if(response instanceof DowloadImageUseCase.ResponseErrorValue){
                            DowloadImageUseCase.ResponseErrorValue responseErrorValue = (DowloadImageUseCase.ResponseErrorValue) response;
                            view.setUpdate(responseErrorValue.getRepositorioFileUi());
                        }
                    }

                    @Override
                    public void onError() {

                    }
                }
        );
    }

    private void saveRegistorRecursos(RepositorioFileUi repositorioFileUi,UseCaseSincrono.Callback<Boolean> callback ) {
        updateSuccesDowloadCasoArchivo.execute(new UpdateSuccesDowloadCasoArchivo.Request(repositorioFileUi.getArchivoId(), repositorioFileUi.getPath()), callback);
    }

    @Override
    public void onClickClose(RepositorioFileUi repositorioFileUi) {
        repositorioFileUi.setCancel(true);
    }

    @Override
    public void onClickArchivo(RepositorioFileUi repositorioFileUi) {
        if(repositorioFileUi.getEstadoFileU()== RepositorioEstadoFileU.DESCARGA_COMPLETA){
            if(view!=null)view.leerArchivo(repositorioFileUi.getPath());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getComportamientoA();
    }
}
