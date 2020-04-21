package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.remote;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaDataSource;

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

public class RubroEvaluacionProcesoListaRemoteDataSource implements RubroEvaluacionProcesoListaDataSource {
    private static final String TAG = RubroEvaluacionProcesoListaRemoteDataSource.class.getSimpleName();


    @Override
    public List<RubroProcesoUi> getFormulaList(String formulaId) {
        return null;
    }

    @Override
    public boolean editarFormula(RubroProcesoUi rubroProcesoUi) {
        return false;
    }

    @Override
    public void getRubroProcesoSesionList(int idrubroformal, int sesionAprendizajeId, int nivel, int calendarioPerioId, int silaboEventoId, int cargaCursoId, ListCallback<Object> callback) {

    }

    @Override
    public void getRubroProcesoSilavoList(int idCalendarioPeriodo, int cargaCursoId, int idcompetencia, int parametrodisenioid, ListCallback<Object> callback) {

    }


    @Override
    public void getRubroProceso(String rubroEvaluacionId, Callback<RubroProcesoUi> callback) {

    }

    @Override
    public void updateRubrosProceso(int rubroEvaluacionId, Callback<Boolean> estado) {

    }

    @Override
    public void showListCamposTematicosListRubros(List<RubroProcesoUi> rubroProcesoUis, Callback<List<IndicadoresCamposAccionUi>> callback) {

    }

    @Override
    public void getCalendarioPeriodo(int calendarioPeriodoId, SuccesCallback<PeriodoUi> callback) {

    }

    @Override
    public boolean validarEvaluacionRubroNormal(String idRubroProceso) {
        return false;
    }

    @Override
    public boolean publicarEvaluacion(String rubricaEvaluacionId) {
        return false;
    }

    @Override
    public void getPeriodoList(int cargaCursoId, int cursoId, CallbackPeriodo callbackPeriodo) {

    }

    @Override
    public void getRubroProcesoList(String idCalendarioPeriodo, int cargaCursoId, int idcompetencia, int parametrodisenioid, CallbackResultado callback) {

    }

    @Override
    public void deleteRubroEvaluacionProceso(RubroProcesoUi rubroProcesoUi, ObjectCallback objectCallback) {

    }

    @Override
    public void useCaseAnclar(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, ObjetoCallback<CapacidadUi, RubroProcesoUi> objetoCallback) {

    }

    @Override
    public void desanclarCaseAnclar(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, ObjetoCallback<CapacidadUi, RubroProcesoUi> objetoCallback) {

    }


    @Override
    public void getTipoFormula(Callback<List<TipoFormulaUi>> listCallback) {

    }

    @Override
    public void getTipoNota(String tipoNotaId, CallbackObject<String, String> callBackTipoNota) {

    }

    /*@Override
    public void getTipoNota(CrearRubroFormulaDataSource.Callback<List<TipoNotaUi>> listCallback) {

    }*/

    @Override
    public void getTipoRedondeo(Callback<List<TipoRedondeadoUi>> listCallback) {

    }

    @Override
    public void getDataSavedRubroEvaluacionProceso(RubroProcesoUi rubroEvaluacionProcesoUi,int cargaCursoId, List<RubroProcesoUi> rubroEvaluacionProcesoUiList, CallbackObject<RubroProcesoUi, Boolean> rubroEvaluacionProcesoUiCallback) {

    }

    @Override
    public void onEvaluacionFormulaList(int cargaCursoId, int cursoId, RubroProcesoUi rubroProcesoUi, CallBackList<List<AlumnosUi>> listcallBackList) {

    }

    @Override
    public void onUpdateEvaluacionFormula(String rubroEvalProcesoId, int personaId, String equipoId, SimpleSuccessCallBack callBack) {

    }

    @Override
    public void onUpdateEvaluacionFormula(SimpleSuccessCallBack callBack) {

    }

    @Override
    public void saveRubroFormulaCapacidad(int cargaCursoId, int calendarioPeriodoId, List<CompetenciaUi> competenciaUiList, SimpleSuccessCallBack callBack) {

    }

    @Override
    public TipoNotaUi getTipoNotaDefault(int progrmaEducativoId, List<TipoNotaUi.Tipo> tipos) {
        return null;
    }

    @Override
    public TipoNotaUi getTipoNotaRubrica(String keyRubroEvaluacion) {
        return null;
    }


}
