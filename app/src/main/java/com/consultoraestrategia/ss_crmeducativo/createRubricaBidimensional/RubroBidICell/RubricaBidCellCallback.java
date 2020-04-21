package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.RubroBidICell;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;

/**
 * Created by @stevecampos on 19/02/2018.
 */

public interface RubricaBidCellCallback {
    void onBtnNegativeClicked();
    void onBtnPostiveCriterioEvalaucion(CriterioEvaluacionUi criterioEvaluacionUi, int x, int y);
}
