package com.consultoraestrategia.ss_crmeducativo.dao.rubroProceso;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.List;


/**
 * Created by CCIE on 30/04/2018.
 */

public interface RubroProcesoDao extends BaseDao<RubroEvaluacionProcesoC> {

    String obtenerUltimoRegistroKey();

    /*Obtiene el mismo Registro por mi Id*/
    RubroEvaluacionProcesoC getMyRegistro(String rubroProcesoKey);

    /*Actualizacion RubroProceso*/
    void actualizarRubroEstado(String key);

    /*RubroPadre*/
    void actualizarRubroEliminado(String key, BaseDaoImpl.Callback<Boolean> result);

    List<RubroEvaluacionProcesoC> get(List<String> rubroEvaluacionKey);

    int cantidadIndicadoresFormula(String keyFormula);

    String cantidadRubroEvaluadosPorSession(int sesionAprendizajeId, DatabaseWrapper databaseWrapper);
}
