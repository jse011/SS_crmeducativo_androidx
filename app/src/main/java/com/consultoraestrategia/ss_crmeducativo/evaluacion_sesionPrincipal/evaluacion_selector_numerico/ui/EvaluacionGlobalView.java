package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_selector_numerico.ui;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.evaluacion_selector_numerico.EvaluacionGlobalPresenter;

/**
 * Created by @stevecampos on 15/11/2017.
 */

public interface EvaluacionGlobalView extends BaseView<EvaluacionGlobalPresenter> {

    void showProgress();

    void hideProgress();

    //ProfileViews
    void showImageProfile(String uri);

    void showName(String name);

    void setHeaderTitle(String title);

    void setupNumberPicker(String[] values);

    void selectNumberPickert(int posicion);

    void hideBtnNextTextColor();

    void hideBtnRetroTextColor();

    void showBtnNextTextColor();

    void showBtnRetroTextColor();

    void hideDialogo();

}
