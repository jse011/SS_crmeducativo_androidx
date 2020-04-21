package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.listener;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CrearRubroEvalUi;
import com.consultoraestrategia.ss_crmeducativo.base.fragment.BaseFragmentListener;

/**
 * Created by SCIEV on 17/10/2017.
 */

public interface CrearRubroListener extends BaseFragmentListener {
    void hideCrearRubro();
    void onSaveRubroEvaluacionProcesoSuccess(String rubroEvaluacionProcesoId, CrearRubroEvalUi crearRubroEvalUi);
}
