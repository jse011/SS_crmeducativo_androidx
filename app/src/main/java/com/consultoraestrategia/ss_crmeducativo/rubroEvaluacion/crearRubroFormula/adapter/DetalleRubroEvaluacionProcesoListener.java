package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula.adapter;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener.RubroEvaluacionProcesoListener;

/**
 * Created by SCIEV on 10/08/2018.
 */

public interface DetalleRubroEvaluacionProcesoListener extends RubroEvaluacionProcesoListener {
    void onRubroEvalButtonDeleteClick(RubroProcesoUi rubroProcesoUi);
}
