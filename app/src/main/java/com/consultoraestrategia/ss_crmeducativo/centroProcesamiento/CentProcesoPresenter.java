package com.consultoraestrategia.ss_crmeducativo.centroProcesamiento;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.view.CentProcesoView;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.view.CerrarCursoDialogView;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.view.GenerarNotasDialogView;
import com.consultoraestrategia.ss_crmeducativo.centroProcesamiento.view.TutorialCentView;

public interface CentProcesoPresenter extends BasePresenter<CentProcesoView> {

    void attachView(RegistroCentProcesamientoView view);

    void onDestroyRegistroCentProcesamientoView();

    void onDestroyTutorialCentView();

    void attachView(TutorialCentView view);

    void onClickCalendario(PeriodoUi periodoUi);

    void onClickTransversal();

    void onClickBase();

    void onClickGenerador();

    void onClickBloqueo();

    void attachView(GenerarNotasDialogView view);

    void onDestroyGenerarNotasDialogView();

    void onClickGenerarNotas();

    void onDestroyCerrarCursoDialogView();

    void attachView(CerrarCursoDialogView view);

    void onClickCerrarEvaluacion();
}
