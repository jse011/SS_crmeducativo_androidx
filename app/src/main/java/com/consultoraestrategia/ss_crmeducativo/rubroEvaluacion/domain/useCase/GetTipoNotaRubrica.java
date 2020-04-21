package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;


import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoNotaUi;

public class GetTipoNotaRubrica {
    private RubroEvaluacionProcesoListaRepository repository;

    public GetTipoNotaRubrica(RubroEvaluacionProcesoListaRepository repository) {
        this.repository = repository;
    }

    public TipoNotaUi execute(String tipoNotaId){
        return repository.getTipoNotaRubrica(tipoNotaId);
    }
}
