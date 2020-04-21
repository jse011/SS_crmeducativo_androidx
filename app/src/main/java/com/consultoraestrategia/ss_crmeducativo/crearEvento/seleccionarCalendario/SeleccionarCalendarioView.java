package com.consultoraestrategia.ss_crmeducativo.crearEvento.seleccionarCalendario;



import com.consultoraestrategia.ss_crmeducativo.crearEvento.CrearEventoPresenter;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoCalendarioUi;

import java.util.List;

public interface SeleccionarCalendarioView {
    void onAttach(CrearEventoPresenter crearEventoPresenter);
    void showListComportamiento(List<TipoCalendarioUi> tipoCalendarioUis);
}
