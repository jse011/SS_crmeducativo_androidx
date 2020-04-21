package com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.presenter;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.SesionAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadParametros;
import com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.FragmentUnidadesView;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.domain.usecase.GetUnidadesList;
import com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.domain.usecase.SaveSesionAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.domain.usecase.UpdateToogleUnidad;

import java.util.ArrayList;
import java.util.List;

public class UnidadesPresenterImpl implements UnidadesPresenter {

    String TAG = UnidadesPresenterImpl.class.getSimpleName();
    FragmentUnidadesView view;
    private int idCargaCurso;
    private int idCalendarioPeriodo;
    private UseCaseHandler handler;
    private GetUnidadesList getUnidadesList;
    private UpdateToogleUnidad updateToogleUnidad;
    private SaveSesionAprendizaje saveSesionAprendizaje;
    private int parametroDisenioId;
    private List<UnidadAprendizajeUi> unidadAprendizajeUiList= new ArrayList<>();
    private int backgroudColor;
    private int personaId;
    private int cursoId;
    private int cargaAdemicaId;
    private int entidadId;
    private int gereferenciaId;
    private int programaEducativoId;
    private int anioAcademicoId;

    public UnidadesPresenterImpl(UseCaseHandler handler, GetUnidadesList getUnidadesList,UpdateToogleUnidad updateToogleUnidad ,SaveSesionAprendizaje saveSesionAprendizaje) {
        this.handler = handler;
        this.getUnidadesList = getUnidadesList;
        this.updateToogleUnidad= updateToogleUnidad;

        this.saveSesionAprendizaje=saveSesionAprendizaje;
    }

    private void initViewUnidades() {

        if (view != null) {
            view.clearUnidades();
            getUnidadesList();

        }
    }

    private void getUnidadesList() {
        unidadAprendizajeUiList.clear();
        if(view !=null) view.hideTextVaciounidades();
        if(view !=null) view.showProgress();
        getUnidadesList.execute(new GetUnidadesList.RequestValues(idCargaCurso, idCalendarioPeriodo, anioAcademicoId), new UseCaseSincrono.Callback<GetUnidadesList.ResponseValue>() {
            @Override
            public void onResponse(boolean success, GetUnidadesList.ResponseValue response) {
                unidadAprendizajeUiList.clear();
                unidadAprendizajeUiList.addAll(response.getUnidadAprendizajesList());
               showUnidadesList(unidadAprendizajeUiList, programaEducativoId);
                if(response.getUnidadAprendizajesList().isEmpty())  if(view !=null) view.showTextVaciounidades();
                if(view !=null) view.hideProgress();
            }
        });
    }

    private void showUnidadesList(List<UnidadAprendizajeUi> unidadAprendizajeUiList, int programaEducativoId) {

        int columnas = 0;//3
        if(view!=null)columnas = view.getColumnasSesionesList();
        Log.d(TAG,"columnas: " + columnas);
        for (UnidadAprendizajeUi unidadAprendizajeUi: unidadAprendizajeUiList){
            List<SesionAprendizajeUi> sesionAprendizajeUiList = unidadAprendizajeUi.getObjectListSesiones();

            if(sesionAprendizajeUiList==null||columnas >= sesionAprendizajeUiList.size()){
                unidadAprendizajeUi.setVisibleVerMas(false);
            } else {
                unidadAprendizajeUi.setVisibleVerMas(true);
            }
            Log.d(TAG,"sesionAprendizajeUiList.size(): " + sesionAprendizajeUiList.size());
        }
        if(view !=null) view.showUnidadesList(unidadAprendizajeUiList, programaEducativoId);
    }


    @Override
    public void setExtras(Bundle bundle) {
        //Log.d(TAG, "setExtras");
        idCargaCurso= bundle.getInt("idCargaCurso", 0);
        idCalendarioPeriodo= bundle.getInt("idCalendarioPeriodo",0);
        parametroDisenioId=bundle.getInt("parametrodisenioid");
        backgroudColor = bundle.getInt("backgroudColor");
        personaId = bundle.getInt("personaId");
        cursoId = bundle.getInt("cursoId");
        entidadId= bundle.getInt("idEntidad");
        CRMBundle crmBundle = new CRMBundle(bundle);
        cargaAdemicaId= crmBundle.getCargaAcademicaId();
        gereferenciaId = crmBundle.getGeoreferenciaId();
        //Log.d(TAG, "cargaAdemicaId "+ cargaAdemicaId);
        programaEducativoId = crmBundle.getProgramaEducativoId();
        anioAcademicoId = crmBundle.getAnioAcademico();

    }

    @Override
    public void onResumeFragment(String idCalendarioPeriodo) {
        this.idCalendarioPeriodo=Integer.parseInt(idCalendarioPeriodo);

        getUnidadesList();
    }

    @Override
    public void saveSesionAprendizaje(SesionAprendizajeUi sesionAprendizajeUi) {
        Log.d(TAG, "saveSesionAprendizaje" + sesionAprendizajeUi.getEstadoEjecucionId());
       handler.execute(saveSesionAprendizaje, new SaveSesionAprendizaje.RequestValues(sesionAprendizajeUi), new UseCase.UseCaseCallback<SaveSesionAprendizaje.ResponseValue>() {
           @Override
           public void onSuccess(SaveSesionAprendizaje.ResponseValue response) {
              Log.d(TAG, "Guardo Sesion Aprendizaje");
           }

           @Override
           public void onError() {

           }
       });

    }

    @Override
    public void onClickSesion(SesionAprendizajeUi sesionAprendizaje) {
        if(view !=null) view.showTabSesiones(parametroDisenioId,personaId, idCargaCurso, cursoId, cargaAdemicaId, backgroudColor ,sesionAprendizaje, entidadId, gereferenciaId);
    }

    @Override
    public void onViewCreated() {
        Log.d(TAG, "onViewCreated");
        if(view !=null) view.showProgress();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(view !=null) view.hideProgress();
                initViewUnidades();
            }
        }, 1000);
    }

    @Override
    public void startUnidadDetalle(UnidadAprendizajeUi unidad) {
        UnidadParametros unidadParametros = new UnidadParametros();
        unidadParametros.setUnidadAprendizajeId(unidad.getUnidadAprendizajeId());
        unidadParametros.setProgramaEducativoId(programaEducativoId);
        unidadParametros.setCalendarioPeriodoId(idCalendarioPeriodo);
        unidadParametros.setCargaAcademicaId(cargaAdemicaId);
        unidadParametros.setCargaCursoId(idCargaCurso);
        unidadParametros.setEntidadId(entidadId);
        unidadParametros.setUnidadTitulo("Unidad "+unidad.getNroUnidad()+": "+unidad.getTitulo());
        if(view !=null) view.startActivityUnidadDetalle(unidadParametros);
    }

    @Override
    public void onClickVerMas(UnidadAprendizajeUi unidadAprendizajeUi) {

        if(unidadAprendizajeUi.isToogle()){
            unidadAprendizajeUi.setToogle(false);
        }else {
            unidadAprendizajeUi.setToogle(true);
        }

        if(view !=null) view.updateItem(unidadAprendizajeUi);

        updateToogleUnidad.execute(new UpdateToogleUnidad.RequestValues(unidadAprendizajeUi), new UseCaseSincrono.Callback<UpdateToogleUnidad.ResponseValue>() {
            @Override
            public void onResponse(boolean success, UpdateToogleUnidad.ResponseValue response) {
                Log.d(TAG, "Guardo Toogle Uniidad Aprendizaje" +response.getEstado());
            }
        });


    }

    @Override
    public void onConfigurationChanged() {
        if(unidadAprendizajeUiList!=null)showUnidadesList(unidadAprendizajeUiList, programaEducativoId);
    }


    @Override
    public void attachView(FragmentUnidadesView view) {
        this.view =view;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        //initViewUnidades();
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

}
