package com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.data.source;

import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.DimensionObservadaUi;

import java.util.List;

public interface InfoEstiloAprendizajeSource {
    interface Callback<T>{
        void load(boolean success, T item);
    }

    void getListaDimensiones(int alumnoId, int entidadId, int georeferenciaId, Callback<AlumnoUi> callback);
}

