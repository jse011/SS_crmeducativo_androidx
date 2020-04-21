package com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.remote;

import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.AprendizajeDataSource;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.callback.GetCompetenciasCallback;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.callback.GetRecursoDidacticoCallback;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.data.source.callback.GetSesionCallback;
import com.consultoraestrategia.ss_crmeducativo.aprendizaje.entities.EvidenciaUi;

import java.util.List;

/**
 * Created by SCIEV on 15/01/2018.
 */

public class AprendizajeRemoteDataSource implements AprendizajeDataSource {

    @Override
    public void updateSucessDowload(String archivoId, String path, Callback<Boolean> booleanCallback) {

    }

    @Override
    public List<EvidenciaUi> getEvidencias(int sesionAprenId) {
        return null;
    }

    @Override
    public void getCompencias(int usuarioId, int sesionAprendizajeId, GetCompetenciasCallback callback) {

    }

    @Override
    public void getSesion(int sesionAprendizajeId, GetSesionCallback callback) {

    }

    @Override
    public void getRecursoDidactico(int sesionAprendizajeId, GetRecursoDidacticoCallback callback) {

    }
}
