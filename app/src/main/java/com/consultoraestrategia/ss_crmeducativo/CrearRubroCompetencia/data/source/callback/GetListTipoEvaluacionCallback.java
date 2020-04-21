package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoEvaluacionUi;

import java.util.List;

/**
 * Created by SCIEV on 16/10/2017.
 */

public interface GetListTipoEvaluacionCallback {
    void onEvaluacionLoad(List<TipoEvaluacionUi> evaluacionUis);
    void onError(String error);
}
