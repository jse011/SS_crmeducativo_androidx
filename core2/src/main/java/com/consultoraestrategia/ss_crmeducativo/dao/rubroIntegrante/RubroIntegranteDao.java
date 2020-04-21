package com.consultoraestrategia.ss_crmeducativo.dao.rubroIntegrante;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC;

import java.util.List;

/**
 * Created by SCIEV on 31/08/2018.
 */

public interface RubroIntegranteDao extends BaseDao<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> {

    List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_INTEGRANTEC> getRubroIntegranteListPorRubroGrupo(String rubroEvaluacionEquipoId);
}
