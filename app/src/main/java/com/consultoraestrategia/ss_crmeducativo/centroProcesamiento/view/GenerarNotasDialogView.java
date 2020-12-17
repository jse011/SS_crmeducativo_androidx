package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.view;

import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.CentProcesoPresenter;

public interface GenerarNotasDialogView {
    void setPresenter(CentProcesoPresenter presenter);

    void showAlertaAsignarNotas(String nombrePeriodo, boolean noAsignado, boolean habilitado, boolean cerrado);

    void hideAlertaAsignarNotas();

    void showProgress();
    void hideProgress();
    void showButtonAction();
    void hideButtonAction();

    void closeDialog();
}
