package com.consultoraestrategia.ss_crmeducativo.eventos.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.EventosRepository;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.TiposEventosUi;

import java.util.List;

public class GetTipoEvento  {
    private EventosRepository repository;

    public GetTipoEvento(EventosRepository repository) {
        this.repository = repository;
    }

    public List<TiposEventosUi> execute(){
        return repository.getTipoEvento();
    }
}
