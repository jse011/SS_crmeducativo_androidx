package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_teclado_numerico.ui;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_teclado_numerico.EvaluacionGlobalPresenter;

/**
 * Created by @stevecampos on 15/11/2017.
 */

public interface EvaluacionGlobalView extends BaseView<EvaluacionGlobalPresenter> {

    void showProgress();

    void hideProgress();

    //ProfileViews
    void showImageProfile(String uri);

    void showName(String name, String firstName);

    void setHeaderTitle(String title);

    void hideBtnNextTextColor();

    void hideBtnRetroTextColor();

    void showBtnNextTextColor();

    void showBtnRetroTextColor();

    void hideDialogo();

    void showNota(String nota);

    void showNotaError(String error);

    void showErrorInvalidNota();

    void showRangoEvaluacion(double valMin, double valMax);
}
