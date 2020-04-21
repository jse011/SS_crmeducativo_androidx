package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoUi;

public class GetTipoEvaluacion {
    private CreateRubBidRepository createRubBidRepository;

    public GetTipoEvaluacion(CreateRubBidRepository createRubBidRepository) {
        this.createRubBidRepository = createRubBidRepository;
    }

    public TipoUi execute(String keyRubroEvaluacion){
        return createRubBidRepository.getTipoEvaluacion(keyRubroEvaluacion);
    }
}
