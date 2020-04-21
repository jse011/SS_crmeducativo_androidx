package com.consultoraestrategia.ss_crmeducativo.tabsSesiones.data.source;

import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.entities.DatosEnsencialesUI;

import java.util.List;

/**
 * Created by SCIEV on 25/01/2018.
 */

public interface TabSesionesDataSource {
    interface ObjectCallback <T>{
        void onUsuarioLoaded(T objeto);
        void onError(String error);
    }
    interface ListCallback <T>{
        void onUsuarioLoaded(List<T> lista);
        void onError(String error);
    }

    DatosEnsencialesUI getDatosEsenciales(int sesionAprendizajeId, int cursoId);


}


