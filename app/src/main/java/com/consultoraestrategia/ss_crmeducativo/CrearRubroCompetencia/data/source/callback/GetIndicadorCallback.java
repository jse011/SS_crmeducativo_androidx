package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.IndicadorUi;

/**
 * Created by SCIEV on 18/10/2017.
 */

public interface GetIndicadorCallback {
    void onIndicadorLoad(IndicadorUi indicadorUi);
    void onError(String error);
}
