package com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDao;
import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_TIPO_EVALUACION;

/**
 * Created by @stevecampos on 18/04/2018.
 */

public interface TipoEvaluacionDao extends BaseIntegerDao<T_RN_MAE_TIPO_EVALUACION> {

    T_RN_MAE_TIPO_EVALUACION obtenerRegistroTipoEvaluacion(int keyTipoEvaluacion);
}
