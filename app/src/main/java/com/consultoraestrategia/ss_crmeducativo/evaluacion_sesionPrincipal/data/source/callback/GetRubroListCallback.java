package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback;

import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;

import java.util.List;

/**
 * Created by SCIEV on 6/10/2017.
 */

public interface GetRubroListCallback {

    void onRecursoLoad(List<RubroEvaluacionUi> rubroEvaluacionUis);
    void onError(String error);
}
