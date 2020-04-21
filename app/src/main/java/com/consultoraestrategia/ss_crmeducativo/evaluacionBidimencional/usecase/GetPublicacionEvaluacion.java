package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.PublicarEvaluacionUi;

public class GetPublicacionEvaluacion {
    private EvalRubBidRepository evalRubBidRepository;

    public GetPublicacionEvaluacion(EvalRubBidRepository evalRubBidRepository) {
        this.evalRubBidRepository = evalRubBidRepository;
    }

    public PublicarEvaluacionUi execute(String rubroEvaluacionId, int personaId){
        return evalRubBidRepository.getPublicacionEvaluacion(rubroEvaluacionId,personaId);
    }
}
