package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.EvaluacionFormula_RubroEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.evaluacionFormula.view.EvaluacionFormulaView;

/**
 * Created by CCIE on 03/05/2018.
 */

public interface EvaluacionFormulaPresenter extends BasePresenter<EvaluacionFormulaView> {
    void onClickRubroEvalProceso(EvaluacionFormula_RubroEvaluacionUi rubroProcesoUi);
    void onClickAlumno(AlumnosUi alumnosUi);
}
