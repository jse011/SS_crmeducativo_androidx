package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.RubricasView;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.RubricasAbstractPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase.EliminarRubricas;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase.GetActualizasRubricas;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase.GetCalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase.PublicarTodasEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.sesiones.domain.useCase.GetRubricaSesionLista;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.view.TabsSesionesActivityV2;

/**
 * Created by CCIE on 07/03/2018.
 */

public class RubricaSesionPresenterImpl extends RubricasAbstractPresenterImpl {

    private final static String RUBRICA_BID_PRESENTER_IMPL_TAG = RubricaSesionPresenterImpl.class.getSimpleName();
    public final static String CARGA_CURSO_ID = "cargaCursoId";
    public final static String SESION_APRENDIZAJE_Id = "sesionesAprendizajeId";
    public final static String CURSO_ID = "cursoId";
    private Resources res;


    private GetRubricaSesionLista getRubricaSesionLista;
    private GetCalendarioPeriodo getCalendarioPeriodo;
    private int calendarioPeriodoId;
    private PeriodoUi periodoUi;

    public RubricaSesionPresenterImpl(UseCaseHandler handler, Resources res, GetActualizasRubricas getActualizasRubricas, EliminarRubricas eliminarRubricas, PublicarTodasEvaluacion publicarTodasEvaluacion, GetRubricaSesionLista getRubricaSesionLista, GetCalendarioPeriodo getCalendarioPeriodo) {
        super(handler, getActualizasRubricas, eliminarRubricas,publicarTodasEvaluacion);
        this.getRubricaSesionLista = getRubricaSesionLista;
        this.getCalendarioPeriodo = getCalendarioPeriodo;
        this.res = res;
    }

    @Override
    protected void getRubricasLista(ListCallback<RubBidUi> listCallback) {
        if(view!=null)view.mostrarProgressBar();
        handler.execute(getRubricaSesionLista,
                new GetRubricaSesionLista.RequestValues(sesionAprendizajeId, cargaCursoId, cursoId, calendarioPeriodoId),
                new UseCase.UseCaseCallback<GetRubricaSesionLista.ResponseValue>() {
                    @Override
                    public void onSuccess(GetRubricaSesionLista.ResponseValue response) {

                        if (response.getList().size() != 0) {
                            Log.d(RUBRICA_BID_PRESENTER_IMPL_TAG, "if ");
                            if (view != null) {
                                view.mostrarListaRubricas(response.getList());
                                view.mostrarListaRubrica();
                                view.ocultarProgressBar();
                                view.ocultarMensaje();
                                //view.actualizarListaRubrica();
                            }
                        } else {
                            Log.d(RUBRICA_BID_PRESENTER_IMPL_TAG, "else ");
                            if (view != null) {
                                view.ocultarListaRubrica();
                                view.mostrarMensaje(res.getString(R.string.empty_data));
                                view.ocultarProgressBar();
                            }
                        }
                    }

                    @Override
                    public void onError() {
                        Log.d(RUBRICA_BID_PRESENTER_IMPL_TAG, "getRubricasLista::onError");
                    }
                });
    }



    int cursoId;
    int sesionAprendizajeId;
    int cargaAcademicaId;

    @Override
    public void setExtras(Bundle extras) {
        if (extras != null) {
            this.cargaCursoId = extras.getInt(CARGA_CURSO_ID);
            this.cursoId = extras.getInt(CURSO_ID);
            this.sesionAprendizajeId = extras.getInt(SESION_APRENDIZAJE_Id);
            this.calendarioPeriodoId = extras.getInt(TabsSesionesActivityV2.INT_CALENDARIOPERIODO_ID);
            this.cargaAcademicaId= extras.getInt(TabsSesionesActivityV2.INT_CARGA_ACADEMICA_ID);
            this.programaEDucativoId=extras.getInt(TabsSesionesActivityV2.INT_PROGRAMA_EDUCATIVO_ID);
            Log.d(RUBRICA_BID_PRESENTER_IMPL_TAG, "cargaCurso : " + cargaCursoId + "/  cursoId / " + cursoId + "  /  sesion " + sesionAprendizajeId+ " cargaAcademicaId" + cargaAcademicaId);

            periodoUi = getCalendarioPeriodo.execute(calendarioPeriodoId, cargaCursoId);
            getRubricaBidAbstractList();

        }

    }

    @Override
    public void onRubricaSelected(RubBidUi rubBidUi) {
        Log.d(RUBRICA_BID_PRESENTER_IMPL_TAG, "onRubricaSelected");
        if (view != null) {
            view.evaluacionRubricaBidimensional(rubBidUi, cargaCursoId);
        }
    }


    @Override
    public void onParentFabClicked() {

    }

   /* @Override
    public void onActualizarRubricaFragment(Intent intent) {

    }*/

    @Override
    public void onCreate() {
        Log.d(RUBRICA_BID_PRESENTER_IMPL_TAG, "onCreate");
    }

    @Override
    public void attachView(RubricasView view) {
        super.attachView(view);
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(RUBRICA_BID_PRESENTER_IMPL_TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onResumeFragment(String idcalendarioPeriodo) {
        periodoUi = getCalendarioPeriodo.execute(calendarioPeriodoId, cargaCursoId);
        getRubricaBidAbstractList();
    }



}
