package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.CrearRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CapacidadUi;

public class GetCapacidad {
    public CrearRubroRepository repository;

    public GetCapacidad(CrearRubroRepository repository) {
        this.repository = repository;
    }

    public CapacidadUi execute(int capacidadId){
        return repository.getCompetencia(capacidadId);
    }
}
