package com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.data.source;

import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.data.source.local.InfoEstiloAprendizajeLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.AlumnoUi;

import java.util.List;

public class InfoEstiloAprendizajeRepository implements InfoEstiloAprendizajeSource {
    private static InfoEstiloAprendizajeRepository INSTANCE;
    private InfoEstiloAprendizajeLocalDataSource localDataSource;

    public static InfoEstiloAprendizajeRepository getInstance(InfoEstiloAprendizajeLocalDataSource localDataSource){
        if(INSTANCE == null)INSTANCE = new InfoEstiloAprendizajeRepository(localDataSource);
        return INSTANCE;
    }

    private InfoEstiloAprendizajeRepository(InfoEstiloAprendizajeLocalDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }

    @Override
    public void getListaDimensiones(int alumnoId, int entidadId, int georeferenciaId, Callback<AlumnoUi> callback) {
        localDataSource.getListaDimensiones(alumnoId, entidadId, georeferenciaId, callback);
    }
}
