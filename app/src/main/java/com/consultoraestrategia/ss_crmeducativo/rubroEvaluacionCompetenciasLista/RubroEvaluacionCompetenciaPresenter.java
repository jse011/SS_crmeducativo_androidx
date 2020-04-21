package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista;

import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.entities.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.view.RubroEvaluacionCompetenciaView;

/**
 * Created by SCIEV on 9/02/2018.
 */

public interface RubroEvaluacionCompetenciaPresenter extends BaseFragmentPresenter<RubroEvaluacionCompetenciaView> {
    void setExtras(Bundle arguments);
    void onClickCompetencia(CompetenciaUi competenciaUi);
    void onClickAceptar();
    void onClickCancelar();
    void onClickTodos();
}
