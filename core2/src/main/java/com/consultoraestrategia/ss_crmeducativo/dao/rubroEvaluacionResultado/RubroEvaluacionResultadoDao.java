package com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionResultado;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionResultado;


/**
 * Created by kike on 22/05/2018.
 */

public interface RubroEvaluacionResultadoDao extends BaseDao<RubroEvaluacionResultado> {
    void actualizarResultadodoAncla(int compentenciaId, int calendarioPeriodoId, int silaboEventoId, String rubroProcesoKey, int anclado);
    boolean f_mediaDesviacionEstandar(int rubroEvaluacionResultadoId);
}
