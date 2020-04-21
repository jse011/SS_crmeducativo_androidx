package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.contenedor;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.FiltroTableUi;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;

/**
 * Created by SCIEV on 30/07/2018.
 */

public interface EvaluacionContainerView extends BaseView<EvaluacionContainerPresenter> {

    void showActividadImportacion(BEVariables beVariables);

    void showMsgActualizacion();

    void hideMsgActualizacion();

    void showMsgCalendarioPeriodo();
    void hideMsgCalendarioPeriodo();
    void serviceUpdateRubro(String rubroEvaluacionId);

    void updateTableView(FiltroTableUi filtroTableUi);

    void changeSwiteOn();
    void changeSwiteOff();

    void changeEyeSimple();
    void changerEyeFocus();

    void showFooter();
    void hideFooter();

    void changeTableAvanzado();
    void changeTableSimple();

    void changeImageClearOn();

    void showBtnClean(boolean isCalendarioVigente);

    void showBtnFooter();
    void hideBtnFooter();

    void showTutorialRubroNormal();

    void showTutorialRubricaDetalle();

    void showBtnHelp();

    void hideBtnHelp();

    void onSincronizate(int programaEducativoId, String rubroEvaluacionId);

    void showDialogProgress();

    void hideDialogProgress();
}
