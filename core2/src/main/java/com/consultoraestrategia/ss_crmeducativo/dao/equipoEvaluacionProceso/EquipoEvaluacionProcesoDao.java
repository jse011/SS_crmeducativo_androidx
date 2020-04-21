package com.consultoraestrategia.ss_crmeducativo.dao.equipoEvaluacionProceso;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.entities.EquipoEvaluacionProcesoC;

import java.util.List;

/**
 * Created by kike on 14/05/2018.
 */

public interface EquipoEvaluacionProcesoDao extends BaseDao<EquipoEvaluacionProcesoC> {
    List<EquipoEvaluacionProcesoC> getEquipoEvaluacionProcesoRubrica(List<String> rubroEvalProcesoKeyList, List<String> equipoKeyList);
}
