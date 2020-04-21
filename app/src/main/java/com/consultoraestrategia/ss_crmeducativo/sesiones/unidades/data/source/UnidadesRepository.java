package com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.data.source;

import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.SesionAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadAprendizajeUi;

public class UnidadesRepository implements UnidadesDataSource {

    UnidadesLocalDataSource UnidadesLocalDataSource;

    public UnidadesRepository(UnidadesLocalDataSource unidadesLocalDataSource) {
        this.UnidadesLocalDataSource = unidadesLocalDataSource;
    }

    @Override
    public void getUnidadesList(int idCargaCurso, int idCalendarioPeriodo, int idAnioAcademico, CallbackUnidades callbackUnidades) {
        UnidadesLocalDataSource.getUnidadesList(idCargaCurso, idCalendarioPeriodo, idAnioAcademico, callbackUnidades);
    }

    @Override
    public void saveToogleUnidad(UnidadAprendizajeUi unidadAprendizajeUi, Callback callback) {
        UnidadesLocalDataSource.saveToogleUnidad(unidadAprendizajeUi, callback);
    }



    @Override
    public void saveSesionAprendizaje(SesionAprendizajeUi sesionAprendizajeUi) {
        UnidadesLocalDataSource.saveSesionAprendizaje(sesionAprendizajeUi);
    }


}
