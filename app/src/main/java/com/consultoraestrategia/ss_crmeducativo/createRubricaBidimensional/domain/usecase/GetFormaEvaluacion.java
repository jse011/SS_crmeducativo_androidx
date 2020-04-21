package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoUi;

public class GetFormaEvaluacion {

    private CreateRubBidRepository createRubBidRepository;

    public GetFormaEvaluacion(CreateRubBidRepository createRubBidRepository) {
        this.createRubBidRepository = createRubBidRepository;
    }

    public TipoUi execute(String keyRubroEvaluacion){
        return createRubBidRepository.getFormaEvaluacion(keyRubroEvaluacion);
    }

}
