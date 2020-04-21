package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_selector_numerico;

import com.consultoraestrategia.ss_crmeducativo.base.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.NotaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_selector_numerico.ui.EvaluacionGlobalView;

/**
 * Created by @stevecampos on 15/11/2017.
 */

public interface EvaluacionGlobalPresenter extends BaseFragmentPresenter<EvaluacionGlobalView> {
    void setExtras();

    void btnFinalizar();

    void btnRetroceder();

    void btnAvanzar();

    void setOrderType(int orderType);

    void onValueChange(int newVal, int oldVal);

}
