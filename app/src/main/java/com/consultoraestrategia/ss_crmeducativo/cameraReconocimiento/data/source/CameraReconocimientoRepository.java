package com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.source;

import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.source.local.CameraReconocimientoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.data.source.remote.CameraReconocimientoRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.cameraReconocimiento.entities.PersonaUi;

import java.util.List;

public class CameraReconocimientoRepository implements CameraReconocimientoDataSource {
    private CameraReconocimientoLocalDataSource localDataSource;
    private CameraReconocimientoRemoteDataSource remoteDataSource;

    public CameraReconocimientoRepository(CameraReconocimientoLocalDataSource localDataSource, CameraReconocimientoRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public PersonaUi getDatosPersona(int usuarioId) {
        return localDataSource.getDatosPersona(usuarioId);
    }

    @Override
    public List<PersonaUi> getPersonasDelCurso(int cargaCursoId) {
        return localDataSource.getPersonasDelCurso(cargaCursoId);
    }

    @Override
    public PersonaUi getDatosAlumnos(int personaId) {
        return localDataSource.getDatosAlumnos(personaId);
    }
}
