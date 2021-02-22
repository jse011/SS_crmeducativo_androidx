package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.view;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.CentProcesoPresenter;

public interface CentProcesoView extends BaseView<CentProcesoPresenter> {
    void showDialogGenerarNotas();

    void showDialogCerrarEvaluacion();
}
