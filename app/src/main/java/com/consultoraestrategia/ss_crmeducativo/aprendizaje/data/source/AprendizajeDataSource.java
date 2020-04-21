package com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source;

import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.callback.GetCompetenciasCallback;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.callback.GetRecursoDidacticoCallback;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.callback.GetSesionCallback;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.EvidenciaUi;

import java.util.List;

/**
 * Created by SCIEV on 15/01/2018.
 */

public interface AprendizajeDataSource {

    void updateSucessDowload(String archivoId, String path, Callback<Boolean> booleanCallback);

    List<EvidenciaUi> getEvidencias(int sesionAprenId);

    interface Callback<T> {
        void onLoad(boolean success, T item);
    }

    void getCompencias(int usuarioId, int sesionAprendizajeId, GetCompetenciasCallback callback);

    void getSesion(int sesionAprendizajeId, GetSesionCallback callback);

    void getRecursoDidactico(int sesionAprendizajeId, GetRecursoDidacticoCallback callback);
}
