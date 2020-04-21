package com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.ui.dialog;

import com.consultoraestrategia.ss_crmeducativo.comportamiento.CreateComportamiento.tabs.create.presenter.TabCreateComportPresenter;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoComportamientoUi;

import java.util.List;

public interface ListaComportamientoView {
    void onAttach(TabCreateComportPresenter tabCreateComportPresenter);

    void showListComportamiento(List<TipoComportamientoUi> tipoUiListHijos);
}
