package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.data.source.RubricaBidRepository;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.PeriodoUi;

public class GetCalendarioPeriodo {
    private RubricaBidRepository repository;

    public GetCalendarioPeriodo(RubricaBidRepository repository) {
        this.repository = repository;
    }

    public PeriodoUi execute(int calendarioPeriodoId, int cargaCursoId){
        return repository.getPeriodo(calendarioPeriodoId,cargaCursoId);
    }
}
