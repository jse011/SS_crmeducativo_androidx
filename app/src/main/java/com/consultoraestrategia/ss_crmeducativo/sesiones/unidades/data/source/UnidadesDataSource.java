package com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.data.source;


import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.SesionAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadAprendizajeUi;

import java.util.List;

public interface UnidadesDataSource {


    interface CallbackUnidades{
        void onListeUnidades(List<UnidadAprendizajeUi> UnidadesList, int status);
    }



    interface Callback <T>{
        void onLoad(boolean success, T item);
    }

    void getUnidadesList(int idCargaCurso, int idCalendarioPeriodo, int idAnioAcademico, CallbackUnidades callbackUnidades);
    void saveToogleUnidad(UnidadAprendizajeUi unidadAprendizajeUi,Callback callback);
    void saveSesionAprendizaje(SesionAprendizajeUi sesionAprendizajeUi);

}
