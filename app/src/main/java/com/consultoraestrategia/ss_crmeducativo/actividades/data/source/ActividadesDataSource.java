package com.consultoraestrategia.ss_crmeducativo.actividades.data.source;

import com.consultoraestrategia.ss_crmeducativo.actividades.entidades.ActividadesUi;
import com.consultoraestrategia.ss_crmeducativo.actividades.entidades.RecursosUi;

import java.util.List;

/**
 * Created by kike on 08/02/2018.
 */

public interface ActividadesDataSource {
    void updateSucessDowload(String archivoId, String path, Callback<Boolean> booleanCallback);

    interface CallbackActividades {
        void onListeActividades(List<ActividadesUi> actividadesUiList, List<RecursosUi> recursosUiList, int status);
    }

    interface CallbackRecuros {
        void onListRecursos(List<RecursosUi> recursosUiList, int status);
    }

    interface Callback<T> {
        void onLoad(boolean success, T item);
    }

    void getActividadesList(int cargaCurso, int sesionAprendizajeId, int backgroundColor, int personaId, CallbackActividades callbackActividades);

    void updateActividad(ActividadesUi actividadesUi, Callback<ActividadesUi> callback);
}
