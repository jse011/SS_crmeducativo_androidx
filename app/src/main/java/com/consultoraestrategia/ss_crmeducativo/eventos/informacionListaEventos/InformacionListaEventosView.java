package com.consultoraestrategia.ss_crmeducativo.eventos.informacionListaEventos;

import com.consultoraestrategia.ss_crmeducativo.eventos.EventosPresenter;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventoAdjuntoUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi;

public interface InformacionListaEventosView {
    void showInformacionEvento(EventosUi eventosUi, EventoAdjuntoUi adjuntoUi, boolean mostrarRecursos);
    void changeLike(EventosUi eventosUi);
    void showCompartirEvento(EventosUi eventosUi);

    void hideInformacionEvento();

    void setPresenter(EventosPresenter presenter);
}
