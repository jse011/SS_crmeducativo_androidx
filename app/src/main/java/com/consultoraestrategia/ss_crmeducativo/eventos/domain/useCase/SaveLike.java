package com.consultoraestrategia.ss_crmeducativo.eventos.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.eventos.data.source.EventosRepository;
import com.consultoraestrategia.ss_crmeducativo.eventos.entities.EventosUi;

public class SaveLike {
    private EventosRepository repository;

    public SaveLike(EventosRepository repository) {
        this.repository = repository;
    }

    public void execute(EventosUi eventosUi){
        repository.saveLike(eventosUi);
    }
}
