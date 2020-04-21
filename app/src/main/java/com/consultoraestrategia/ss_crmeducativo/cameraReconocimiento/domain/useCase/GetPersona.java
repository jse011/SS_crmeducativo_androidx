package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.source.CameraReconocimientoRepository;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.PersonaUi;

public class GetPersona {
    private CameraReconocimientoRepository repository;

    public GetPersona(CameraReconocimientoRepository repository) {
        this.repository = repository;
    }

    public PersonaUi execute(int usuarioId){
        return repository.getDatosPersona(usuarioId);
    }
}
