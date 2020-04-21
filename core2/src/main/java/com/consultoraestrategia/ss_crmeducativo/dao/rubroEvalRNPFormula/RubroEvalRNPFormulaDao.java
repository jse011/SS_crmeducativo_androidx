package com.consultoraestrategia.ss_crmeducativo.dao.rubroEvalRNPFormula;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDaoImpl;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvalRNPFormulaC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;

import java.util.List;

/**
 * Created by CCIE on 30/04/2018.
 */

public interface RubroEvalRNPFormulaDao extends BaseDao<RubroEvalRNPFormulaC> {
    List<RubroEvalRNPFormulaC>getListaRubroEvalRNPFormula(String key);
    /*Formula de RubroEvaluacionProceso*/

    void rubroBidRelacionadosFormula(String keyRubro, BaseDaoImpl.Callback<List<RubroEvaluacionProcesoC>> result);

    List<RubroEvaluacionProcesoC> getListaRubroFormula(String key);

    List<RubroEvalRNPFormulaC> getListaRubroEvalRNPFormula(List<String> rubroProcesoEvalaucionIdList);

}
