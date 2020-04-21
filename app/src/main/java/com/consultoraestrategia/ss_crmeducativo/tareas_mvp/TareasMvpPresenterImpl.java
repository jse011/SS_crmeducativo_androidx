package com.consultoraestrategia.ss_crmeducativo.tareas_mvp;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.entities.SessionUser;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC_Table;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioEstadoFileU;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.useCase.DowloadImageUseCase;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.domain_usecase.GetParametroDisenio;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.domain_usecase.GetTareasUIList;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.domain_usecase.MoverArchivosAlaCarpetaTarea;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.domain_usecase.UpdateSuccesDowloadArchivo;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.HeaderTareasAprendizajeUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.RecursosUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.RubroEvalProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.ui.FragmentTareas;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by irvinmarin on 06/11/2017.
 */

public class TareasMvpPresenterImpl implements TareasMvpPresenter {
    private static final String TAG = TareasMvpPresenterImpl.class.getSimpleName();
    private TareasMvpView view;
    private UseCaseHandler handler;
    private GetTareasUIList getTareasUIList;
    private GetParametroDisenio getParametroDisenio;
    int tipoTarea = 0;
    int idCargaCurso = 0;
    int mSesionAprendizajeId = 0;
    int idUsuario;
    int idCurso;
    private int idCalendarioPeriodo;
    private int parametrodisenioid;
    private int programaEducativoId;
    private ParametroDisenioUi parametroDisenioUi;
    private DowloadImageUseCase dowloadImageUseCase;
    private UpdateSuccesDowloadArchivo updateSuccesDowloadArchivo;
    private MoverArchivosAlaCarpetaTarea moverArchivosAlaCarpetaTarea;
    private List<HeaderTareasAprendizajeUI> headerTareasAprendizajeUIList = new ArrayList<>();
    private int anioAcademicoId;

    public TareasMvpPresenterImpl(UseCaseHandler handler, GetTareasUIList getTareasUIList, GetParametroDisenio getParametroDisenio,
                                  DowloadImageUseCase dowloadImageUseCase,
                                  UpdateSuccesDowloadArchivo updateSuccesDowloadArchivo,
                                  MoverArchivosAlaCarpetaTarea moverArchivosAlaCarpetaTarea) {
        this.handler = handler;
        this.getTareasUIList = getTareasUIList;
        this.getParametroDisenio = getParametroDisenio;
        this.dowloadImageUseCase = dowloadImageUseCase;
        this.updateSuccesDowloadArchivo = updateSuccesDowloadArchivo;
        this.moverArchivosAlaCarpetaTarea = moverArchivosAlaCarpetaTarea;
    }

    @Override
    public void attachView(TareasMvpView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
    }

    private void getTareasCurso(int tipoTarea, int idUsuario, int idCargaCurso, int mSesionAprendizajeId) {
        if (view!=null)view.hideMessage();
        if (view!=null)view.showProgress();
        int calendarioPeriodoId = 0;
        if(idCalendarioPeriodo != 0)calendarioPeriodoId = idCalendarioPeriodo;
        handler.execute(getTareasUIList, new GetTareasUIList.RequestValues(idUsuario, idCargaCurso, tipoTarea, mSesionAprendizajeId,calendarioPeriodoId, anioAcademicoId),
                new UseCase.UseCaseCallback<GetTareasUIList.ResponseValue>() {
            @Override
            public void onSuccess(GetTareasUIList.ResponseValue response) {
                //#region
                try {
                    for(HeaderTareasAprendizajeUI newheaderTareasAprendizajeUI: response.getHeaderTareasAprendizajeUIList()){

                        for(HeaderTareasAprendizajeUI headerTareasAprendizajeUI: headerTareasAprendizajeUIList){

                            if(headerTareasAprendizajeUI.getIdUnidadAprendizaje()==newheaderTareasAprendizajeUI.getIdUnidadAprendizaje()){

                                for (TareasUI newtareasUI : newheaderTareasAprendizajeUI.getTareasUIList()){

                                    for (TareasUI tareasUI : headerTareasAprendizajeUI.getTareasUIList()){

                                        if(tareasUI.getKeyTarea().equals(newtareasUI.getKeyTarea())){

                                            int pocision =0;
                                            for (RecursosUI newrepositorioFileUi : newtareasUI.getRecursosUIList()){

                                                for (RecursosUI repositorioFileUi : tareasUI.getRecursosUIList()){

                                                    if(repositorioFileUi.getArchivoId().equals(newrepositorioFileUi.getArchivoId())){
                                                        newtareasUI.getRecursosUIList().set(pocision, repositorioFileUi);
                                                    }

                                                }
                                                pocision++;
                                            }

                                        }

                                    }

                                }

                            }

                        }

                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

                headerTareasAprendizajeUIList.clear();
                headerTareasAprendizajeUIList.addAll(response.getHeaderTareasAprendizajeUIList());
                if (view!=null)view.showTareasUIList(headerTareasAprendizajeUIList, idCurso, parametroDisenioUi);
                 if(response.getHeaderTareasAprendizajeUIList().isEmpty())if(view!=null)view.showMessage();
                if(view!=null)view.hideProgress();
            }

            @Override
            public void onError() {
                if (view!=null)view.hideTareasUIList();
                if (view!=null)view.showMessage();
                Log.d(TAG, "Error");
            }
        });
    }

    private void getParametroDisenio(){
        getParametroDisenio.execute(new GetParametroDisenio.RequestValues(parametrodisenioid),
                new UseCaseSincrono.Callback<GetParametroDisenio.ResponseValue>() {
                    @Override
                    public void onResponse(boolean success, GetParametroDisenio.ResponseValue value) {
                        if(success){
                            parametroDisenioUi = value.getParametroDisenioUi();
                        }else {
                            parametroDisenioUi = new ParametroDisenioUi();
                        }
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
        this.onResumeFragment();
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
        Log.d(TAG, "onBackPressed   ");
    }

    @Override
    public void setExtras(Bundle extras) {
        if (extras == null)return;
        this.tipoTarea = extras.getInt(FragmentTareas.tipoTareas,0);
        this.idCargaCurso = extras.getInt(FragmentTareas.mIdCargaCurso,0);
        this.mSesionAprendizajeId = extras.getInt(FragmentTareas.mSesionAprendizajeId,0);
        this.idCalendarioPeriodo = extras.getInt(FragmentTareas.mCalendarioPeriodoid,0);

        this.idUsuario = SessionUser.getCurrentUser().getUserId();
        Log.d(TAG, "idUsuario : " + idUsuario);
        this.idCurso = extras.getInt(FragmentTareas.mIdCurso,0);
        this.parametrodisenioid = extras.getInt(FragmentTareas.mParametrodisenioid,0);
        CRMBundle crmBundle = new CRMBundle(extras);
        this.programaEducativoId = crmBundle.getProgramaEducativoId();
        this.anioAcademicoId = crmBundle.getAnioAcademico();
        getParametroDisenio();
        if(view!=null)view.showProgress();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(view!=null)view.hideProgress();
                getTareas(idCargaCurso, idCurso, mSesionAprendizajeId, tipoTarea);
            }
        }, 1000);


    }


    public void getTareas(int mIdCargaCurso, int mIdCurso, int mSesionAprendizajeId, int tipoTareas) {
        Log.d(TAG, "mIdCargaCurso : " + mIdCargaCurso);
        Log.d(TAG, "mIdCurso : " + mIdCurso);
        Log.d(TAG, "mSesionAprendizajeId : " + mSesionAprendizajeId);
        getTareasCurso(tipoTareas, idUsuario, mIdCargaCurso, mSesionAprendizajeId);
    }

    @Override
    public void deleteTarea(TareasUI tareasUI) {
        TareasC tareas = SQLite.select()
                .from(TareasC.class)
                .where(TareasC_Table.key.is(tareasUI.getKeyTarea()))
                .querySingle();
        if (tareas != null) {
            tareas.setEstadoId(265);
            tareas.setSyncFlag(TareasC.FLAG_UPDATED);
            tareas.setEstadoExportado(0);
            tareas.save();
            if(view!=null)view.exportarTareasEliminadas(programaEducativoId);
            if(view!=null)view.exportarTareasEliminadas(programaEducativoId);

            Log.d(TAG, "Tarea ELiminar");
        } else {
            Log.d(TAG, "No hay Tarea para ELiminar");
        }

        cancelDowload(tareasUI);

        getTareasCurso(tipoTarea, idUsuario, idCargaCurso, mSesionAprendizajeId);

    }



    @Override
    public void onResumeFragment() {
        getTareas(idCargaCurso, idCurso, mSesionAprendizajeId, tipoTarea);
    }

    @Override
    public void onClickedCrearTarea(HeaderTareasAprendizajeUI headerTareasAprendizajeUI, int idSilaboEvento, int mIdCurso) {

        if(view!=null)view.showActivityCrearTareas(headerTareasAprendizajeUI,idUsuario,idSilaboEvento,mSesionAprendizajeId, idCurso, parametroDisenioUi.getColor1(), programaEducativoId);
    }

    @Override
    public void onClickedOpTareaEdit(TareasUI tareasUI, HeaderTareasAprendizajeUI headerTareasAprendizajeUI) {
        if(view!=null)view.showlActivityEditTareas(tareasUI,headerTareasAprendizajeUI, idUsuario,headerTareasAprendizajeUI.getIdSilaboEvento(),mSesionAprendizajeId, idCurso, parametroDisenioUi.getColor1(), programaEducativoId);
        cancelDowload(tareasUI);
    }
    @Override
    public void onActualizarTarea(HeaderTareasAprendizajeUI headerTareasAprendizajeUI) {
        BEVariables beVariables = new BEVariables();
        beVariables.setUnidadAprendizajeId(headerTareasAprendizajeUI.getIdUnidadAprendizaje());
        if(view!=null)view.onShowActualizarTareas(beVariables);
    }

    @Override
    public void onChangeEstado(TareasUI tareasUI) {
        switch (tareasUI.getEstado()){
            case Creado:
                tareasUI.setEstado(TareasUI.Estado.Publicado);
                updateTareaEstado(tareasUI);
                break;
            case Publicado:
                tareasUI.setEstado(TareasUI.Estado.Creado);
                updateTareaEstado(tareasUI);
                break;
        }

    }

    @Override
    public void onCrearRubro(TareasUI tareasUI, HeaderTareasAprendizajeUI headerTareasAprendizajeUI) {
        if(view!=null)view.showActivityCrearRubro(tareasUI, headerTareasAprendizajeUI.getIdSilaboEvento(), idCalendarioPeriodo, programaEducativoId,mSesionAprendizajeId, parametroDisenioUi.getColor1(), idCurso);
    }

    @Override
    public void onCrearRubrica(TareasUI tareasUI, HeaderTareasAprendizajeUI headerTareasAprendizajeUI) {
        if(view!=null)view.showActivityCrearRubrica(tareasUI, idCalendarioPeriodo, idCargaCurso, idCurso, programaEducativoId,mSesionAprendizajeId, parametroDisenioUi.getColor1());
    }

    @Override
    public void onClikRubroTarea(TareasUI tareasUI) {
        RubroEvalProcesoUi rubroEvalProcesoUi = tareasUI.getRubroEvalProcesoUi();
        if(rubroEvalProcesoUi == null)return;
        switch (rubroEvalProcesoUi.getTipoRubroEvalProcesoUi()){
            case BIDIMENCIONAL:
                showEvaluacionRubrica(tareasUI,rubroEvalProcesoUi);
                break;
            case UNIDIMENCIONAL:
                showEvaluacionRubro(tareasUI, rubroEvalProcesoUi);
                break;
        }
    }



    private void showEvaluacionRubro(TareasUI tareasUI, RubroEvalProcesoUi rubroEvalProcesoUi) {
        switch (rubroEvalProcesoUi.getFormaRubroEvalProcesoUi()){
            case GRUPAL:
                if(view!=null)view.showEvaluacionRubroGrupal(tareasUI,rubroEvalProcesoUi, idCargaCurso, mSesionAprendizajeId, idCurso, parametroDisenioUi.getColor1());
                break;
            case INDIVIDUAL:
                if(view!=null)view.showEvaluacionRubroIndividual(tareasUI,rubroEvalProcesoUi, idCargaCurso, mSesionAprendizajeId, idCurso, parametroDisenioUi.getColor1());
                break;
        }
    }

    private void showEvaluacionRubrica(TareasUI tareasUI, RubroEvalProcesoUi rubroEvalProcesoUi) {
        switch (rubroEvalProcesoUi.getFormaRubroEvalProcesoUi()){
            case GRUPAL:
                if(view !=null)view.showEvaluacionRubricaGrupal(tareasUI,rubroEvalProcesoUi,idCargaCurso, parametroDisenioUi.getColor1());
                break;
            case INDIVIDUAL:
                if(view !=null)view.showEvaluacionRubricaIndividual(tareasUI,rubroEvalProcesoUi, idCargaCurso, parametroDisenioUi.getColor1());
                break;
        }
    }


    private void updateTareaEstado(final TareasUI tareasUI) {

        try {
            new Thread(){
                @Override
                public void run() {
                    TareasC tarea = SQLite.select()
                            .from(TareasC.class)
                            .where(TareasC_Table.key.eq(tareasUI.getKeyTarea()))
                            .querySingle();
                    int estadoId = 0;
                    switch (tareasUI.getEstado()){
                        case Creado:
                            estadoId = 263;
                            break;
                        case Publicado:
                            estadoId = 264;
                            break;
                        case Eliminado:
                            estadoId = 265;
                            break;
                    }

                    tarea.setEstadoId(estadoId);
                    tarea.setSyncFlag(TareasC.FLAG_UPDATED);
                    tarea.save();
                    if(view!=null)view.showServiceExportTarea(programaEducativoId);
                }
            }.start();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public void actualizarTareasPeriodo(String idCalendarioPeriodo) {
     this.idCalendarioPeriodo=Integer.parseInt(idCalendarioPeriodo);
     onResumeFragment();
    }

    @Override
    public void onClickDownload(final RepositorioFileUi repositorioFileUi) {
        handler.execute(dowloadImageUseCase, new DowloadImageUseCase.RequestValues(repositorioFileUi),
                new UseCase.UseCaseCallback<UseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(UseCase.ResponseValue response) {
                        if(response instanceof DowloadImageUseCase.ResponseProgressValue){
                            DowloadImageUseCase.ResponseProgressValue responseProgressValue = (DowloadImageUseCase.ResponseProgressValue) response;
                            if(view!=null)view.setUpdateProgress(responseProgressValue.getRepositorioFileUi(), responseProgressValue.getCount());
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

                                        moverArchivosAlaCarpetaTarea(repositorioFileUi);
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
                            if(view!=null)view.setUpdate(responseErrorValue.getRepositorioFileUi());
                        }
                    }

                    @Override
                    public void onError() {

                    }
                }
        );
    }

    private void moverArchivosAlaCarpetaTarea(RepositorioFileUi repositorioFileUi) {
        List<RepositorioFileUi> repositorioFileUiList = new ArrayList<>();
        repositorioFileUiList.add(repositorioFileUi);

        String nombreCurso = "";
        String titulo = "";
        if(repositorioFileUi instanceof RecursosUI){
            TareasUI tareasUI = ((RecursosUI)repositorioFileUi).getTarea();
            if(tareasUI!=null){
                titulo = tareasUI.getTituloTarea();
                nombreCurso=tareasUI.getNombreCurso();
            }
        }
        moverArchivosAlaCarpetaTarea.execute(nombreCurso,titulo, repositorioFileUiList );
    }

    private void saveRegistorRecursos(RepositorioFileUi repositorioFileUi,UseCaseSincrono.Callback<Boolean> callback ) {
        updateSuccesDowloadArchivo.execute(new UpdateSuccesDowloadArchivo.Request(repositorioFileUi.getArchivoId(), repositorioFileUi.getPath()), callback);
    }

    @Override
    public void onClickClose(RepositorioFileUi repositorioFileUi) {
        repositorioFileUi.setCancel(true);
    }

    @Override
    public void onClickArchivo(RepositorioFileUi repositorioFileUi) {
        switch (repositorioFileUi.getTipoFileU()){
            case VINCULO:
                if(view!=null)view.showVinculo(repositorioFileUi.getUrl());
                break;
            case YOUTUBE:
                if(view!=null)view.showYoutube(repositorioFileUi.getUrl());
                break;
            case MATERIALES:
                break;
            default:
                if(repositorioFileUi.getEstadoFileU()== RepositorioEstadoFileU.DESCARGA_COMPLETA){
                    if(view!=null)view.leerArchivo(repositorioFileUi.getPath());
                }
                break;
        }


    }

    @Override
    public void onClickParentFabClicked() {
        if(!headerTareasAprendizajeUIList.isEmpty()){
            HeaderTareasAprendizajeUI headerTareasAprendizajeUI = headerTareasAprendizajeUIList.get(0);
            if(view!=null)view.showCrearTarea(headerTareasAprendizajeUI, headerTareasAprendizajeUI.getIdSilaboEvento(), idCargaCurso);
        }
    }

    @Override
    public void onDestroyView() {
        cancelAllDowload();
        view = null;
        Log.d(TAG, "onDestroyView");
    }

    private void cancelAllDowload() {
        try {
            for(HeaderTareasAprendizajeUI headerTareasAprendizajeUI: headerTareasAprendizajeUIList){
                for (TareasUI tareasUI : headerTareasAprendizajeUI.getTareasUIList()){
                    for (RepositorioFileUi repositorioFileUi : tareasUI.getRecursosUIList()){
                        repositorioFileUi.setCancel(true);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void cancelDowload(TareasUI tareasUI) {
        try {
            for (RepositorioFileUi repositorioFileUi : tareasUI.getRecursosUIList()){
                repositorioFileUi.setCancel(true);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
