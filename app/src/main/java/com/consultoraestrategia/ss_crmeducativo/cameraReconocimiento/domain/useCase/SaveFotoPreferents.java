package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.preferent.ReconocimientoPreferentRepository;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.SentimientoUi;

import java.util.List;

public class SaveFotoPreferents {
    private ReconocimientoPreferentRepository repository;

    public SaveFotoPreferents(ReconocimientoPreferentRepository repository) {
        this.repository = repository;
    }

    public void execute(List<SentimientoUi> urls, int personaId){
        repository.guardarFotos(urls, personaId, new ReconocimientoPreferentRepository.Callback<Boolean>() {
            @Override
            public void onLoad(boolean success, Boolean item) {

            }
        });
    }

}
