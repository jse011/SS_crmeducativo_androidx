package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase;


import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;

public class PublicarTodasEvaluacion {
    private RubroEvaluacionProcesoListaRepository repository;

    public PublicarTodasEvaluacion(RubroEvaluacionProcesoListaRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String rubricaEvaluacionId){
        return repository.publicarEvaluacion(rubricaEvaluacionId);
    }
}
