package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.filtradorDialogFragment;

import com.consultoraestrategia.ss_crmeducativo.base.BaseView;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.FiltradoUi;

import java.util.List;

/**
 * Created by kike on 11/04/2018.
 */

public interface FiltradoView extends BaseView<FiltradoPresenter> {
    void mostrarFiltro(List<FiltradoUi> filtradoUiList);

    void mostrarMensaje(String mensaje);

    void onSuccess(FiltradoUi filtradoUi);
}
