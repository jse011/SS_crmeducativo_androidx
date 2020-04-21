package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.listener;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CriterioEvalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ValorTipoNotaUi;

import java.util.List;

/**
 * Created by SCIEV on 18/10/2017.
 */

public interface CriterioEvalListener {
    void onClickItemCriterioEval(CriterioEvalUi criterioEvalUi, List<ValorTipoNotaUi> valorTipoNotaUi);
    void editCriterioEval(List<ValorTipoNotaUi> valorTipoNotaUi);
}
