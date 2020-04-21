package com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source;

import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.callback.GetCompetenciasCallback;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.callback.GetRecursoDidacticoCallback;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.callback.GetSesionCallback;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.local.AprendizajeLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.remote.AprendizajeRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.EvidenciaUi;

import java.util.List;

/**
 * Created by SCIEV on 15/01/2018.
 */

public class AprendizajeRepository implements AprendizajeDataSource {

    public static AprendizajeRepository INSTANCE = null;
    private AprendizajeLocalDataSource localDataSource;
    private AprendizajeRemoteDataSource remoteDataSource;

    public static AprendizajeRepository getInstance(AprendizajeLocalDataSource localDataSource, AprendizajeRemoteDataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new AprendizajeRepository(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }

    private AprendizajeRepository(AprendizajeLocalDataSource localDataSource, AprendizajeRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void updateSucessDowload(String archivoId, String path, Callback<Boolean> booleanCallback) {
        localDataSource.updateSucessDowload(archivoId, path, booleanCallback);
    }

    @Override
    public List<EvidenciaUi> getEvidencias(int sesionAprenId) {
        return localDataSource.getEvidencias(sesionAprenId);
    }

    @Override
    public void getCompencias(int usuarioId, int sesionAprendizajeId, GetCompetenciasCallback callback) {
        localDataSource.getCompencias(usuarioId, sesionAprendizajeId, callback);
        remoteDataSource.getCompencias(usuarioId, sesionAprendizajeId, callback);
    }

    @Override
    public void getSesion(int sesionAprendizajeId, GetSesionCallback callback) {
        localDataSource.getSesion(sesionAprendizajeId, callback);
        remoteDataSource.getSesion(sesionAprendizajeId, callback);
    }

    @Override
    public void getRecursoDidactico(int sesionAprendizajeId, GetRecursoDidacticoCallback callback) {
        localDataSource.getRecursoDidactico(sesionAprendizajeId, callback);
        remoteDataSource.getRecursoDidactico(sesionAprendizajeId, callback);
    }
}
