package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.view;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.RubroEvaluacionCompetenciaPresenter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacionCompetenciasLista.entities.CompetenciaUi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 9/02/2018.
 */

public interface RubroEvaluacionCompetenciaView extends BaseView<RubroEvaluacionCompetenciaPresenter> {
    void showCompetencias(List<CompetenciaUi> competenciaUis);
    void showProgress();
    void hideProgress();
    void hideDialog();
    void sussesCompetencias(ArrayList<Integer> CompetenciasId);
    void onCheckTotal(boolean check);
}
