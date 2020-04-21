package com.consultoraestrategia.ss_crmeducativo.dao.evaluacionProceso;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.List;

/**
 * Created by kike on 14/05/2018.
 */

public interface EvaluacionProcesoDao extends BaseDao<EvaluacionProcesoC> {
    /*filtrado por rubroProcesoKey, alumnnosKey*/
    List<EvaluacionProcesoC> getListaEvaluacionProceso(String rubroProcesKey, int personaKey);

    EvaluacionProcesoC getEvaluacionProceso(String rubroProcesKey, int personaKey);

    List<EvaluacionProcesoC> getListaEvaluacionProcesoRubroFormula(String rubroProcesKey, int personaKey);

    boolean crearEvaluacionProceso(String rubroEvalProcesoId, int cargaCursoId);

    boolean crearEvaluacionProcesoFormula(String rubroEvalProcesoId, int cargaCursoId, DatabaseWrapper databaseWrapper);

    boolean evaluarRubroFormulaPersona(String rubroEvalProcesoId, int personaId);

    List<EvaluacionProcesoC> getEvaluacionProcesoRubrica(List<String> rubroEvalProcesoKeyList, List<Integer> personIdList);

    List<EvaluacionProcesoC> getEvaluacionProcesoRubricaEquipo(List<String> rubroEvalProcesoKeyList, List<String> equipoRubroKeys);

    boolean f_mediaDesviacionEstandar(String key);
    boolean f_mediaDesviacionEstandarFormula(String key);
    interface UpdateEvaluacionFormulaCallback{
        void onPreload();// Se inicia si existe algun cambio en las evaluaciones
        void onSucces();
        void onError();
    }
    void onUpdateEvaluacionFormula(UpdateEvaluacionFormulaCallback callback);
}
