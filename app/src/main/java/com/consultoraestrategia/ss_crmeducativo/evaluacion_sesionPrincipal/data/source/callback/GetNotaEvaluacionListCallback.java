package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback;


import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;

import java.util.List;

/**
 * Created by SCIEV on 9/10/2017.
 */

public interface GetNotaEvaluacionListCallback {
    void onRecursoLoad(List<GrupoEvaluacionUi> itemEvaluacionUis);
    void onError(String errot);
}
