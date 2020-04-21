package com.consultoraestrategia.ss_crmeducativo.dao.rubroEquipo;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC;

import java.util.List;

/**
 * Created by SCIEV on 31/08/2018.
 */

public interface RubroEquipoDao extends BaseDao<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> {
    List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> getEquipoRubroList(List<String> rubroEvalProcesoKeyList);
}
