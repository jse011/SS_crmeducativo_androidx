package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.PublicarEvaluacionUi;

public class UpdatePublicacionEvaluacion {
    private EvalRubBidRepository evalRubBidRepository;

    public UpdatePublicacionEvaluacion(EvalRubBidRepository evalRubBidRepository) {
        this.evalRubBidRepository = evalRubBidRepository;
    }

    public void execute(PublicarEvaluacionUi publicarEvaluacionUi){
        evalRubBidRepository.updatePublicacionEvaluacion(publicarEvaluacionUi);
    }
}
