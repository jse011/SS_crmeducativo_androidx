package com.consultoraestrategia.ss_crmeducativo.dao.rubroEvaluacionDao;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.entities.CriterioRubroEvaluacionC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;

import java.util.List;

/**
 * Created by SCIEV on 24/04/2018.
 */

public interface RubroEvaluacionDao extends BaseDao<RubroEvaluacionProcesoC> {

    List<CriterioRubroEvaluacionC> getCriterio(String rubroEvalProcesoId);

}
