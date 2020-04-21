package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros;

import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;

import java.util.List;

/**
 * Created by Jse on 01/08/2018.
 */

public interface SeleccionarRubrosView extends BaseView<SeleccionarRubrosPresenter> {
    void showListaRubros(CapacidadUi capacidadUi, List<RubroProcesoUi> rubroProcesoUiList, int pocisionSelecion);
    void changeNombreToolbar(String nombre);
    void changeListRubros();
    void succesSeleccion(CapacidadUi capacidadUi);
    void closeActivity();
    void removerSesion();
    void setTituloCompetencia(String nombre);
    void setTituloCapacidad(String nombre);
    void setItemtoolbarCheck(boolean check);

    void updateRubroValidado(RubroProcesoUi rubroProcesoUi);
}
