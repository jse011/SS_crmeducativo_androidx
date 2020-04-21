package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.sesion;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CrearRubroEvalUi;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.CompetenciaCheck;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.TipoCompetencia;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetCalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetRubroProcesoSesionList;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.AbstractPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.ChangeToogle;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.DeleteRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.PublicarTodasEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.ShowCamposTematicos;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.ShowDesempenioIcds;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.view.FragmentAbstract;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.view.TabsSesionesActivityV2;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 6/02/2018.
 */

public class RubroEvalProcesoListaPresenterImpl extends AbstractPresenterImpl {

    protected int personaId;
    protected int sesionAprendizajeId;
    protected int backgraound;
    protected int idCargaCurso;
    protected int idCargaAcademica;
    protected int nivel;
    public static final String TAG_1 = RubroEvalProcesoListaPresenterImpl.class.getSimpleName();
    private GetRubroProcesoSesionList getRubroResultadoSesion;
    private GetCalendarioPeriodo getCalendarioPeriodo;
    private CapacidadUi selectCapacidadUi;
    private int silavoEventoId;
    private int calendarioPeriodoId;
    private int competenciaId;
    private int rubroEvaluaiconResultadoId;
    private int programaEducativoId;
    public static final int INT_COMPETENCIABASE= 1;
    private int entidadId;
    private int georeferenciaId;

    public RubroEvalProcesoListaPresenterImpl(UseCaseHandler handler, Resources res, DeleteRubroProceso deleteRubroProcesoSilabo, ShowCamposTematicos showCamposTematicos, ShowDesempenioIcds showDesempenioIcds, GetRubroProceso getRubroProceso, GetRubroProcesoSesionList getRubroResultadoSesion, GetCalendarioPeriodo getCalendarioPeriodo, ChangeToogle changeToogle, PublicarTodasEvaluacion updatePublicacionEvaluacion) {
        super(handler,res, deleteRubroProcesoSilabo, showCamposTematicos, showDesempenioIcds, getRubroProceso,changeToogle, updatePublicacionEvaluacion);
        this.getRubroResultadoSesion = getRubroResultadoSesion;
        this.getCalendarioPeriodo =getCalendarioPeriodo;
    }

    /*public RubroEvalProcesoListaPresenterImpl(UseCaseHandler handler, DeleteRubroProceso deleteRubroProcesoSilabo, GetRubroProceso getRubroProceso, GetRubroProcesoSesionList getRubroResultadoSesion, GetRubroProcesoSesionFiltroList getRubroProcesoSesionFiltroList) {
        super(handler, deleteRubroProcesoSilabo, getRubroProceso);
        this.getRubroResultadoSesion = getRubroResultadoSesion;
        this.getRubroProcesoSesionFiltroList = getRubroProcesoSesionFiltroList;
    }*/

    @Override
    protected int getProgramaEducativoId() {
        return programaEducativoId;
    }

    @Override
    public void onActivityCreated() {
        super.onActivityCreated();
        if(view!=null)view.hideCardProcesamiento();
        getRubroProceso(INT_COMPETENCIABASE);
    }

    @Override
    public void onCreateView() {
        super.onCreateView();
    }

    @Override
    protected void onClickCapacidad(CapacidadUi capacidadUi) {

    }

    @Override
    public void onClickAddRubroEvaluacionCapacidad(CapacidadUi capacidadUi) {
        Log.d(TAG_1, "onClickAddRubroEvaluacionCapacidad competenciaId: " + capacidadUi.getId());
        if (isNullView()) return;
        capacidadUi.setToogle(true);
        ChangeToogle.Response response = changeToogle.execute(new ChangeToogle.Requests(capacidadUi.isToogle(),capacidadUi.getId()));

        this.selectCapacidadUi = capacidadUi;
        view.showCrearRubro(sesionAprendizajeId, silavoEventoId,calendarioPeriodoId, capacidadUi.getId(), 0,new ArrayList<Integer>(),programaEducativoId, cursoId);
    }

    @Override
    public void setExtras(Bundle arguments) {
        personaId = arguments.getInt(TabsSesionesActivityV2.INT_PERSONAID);
        sesionAprendizajeId = arguments.getInt(TabsSesionesActivityV2.INT_SESIONAPRENDIZAJEID);
        backgraound = arguments.getInt(TabsSesionesActivityV2.INT_BACKGROUND);
        idCargaCurso = arguments.getInt(TabsSesionesActivityV2.INT_CARGACURSO_ID);
        cursoId = arguments.getInt(TabsSesionesActivityV2.INT_CURSO_ID);
        silavoEventoId = arguments.getInt(TabsSesionesActivityV2.INT_SILAVOEVENTO_ID);
        rubroEvaluaiconResultadoId = arguments.getInt(TabsSesionesActivityV2.INT_RUBROEVALUACIONRESULTADO_ID);
        calendarioPeriodoId = arguments.getInt(TabsSesionesActivityV2.INT_CALENDARIOPERIODO_ID);
        nivel = arguments.getInt(TabsSesionesActivityV2.INT_NIVEL);
        programaEducativoId =  arguments.getInt(TabsSesionesActivityV2.INT_PROGRAMA_EDUCATIVO_ID);
        idCargaAcademica= arguments.getInt(TabsSesionesActivityV2.INT_CARGA_ACADEMICA_ID);
        CRMBundle crmBundle = new CRMBundle(arguments);
        entidadId = crmBundle.getEntidadId();
        georeferenciaId = crmBundle.getGeoreferenciaId();

        Log.d(TAG, "arguments: " + idCargaAcademica);
    }


    @Override
    public void onClickLongClickRubroEvaluacion(RubroProcesoUi rubroProcesoUi) {

    }

    @Override
    public void onSelectDialogListIndicadorCampotematico(int competenciaId, int indicadorId, ArrayList<Integer> campotematicoIds) {
        Log.d(TAG, "onSelectDialogListIndicadorCampotematico competenciaId: " + competenciaId);
        if (isNullView()) return;
        view.showCrearRubro(sesionAprendizajeId, silavoEventoId, calendarioPeriodoId, competenciaId, indicadorId, campotematicoIds, programaEducativoId, cursoId);
        view.hideListaIndicadores();
    }

    @Override
    public void onSalirDialogListIndicadorCampotematico() {

    }

  /*  @Override
    public void onSucessDialogCrearRubroEvaluacionProceso(int rubroevaluacionProcesoId, CrearRubroEvalUi crearRubroEvalUi) {

    }*/

    @Override
    public void onSucessDialogCrearRubroEvaluacionProceso(String rubroevaluacionProcesoId, CrearRubroEvalUi crearRubroEvalUi) {
        Log.d(TAG, "addRubroProceso :" + rubroevaluacionProcesoId);
        if (selectCapacidadUi == null) return;
        final CapacidadUi capacidadUi = new CapacidadUi(selectCapacidadUi.getId());
        final int posicion = selectCapacidadUi.getRubroProcesoUis().size() + 1;
        getRubroProceso(rubroevaluacionProcesoId, new Callback<RubroProcesoUi>() {
            @Override
            public void onListLoaded(RubroProcesoUi rubroProcesoUi) {
                if (isNullView()) return;
                Log.d(TAG, "selectCapacidadUi :" + selectCapacidadUi.getId());
                rubroProcesoUi.setPosicion(posicion);
                rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.SESION);
                if(view == null)return;
                view.addRubroProceso(capacidadUi, rubroProcesoUi);
            }
        });
    }



  /*  @Override
    public void onSucessDialogCrearRubroEvaluacionProceso(CapacidadUi capacidadUi, CrearRubroEvalUi crearRubroEvalUi) {

    }*/

    @Override
    public void onSalirDialogCrearRubroEvaluacionProceso() {

    }

    @Override
    public void onSussesFiltroCompetencias(ArrayList<Integer> competenciaIdList) {
        if (competenciaIdList == null) return;
       // getRubroProcesoList(competenciaIdList);
    }



    @Override
    public void onClickRubroEvaluacion(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi) {

        if (rubroProcesoUi == null) return;
        switch (rubroProcesoUi.getFormEvaluacion()) {
            case INDIVIDUAL:
                if(view == null)return;
                view.showEvaluacionUnidimencionalSesion(rubroProcesoUi.getKey(), rubroProcesoUi.getTitulo(), sesionAprendizajeId, idCargaCurso, cursoId, !capacidadUi.isCalendarioAnclar(), rubroProcesoUi.getTipoNotaId(), entidadId, georeferenciaId, programaEducativoId);
                break;
            case GRUPAL:
                if(view == null)return;
                view.showEvaluacionUnidimencionalGrupos(rubroProcesoUi.getKey(), rubroProcesoUi.getTitulo(), sesionAprendizajeId, idCargaCurso, cursoId,idCargaAcademica, !capacidadUi.isCalendarioAnclar(), rubroProcesoUi.getTipoNotaId());
                break;
        }
    }

    @Override
    public void onUpdateRubroEvaluacion(String rubroEvaluacionId, final int programaEducativoId) {

        getRubroProceso(rubroEvaluacionId, new Callback<RubroProcesoUi>() {
            @Override
            public void onListLoaded(RubroProcesoUi rubroProcesoUi) {
                if (isNullView()) return;
                int posicion = -1;
                switch (nivel) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:

                        break;
                }
                CapacidadUi capacidadUi = new CapacidadUi();
                capacidadUi.setId(rubroProcesoUi.getCapacidadId());
                posicion = rubroProcesoList.indexOf(capacidadUi);
                if (posicion == -1) return;
                capacidadUi = (CapacidadUi) rubroProcesoList.get(posicion);
                List<RubroProcesoUi> rubroProcesoUiList = capacidadUi.getRubroProcesoUis();
                if (rubroProcesoUiList == null) return;
                int posicionCapacidad = rubroProcesoUiList.indexOf(rubroProcesoUi);
                rubroProcesoUi.setPosicion(posicionCapacidad + 1);
                rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.SESION);
                if(view!=null)view.updateRubroProceso(capacidadUi, rubroProcesoUi, programaEducativoId);

            }
        });


        Log.d(TAG, "onUpdateRubroEvaluacion");
    }

    @Override
    public void onActivityResult(Bundle bundle) {
        int tipo = bundle.getInt(FragmentAbstract.TIPO);
        switch (tipo) {
            case FragmentAbstract.TIPO_UPDATE_COMPETENCIA:
                ArrayList<Integer> competenciaIdList = bundle.getIntegerArrayList(FragmentAbstract.LIST_COMPETENCIAS);
                onSussesFiltroCompetencias(competenciaIdList);
                break;
            case FragmentAbstract.TIPO_UPDATE_RUBRO_EVALUACION:
                // int rubroEvaluacionId = bundle.getInt(FragmentAbstract.INT_RUBROEVALUACION_ID);
                String rubroEvaluacionId = bundle.getString(FragmentAbstract.INT_RUBROEVALUACION_ID);
                onUpdateRubroEvaluacion(rubroEvaluacionId, programaEducativoId);
                break;
            case FragmentAbstract.TIPO_UPDATE_RUBRO_EVALUACION_FORMULA://TIPO_UPDATE_RUBRO_EVALUACION
                //int rubroEvaluacionId = bundle.getInt(FragmentAbstract.INT_RUBROEVALUACION_ID);
                RubroProcesoUi rubroUi = Parcels.unwrap(bundle.getParcelable(FragmentAbstract.EXTRA_RUBRO));
                CapacidadUi capacidadUi = Parcels.unwrap(bundle.getParcelable(FragmentAbstract.EXTRA_CAPACIDAD));
                if (rubroUi == null && capacidadUi == null) return;
                String rubroEvaluacionformulaId = rubroUi.getKey();
                Log.d(TAG, "rubroEvaluacionId: " + rubroEvaluacionformulaId);
                onUpdateRubroEvaluacion(rubroEvaluacionformulaId, programaEducativoId);

                break;
            case FragmentAbstract.TIPO_ADD_RUBRO_EVALUACION:
                //int addRubroEvaluacionId = bundle.getInt(FragmentAbstract.INT_RUBROEVALUACION_ID);
                String addRubroEvaluacionId = bundle.getString(FragmentAbstract.INT_RUBROEVALUACION_ID);
                onAddRubroEvaluacion(addRubroEvaluacionId);
                break;
            default:
                Log.d(TAG, "default");
                break;
        }
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
    }

    @Override
    public void onResume() {
        super.onResume();
        //getRubroProceso(INT_COMPETENCIABASE);
    }

    @Override
    public void onActualizarRegitroSesion() {
        Log.d(TAG_1, "onActualizarRegitroSesion");
        getRubroProceso(INT_COMPETENCIABASE);
    }

    @Override
    public void onResumeFragment() {
        Log.d(TAG_1, "onResumeFragment");
        getRubroProceso(INT_COMPETENCIABASE);
    }

    @Override
    public void onselectedItem(ArrayList<CompetenciaCheck> tipoCompetencia) {
        int idrubroformal=1;
        int contador=0;
        for(CompetenciaCheck competenciaCheck: tipoCompetencia){
            if(competenciaCheck.getChecked()){
                contador++;
                if( competenciaCheck.getTipoCompetencia().getNombre().equals(TipoCompetencia.OTROS.getNombre()))idrubroformal=0;
            }
        }
        if(contador>1){
            idrubroformal=3;
        }
        getRubroProceso(idrubroformal);
    }


    @Override
    public void onRefrescarFragment(String idCalendarioPeriodo, boolean status) {
        Log.d(TAG_1, "onRefrescarFragment");
        getRubroProceso(INT_COMPETENCIABASE);
    }

    @Override
    public void onAddRubroEvaluacion(String addRubroEvaluacionId) {
        getRubroProceso(addRubroEvaluacionId, new Callback<RubroProcesoUi>() {
            @Override
            public void onListLoaded(RubroProcesoUi rubroProcesoUi) {
                CapacidadUi capacidadUi = new CapacidadUi();
                capacidadUi.setId(rubroProcesoUi.getCapacidadId());
                int posicion = rubroProcesoList.indexOf(capacidadUi);
                if (posicion == -1) return;
                capacidadUi = (CapacidadUi) rubroProcesoList.get(posicion);
                List<RubroProcesoUi> rubroProcesoUiList = capacidadUi.getRubroProcesoUis();
                if (rubroProcesoUiList == null) return;
                int posicionRubro = rubroProcesoUiList.size() + 1;
                rubroProcesoUi.setPosicion(posicionRubro);
                rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.SESION);
                view.addRubroProceso(capacidadUi, rubroProcesoUi);
            }
        });
    }

    private void getRubroProceso(int idcompetencia){
        if (view != null)view.showTipoRubroProceso();
        if (view != null)view.showProgres();

        if (view !=null) view.showMensaje("");
        handler.execute(getRubroResultadoSesion,
                new GetRubroProcesoSesionList.RequestValues(idcompetencia,sesionAprendizajeId, nivel, calendarioPeriodoId, silavoEventoId, idCargaCurso),
                new UseCase.UseCaseCallback<GetRubroProcesoSesionList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetRubroProcesoSesionList.ResponseValue response) {
                        rubroProcesoList.clear();
                        rubroProcesoList.addAll(response.getItems());

                        Log.d(TAG, "items"+ response.getItems().size());
                        if (view != null)view.showRubroEvaluacionProceso(response.getItems());
                        if (view != null)view.hideProgres();

                        if(response.getItems().isEmpty())if (!isNullView())view.showMensaje(res.getString(R.string.empty_data));
                    }

                    @Override
                    public void onError() {
                        rubroProcesoList.clear();
                    }
                }
        );

    }





}
