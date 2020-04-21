package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.selecionarRubros;


import com.consultoraestrategia.ss_crmeducativo.base.activity.BasePresenter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;

/**
 * Created by Jse on 01/08/2018.
 */

public interface SeleccionarRubrosPresenter extends BasePresenter<SeleccionarRubrosView> {
    void onQueryTextChange(String newText);
    void onClickRubrosAsociados(RubroProcesoUi rubroProcesoUi, CapacidadUi capacidadUi);

    void onClickAceptarSelecion();
    void onClickSelecionAll();
    void onClickSelecionOff();
}
