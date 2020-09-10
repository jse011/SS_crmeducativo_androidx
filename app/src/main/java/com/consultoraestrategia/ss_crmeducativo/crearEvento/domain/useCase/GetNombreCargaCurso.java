package com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.CrearEventoRepository;

public class GetNombreCargaCurso {

    private CrearEventoRepository repository;

    public GetNombreCargaCurso(CrearEventoRepository repository) {
        this.repository = repository;
    }

    public String execute(int cargaCursoId){
        return repository.getNombreCargaCurso(cargaCursoId);
    }
}
