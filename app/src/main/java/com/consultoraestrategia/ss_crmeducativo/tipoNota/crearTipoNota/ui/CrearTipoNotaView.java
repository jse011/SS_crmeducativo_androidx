package com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.ui;


import com.consultoraestrategia.ss_crmeducativo.base.activity.BaseView;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.crearTipoNota.CrearTipoNotaPresenter;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoEscalaEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.tipoNota.entidades.TipoSelectorUi;

import java.util.List;

/**
 * Created by kike on 16/02/2018.
 */

public interface CrearTipoNotaView extends BaseView<CrearTipoNotaPresenter> {

    void showProgress();

    void hideProgress();




    void hideCheckBoxIntervalo();

    void showValorMinMax();

    void hideValorMinMax();

    /*Tipo Seleccion*/

    void showTipoSelectorSelected(String tipoSelectorUi);

    void showTipoEscalaEvaluacionSelected(String tipoEscalaEvaluacionUi);

    /*Vistas Visible and Hide*/
    void showTipoSelector();

    void showSelectorIconos();

    void showValorNumerico();

    void showSelectorNumerico();

}
