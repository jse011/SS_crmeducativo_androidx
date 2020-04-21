package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.data.source.callback;

import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CampotematicoUi;

import java.util.List;

/**
 * Created by SCIEV on 30/10/2017.
 */

public interface GetCampotematicoCallback {

    void onRecursoLoad(List<CampotematicoUi> campotematicoUis);
    void onError(String errot);
}
