package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.principal;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.RubricasView;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.RubricasAbstractPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase.EliminarRubricas;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase.GetActualizasRubricas;

import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase.PublicarTodasEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.principal.domain.useCase.GetRubricaBidLista;

/**
 * Created by CCIE on 07/03/2018.
 */

public class RubricaBidPresenterImpl extends RubricasAbstractPresenterImpl {

    private static String RUBRICA_BID_PRESENTER_IMPL_TAG = RubricaBidPresenterImpl.class.getSimpleName();
    public static String CARGA_CURSO_ID = "idCargaCurso";
    public static String CURSO_ID = "cursoId";

    private Resources res;
    private GetRubricaBidLista getRubricaBidLista;
    private int parametrodisenioid;
    private int idCalendarioPeriodo;



    public RubricaBidPresenterImpl(UseCaseHandler handler, Resources res, GetActualizasRubricas getActualizasRubricas, EliminarRubricas eliminarRubricas, PublicarTodasEvaluacion publicarTodasEvaluacion,GetRubricaBidLista getRubricaBidLista) {
        super(handler, getActualizasRubricas, eliminarRubricas,publicarTodasEvaluacion);
        this.getRubricaBidLista = getRubricaBidLista;
        this.res = res;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void getRubricasLista(ListCallback<RubBidUi> listCallback) {
        Log.d(RUBRICA_BID_PRESENTER_IMPL_TAG, "getRubroProcesoList +  " + idCalendarioPeriodo);
        if (idCalendarioPeriodo == 0){
            if(view!=null)view.mostrarMensaje(res.getString(R.string.empty_calendario));
            if(view!=null)view.ocultarProgressBar();
            return;
        }
        if(view!=null)view.mostrarProgressBar();
        handler.execute(getRubricaBidLista,
                new GetRubricaBidLista.RequestValues(idCalendarioPeriodo, cargaCursoId, cursoId),
                new UseCase.UseCaseCallback<GetRubricaBidLista.ResponseValue>() {
                    @Override
                    public void onSuccess(GetRubricaBidLista.ResponseValue response) {
                        if(response.getList()==null){
                            if(view!=null)view.mostrarProgressBar();
                        } else if (response.getList().size() != 0) {
                            Log.d(RUBRICA_BID_PRESENTER_IMPL_TAG, "if ");
                            Log.d(RUBRICA_BID_PRESENTER_IMPL_TAG, "CountListRubrica " + response.getList().size());
                            if (view != null)view.mostrarListaRubricas(response.getList());
                            if (view != null)view.mostrarListaRubrica();
                            if (view != null)view.ocultarProgressBar();
                            if (view != null) view.ocultarMensaje();
                        } else {
                            Log.d(RUBRICA_BID_PRESENTER_IMPL_TAG, "else ");
                            if (view != null) {
                                view.ocultarListaRubrica();
                                view.mostrarMensaje(res.getString(R.string.empty_data2));
                                view.ocultarProgressBar();
                            }
                        }
                    }

                    @Override
                    public void onError() {
                        if (view != null)view.ocultarProgressBar();
                        Log.d(RUBRICA_BID_PRESENTER_IMPL_TAG, "getRubricasLista::onError");
                    }
                });
    }




    @Override
    public void onRubricaSelected(RubBidUi rubBidUi) {
        if (view != null) {
            view.evaluacionRubricaBidimensional(rubBidUi, cargaCursoId );
        }
    }
    int cursoId;

    @Override
    public void setExtras(Bundle extras) {
        if (extras != null) {
            this.cargaCursoId = extras.getInt(CARGA_CURSO_ID);
            this.cursoId = extras.getInt(CURSO_ID);
            this.programaEDucativoId=extras.getInt("idProgramaEducativo");
            this.parametrodisenioid=extras.getInt("parametrodisenioid");
            this.idCalendarioPeriodo=extras.getInt("idCalendarioPeriodo");

            Log.d(RUBRICA_BID_PRESENTER_IMPL_TAG, "cargaCurso : " + cargaCursoId + "/  cursoId / " + cursoId);
        }
        if(view!=null)view.mostrarProgressBar();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getRubricaBidAbstractList();
            }
        }, 500);

    }

    @Override
    public void onCreate() {
        Log.d(RUBRICA_BID_PRESENTER_IMPL_TAG, "onCreate");
        //view.showPeriodo();
    }


    @Override
    public void attachView(RubricasView view) {
        super.attachView(view);

    }

    public int  getIdCalendarioPeriodoID() {
        return idCalendarioPeriodo;
    }

    @Override
    public void onParentFabClicked() {
        launchCreateRubBidActivity(String.valueOf(cargaCursoId), String.valueOf(cursoId), getIdCalendarioPeriodoID(),programaEDucativoId);
    }

    private void launchCreateRubBidActivity(String idCargaCurso, String idCurso, int  idCalendarioPeriodo,int idProgramaEducativo) {
        if (view != null)
            view.launchCreateRubBidActivity(idCargaCurso, idCurso, idCalendarioPeriodo, idProgramaEducativo);
    }

    @Override
    public void onResumeFragment(final String idcalendarioPeriodo) {
     try{

         if(view!=null)view.mostrarProgressBar();
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 RubricaBidPresenterImpl.this.idCalendarioPeriodo=Integer.parseInt(idcalendarioPeriodo);
                 if(idCalendarioPeriodo!=0)getRubricaBidAbstractList();
             }
         },500);

     }catch (Exception e){
         e.printStackTrace();
     }

    }


}
