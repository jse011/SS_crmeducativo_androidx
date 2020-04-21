package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.source;

import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.PersonaUi;

import java.util.List;

public interface CameraReconocimientoDataSource {
    PersonaUi getDatosPersona(int usuarioId);
    List<PersonaUi> getPersonasDelCurso(int cargaCursoId);
    PersonaUi getDatosAlumnos(int personaId);
}
