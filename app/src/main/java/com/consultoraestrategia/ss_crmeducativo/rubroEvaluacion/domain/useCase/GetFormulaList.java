package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;

import java.util.List;

public class GetFormulaList {
    private RubroEvaluacionProcesoListaRepository repository;

    public GetFormulaList(RubroEvaluacionProcesoListaRepository repository) {
        this.repository = repository;
    }

    public List<RubroProcesoUi> execute(String formulaId){
        return repository.getFormulaList(formulaId);
    }
}
