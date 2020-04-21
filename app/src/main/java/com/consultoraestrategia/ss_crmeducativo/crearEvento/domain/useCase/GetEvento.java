package com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.CrearEventoRepository;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.EventoUi;

import java.util.List;

public class GetEvento {
    private CrearEventoRepository repository;

    public GetEvento(CrearEventoRepository repository) {
        this.repository = repository;
    }

    public EventoUi execute(String eventoId){
        return repository.getEventoDocente(eventoId);
    }

}
