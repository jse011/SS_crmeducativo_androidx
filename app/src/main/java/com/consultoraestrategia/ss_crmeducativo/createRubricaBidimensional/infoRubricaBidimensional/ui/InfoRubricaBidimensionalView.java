package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.ui;


import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.infoRubricaBidimensional.InfoRubricaBidimensionalPresent;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CriterioEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.ValorTipoNotaUi;

import java.util.List;

public interface InfoRubricaBidimensionalView extends BaseView<InfoRubricaBidimensionalPresent> {

    void showIndicador(IndicadorUi indicadorUi);

    void showValorTipoNota(List<ValorTipoNotaUi> valorTipoNotaUis);

    void showCel(List<CriterioEvaluacionUi> criterioEvaluacionUis);

    void saveSuccess(IndicadorUi indicadorUi);
}
