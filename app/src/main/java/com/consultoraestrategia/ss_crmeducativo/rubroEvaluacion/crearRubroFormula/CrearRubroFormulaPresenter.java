package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.crearRubroFormula;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;

/**
 * Created by CCIE on 26/03/2018.
 */

public interface CrearRubroFormulaPresenter extends BasePresenter<CrearRubroFormulaView>{

    void onBtnTipoFormulaClicked();
    void onBtnTipoNotaClicked();
    void onBtnTipoValorRedondeoClicked();

    void onSelectClickRubroAsociados(RubroProcesoUi rubroEvaluacionProcesoUi);
    void onRubroEvaluacionProcesoSaved(RubroProcesoUi rubroEvaluacionProcesoUiPeso );
    void onSelectButtonAceptar(RubroProcesoUi rubroEvaluacionProcesoUi);

    void onActualizarItemTipoNota(String nombre);
    void onTextChangedRubroAsociados(RubroProcesoUi rubroEvaluacionProcesoUi, CharSequence peso);

    void onDeleteRubroEval(RubroProcesoUi rubroProcesoUi);

    void onClikInfoTipoNota();
}
