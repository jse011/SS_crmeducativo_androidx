package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.TipoNotaUi;

public class GetTipoNotaRubrica {
    private CreateRubBidRepository createRubBidRepository;

    public GetTipoNotaRubrica(CreateRubBidRepository createRubBidRepository) {
        this.createRubBidRepository = createRubBidRepository;
    }

    public TipoNotaUi execute(String keyRubroEvaluacion){
        return createRubBidRepository.getTipoNotaRubrica(keyRubroEvaluacion);
    }
}
