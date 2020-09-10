package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;

import java.util.List;

public class ChangeEstadoActualizacion {
    private RubroEvaluacionProcesoListaRepository repository;

    public ChangeEstadoActualizacion(RubroEvaluacionProcesoListaRepository repository) {
        this.repository = repository;
    }

    public void execute(List<RubroProcesoUi>  rubBidUiList){
        repository.changeEstadoActualizacion(rubBidUiList);
    }
}
