package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.listener;

import android.view.View;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;

/**
 * Created by SCIEV on 7/02/2018.
 */

public interface RubroEvaluacionProcesoListener {
    void onClickAddRubroEvaluacionCapacidad(CapacidadUi capacidadUi);

    void onClickCapacidad(CapacidadUi capacidadUi);

    void onClickRubroEvaluacion(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi);

    void onClickLongClickEvaluacion(RubroProcesoUi rubroProcesoUi);

    void onClickRubrosAsociados(RubroProcesoUi rubroProcesoUi, CapacidadUi capacidadUi);

    void onOpciones(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, View view);

    void onClickCamposTematicos(RubroProcesoUi rubroProcesoUi);

    void onPesoChanged(RubroProcesoUi rubroEvaluacionProcesoUi, CharSequence charSequence);

    void onClickSelectRubros(CapacidadUi capacidadUi);

    void onClickAncla(CapacidadUi capacidadUi);
}
