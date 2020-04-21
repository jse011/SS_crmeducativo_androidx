package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.local.RubroEvaluacionProcesoListaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.remote.RubroEvaluacionProcesoListaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.IndicadoresCamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoFormulaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoRedondeadoUi;

import java.util.List;


/**
 * Created by @stevecampos on 17/10/2017.
 */

public class RubroEvaluacionProcesoListaRepository implements RubroEvaluacionProcesoListaDataSource {

    private static final String TAG = RubroEvaluacionProcesoListaRepository.class.getSimpleName();
    private RubroEvaluacionProcesoListaLocalDataSource localDataSource;
    private RubroEvaluacionProcesoListaRemoteDataSource remoteDataSource;

    public RubroEvaluacionProcesoListaRepository(RubroEvaluacionProcesoListaLocalDataSource localDataSource, RubroEvaluacionProcesoListaRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public List<RubroProcesoUi> getFormulaList(String formulaId) {
        return localDataSource.getFormulaList(formulaId);
    }

    @Override
    public boolean editarFormula(RubroProcesoUi rubroProcesoUi) {
        return localDataSource.editarFormula(rubroProcesoUi);
    }

    @Override
    public void getRubroProcesoSesionList(int idrubroformal, int sesionAprendizajeId, int nivel, int calendarioPerioId, int silaboEventoId, int cargaCursoId, ListCallback<Object> callback) {
         localDataSource.getRubroProcesoSesionList(idrubroformal,sesionAprendizajeId,nivel,calendarioPerioId,silaboEventoId, cargaCursoId, callback);

    }

    @Override
    public void getRubroProcesoSilavoList(int idCalendarioPeriodo, int cargaCursoId, int idcompetencia, int parametrodisenioid, ListCallback<Object> callback) {
        localDataSource.getRubroProcesoSilavoList(idCalendarioPeriodo, cargaCursoId, idcompetencia, parametrodisenioid, callback);
    }


    @Override
    public void getRubroProceso(String rubroEvaluacionId, Callback<RubroProcesoUi> callback) {
        localDataSource.getRubroProceso(rubroEvaluacionId,callback);
    }

    @Override
    public void updateRubrosProceso(int rubroEvaluacionId, Callback<Boolean> estado) {

    }

    @Override
    public void showListCamposTematicosListRubros(List<RubroProcesoUi> rubroProcesoUis, Callback<List<IndicadoresCamposAccionUi>> callback) {
        localDataSource.showListCamposTematicosListRubros(rubroProcesoUis,callback);
    }

    @Override
    public void getCalendarioPeriodo(int calendarioPeriodoId, SuccesCallback<PeriodoUi> callback) {
        localDataSource.getCalendarioPeriodo(calendarioPeriodoId, callback);
    }
    @Override
    public boolean validarEvaluacionRubroNormal(String idRubroProceso) {
        return localDataSource.validarEvaluacionRubroNormal(idRubroProceso);
    }

    @Override
    public boolean publicarEvaluacion(String rubricaEvaluacionId) {
        return localDataSource.publicarEvaluacion(rubricaEvaluacionId);
    }

    @Override
    public void getTipoFormula(Callback<List<TipoFormulaUi>> listCallback) {
        localDataSource.getTipoFormula(listCallback);
        remoteDataSource.getTipoFormula(listCallback);
    }

    @Override
    public void getTipoNota(String tipoNotaId,CallbackObject<String, String> callBackTipoNota) {
        localDataSource.getTipoNota(tipoNotaId,callBackTipoNota);
        remoteDataSource.getTipoNota(tipoNotaId,callBackTipoNota);
    }

    /*@Override
    public void getTipoNota(Callback<List<TipoNotaUi>> listCallback) {
        localDataSource.getTipoNota(listCallback);
        remoteDataSource.getTipoNota(listCallback);
    }*/

    @Override
    public void getTipoRedondeo(Callback<List<TipoRedondeadoUi>> listCallback) {
        localDataSource.getTipoRedondeo(listCallback);
        remoteDataSource.getTipoRedondeo(listCallback);
    }

    @Override
    public void getDataSavedRubroEvaluacionProceso(RubroProcesoUi rubroEvaluacionProcesoUi, int cargaCursoId,List<RubroProcesoUi> rubroEvaluacionProcesoUiList,final  CallbackObject<RubroProcesoUi, Boolean> rubroEvaluacionProcesoUiCallback) {
        localDataSource.getDataSavedRubroEvaluacionProceso(rubroEvaluacionProcesoUi,cargaCursoId,rubroEvaluacionProcesoUiList,rubroEvaluacionProcesoUiCallback);
    }

    @Override
    public void onEvaluacionFormulaList(int cargaCursoId, int cursoId, RubroProcesoUi rubroProcesoUi, CallBackList<List<AlumnosUi>> listcallBackList) {
        localDataSource.onEvaluacionFormulaList(cargaCursoId, cursoId, rubroProcesoUi, listcallBackList);
    }


    @Override
    public void onUpdateEvaluacionFormula(String rubroEvalProcesoId, int personaId, String equipoId, SimpleSuccessCallBack callBack) {
        localDataSource.onUpdateEvaluacionFormula(rubroEvalProcesoId, personaId, equipoId, callBack);
    }

    @Override
    public void onUpdateEvaluacionFormula(SimpleSuccessCallBack callBack) {
        localDataSource.onUpdateEvaluacionFormula(callBack);
    }

    @Override
    public void saveRubroFormulaCapacidad(int cargaCursoId, int calendarioPeriodoId,List<CompetenciaUi> competenciaUiList, SimpleSuccessCallBack callBack) {
        localDataSource.saveRubroFormulaCapacidad(cargaCursoId,calendarioPeriodoId, competenciaUiList, callBack);
    }

    @Override
    public TipoNotaUi getTipoNotaDefault(int progrmaEducativoId, List<TipoNotaUi.Tipo> tipos) {
        return localDataSource.getTipoNotaDefault(progrmaEducativoId, tipos);
    }

    @Override
    public TipoNotaUi getTipoNotaRubrica(String tipoNotaId) {
        return localDataSource.getTipoNotaRubrica(tipoNotaId);
    }

    @Override
    public void getPeriodoList(int cargaCursoId, int cursoId, CallbackPeriodo callbackPeriodo) {
        localDataSource.getPeriodoList(cargaCursoId, cursoId, callbackPeriodo);
        remoteDataSource.getPeriodoList(cargaCursoId, cursoId, callbackPeriodo);
    }

    @Override
    public void getRubroProcesoList(String idCalendarioPeriodo, int cargaCursoId, int idcompetencia, int parametrodisenioid,CallbackResultado callback) {
        localDataSource.getRubroProcesoList(idCalendarioPeriodo,cargaCursoId,idcompetencia, parametrodisenioid,callback);
        remoteDataSource.getRubroProcesoList(idCalendarioPeriodo,cargaCursoId,idcompetencia, parametrodisenioid,callback);
    }

    @Override
    public void deleteRubroEvaluacionProceso(RubroProcesoUi rubroProcesoUi, ObjectCallback objectCallback) {
        localDataSource.deleteRubroEvaluacionProceso(rubroProcesoUi,objectCallback);
        remoteDataSource.deleteRubroEvaluacionProceso(rubroProcesoUi,objectCallback);
    }

    @Override
    public void useCaseAnclar(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, ObjetoCallback<CapacidadUi, RubroProcesoUi> objetoCallback) {
        localDataSource.useCaseAnclar(capacidadUi,rubroProcesoUi,objetoCallback);
    }

    @Override
    public void desanclarCaseAnclar(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, ObjetoCallback<CapacidadUi, RubroProcesoUi> objetoCallback) {
        localDataSource.desanclarCaseAnclar(capacidadUi, rubroProcesoUi, objetoCallback);
    }
}

