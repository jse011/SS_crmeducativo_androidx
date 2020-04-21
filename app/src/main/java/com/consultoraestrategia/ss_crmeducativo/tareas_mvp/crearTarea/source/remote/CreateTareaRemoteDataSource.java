package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.remote;

import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.CreateTareaDataSource;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.callbacks.CrearTareaCallback;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.callbacks.EliminarRecursoCallback;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.usecase.CrearTareaUseCase;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.usecase.EliminarRecursoUseCase;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.RecursosUI;

import java.util.List;

/**
 * Created by @stevecampos on 21/09/2017.
 */

public class CreateTareaRemoteDataSource implements CreateTareaDataSource {



    public CreateTareaRemoteDataSource() {
    }


    @Override
    public void crearTarea(CrearTareaUseCase.RequestValues requestValues, CrearTareaCallback callback) {

    }

    @Override
    public void eliminarRecurso(EliminarRecursoUseCase.RequestValues requestValues, EliminarRecursoCallback callback) {

    }

    @Override
    public void getArchivosUsuario(String tareaId, Callback<List<RecursosUI>> callback) {

    }

    @Override
    public void updateArchivosTarea(List<RepositorioFileUi> repositorioFileUis) {

    }
}
