package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;

public class EditarFormula {
    private RubroEvaluacionProcesoListaRepository repository;

    public EditarFormula(RubroEvaluacionProcesoListaRepository repository) {
        this.repository = repository;
    }

    public boolean execute(RubroProcesoUi rubroProcesoUi){
        return repository.editarFormula(rubroProcesoUi);
    }
}
