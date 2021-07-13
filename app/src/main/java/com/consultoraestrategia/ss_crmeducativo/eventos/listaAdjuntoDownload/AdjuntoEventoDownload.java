package com.consultoraestrategia.ss_crmeducativo.eventos.listaAdjuntoDownload;

import com.consultoraestrategia.ss_crmeducativo.eventos.EventosPresenter;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventoAdjuntoUi;

import java.util.List;

public interface AdjuntoEventoDownload {
    void setPresenter(EventosPresenter presenter);

    void setList(List<EventoAdjuntoUi> adjuntoUiList);
}
