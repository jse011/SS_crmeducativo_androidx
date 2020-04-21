package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source;

import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.callbacks.GetTareasListCallback;

import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public interface TareasMvpDataSource {

    void updateArchivosTarea(List<RepositorioFileUi> repositorioFileUis);

    interface CallbackTareas{
        void onParametroDisenio(ParametroDisenioUi parametroDisenioUi, int status);
    }

    interface Callback <T>{
        void onLoad(boolean success, T item);
    }

    void getTareasUIList(int idUsuario, int idCargaCurso, int tipoTarea, int sesionAprendizajeId, int calendarioPeriodoId, int anioAcademicoId, GetTareasListCallback callback);

    void getParametroDisenio(int parametroDisenioId, CallbackTareas callbackTareas);

    void updateSucessDowload(String archivoId, String path, Callback<Boolean> callback);
}
