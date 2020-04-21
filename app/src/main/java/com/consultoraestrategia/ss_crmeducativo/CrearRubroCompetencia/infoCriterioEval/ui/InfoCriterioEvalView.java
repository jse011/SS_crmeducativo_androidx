package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.ui;


import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.InfoCriterioEvalPresenter;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;

import java.util.List;

public interface InfoCriterioEvalView extends BaseView<InfoCriterioEvalPresenter> {

    void showIndicador(IndicadorUi indicadorUi);

    void showValorTipoNota(List<ValorTipoNotaUi> valorTipoNotaUis);
}
