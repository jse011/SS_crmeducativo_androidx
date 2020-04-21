package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase;


import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.source.CameraReconocimientoRepository;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.PersonaUi;

import java.util.List;

public class GetPersonas {
    private CameraReconocimientoRepository repository;

    public GetPersonas(CameraReconocimientoRepository repository) {
        this.repository = repository;
    }

    public List<PersonaUi> execute(int cargaCursoId){
        return repository.getPersonasDelCurso(cargaCursoId);
    }
}
