package com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.callback;

import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CampotematicoUi;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.CompetenciaUi;

import java.util.List;

/**
 * Created by SCIEV on 15/01/2018.
 */

public interface GetCompetenciasCallback {
    void onRecursoLoad(List<Object> competenciaUis, List<CampotematicoUi> campotematicoUiList);
    void onError(String errot);

}
