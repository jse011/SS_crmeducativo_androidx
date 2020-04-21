package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.preferent.ReconocimientoPreferentRepository;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.SentimientoUi;

import java.util.List;

public class GetFotoPreferents {
    private ReconocimientoPreferentRepository repository;

    public GetFotoPreferents(ReconocimientoPreferentRepository repository) {
        this.repository = repository;
    }

    public List<SentimientoUi> execute(int personaId){
        return repository.getFotos(personaId);
    }

}
