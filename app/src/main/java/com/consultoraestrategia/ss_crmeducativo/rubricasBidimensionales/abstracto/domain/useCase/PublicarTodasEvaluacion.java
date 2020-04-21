package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.data.source.RubricaBidRepository;

public class PublicarTodasEvaluacion {
    private RubricaBidRepository repository;

    public PublicarTodasEvaluacion(RubricaBidRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String rubricaEvaluacionId){
        return repository.publicarEvaluacion(rubricaEvaluacionId);
    }
}
