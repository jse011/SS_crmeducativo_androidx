package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.EstrategiaEvalUi;

public class GetTituloRubrica {
    private CreateRubBidRepository createRubBidRepository;

    public GetTituloRubrica(CreateRubBidRepository createRubBidRepository) {
        this.createRubBidRepository = createRubBidRepository;
    }

    public EstrategiaEvalUi execute(String keyRubroEvaluacion){
        return createRubBidRepository.getTituloRubrica(keyRubroEvaluacion);
    }
}
