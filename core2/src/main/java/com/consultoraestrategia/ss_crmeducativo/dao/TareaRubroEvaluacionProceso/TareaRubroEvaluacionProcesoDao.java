package com.consultoraestrategia.ss_crmeducativo.dao.TareaRubroEvaluacionProceso;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.entities.TareaRubroEvaluacionProceso;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public interface TareaRubroEvaluacionProcesoDao extends BaseDao<TareaRubroEvaluacionProceso> {
    boolean elimarTareaRubroEvaluacionProceso(String rubroEvalProcesoId);
    TareaRubroEvaluacionProceso getTareaRubroPorRubroId(String rubroEvaluacionProcesoId);
    TareaRubroEvaluacionProceso getTareaRubroPorRubroId(String key, DatabaseWrapper databaseWrapper);
}
