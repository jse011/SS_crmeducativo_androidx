package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.sesion.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.SimpleItemAnimator;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CrearRubroEvalUi;
import com.consultoraestrategia.ss_crmeducativo.FiltroRubroCompetencia.entities.CompetenciaCheck;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseHandler;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseThreadPoolScheduler;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.contenedor.EvaluacionContainerActivity;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion.ui.RegistroFragment;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_sesion_grupo.ui.RegistroGrupoFragment;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.CompetenciaAdapter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.adapters.CompetenciaItemDecoration;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.local.RubroEvaluacionProcesoListaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.remote.RubroEvaluacionProcesoListaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.ChangeEstadoActualizacion;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetCalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase.GetRubroProcesoSesionList;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.data.source.CasoUsoRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.data.source.local.CasoUsoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.ChangeToogle;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.DeleteRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.PublicarTodasEvaluacion;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.view.FragmentAbstract;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.sesion.RubroEvalProcesoListaPresenterImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SCIEV on 6/02/2018.
 */

public class FragmentRubroEvalProcesoLista extends FragmentAbstract<FragmentRubroEvalProcesoLista, RubroEvalProcesoListaPresenterImpl> {

    public static final String TAG_SESIONAPRENDIZAJEID = "SesionAprendizajeId";
    public static final String TAG_PROGRAMAEDUCATIVO = "ProgramaEducativoId";
    public static final String TAG_CARGACURSO_ID = "CursoAprendizajeId";
    public static final String TAG_CURSO_ID = "CursoId";
    public static final String TAG_TIPONOTAID = "TipoNotaId";
    public static final String TAG_CARGA_ACADEMICA_ID = "idCargaAcademica";
    public static final String TAG_ENTIDAD_ID = "idEntidad";
    public static final String TAG_GEOREFERENCIA_ID = "idGeoreferencia";
    private CompetenciaItemDecoration competenciaItemDecoration;

    public static FragmentRubroEvalProcesoLista newInstance(Bundle args) {
        FragmentRubroEvalProcesoLista fragment = new FragmentRubroEvalProcesoLista();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected RubroEvalProcesoListaPresenterImpl getPresenter() {
        RubroEvaluacionProcesoListaRepository repository = new RubroEvaluacionProcesoListaRepository(
                new RubroEvaluacionProcesoListaLocalDataSource(),
                new RubroEvaluacionProcesoListaRemoteDataSource()
        );
        CasoUsoRepository casoUsoRepository = new CasoUsoRepository(new CasoUsoLocalDataSource());

        return new RubroEvalProcesoListaPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),getResources(),
                new DeleteRubroProceso(casoUsoRepository),
                new com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.ShowCamposTematicos(casoUsoRepository),
                new com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase.ShowDesempenioIcds(casoUsoRepository),
                new GetRubroProceso(repository),
                new GetRubroProcesoSesionList(repository),
                new GetCalendarioPeriodo(repository),
                new ChangeToogle(casoUsoRepository),
                new PublicarTodasEvaluacion(repository),
                new ChangeEstadoActualizacion(repository)
        );

    }

    @Override
    public void showEvaluacionUnidimencionalSesion(int rubroEvaluacionId, String titulo, int sesionAprendizajeId, int cargaCursoId, int cursoId, boolean disabledEval) {

    }

    @Override
    public void showEvaluacionUnidimencionalSesion(String rubroEvaluacionId, String titulo, int sesionAprendizajeId, int cargaCursoId, int cursoId, boolean disabledEval, String rubroEvaluacionTipoNotaId, int entidadId, int georeferenciaId, int programaEDucativoId) {
        Bundle bundle = getArguments();
        if(bundle==null){
            Log.e(TAG,"Metodo nesesita de los parametros bundle");
            return;
        }
        bundle.putInt(TAG_SESIONAPRENDIZAJEID, sesionAprendizajeId);
        bundle.putInt(TAG_CARGACURSO_ID, cargaCursoId);
        bundle.putInt(TAG_CURSO_ID, cursoId);
        bundle.putInt(TAG_GEOREFERENCIA_ID, georeferenciaId);
        bundle.putInt(TAG_ENTIDAD_ID, entidadId);
        bundle.putString(TAG_TIPONOTAID, rubroEvaluacionTipoNotaId);
        bundle.putInt(TAG_PROGRAMAEDUCATIVO, programaEDucativoId);
        Intent intent = EvaluacionContainerActivity.createContainerActivityIntent(getContext(), bundle, rubroEvaluacionId, titulo, disabledEval, rubroEvaluacionTipoNotaId, RegistroFragment.class);
        startActivityForResult(intent, EvaluacionContainerActivity.REQUESTCODE);
    }

    @Override
    public void showEvaluacionUnidimencionalGrupos(int rubroEvaluacionId, String titulo, int sesionAprendizajeId, int cargaCursoId, int cursoId, boolean disabledEval) {

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
    public void updateRubroProceso(Object o, RubroProcesoUi rubroProcesoUi, int programaEducativoId) {
        adapter.updateRubroProceso(o, rubroProcesoUi);
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
        adapter = new CompetenciaAdapter(new ArrayList<>(), this, CompetenciaAdapter.LIST_VERTICAL);
        adapter.setRecyclerView(rcRubroEvaluacionResultado);
        rcRubroEvaluacionResultado.setLayoutManager(new LinearLayoutManager(getContext()));
        ((SimpleItemAnimator) rcRubroEvaluacionResultado.getItemAnimator()).setSupportsChangeAnimations(false);
        rcRubroEvaluacionResultado.setHasFixedSize(true);
        rcRubroEvaluacionResultado.setAdapter(adapter);
        if(competenciaItemDecoration==null){
            competenciaItemDecoration = new CompetenciaItemDecoration(0, 200);
            rcRubroEvaluacionResultado.addItemDecoration(competenciaItemDecoration);
        }
    }

    @Override
    public void showEvaluacionUnidimencionalGrupos(String rubroEvaluacionId, String titulo, int sesionAprendizajeId, int cargaCursoId, int cursoId,int idcargaAcademica, boolean disabledEval, String evaluacionEvaluacionTipoNotaId) {
        Bundle bundle = getArguments();
        bundle.putInt(TAG_SESIONAPRENDIZAJEID, sesionAprendizajeId);
        bundle.putInt(TAG_CARGACURSO_ID, cargaCursoId);
        bundle.putInt(TAG_CURSO_ID, cursoId);
        bundle.putInt(TAG_CARGA_ACADEMICA_ID, idcargaAcademica);
        Intent intent = EvaluacionContainerActivity.createContainerActivityIntent(getContext(), bundle, rubroEvaluacionId, titulo, disabledEval, evaluacionEvaluacionTipoNotaId,RegistroGrupoFragment.class);
        startActivityForResult(intent, EvaluacionContainerActivity.REQUESTCODE);
    }

    @Override
    public void onClickAceptarDialog(CapacidadUi capacidadUi, CrearRubroEvalUi crearRubroEvalUi) {

    }

    @Override
    public void onAddBasicDialog(CapacidadUi capacidadUi, CrearRubroEvalUi crearRubroEvalUi) {

    }

    @Override
    public void mostrarObjectLista(List<Object> objectList) {

    }

    @Override
    public void updateAncladoCapacidad(CapacidadUi capacidadUi) {
        adapter.changeAncladoCompetencia(capacidadUi);
    }

    public void onActualizarRegitroSesion() {
        presenter.onActualizarRegitroSesion();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void onParentFabClicked(ArrayList<CompetenciaCheck> tipoCompetenciaList) {
        Log.d(TAG, "onParentFabClicked");
        presenter.onselectedItem(tipoCompetenciaList);
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
