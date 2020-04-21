package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarCamposAccion.view;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarCamposAccion.MostrarCamposAccionPresenter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.IndicadoresCamposAccionUi;

import java.util.List;

/**
 * Created by kike on 08/05/2018.
 */

public interface MostrarCamposAccionView extends BaseView<MostrarCamposAccionPresenter> {
    void actualizarLista(List<IndicadoresCamposAccionUi> indicadoresCamposAccionUis);
}
