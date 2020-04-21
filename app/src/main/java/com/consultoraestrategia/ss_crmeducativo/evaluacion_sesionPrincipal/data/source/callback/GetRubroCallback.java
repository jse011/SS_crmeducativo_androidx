package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback;


import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;

/**
 * Created by SCIEV on 6/10/2017.
 */

public interface GetRubroCallback {

    void onRecursoLoad(RubroEvaluacionUi rubroEvaluacionUi);
    void onError(String error);
}
