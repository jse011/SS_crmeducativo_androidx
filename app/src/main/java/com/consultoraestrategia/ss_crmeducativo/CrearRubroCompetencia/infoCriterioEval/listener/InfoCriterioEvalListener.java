package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.listener;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentListener;

import java.util.List;

public interface InfoCriterioEvalListener extends BaseFragmentListener {

    void saveListCriteriosEval(List<ValorTipoNotaUi> valorTipoNotaUiList);
}
