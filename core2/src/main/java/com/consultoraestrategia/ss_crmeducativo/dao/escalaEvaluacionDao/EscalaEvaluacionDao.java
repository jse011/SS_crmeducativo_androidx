package com.consultoraestrategia.ss_crmeducativo.dao.escalaEvaluacionDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDao;
import com.consultoraestrategia.ss_crmeducativo.entities.EscalaEvaluacion;

/**
 * Created by @stevecampos on 18/04/2018.
 */

public interface EscalaEvaluacionDao extends BaseIntegerDao<EscalaEvaluacion> {

    EscalaEvaluacion getEscalaEvalPorTipoNota(String tipoNotaKey);
    EscalaEvaluacion getEscalaEvalPorRubrosAsociados(String rnpFormulaKeySec);
}
