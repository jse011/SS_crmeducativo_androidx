package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.source.CameraReconocimientoRepository;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.PersonaUi;

public class GetAlumno {
    private CameraReconocimientoRepository repository;

    public GetAlumno(CameraReconocimientoRepository repository) {
        this.repository = repository;
    }

    public PersonaUi execute(int personaId){
        return repository.getDatosAlumnos(personaId);
    }
}
