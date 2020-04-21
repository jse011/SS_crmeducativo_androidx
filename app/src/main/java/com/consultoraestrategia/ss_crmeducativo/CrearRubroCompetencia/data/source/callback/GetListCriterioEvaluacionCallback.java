package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ValorTipoNotaUi;

import java.util.List;

/**
 * Created by SCIEV on 18/10/2017.
 */

public interface GetListCriterioEvaluacionCallback {
    void onTipoNotaLoad(List<ValorTipoNotaUi> valorTipoNotaUis);
    void onError(String error);
}
