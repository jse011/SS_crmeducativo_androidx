package com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.crearRubroListaInidicadores.entitie.CompetenciaUi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SCIEV on 11/10/2017.
 */

public interface ListaIndicadoresView extends BaseView<ListaIndicadoresPresenter> {
    void hideDialog();

    void showListInidicadores(List<CompetenciaUi> competenciaUiList);

    void hideListInidicadores();

    void showProgress();

    void hideProgress();

    void retornarIndicadorCampotematico(int competenciaId, int indicadorId, ArrayList<Integer> campotematicoIds);

    void showMensaje(int mensaje);

    void showCompetencia(CompetenciaUi competenciaUi, CapacidadUi capacidadUi);

    void actualizarComptenciaLista();

}
