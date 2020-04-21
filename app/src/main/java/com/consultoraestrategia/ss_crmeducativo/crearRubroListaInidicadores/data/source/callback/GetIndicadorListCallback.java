package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.source.callback;

import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CompetenciaUi;

import java.util.List;

/**
 * Created by SCIEV on 30/10/2017.
 */

public interface GetIndicadorListCallback {

    void onRecursoLoad(List<CompetenciaUi> competenciaUiList);
    void onError(String errot);
}
