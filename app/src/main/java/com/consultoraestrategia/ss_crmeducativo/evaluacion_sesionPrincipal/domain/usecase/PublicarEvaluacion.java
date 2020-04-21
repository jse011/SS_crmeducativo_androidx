package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.RubroRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.OptionPublicar;

public class PublicarEvaluacion {
    private RubroRepository rubroRepository;

    public PublicarEvaluacion(RubroRepository rubroRepository) {
        this.rubroRepository = rubroRepository;
    }

    public void execute(OptionPublicar optionPublicar){
        rubroRepository.publicarEvaluacion(optionPublicar);
    }
}
