package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.contenedor;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;

/**
 * Created by SCIEV on 30/07/2018.
 */

public interface EvaluacionContainerPresenter extends BasePresenter<EvaluacionContainerView> {
    void onBtnActualizarRubro();
    void onUpdateRubro();

    void onQueryTextSubmit(String query);
    void onQueryTextChange(String newText);

    void onClikCornerTableView();

    void onClickEye();

    void onClickFooter();

    void onClickClear();

    void initTutorial();

    void onCreateOptionsMenu();
}
