package com.consultoraestrategia.ss_crmeducativo.actividades.data.source;

import com.consultoraestrategia.ss_crmeducativo.actividades.data.source.local.ActividadesLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.actividades.data.source.remote.ActividadesRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.actividades.entidades.ActividadesUi;

/**
 * Created by kike on 08/02/2018.
 */

public class ActividadesRepository implements ActividadesDataSource {
    private ActividadesLocalDataSource localDataSource;

    public ActividadesRepository(ActividadesLocalDataSource localDataSource, ActividadesRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
    }

    @Override
    public void updateSucessDowload(String archivoId, String path, Callback<Boolean> booleanCallback) {
        localDataSource.updateSucessDowload(archivoId, path, booleanCallback);
    }

    @Override
    public void getActividadesList(int cargaCurso, int sesionAprendizajeId, int backgroundColor, int personaId, CallbackActividades callbackActividades) {
        localDataSource.getActividadesList(cargaCurso, sesionAprendizajeId, backgroundColor, personaId, callbackActividades);
    }

    @Override
    public void updateActividad(ActividadesUi actividadesUi, Callback<ActividadesUi> callback) {
        localDataSource.updateActividad(actividadesUi, callback);
    }
}
