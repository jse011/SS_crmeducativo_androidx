package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias;

import android.app.AlertDialog;
import android.os.Bundle;


import com.consultoraestrategia.ss_crmeducativo.base.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.FiltradoUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtroDialog.FiltroView;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.view.EvaluacionCompetenciasView;

/**
 * Created by CCIE on 09/03/2018.
 */

public interface EvaluacionCompetenciasPresenter extends BaseFragmentPresenter<EvaluacionCompetenciasView>, com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentPresenter<EvaluacionCompetenciasView> {
    int getCalendarioPeriodoId();

    void onResumeFragment(Bundle bundle);

    void onAceptarDialogEvaluado(Object o);

    //Actualizacon de Items EvaluacionRubroFragmentCompentencias
    void onRefrescarItems();

    void onResumeFragment();

    void actualizarResultadosPeriodo(String idCalendarioPeriodo, boolean status);

    void clickCompetencia(CompetenciaUi competenciaUi);

    void executeAnclado(AlertDialog alertDialog);

    void onClickedBtnCerrar();

    void onbtnAceptarFiltroDialog();

    void filtradoCheckItemFiltroDialog(FiltradoUi filtradoUi);

    void attachView(FiltroView filtroDialog);

    void onDestroyDialogFiltro();

    void onResumeDialogFiltro();

    void onClickFiltro();
}
