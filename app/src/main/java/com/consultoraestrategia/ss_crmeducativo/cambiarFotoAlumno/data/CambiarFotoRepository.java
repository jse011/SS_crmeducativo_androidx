package com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.data;

import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.data.local.CambiarFotoLocalDataSourse;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.data.remote.CambiarFotoRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.entities.PersonaUi;

import java.util.List;

public class CambiarFotoRepository implements CamabiarFotoDataSourse {

    private CambiarFotoLocalDataSourse localDataSourse;
    private CambiarFotoRemoteDataSource remoteDataSource;

    public CambiarFotoRepository(CambiarFotoLocalDataSourse localDataSourse, CambiarFotoRemoteDataSource remoteDataSource) {
        this.localDataSourse = localDataSourse;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public List<PersonaUi> getPersonasDelCurso(int cargaCursoId) {
        return localDataSourse.getPersonasDelCurso(cargaCursoId);
    }

    @Override
    public void savePathPersona(PersonaUi personaUi) {
        localDataSourse.savePathPersona(personaUi);
    }

    @Override
    public void uploadFileCasoRubro(PersonaUi personaUi,  Callback<String> stringCallback) {
        remoteDataSource.uploadFileCasoRubro(personaUi, stringCallback);
    }


}
