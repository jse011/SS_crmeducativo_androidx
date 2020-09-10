package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.silabo;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.CompetenciaCheck;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.TipoCompetencia;
import com.consultoraestrategia.ss_crmeducativo.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.ChangeEstadoActualizacion;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.AutoSaveFormulaCapacidad;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroEvaluacionResultadoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.AbstractPresenterImpl;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.ChangeToogle;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.DeleteRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.PublicarTodasEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.ShowCamposTematicos;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.ShowDesempenioIcds;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.view.FragmentAbstract;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.view.FragmentAbstractView;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros.SeleccionarRubrosActivity;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros.SeleccionarRubrosListWrapper;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.AnclarUse;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.DesanclarFormula;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetPeriodoList;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetRubroEvalProcesoList;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.obtenerRubroEvaluacionProceso.remote.BEObtenerDatosRubroProcesoRemote;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kike on 12/02/2018.
 */

public class RubroResultadoSilaboPresenterImpl extends AbstractPresenterImpl {

    private static int NIVEL_AREA = 1;
    private static int NIVEL_COMPETENCIA = 2;
    private static int NIVEL_CAPACIDAD = 3;
    public static int REGISTRO_ERROR = 0;
    public static int REGISTRO_SUCCESS = 1;
    public static int REGISTRO_MESSAGE = 2;
    public static final int COMPETENCIA_BASE = 1;
    private int cargaCursoId, cargaAcademicaId;
    private GetPeriodoList getPeriodoList;
    private GetRubroEvalProcesoList rubroEvalProcesoList;
    private AnclarUse anclarUse;
    private DesanclarFormula desanclarFormula;
    private BEObtenerDatosRubroProcesoRemote beObtenerDatosRubroProcesoRemote;
    private int parametrodisenioid;
    PeriodoUi periodoSelected;
    int idCalendarioPeriodo;
    private int entidadId;
    private int georeferenciaId;
    private int idcompetencia;
    private AutoSaveFormulaCapacidad saveFormulaCapacidad;
    private ChangeEstadoActualizacion changeEstadoActualizacion;


    public RubroResultadoSilaboPresenterImpl(UseCaseHandler handler, Resources res,
                                             DeleteRubroProceso deleteRubroProcesoSilabo,
                                             ShowCamposTematicos showCamposTematicos,
                                             ShowDesempenioIcds showDesempenioIcds,
                                             GetRubroProceso getRubroProceso,
                                             GetPeriodoList getPeriodoList,
                                             GetRubroEvalProcesoList rubroEvalProcesoList,
                                             AnclarUse anclarUse,
                                             ChangeToogle changeToogle,
                                             DesanclarFormula desanclarFormula,
                                             PublicarTodasEvaluacion publicarTodasEvaluacion,
                                             AutoSaveFormulaCapacidad saveFormulaCapacidad,
                                             ChangeEstadoActualizacion changeEstadoActualizacion) {
        super(handler,res, deleteRubroProcesoSilabo,showCamposTematicos, showDesempenioIcds, getRubroProceso, changeToogle, publicarTodasEvaluacion);
        this.getPeriodoList = getPeriodoList;
        this.rubroEvalProcesoList = rubroEvalProcesoList;
        this.anclarUse = anclarUse;
        this.desanclarFormula = desanclarFormula;
        this.saveFormulaCapacidad = saveFormulaCapacidad;
        //   this.beObtenerDatosRubroProcesoRemote = beObtenerDatosRubroProcesoRemote;
        this.changeEstadoActualizacion = changeEstadoActualizacion;
    }


    private int programaEducativoId;

    @Override
    public void setExtras(Bundle arguments) {
        Log.d(TAG, "idCalendarioPeriodo setExtras" );
        if (arguments != null) {
            this.cargaCursoId = arguments.getInt("idCargaCurso");
            this.cursoId = arguments.getInt("cursoId");
            this.programaEducativoId = arguments.getInt("idProgramaEducativo");
            this.parametrodisenioid =  arguments.getInt("parametrodisenioid", 0);
            this.idCalendarioPeriodo = arguments.getInt("idCalendarioPeriodo",0);
            Log.d(TAG, "idCalendarioPeriodo: " + idCalendarioPeriodo );
            CRMBundle crmBundle = new CRMBundle(arguments);
            this.cargaAcademicaId =crmBundle.getCargaAcademicaId();
//                    Log.d(TAG, "cargaCurso : " + cargaCursoId + "/  cursoId / " + cursoId + " / programaEducativoId / " + programaEducativoId + "idCalendarioPeriodo "+ idCalendarioPeriodo + " cargaAcademicaId " + cargaAcademicaId );
            this.entidadId = crmBundle.getEntidadId();
            this.georeferenciaId = crmBundle.getGeoreferenciaId();
            this.idcompetencia = COMPETENCIA_BASE;
            if(view!=null)view.showProgres();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getRubroProceso(idcompetencia,idCalendarioPeriodo);
                }
            }, 1000);


        }
    }

    private CapacidadUi capacidadUiAgregaRubro;

    @Override
    public void onClickAddRubroEvaluacionCapacidad(CapacidadUi capacidadUi) {
//        Log.d(TAG, "capacidadUi : " + capacidadUi.getTitle());
//        Log.d(TAG, "onClickAddRubroEvaluacionCapacidad : " + capacidadUi.getSilaboEventoId());

        capacidadUi.setToogle(true);
        ChangeToogle.Response response = changeToogle.execute(new ChangeToogle.Requests(capacidadUi.isToogle(),capacidadUi.getId()));

        List<RubroProcesoUi> listaNuevaProceso = new ArrayList<>();
        if (capacidadUi.getNivel() == NIVEL_AREA) {
            /*Aqui llegan sin indicadores*/
//            Log.d(TAG, "NIVEL_AREA  ");
            this.capacidadUiAgregaRubro = capacidadUi;
            if (view != null)
                view.showCrearRubro(0, capacidadUi.getSilaboEventoId(), capacidadUi.getCalendarioId(), 0, 0, new ArrayList<Integer>(),programaEducativoId, cursoId);
        } else if (capacidadUi.getNivel() == NIVEL_CAPACIDAD) {
            /*Llegan con indicadores*/
//            Log.d(TAG, "NIVEL_COMPETENCIA  ");
            this.capacidadUiAgregaRubro = capacidadUi;
            if (capacidadUi.getRubroProcesoUis().isEmpty()) {
                if (view != null)
                    view.showCrearRubro(0, capacidadUi.getSilaboEventoId(),capacidadUi.getCalendarioId(), capacidadUi.getId(), 0,new ArrayList<Integer>(),programaEducativoId, cursoId);
            } else if (capacidadUi.getRubroProcesoUis() != null) {
                int trueCount = 0;
                for (int i = 0; i < capacidadUi.getRubroProcesoUis().size(); i++) {
                    if (capacidadUi.getRubroProcesoUis().get(i).isCheck()) {
                        trueCount++;
                        RubroProcesoUi procesoUi = capacidadUi.getRubroProcesoUis().get(i);

                        //    Log.d(TAG, "numero : " + procesoUi.getPosicion() + " / id " + procesoUi.getId());
                        RubroProcesoUi procesoUiNuevo = new RubroProcesoUi();
                        //  procesoUiNuevo.setId(procesoUi.getId());
                        procesoUiNuevo.setKey(procesoUi.getKey());
                        procesoUiNuevo.setDesempenioIcdId(procesoUi.getDesempenioIcdId());
                        procesoUiNuevo.setTitulo(procesoUi.getTitulo());
                        procesoUiNuevo.setExportado(procesoUi.isExportado());
                        procesoUiNuevo.setSubTitulo(procesoUi.getSubTitulo());
                        procesoUiNuevo.setFecha(Utils.getDatePhone());
                        procesoUiNuevo.setColorRubro(procesoUi.getColorRubro());
                        procesoUiNuevo.setCheck(procesoUi.isCheck());
                        procesoUiNuevo.setTipoNotaId(procesoUi.getTipoNotaId());
                        procesoUiNuevo.setTipoFormulaId(procesoUi.getTipoFormulaId());
                        procesoUiNuevo.setTipoValorRedondeoId(procesoUi.getTipoValorRedondeoId());
                        procesoUiNuevo.setValorPorDefecto(procesoUi.getValorPorDefecto());
                        procesoUiNuevo.setTipoEvaluacionId(procesoUi.getTipoEvaluacionId());
                        procesoUiNuevo.setPeso(procesoUi.getPeso());
                        procesoUiNuevo.setColorRubro(procesoUi.getColorRubro());
                        procesoUiNuevo.setAndroidId(procesoUi.getAndroidId());
                        procesoUiNuevo.setCalendarioPeriodId(capacidadUi.getCalendarioId());
                        procesoUiNuevo.setCapacidadId(capacidadUi.getId());
                        procesoUiNuevo.setPosicion(procesoUi.getPosicion());
                        procesoUiNuevo.setMedia(procesoUi.getMedia());
                        procesoUiNuevo.setDesviacionE(procesoUi.getDesviacionE());
                        procesoUiNuevo.setFecha(procesoUi.getFecha());
                        procesoUiNuevo.setRubrosAsociadosUiList(procesoUi.getRubrosAsociadosUiList());
                        procesoUiNuevo.setTituloRubrica(procesoUi.getTituloRubrica());
                        procesoUiNuevo.setCheckDisbled(true);
                        procesoUiNuevo.setFormEvaluacion(procesoUi.getFormEvaluacion());
                        procesoUiNuevo.setTiposRubros(procesoUi.getTiposRubros());
                        procesoUiNuevo.setOrigen(procesoUi.getOrigen());
                        procesoUiNuevo.setTipo(procesoUi.getTipo());
                        procesoUiNuevo.setTipoEvaluacion(procesoUi.getTipoEvaluacion());
                        procesoUiNuevo.setFormEvaluacion(procesoUi.getFormEvaluacion());
                        listaNuevaProceso.add(procesoUiNuevo);
                        Log.d(TAG, "if : " + trueCount);
                    }
                }

                if (trueCount >= 1) {
                    if (view != null)view.initDialogTypeFormula(listaNuevaProceso, capacidadUi, programaEducativoId, cargaCursoId);
                } else if (trueCount == 0) {
                    if (view != null)
                        view.showCrearRubro(0, capacidadUi.getSilaboEventoId(),capacidadUi.getCalendarioId(), capacidadUi.getId(), 0,new ArrayList<Integer>(),programaEducativoId, cursoId);
                } /*else if (trueCount == 1) {
                    if (view != null) {
                        view.hideProgres();
                        Log.d(TAG, "Debe Seleccionar 2 Items");
                        view.showMessage("Debe Seleccionar 2 Items");
                    }
                }*/
            } else {
                if (view != null)
                    view.showCrearRubro(0, capacidadUi.getSilaboEventoId(),capacidadUi.getCalendarioId(), capacidadUi.getId(), 0,new ArrayList<Integer>(),programaEducativoId, cursoId);
            }
        } else if (capacidadUi.getNivel() == NIVEL_COMPETENCIA) {
            this.capacidadUiAgregaRubro = capacidadUi;
            /*Llegan con indicadores*/
//            Log.d(TAG, "NIVEL_CAPACIDAD  ");
        } else {
//            Log.d(TAG, "Nivel Diferente ");
        }

    }


    @Override
    public void onClickRubroEvaluacion(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi) {
        if (rubroProcesoUi == null) return;
        switch (rubroProcesoUi.getFormEvaluacion()) {
            case INDIVIDUAL:
                if (view == null) return;

                view.showEvaluacionUnidimencionalSesion(rubroProcesoUi.getKey(), rubroProcesoUi.getTitulo(), 0, cargaCursoId, cursoId, !capacidadUi.isCalendarioAnclar(), rubroProcesoUi.getTipoNotaId(), entidadId, georeferenciaId, programaEducativoId);
                break;
            case GRUPAL:
                if (view == null) return;
                view.showEvaluacionUnidimencionalGrupos(rubroProcesoUi.getKey(), rubroProcesoUi.getTitulo(), 0, cargaCursoId, cursoId, cargaAcademicaId, !capacidadUi.isCalendarioAnclar(), rubroProcesoUi.getTipoNotaId());
                break;
            case FORMULA:
               /* RubroEvaluacionProcesoUi rubroEvaluacionProcesoUi = new RubroEvaluacionProcesoUi();
                rubroEvaluacionProcesoUi.setKey(rubroProcesoUi.getKey());
                rubroEvaluacionProcesoUi.setTitle(rubroProcesoUi.getTitulo());
                rubroEvaluacionProcesoUi.setSubTitulo(rubroProcesoUi.getSubTitulo());
                rubroEvaluacionProcesoUi.setIdTipoNota(rubroProcesoUi.getTipoNotaId());
                rubroEvaluacionProcesoUi.setIdTipoFormula(rubroProcesoUi.getTipoFormulaId());
                Log.d(TAG, "getTipoNotaId : " + rubroProcesoUi.getTipoNotaId() + "TipoFormulaID " + rubroProcesoUi.getTipoFormulaId());
                for (RubrosAsociadosUi itemRubrosAsociadosUi : rubroProcesoUi.getRubrosAsociadosUiList()) {

                    com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionResultadoList.entities.RubrosAsociadosUi rubrosAsociadosUi = rubroEvaluacionProcesoUi.newInstanceRubrosAsociadosUi();
                    String rubroProcesoPrincipal = String.valueOf(rubrosAsociadosUi.getIdRubroEvaluacionProcesoPrincipal());
                    String rubroProcesoSencundario = String.valueOf(rubrosAsociadosUi.getIdRubroEvaluacionProcesoSecundario());


                    rubrosAsociadosUi.setIdRubroEvaluacionProcesoPrincipal(rubroProcesoPrincipal);
                    rubrosAsociadosUi.setIdRubroEvaluacionProcesoSecundario(rubroProcesoSencundario);
                    rubrosAsociadosUi.setNumeroRubroAsociado(itemRubrosAsociadosUi.getNumeroRubroAsociado());
                    rubroEvaluacionProcesoUi.addRubrosAsociados(rubrosAsociadosUi);
                }*/
                // if (view != null)view.initViewTipoRubros(new RubroEvaluacionResultadoUi(), rubroEvaluacionProcesoUi, cargaCursoId, cursoId);
                if (view != null)
                    view.initViewTipoRubros(new RubroEvaluacionResultadoUi(), rubroProcesoUi, cargaCursoId, cursoId);
                break;
        }

    }


    @Override
    public void onClickLongClickRubroEvaluacion(RubroProcesoUi rubroProcesoUi) {
        Log.d(TAG, "onClickLongClickRubroEvaluacion");
    }

    @Override
    public void onSelectDialogListIndicadorCampotematico(int competenciaId, int indicadorId, ArrayList<Integer> campotematicoIds) {
        Log.d(TAG, "onSelectDialogListIndicadorCampotematico");
        if (view != null) {
            view.hideListaIndicadores();
            view.showCrearRubro(0, capacidadUiAgregaRubro.getSilaboEventoId(), capacidadUiAgregaRubro.getCalendarioId(), competenciaId, indicadorId, campotematicoIds, programaEducativoId, cursoId);
        }
    }


    @Override
    public void onSalirDialogListIndicadorCampotematico() {
        Log.d(TAG, "onSalirDialogListIndicadorCampotematico");
    }

    @Override
    public void onSucessDialogCrearRubroEvaluacionProceso(String rubroevaluacionProcesoId, com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CrearRubroEvalUi crearRubroEvalUi) {
        Log.d(TAG, "CapacidadDIalogAceptar : " + rubroevaluacionProcesoId + "");
        getRubroProceso(rubroevaluacionProcesoId, new Callback<RubroProcesoUi>() {
            @Override
            public void onListLoaded(RubroProcesoUi rubroProcesoUi) {
                if (isNullView()) return;

                CapacidadUi capacidadUi = new CapacidadUi();
                capacidadUi.setId(rubroProcesoUi.getCapacidadId());
                int posicion = rubroProcesoList.indexOf(capacidadUi);
                if (posicion == -1) return;
                capacidadUi = (CapacidadUi) rubroProcesoList.get(posicion);
                List<RubroProcesoUi> rubroProcesoUiList = capacidadUi.getRubroProcesoUis();
                if (rubroProcesoUiList == null) return;
                int posicionRubro = rubroProcesoUiList.size() + 1;
                rubroProcesoUi.setPosicion(posicionRubro);
                rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.SILABO);
                if (view != null) view.addRubroProceso(capacidadUi, rubroProcesoUi);
            }
        });
    }


    @Override
    public void onSalirDialogCrearRubroEvaluacionProceso() {
        Log.d(TAG, "onSalirDialogCrearRubroEvaluacionProceso");
    }

    @Override
    public void onSussesFiltroCompetencias(ArrayList<Integer> competenciaIdList) {
        Log.d(TAG, "onSussesFiltroCompetencias");
    }


    @Override
    protected int getProgramaEducativoId() {
        return programaEducativoId;
    }


    @Override
    protected void onClickCapacidad(CapacidadUi capacidadUi) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "idCalendarioPeriodo onCreate");
    }

    @Override
    public void attachView(FragmentAbstractView view) {
        super.attachView(view);

        Log.d(TAG, "idCalendarioPeriodo attachView");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "idCalendarioPeriodo onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "idCalendarioPeriodo onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "idCalendarioPeriodo onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "idCalendarioPeriodo onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "idCalendarioPeriodo onDestroy");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "idCalendarioPeriodo onBackPressed");
    }

    @Override
    public void onAttach() {
        super.onAttach();
        Log.d(TAG, "idCalendarioPeriodo onAttach");
    }

    @Override
    public void onCreateView() {
        super.onCreateView();
        Log.d(TAG, "idCalendarioPeriodo onCreateView");
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        Log.d(TAG, "idCalendarioPeriodo onViewCreated");
    }

    @Override
    public void onActivityCreated() {
        super.onActivityCreated();
        Log.d(TAG, "idCalendarioPeriodo onActivityCreated");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
        Log.d(TAG, "idCalendarioPeriodo onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "idCalendarioPeriodo onDetach");
    }

    @Override
    public void onAddBasicDialog(CapacidadUi capacidadUi, com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CrearRubroEvalUi crearRubroEvalUi) {
        super.onAddBasicDialog(capacidadUi, crearRubroEvalUi);
        Log.d(TAG, "onAddBasicDialog");
    }




    @Override
    public void onClickRubrosAsociados(RubroProcesoUi rubroProcesoUi, CapacidadUi capacidadUi) {
        Log.d(TAG, "onClickRubrosAsociados : " + rubroProcesoUi.isCheck());
        /*if (rubroProcesoUi.isCheck()) {
            rubroProcesoUi.setCheck(false);
        } else {
            rubroProcesoUi.setCheck(true);
        }*/
        CompetenciaUi competenciaUiClone = new CompetenciaUi();
        competenciaUiClone.setId(capacidadUi.getCompetenciaUi().getId());
        competenciaUiClone.setTitulo(capacidadUi.getCompetenciaUi().getTitulo());
        competenciaUiClone.setNivel(capacidadUi.getCompetenciaUi().getNivel());
        competenciaUiClone.setIdCalendarioPeriodo(capacidadUi.getCompetenciaUi().getIdCalendarioPeriodo());
        competenciaUiClone.setTipo(capacidadUi.getCompetenciaUi().getTipo());
        capacidadUi.setCompetenciaUi(competenciaUiClone);
        if(view!=null)view.showSeleccionarRubros(capacidadUi);

    }

    @Override
    public void onAgregarRubroFormula(CapacidadUi capacidadUi, RubroProcesoUi crearRubroEvalUi) {
        if (view != null) {
            Log.d(TAG, "agregandoFormula : " + crearRubroEvalUi.getRubrosAsociadosUiList().size());
            view.addRubroProceso(capacidadUi, crearRubroEvalUi);
        }
        //limpiarCheck(capacidadUi);
    }

    private void limpiarCheck(CapacidadUi capacidadUi) {
        int posicionCapacidad = rubroProcesoList.indexOf(capacidadUi);
        if (posicionCapacidad != -1) {
            CapacidadUi capacidadUi1 = (CapacidadUi) rubroProcesoList.get(posicionCapacidad);
            if (capacidadUi1 != null) {
                for (int i = 0; i < capacidadUi1.getRubroProcesoUis().size(); i++) {
                    RubroProcesoUi rubroProcesoUi = capacidadUi1.getRubroProcesoUis().get(i);
                    rubroProcesoUi.setCheck(false);
                }
            }
            if (view != null) view.refreshList(capacidadUi);
        }
    }


    @Override
    public void onUpdateRubroEvaluacion(String rubroEvaluacionId, final int programaEducativoId) {
//        Log.d(TAG, "rubroEvaluacionId :" + rubroEvaluacionId);
        getRubroProceso(rubroEvaluacionId, new Callback<RubroProcesoUi>() {
            @Override
            public void onListLoaded(RubroProcesoUi rubroProcesoUi) {
                if (isNullView()) return;
                CapacidadUi capacidadUi = new CapacidadUi();
                capacidadUi.setId(rubroProcesoUi.getCapacidadId());
                int posicion = rubroProcesoList.indexOf(capacidadUi);
                if (posicion == -1) return;
                capacidadUi = (CapacidadUi) rubroProcesoList.get(posicion);
                List<RubroProcesoUi> rubroProcesoUiList = capacidadUi.getRubroProcesoUis();
                if (rubroProcesoUiList == null) return;
                int posicionCapacidad = rubroProcesoUiList.indexOf(rubroProcesoUi);
                rubroProcesoUi.setPosicion(posicionCapacidad + 1);
                rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.SILABO);
                if (view != null) view.updateRubroProceso(capacidadUi, rubroProcesoUi, programaEducativoId);
            }
        });

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
                rubroProcesoUi.setOrigen(RubroProcesoUi.Origen.SILABO);
                if (view != null) view.addRubroProceso(capacidadUi, rubroProcesoUi);
            }
        });
    }

    @Override
    public void onActivityResult(Bundle bundle) {
        int tipo = bundle.getInt(FragmentAbstract.TIPO);
//        Log.d(TAG, "tipo :" + tipo);
        switch (tipo) {
            case FragmentAbstract.TIPO_UPDATE_RUBRO_EVALUACION:
                // int rubroEvaluacionId = bundle.getInt(FragmentAbstract.INT_RUBROEVALUACION_ID);
                String rubroEvaluacionId = bundle.getString(FragmentAbstract.INT_RUBROEVALUACION_ID);
                int programaEducativoId = bundle.getInt(FragmentAbstract.EXTRA_PROGRAMA_EDUCATIVO);
                onUpdateRubroEvaluacion(rubroEvaluacionId, programaEducativoId);
                break;
            case FragmentAbstract.TIPO_ADD_RUBRO_EVALUACION:
                //int addRubroEvaluacionId = bundle.getInt(FragmentAbstract.INT_RUBROEVALUACION_ID);
                String addRubroEvaluacionId = bundle.getString(FragmentAbstract.INT_RUBROEVALUACION_ID);
                onAddRubroEvaluacion(addRubroEvaluacionId);
                break;
            case FragmentAbstract.TIPO_ADD_RUBRO_EVALUACION_FORMULA:
                RubroProcesoUi rubroProcesoUiFormula = Parcels.unwrap(bundle.getParcelable("rubroUi"));
//                Log.d(TAG, "success : " + rubroProcesoUiFormula.getRubrosAsociadosUiList().size());
                CapacidadUi capacidadUiFormula = Parcels.unwrap(bundle.getParcelable("capacidadUi"));
                if (rubroProcesoUiFormula == null && capacidadUiFormula == null) return;
                onAgregarRubroFormula(capacidadUiFormula, rubroProcesoUiFormula);
                break;
            case FragmentAbstract.TIPO_UPDATE_RUBRO_EVALUACION_FORMULA:
                RubroProcesoUi rubroUi = Parcels.unwrap(bundle.getParcelable(FragmentAbstract.EXTRA_RUBRO));
                CapacidadUi capacidadUi = Parcels.unwrap(bundle.getParcelable(FragmentAbstract.EXTRA_CAPACIDAD));
                if (rubroUi == null && capacidadUi == null) return;
                onActualizarRubrosProceso(capacidadUi, rubroUi);
                break;
            case FragmentAbstract.TIPO_SELECCIONAR_RUBRO:
                SeleccionarRubrosListWrapper seleccionarRubrosListWrapper = Parcels.unwrap(bundle.getParcelable(SeleccionarRubrosActivity.EXTRA_CAPACIDAD));
                CapacidadUi capacidadUiSelect = seleccionarRubrosListWrapper.getCapacidadUi();
                if(capacidadUiSelect==null)return;
                onSelecionarRubros(capacidadUiSelect);
                break;
            default:
                Log.d(TAG, "default");
                break;
        }
    }


    private void onSelecionarRubros(CapacidadUi capacidadUiSelect) {
        onClickAddRubroEvaluacionCapacidad(capacidadUiSelect);
    }


    private void onActualizarRubrosProceso(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi) {
        if (view != null) {
            view.updateRubroProceso(capacidadUi, rubroProcesoUi, getProgramaEducativoId());
        }
    }

    @Override
    public void onAceptarDialogAnclar(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi) {
        // initCasoUsoFormulaAncla(capacidadUi, rubroProcesoUi);
        validacionAncla(capacidadUi, rubroProcesoUi);
    }

    private void initCasoUsoFormulaAncla(final CapacidadUi capacidadUi, final RubroProcesoUi rubroProcesoUi) {
        //rubroProcesoUi.setTipoAncla(true);

        // validacionAncla(capacidadUi,rubroProcesoUi);

//        Log.d(TAG, "initCasoUsoFormula : " + capacidadUi.getId() + "/ rubroProcesoUi" + rubroProcesoUi.getKey() + " " +
//                " / Calendario Periodo Rub" + rubroProcesoUi.getCalendarioPeriodId() + "/ Cap : " + capacidadUi.getCalendarioId() + "" +
//                "/ SilaboEvento : " + rubroProcesoUi.getSilaboEventoId() + " / Cap : " + capacidadUi.getSilaboEventoId());
        anclarUse.execute(new AnclarUse.RequestValues(capacidadUi, rubroProcesoUi), new UseCaseSincrono.Callback<AnclarUse.ResponseValue>() {
            @Override
            public void onResponse(boolean success, AnclarUse.ResponseValue response) {
                if(response.isSuccess()){
                    rubroProcesoUi.setTipoFormula(RubroProcesoUi.TipoFormula.ANCLADA);
                    rubroProcesoUi.setTipoAncla(true);
                    capacidadUi.setRubroEvalAnclado(rubroProcesoUi);
                    if (view != null) view.updateAncladoCapacidad(capacidadUi);
                    if (view != null) view.updateRubroProceso(capacidadUi, rubroProcesoUi, getProgramaEducativoId());
                }else {
                    if(!TextUtils.isEmpty(response.getMensaje()))if(view!=null)view.showMessage(response.getMensaje());
                }
            }
        });

    }



    private void validacionAncla(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi) {
        Log.d(TAG, "");
        boolean anclaExistente = false;
        //if(capacidadUi.getRubroProcesoUis())
        for (RubroProcesoUi procesoUi : capacidadUi.getRubroProcesoUis()) {
            if (procesoUi.isTipoAncla()) {
                anclaExistente = true;
            }
        }


        if (anclaExistente) {
            if (view != null) view.showMessage("Solo se puede Anclar una vez");
        } else {
            initCasoUsoFormulaAncla(capacidadUi, rubroProcesoUi);
        }

    }

    @Override
    public void actualizarListaRubricas() {
        Log.d(TAG, "actualizarListaRubricas");

    }

    @Override
    public void onClickSelectRubros(CapacidadUi capacidadUi) {
        capacidadUi.setToogle(true);
        ChangeToogle.Response response = changeToogle.execute(new ChangeToogle.Requests(capacidadUi.isToogle(),capacidadUi.getId()));
        if(view!=null)view.showSeleccionarRubros(capacidadUi);
    }

    @Override
    public void onRefrescarFragment(final String idCalendarioPeriodo, boolean status) {
        if(view!=null)view.showProgres();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getRubroProceso(idcompetencia, Integer.parseInt(TextUtils.isEmpty(idCalendarioPeriodo)? "0":idCalendarioPeriodo));
            }
        },500);

    }

    @Override
    public void onActualizarRegitroSesion() {

    }

    @Override
    public void onResumeFragment() {

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
        getRubroProceso(idrubroformal, idCalendarioPeriodo);
        //view.setListCompetenciaChecked(tipoCompetencia);
    }

    List<Object> objectListCompetenciabase;

    private void onEvaluacionRubroSilabo(List<Object> objectList) {
        if (objectList != null) {
            this.objectListCompetenciabase = objectList;
            mostrarObjectLista(objectList);
          //  hideProgress();
        }
    }


    private void mostrarObjectLista(List<Object> list) {
        if (view != null) {
            view.mostrarObjectLista(list);
        }
    }


    public int getCalendarioPeriodoId() {
        return idCalendarioPeriodo;
    }

    @Override
    public void onClickAncla(CapacidadUi capacidadUi) {
        capacidadUi.setToogle(true);
        ChangeToogle.Response response = changeToogle.execute(new ChangeToogle.Requests(capacidadUi.isToogle(),capacidadUi.getId()));
        RubroProcesoUi rubroProcesoUi = capacidadUi.getRubroEvalAnclado();
        if(rubroProcesoUi==null)return;
        if(rubroProcesoUi.getTipoFormula()== RubroProcesoUi.TipoFormula.ANCLADA){
            if(view!=null)view.showDialogDesanclar(capacidadUi);
        }else if(rubroProcesoUi.getTipoFormula()== RubroProcesoUi.TipoFormula.EVALUADA){
            if(view!=null)view.showDialogDesanclar(capacidadUi);
        }

    }

    private void desanclarFormula(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi) {
        desanclarFormula.execute(new DesanclarFormula.Request(rubroProcesoUi, capacidadUi), new UseCaseSincrono.Callback<DesanclarFormula.Response>() {
            @Override
            public void onResponse(boolean success, DesanclarFormula.Response value) {
                if (view != null) view.updateAncladoCapacidad(value.getCapacidadUi());
                if (view != null) view.updateRubroProceso(value.getCapacidadUi(), value.getRubroformula(), getProgramaEducativoId());
            }
        });
    }

    @Override
    public void onAceptarDialogDesanAnclar(CapacidadUi capacidadUi) {
        RubroProcesoUi rubroProcesoUi = capacidadUi.getRubroEvalAnclado();
        if(rubroProcesoUi==null)return;
        if(rubroProcesoUi.getTipoFormula()== RubroProcesoUi.TipoFormula.ANCLADA || rubroProcesoUi.getTipoFormula()== RubroProcesoUi.TipoFormula.EVALUADA){
            desanclarFormula(capacidadUi,rubroProcesoUi);
        }

    }

    @Override
    public void onClickAceptarCalcular() {

        List<CompetenciaUi> competenciaUiList = new ArrayList<>();
        for (Object  o : rubroProcesoList){
            if(o instanceof CompetenciaUi){
                competenciaUiList.add((CompetenciaUi)o);
            }
        }
        if(view!=null)view.showDialogProgress();
        saveFormulaCapacidad.execute(new AutoSaveFormulaCapacidad.Response(cargaCursoId, idCalendarioPeriodo,competenciaUiList), new UseCaseSincrono.Callback<AutoSaveFormulaCapacidad.Request>() {
            @Override
            public void onResponse(boolean success, AutoSaveFormulaCapacidad.Request value) {
                if(value.isSuccess()){
                    getRubroProceso(idcompetencia, idCalendarioPeriodo);
                    if(view!=null)view.actualizarRubroTipoAncla();
                    if(view!=null)view.initServiceUpdate(programaEducativoId);

                }else {
                    if(view!=null)view.showMensaje("Error, acción cancelada");
                }
                Log.d(TAG,"onResponse");
                if(view!=null)view.hideDialogProgress();
            }
        });
    }

    @Override
    public void comprobarActualizacionRubros(Map<RubroProcesoUi, CapacidadUi> rubroProcesoUiList) {
        List<RubroProcesoUi> modificados = new ArrayList<>();
        for (Map.Entry<RubroProcesoUi,CapacidadUi> procesoUiCapacidadUiEntry: rubroProcesoUiList.entrySet()){
            RubroProcesoUi rubroProcesoUi = procesoUiCapacidadUiEntry.getKey();
            if(!rubroProcesoUi.isExportado())modificados.add(rubroProcesoUi);
        }
        changeEstadoActualizacion.execute(modificados);

        for (RubroProcesoUi rubroProcesoUi: modificados){
            if(rubroProcesoUi.isExportado()){
                CapacidadUi capacidadUi = rubroProcesoUiList.get(rubroProcesoUi);
                if(view!=null)view.updateRubroProceso(capacidadUi, rubroProcesoUi, programaEducativoId);
            }
        }
    }

    private void getRubroProceso(int idcompetencia, int idCalendarioPeriodo){

        final boolean notChangeList = this.idCalendarioPeriodo==idCalendarioPeriodo&&this.idcompetencia==idcompetencia;
        if(notChangeList){
            if(view!=null)view.savePositionList();
        }else {
            if(view!=null)view.clearPositionList();
        }

        this.idcompetencia = idcompetencia;
        this.idCalendarioPeriodo = idCalendarioPeriodo;


        if (view != null)view.showTipoRubroProceso();
        if (view != null)view.showProgres();
        //        Log.d(TAG, "getRubroProcesoListcompetenciabase +  " + idcompetencia);
//        Log.d(TAG, "getRubroProcesoListcompetenciabase +  " + parametrodisenioid);
        if (!isNullView()) view.showMensaje("");
        handler.execute(rubroEvalProcesoList,
                new GetRubroEvalProcesoList.RequestValues(idCalendarioPeriodo, cargaCursoId, idcompetencia, parametrodisenioid),
                new UseCase.UseCaseCallback<GetRubroEvalProcesoList.ResponseValue>() {
                    @Override
                    public void onSuccess(GetRubroEvalProcesoList.ResponseValue response) {
                        if(response.getItems()==null){
                            if(view!=null)view.showProgres();
                            if(!notChangeList)if(view!=null)view.hideCardProcesamiento();//Ocultar cuando exista un cambio al listar los rubros
                        }else {
                            /*Recuperar la posicion*/
                            for (Object o: rubroProcesoList){
                                if(o instanceof CapacidadUi)
                                    for (Object o2: response.getItems()){
                                        if(o2 instanceof CapacidadUi){
                                            CapacidadUi capacidadUi = (CapacidadUi)o;
                                            CapacidadUi capacidadUi2 = (CapacidadUi)o2;
                                            if(capacidadUi.getId() == capacidadUi2.getId()){
                                                if(capacidadUi.getRubroProcesoUis().size()==capacidadUi2.getRubroProcesoUis().size()){
                                                    capacidadUi2.setRecylerViewState(capacidadUi.getRecylerViewState());
                                                }
                                            }
                                        }
                                    }
                            }
                            rubroProcesoList.clear();
                            Log.d(TAG, "items"+ response.getItems().size());
                            if (view != null)view.showRubroEvaluacionProceso(response.getItems());
                            if (view != null)view.hideProgres();
                            rubroProcesoList.addAll(response.getItems());
                            if(response.getItems().isEmpty())if (!isNullView())view.showMensaje(res.getString(R.string.empty_data2));
                            ParametroDisenioUi parametroDisenioUi = null;
                            boolean anclar = false;
                            boolean editar = false;
                            String color1= null;
                            String color2= null;
                            for (Object  o : response.getItems()){
                                if(o instanceof CompetenciaUi){
                                    CompetenciaUi competenciaUi = (CompetenciaUi)o;
                                    parametroDisenioUi = competenciaUi.getParametroDisenioUi();
                                    if(parametroDisenioUi!=null){
                                        color1 = parametroDisenioUi.getColor1();
                                        color2 = parametroDisenioUi.getColor2();
                                    }
                                }else if(o instanceof CapacidadUi){
                                    CapacidadUi capacidadUi =(CapacidadUi)o;
                                    anclar = capacidadUi.isCalendarioAnclar();
                                    editar = capacidadUi.isCalendarioEditar();
                                }
                            }
                            if(editar){
                                if(view!=null)view.showCardProcesamiento(color1, color2);
                            }else {
                                if(view!=null)view.hideCardProcesamiento();
                            }
                        }
                    }

                    @Override
                    public void onError() {
                        if (view != null)view.hideProgres();
                        rubroProcesoList.clear();
                    }
                });

    }
}
