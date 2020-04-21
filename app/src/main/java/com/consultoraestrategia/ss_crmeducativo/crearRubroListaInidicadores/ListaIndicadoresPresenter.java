package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores;

import android.os.Bundle;

import com.consultoraestrategia.ss_crmeducativo.base.BaseFragmentPresenter;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CampotematicoUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CompetenciaUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.IndicadorUi;

/**
 * Created by SCIEV on 11/10/2017.
 */

public interface ListaIndicadoresPresenter extends BaseFragmentPresenter<ListaIndicadoresView> {
    void setExtras(Bundle extras);

    void onSelectCampotematico(CompetenciaUi competenciaUi, IndicadorUi indicadorUi, CampotematicoUi campotematicoUi);

    void onSeleccionarIndicador();

    void onClickIndicador(CompetenciaUi competencia, IndicadorUi indicadorUi);
}
