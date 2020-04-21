package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.listener;

import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CampotematicoUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.IndicadorUi;

/**
 * Created by SCIEV on 10/10/2017.
 */

public interface CampotematicoListener {

    void onCampotematicoSelect(CompetenciaUi competenciaUi, IndicadorUi indicadorUi, CampotematicoUi campotematicoUi);

    void onClickIndicador(CompetenciaUi competencia, IndicadorUi indicadorUi);
}
