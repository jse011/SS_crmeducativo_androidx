package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.CompetenciaUi;

import java.util.List;

public class GetCompetenciaRubrica {
    private CreateRubBidRepository createRubBidRepository;

    public GetCompetenciaRubrica(CreateRubBidRepository createRubBidRepository) {
        this.createRubBidRepository = createRubBidRepository;
    }

    public List<CompetenciaUi> execute(String keyRubroEvaluacion){
        return createRubBidRepository.getCompetencias(keyRubroEvaluacion);
    }
}
