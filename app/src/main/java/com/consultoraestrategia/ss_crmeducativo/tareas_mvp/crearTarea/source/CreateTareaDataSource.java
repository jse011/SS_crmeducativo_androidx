package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source;

import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.callbacks.CrearTareaCallback;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.callbacks.EliminarRecursoCallback;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.usecase.CrearTareaUseCase;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.usecase.EliminarRecursoUseCase;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.RecursosUI;

import java.util.List;

/**
 * Created by @stevecampos on 21/09/2017.
 */

public interface CreateTareaDataSource {

    interface Callback<T>{
        void onLoad(boolean success, T item);
    }
    void crearTarea(CrearTareaUseCase.RequestValues requestValues, CrearTareaCallback callback);

    void eliminarRecurso(EliminarRecursoUseCase.RequestValues requestValues, EliminarRecursoCallback callback);

    void getArchivosUsuario(String tareaId, Callback<List<RecursosUI>> callback);

    void updateArchivosTarea(List<RepositorioFileUi> repositorioFileUis);
}
