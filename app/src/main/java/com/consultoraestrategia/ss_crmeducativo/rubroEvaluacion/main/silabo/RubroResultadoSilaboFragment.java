package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.silabo;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.SimpleItemAnimator;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CrearRubroEvalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.listener.CrearRubroListener;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.CompetenciaCheck;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.bundle.CRMBundle;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.contenedor.EvaluacionContainerActivity;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.ui.RegistroFragment;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.ui.RegistroGrupoFragment;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.CompetenciaAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.CompetenciaItemDecoration;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.local.RubroEvaluacionProcesoListaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.remote.RubroEvaluacionProcesoListaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.ChangeEstadoActualizacion;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.AutoSaveFormulaCapacidad;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.data.source.CasoUsoRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.data.source.local.CasoUsoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.ChangeToogle;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.PublicarTodasEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.view.FragmentAbstract;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros.SeleccionarRubrosActivity;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.AnclarUse;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.DesanclarFormula;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetPeriodoList;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetRubroEvalProcesoList;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RubroSilaboListener;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by kike on 12/02/2018.
 */

public class RubroResultadoSilaboFragment extends FragmentAbstract<RubroResultadoSilaboFragment, RubroResultadoSilaboPresenterImpl> implements CrearRubroListener  {

    public static final String TAG_SESIONAPRENDIZAJEID = "SesionAprendizajeId";
    public static final String TAG_CARGACURSO_ID = "CursoAprendizajeId";
    public static final String TAG_CURSO_ID = "CursoId";
    public static final String TAG_ENTIDAD_ID = "EntidadId";
    public static final String TAG_GEOREFERENCIA_ID = "GeoreferenciaId";
    public static final String TAG_TIPONOTAID= "TipoNotaId";
    public static final String TAG_CARGAACADEMICAID= "idCargaAcademica";
    public static final String TAG_PROGRAMA_EDUCATIVO_ID= "idProgramaEducativoId";
    public static final int SELECCIONAR_RUBROS = 25;

    private RubroSilaboListener rubroSilaboListener;
    private CompetenciaItemDecoration competenciaItemDecoration;

    public static RubroResultadoSilaboFragment newInstance(Bundle args) {
        Log.d(RubroResultadoSilaboFragment.class.getSimpleName(), "idCalendarioPeriodo newInstance");
        RubroResultadoSilaboFragment fragment = new RubroResultadoSilaboFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RubroResultadoSilaboPresenterImpl getPresenter() {
        RubroEvaluacionProcesoListaRepository rubroResultadoSilaboRepository = new RubroEvaluacionProcesoListaRepository(
               new RubroEvaluacionProcesoListaLocalDataSource(),
                new RubroEvaluacionProcesoListaRemoteDataSource());

        CasoUsoRepository casoUsoRepository = new CasoUsoRepository(new CasoUsoLocalDataSource());

        RubroEvaluacionProcesoListaRepository rubroEvaluacionProcesoListaRepository = new RubroEvaluacionProcesoListaRepository(
                new RubroEvaluacionProcesoListaLocalDataSource(),
                new RubroEvaluacionProcesoListaRemoteDataSource()
        );

        return new RubroResultadoSilaboPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),getResources(),
                new com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.DeleteRubroProceso(casoUsoRepository),
                new com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.ShowCamposTematicos(casoUsoRepository),
                new com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.ShowDesempenioIcds(casoUsoRepository),
                new GetRubroProceso(rubroEvaluacionProcesoListaRepository),
                new GetPeriodoList(rubroResultadoSilaboRepository),
                new GetRubroEvalProcesoList(rubroEvaluacionProcesoListaRepository),
                new AnclarUse(rubroResultadoSilaboRepository),
                new ChangeToogle(casoUsoRepository),
                new DesanclarFormula(rubroResultadoSilaboRepository),
                new PublicarTodasEvaluacion(rubroEvaluacionProcesoListaRepository),
                new AutoSaveFormulaCapacidad(rubroResultadoSilaboRepository),
                new ChangeEstadoActualizacion(rubroResultadoSilaboRepository)
        );
    }


    @Override
    public void onClickAceptarDialog(CapacidadUi capacidadUi, CrearRubroEvalUi crearRubroEvalUi) {

    }

    @Override
    public void onAddBasicDialog(CapacidadUi capacidadUi, CrearRubroEvalUi crearRubroEvalUi) {

    }

    @Override
    public void showSeleccionarRubros(final CapacidadUi capacidadUi) {
        Intent intent = SeleccionarRubrosActivity.getSeleccionarRubrosActivityIntent(getContext(), capacidadUi);
        startActivityForResult(intent, RubroResultadoSilaboFragment.SELECCIONAR_RUBROS);
    }

    @Override
    public void showRubroEvaluacionProceso(List<Object> items) {
        adapter.setItems(items);
    }

    @Override
    public void updateCompetencia(Object o) {
        adapter.updateItem(o);
    }

    @Override
    public void addRubroProceso(Object o, RubroProcesoUi rubroProcesoUi) {
        adapter.addRubroProceso(o, rubroProcesoUi);
        hideProgres();
    }

    @Override
    public void deleteRubro(CapacidadUi capacidadUi, RubroProcesoUi rubroEvaluacionProcesoUi, int programaEducativoId) {
        adapter.deleteRubroProceso(capacidadUi, rubroEvaluacionProcesoUi);
        refrescarListener.onDeleteRubro();
        initServiceUpdate(programaEducativoId);
    }

    @Override
    public void refreshList(Object o) {
        adapter.refreshList(o);
    }

    @Override
    public void setupAdapter() {
        adapter = new CompetenciaAdapter(new ArrayList<>(), this, CompetenciaAdapter.LIST_HORIZONTAL);
        adapter.setRecyclerView(rcRubroEvaluacionResultado);
        rcRubroEvaluacionResultado.setLayoutManager(new LinearLayoutManager(getContext()));
        ((SimpleItemAnimator) rcRubroEvaluacionResultado.getItemAnimator()).setSupportsChangeAnimations(false);
        rcRubroEvaluacionResultado.setHasFixedSize(true);
        rcRubroEvaluacionResultado.setAdapter(adapter);

        if(competenciaItemDecoration==null){
            competenciaItemDecoration = new CompetenciaItemDecoration((int)Utils.convertDpToPixel(16f, getContext()), 200);
            rcRubroEvaluacionResultado.addItemDecoration(competenciaItemDecoration);
        }
    }

    @Override
    public void mostrarObjectLista(List<Object> objectList) {
        adapter.setItems(objectList);
    }

    @Override
    public void updateAncladoCapacidad(CapacidadUi capacidadUi) {
        adapter.changeAncladoCompetencia(capacidadUi);
    }


    @Override
    public void onClickSelectRubros(CapacidadUi capacidadUi) {
        presenter.onClickSelectRubros(capacidadUi);
    }

    @Override
    public void showEvaluacionUnidimencionalSesion(int rubroEvaluacionId, String titulo, int sesionAprendizajeId, int cargaCursoId, int cursoId, boolean disabledEval) {

    }

    @Override
    public void showEvaluacionUnidimencionalSesion(String rubroEvaluacionId, String titulo, int sesionAprendizajeId, int cargaCursoId, int cursoId, boolean disabledEval, String rubroEvaluacionTipoNotaId, int entidadId, int georeferenciaId, int programaEducativoId) {
        Bundle bundle =getArguments();
        bundle.putInt(TAG_SESIONAPRENDIZAJEID, sesionAprendizajeId);
        bundle.putInt(TAG_CARGACURSO_ID, cargaCursoId);
        bundle.putInt(TAG_CURSO_ID, cursoId);
        bundle.putInt(TAG_ENTIDAD_ID, entidadId);
        bundle.putInt(TAG_GEOREFERENCIA_ID, georeferenciaId);
        bundle.putInt(TAG_PROGRAMA_EDUCATIVO_ID, programaEducativoId);
        Intent intent = EvaluacionContainerActivity.createContainerActivityIntent(getContext(), bundle, rubroEvaluacionId, titulo, disabledEval, rubroEvaluacionTipoNotaId, RegistroFragment.class);
        startActivityForResult(intent, EvaluacionContainerActivity.REQUESTCODE);
    }

    @Override
    public void showEvaluacionUnidimencionalGrupos(int rubroEvaluacionId, String titulo, int sesionAprendizajeId, int cargaCursoId, int cursoId, boolean disabledEval) {

    }

    @Override
    public void showEvaluacionUnidimencionalGrupos(String rubroEvaluacionId, String titulo, int sesionAprendizajeId, int cargaCursoId, int cursoId,int idcargaAcademica, boolean disabledEval, String rubroEvaluacionTipoNotaId) {
        Bundle bundle = getArguments();
        bundle.putInt(TAG_SESIONAPRENDIZAJEID, sesionAprendizajeId);
        bundle.putInt(TAG_CARGACURSO_ID, cargaCursoId);
        bundle.putInt(TAG_CURSO_ID, cursoId);
        CRMBundle crmBundle= new CRMBundle(getArguments());

        Intent intent = EvaluacionContainerActivity.createContainerActivityIntent(getContext(), bundle, rubroEvaluacionId, titulo, crmBundle.isCalendarioActivo(), rubroEvaluacionTipoNotaId, RegistroGrupoFragment.class);
        startActivityForResult(intent, EvaluacionContainerActivity.REQUESTCODE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            rubroSilaboListener = (RubroSilaboListener) context;
        } catch (Exception e) {
            Log.d(TAG, "ImplementarloActivity : " + e.toString());
        }
    }

    @Override
    public void updateRubroProceso(Object o, RubroProcesoUi rubroProcesoUi, int programaEducativoId) {
        adapter.updateRubroProceso(o, rubroProcesoUi);
        actualizarRubroTipoAncla();
        initServiceUpdate(programaEducativoId);
    }

    @Override
    public void actualizarRubroTipoAncla() {
        if (rubroSilaboListener != null) {
            rubroSilaboListener.actualizarRubroTipoAncla();
        }
    }

    public void onActualizarRegitroSilabo() {
        Log.d(TAG,"onActualizarRegitroSilabo");
        presenter.actualizarListaRubricas();

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public int getCalendarioPeriodoId() {
        if (presenter == null) return 0;
        return presenter.getCalendarioPeriodoId();
    }

    public void onParentFabClicked(ArrayList<CompetenciaCheck> tipoCompetencia) {
        Log.d(TAG, "onParentFabClicked");
        presenter.onselectedItem(tipoCompetencia);
    }

    @Override
    public void savePositionList() {
        super.savePositionList();
        adapter.savePositionList();
    }

    @Override
    public void clearPositionList() {
        super.clearPositionList();
        adapter.clearPositionList();
    }

    public void comprobarActualizacionRubros() {
        Map<RubroProcesoUi, CapacidadUi> rubroProcesoUiList = new HashMap<>();
        CompetenciaAdapter competenciaAdapter = (CompetenciaAdapter)rcRubroEvaluacionResultado.getAdapter();
        if(competenciaAdapter!=null){
           for (Object o : competenciaAdapter.getItems()){
               if(o instanceof CompetenciaUi){
                   CompetenciaUi competenciaUi = (CompetenciaUi)o;
                   for (CapacidadUi capacidadUi : competenciaUi.getCapacidadUis()){
                       for (RubroProcesoUi rubroProcesoUi : capacidadUi.getRubroProcesoUis()){
                           rubroProcesoUiList.put(rubroProcesoUi, capacidadUi);
                       }
                   }
               }
           }
        }
        presenter.comprobarActualizacionRubros(rubroProcesoUiList);
    }
}
