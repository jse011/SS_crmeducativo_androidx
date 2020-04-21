package com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.presenter;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadParametros;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase.GetIndicadoresUnidad;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase.GetRubricasUnidad;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase.GetRubrosCompetenciaUnidad;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.RubricaUi;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.frames.evaluacion.view.EvaluacionView;

public class EvaluacionUnidadPresenterImpl extends BaseFragmentPresenterImpl<EvaluacionView> implements EvaluacionUnidadPresenter{

    private String TAG= EvaluacionUnidadPresenterImpl.class.getSimpleName();
    private GetIndicadoresUnidad getIndicadoresUnidad;
    private int unidadAprendizajeId;
    private int calendarioPeriodoId;
    private int cargaCursoId;
    private int programaEducativoId;
    private int cargaAcademicaId;
    private GetRubricasUnidad getRubricasUnidad;
    private GetRubrosCompetenciaUnidad getRubrosCompetenciaUnidad;

    public EvaluacionUnidadPresenterImpl(UseCaseHandler handler, Resources res,GetIndicadoresUnidad getIndicadoresUnidad,GetRubricasUnidad getRubricasUnidad,GetRubrosCompetenciaUnidad getRubrosCompetenciaUnidad) {
        super(handler, res);
        this.getIndicadoresUnidad=getIndicadoresUnidad;
        this.getRubricasUnidad=getRubricasUnidad;
        this.getRubrosCompetenciaUnidad=getRubrosCompetenciaUnidad;
    }

    @Override
    protected String getTag() {
        return TAG;
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

    private void getIndicadores() {
        if(view==null)return;
        GetIndicadoresUnidad.Response response= getIndicadoresUnidad.execute(new GetIndicadoresUnidad.Requests(unidadAprendizajeId));
//        if (response.getIndicadorUis().isEmpty())
//        else{
//
//        }
        if(view!=null)view.setListIndicadores(response.getIndicadorUis());
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
        UnidadParametros unidadParametros = UnidadParametros.clone(extras);
        if (unidadParametros!=null){
            this.unidadAprendizajeId = unidadParametros.getUnidadAprendizajeId();
            this.calendarioPeriodoId= unidadParametros.getCalendarioPeriodoId();
            this.cargaCursoId= unidadParametros.getCargaCursoId();
            this.programaEducativoId=unidadParametros.getProgramaEducativoId();
            this.cargaAcademicaId=unidadParametros.getCargaAcademicaId();
            Log.d(TAG, "unidadAprendizajeId "+ unidadAprendizajeId+ " cargaCursoId "+cargaCursoId + " calendarioPeriodoId "+calendarioPeriodoId + " programaEducativoId "+programaEducativoId);
        }
        if(view!=null)view.initAdapter();
        getIndicadores();
        getRubricas();
        getrubros();
    }

    private void getrubros() {
        if(view==null)return;
        handler.execute(getRubrosCompetenciaUnidad, new GetRubrosCompetenciaUnidad.RequestValues(unidadAprendizajeId, cargaCursoId, calendarioPeriodoId), new UseCase.UseCaseCallback<GetRubrosCompetenciaUnidad.ResponseValues>() {
            @Override
            public void onSuccess(GetRubrosCompetenciaUnidad.ResponseValues response) {
                Log.d(TAG, "competencias  size "+ response.getObjects().size());
                if(view!=null){
                    view.setListCompetenciasRubros( response.getObjects());
                    if(response.getObjects().isEmpty())
                        view.showTxtVacioRubro();
                    else view.hideTxtVacioRubro();
                }

            }

            @Override
            public void onError() {

            }
        });

    }

    private void getRubricas() {
    if(view==null)return;

    handler.execute(getRubricasUnidad, new GetRubricasUnidad.RequestValues(unidadAprendizajeId, cargaCursoId, calendarioPeriodoId), new UseCase.UseCaseCallback<GetRubricasUnidad.ResponseValues>() {
        @Override
        public void onSuccess(GetRubricasUnidad.ResponseValues response) {
            Log.d(TAG, "rubricas size "+ response.getRubricaUiList().size());
            if(view!=null){
                view.setListRubricas( response.getRubricaUiList());
                if(response.getRubricaUiList().isEmpty())view.showTxtVacioRubrica();
                else view.hideTxtVacioRubrica();
            }
        }

        @Override
        public void onError() {

        }
    });
    }

    @Override
    public void onClickRubrica(RubricaUi rubricaUi) {
      //  Log.d(TAG, "rubricas size "+ response.getRubricaUiList().size());
        switch (rubricaUi.getTipo()){
            case RUBRO:
                showEvaluacionRubro(rubricaUi);
                break;
             default:
                 showEvaluacionRubrica(rubricaUi);
                 break;
        }
       
    }

    private void showEvaluacionRubro(RubricaUi rubroProcesoUi) {
        switch (rubroProcesoUi.getForma()){
            case INDIVIDUAL:
                if(view!=null)view.showEvaluacionRubroIndividual(rubroProcesoUi.getIdRubrica(), rubroProcesoUi.getTitulo(), 0, cargaCursoId, 0, true, rubroProcesoUi.getTipoNotaId(), 0, 0, programaEducativoId);
                break;
            default:
                if(view!=null)view.showEvaluacionRubroGrupal(rubroProcesoUi.getIdRubrica(), rubroProcesoUi.getTitulo(), 0, cargaCursoId, 0, cargaAcademicaId, true, rubroProcesoUi.getTipoNotaId(), programaEducativoId);
                break;
        }

    }

    private void showEvaluacionRubrica(RubricaUi rubricaUi) {
        CRMBundle crmBundle = new CRMBundle(extras);
        crmBundle.setCalendarioActivo(false);
        crmBundle.setCalendarioEditar(rubricaUi.isHabilitado());
        switch (rubricaUi.getForma()){
            case INDIVIDUAL:
                if(view!=null)view.showEvaluacionIndividualRubrica(crmBundle, cargaCursoId, rubricaUi.getIdRubrica());
                break;
            default:
                if(view!=null)view.showEvaluacionGrupalRubrica(crmBundle, cargaCursoId, rubricaUi.getIdRubrica());
                break;
        }
    }
}
