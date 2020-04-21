package com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.CrearEventoRepository;
import com.consultoraestrategia.ss_crmeducativo.crearEvento.entities.TipoEventoUi;

import java.util.List;

public class GetTipoEvento {
    private CrearEventoRepository repository;

    public GetTipoEvento(CrearEventoRepository repository) {
        this.repository = repository;
    }

    public List<TipoEventoUi> execute(){
        return repository.getTipoEventos();
    }
}
