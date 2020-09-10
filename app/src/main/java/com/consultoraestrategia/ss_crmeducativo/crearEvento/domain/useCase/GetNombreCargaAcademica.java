package com.consultoraestrategia.ss_crmeducativo.crearEvento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.crearEvento.data.source.CrearEventoRepository;

public class GetNombreCargaAcademica {

    private CrearEventoRepository repository;

    public GetNombreCargaAcademica(CrearEventoRepository repository) {
        this.repository = repository;
    }

    public String execute(int cargaAcademicaId){
        return repository.getNombreCargaAcademica(cargaAcademicaId);
    }
}
