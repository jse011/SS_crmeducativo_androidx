package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback;

import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.IndicadorUi;

import java.util.List;

/**
 * Created by SCIEV on 30/10/2017.
 */

public interface GetIndicadorListCallback {

    void onRecursoLoad(List<IndicadorUi> indicadorUis);
    void onError(String errot);
}
