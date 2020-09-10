package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.data.source.RubricaBidRepository;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;

import java.util.List;

public class ChangeEstadoActualizacion {
    private RubricaBidRepository rubricaBidRepository;

    public ChangeEstadoActualizacion(RubricaBidRepository rubricaBidRepository) {
        this.rubricaBidRepository = rubricaBidRepository;
    }

    public void execute(List<RubBidUi>  rubBidUiList){
       rubricaBidRepository.changeEstadoActualizacion(rubBidUiList);
    }
}
