package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.IndicadoresCamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoFormulaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoRedondeadoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.tablaAdapter.estructura.FormulaCelda;

import java.util.List;

/**
 * Created by @stevecampos on 17/10/2017.
 */

public interface RubroEvaluacionProcesoListaDataSource {

    List<RubroProcesoUi> getFormulaList(String formulaId);

    boolean editarFormula(RubroProcesoUi rubroProcesoUi);

    interface ListCallback<T> {
        void onLoaded(List<T> list);
    }
    interface Callback<T> {
        void onLoaded(T item);
    }

    interface SuccesCallback<T> {
        void onLoaded(boolean success, T item);
    }

    interface SimpleSuccessCallBack<T>{
        void onSuccess(boolean success);
    }

    interface CallbackObject<T,Q>{
        void onCreateRubroEval(T rubroEvaluacionUi, Q succes);
    }

    interface CallBackList<T> {
        void onLoadList(List<AlumnosUi> tList, List<List<FormulaCelda>> formulaCeldasList);
    }

    interface CallbackResultado {
        void onRubroProcesoList(List<Object> rubroEvaluacionResultadoUis, int status);
    }


    interface CallbackPeriodo {
        void onListPeriodo(List<PeriodoUi> periodoUis, int status);
    }

    interface ObjectCallback {
        void onDelete(RubroProcesoUi rubroProcesoUi, int validateSuccess);
    }
    interface ObjetoCallback<T,Q>{
        void onObject(T capacidad,Q rubroProceso);
        void onError(String mensaje);
    }

    void getRubroProcesoSesionList(int idrubroformal, int sesionAprendizajeId, int nivel, int calendarioPerioId, int silaboEventoId, int cargaCursoId, ListCallback<Object> callback);
    void getRubroProcesoSilavoList(int idCalendarioPeriodo, int cargaCursoId, int idcompetencia, int parametrodisenioid, ListCallback<Object> callback);
    void getRubroProceso(String rubroEvaluacionId, Callback<RubroProcesoUi> callback);
    void updateRubrosProceso(int rubroEvaluacionId, Callback<Boolean> estado);
    void showListCamposTematicosListRubros(List<RubroProcesoUi> rubroProcesoUis, Callback<List<IndicadoresCamposAccionUi>> callback);
    void getCalendarioPeriodo(int calendarioPeriodoId, SuccesCallback<PeriodoUi> callback);
    boolean validarEvaluacionRubroNormal(String idRubroProceso);
    boolean publicarEvaluacion(String rubricaEvaluacionId);


    void getPeriodoList(int cargaCursoId, int cursoId, CallbackPeriodo callbackPeriodo);
    void getRubroProcesoList(String idCalendarioPeriodo, int cargaCursoId,int idcompetencia,  int parametrodisenioid,CallbackResultado callback);
    void deleteRubroEvaluacionProceso(RubroProcesoUi rubroProcesoUi, ObjectCallback objectCallback);
    void useCaseAnclar(CapacidadUi capacidadUi , RubroProcesoUi rubroProcesoUi, ObjetoCallback<CapacidadUi,RubroProcesoUi> objetoCallback);
    void desanclarCaseAnclar(CapacidadUi capacidadUi , RubroProcesoUi rubroProcesoUi,ObjetoCallback<CapacidadUi,RubroProcesoUi> objetoCallback);

    /*Crear Rubro*/
    void getTipoFormula(Callback<List<TipoFormulaUi>> listCallback);
    void getTipoNota(String tipoNotaId,CallbackObject<String,String>callBackTipoNota);
    void getTipoRedondeo(Callback<List<TipoRedondeadoUi>> listCallback);
    void getDataSavedRubroEvaluacionProceso(RubroProcesoUi rubroEvaluacionProcesoUi,int cargaCursoId, List<RubroProcesoUi> rubroEvaluacionProcesoUiList, CallbackObject<RubroProcesoUi, Boolean> rubroEvaluacionProcesoUiCallback);


    /*Evaluacion Rubro Fromula */
    void onEvaluacionFormulaList(int cargaCursoId, int cursoId, RubroProcesoUi rubroProcesoUi, CallBackList<List<AlumnosUi>> listcallBackList);
    void onUpdateEvaluacionFormula(String rubroEvalProcesoId, int personaId, String equipoId, SimpleSuccessCallBack callBack);
    void onUpdateEvaluacionFormula(SimpleSuccessCallBack callBack);


    void saveRubroFormulaCapacidad(int cargaCursoId, int calendarioPeriodoId,List<CompetenciaUi> competenciaUiList, SimpleSuccessCallBack callBack);

    TipoNotaUi getTipoNotaDefault(int progrmaEducativoId, List<TipoNotaUi.Tipo> tipos);

    TipoNotaUi getTipoNotaRubrica(String tipoNotaId);
}

