package com.consultoraestrategia.ss_crmeducativo.dao.TareaRubroEvaluacionProceso;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

public interface TareaRubroEvaluacionProcesoDao {
    boolean elimarTareaRubroEvaluacionProceso(String rubroEvalProcesoId);
}
