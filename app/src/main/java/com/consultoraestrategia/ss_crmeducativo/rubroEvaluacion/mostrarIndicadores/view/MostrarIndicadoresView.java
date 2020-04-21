package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores.view;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores.MostrarIndicadoresPresenter;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores.entidades.IndicadoresUi;

import java.util.List;

/**
 * Created by kike on 10/05/2018.
 */

public interface MostrarIndicadoresView extends BaseView<MostrarIndicadoresPresenter> {
    void actualizarList(List<IndicadoresUi> indicadoresUiList);
}
