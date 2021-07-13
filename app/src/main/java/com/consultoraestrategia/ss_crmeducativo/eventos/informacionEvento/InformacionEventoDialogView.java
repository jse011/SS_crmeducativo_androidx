package com.consultoraestrategia.ss_crmeducativo.eventos.informacionEvento;


import com.consultoraestrategia.ss_crmeducativo.eventos.EventosPresenter;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventoAdjuntoUi;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi;

public interface InformacionEventoDialogView {
    void setPresenter(EventosPresenter presenter);

    void showInformacionEvento(EventosUi eventosUi, EventoAdjuntoUi adjuntoUiPreviewSelected);
    void changeLike(EventosUi eventosUi);

    void showCompartirEvento(EventosUi eventosUi);

    void hideInformacionEvento();

}
