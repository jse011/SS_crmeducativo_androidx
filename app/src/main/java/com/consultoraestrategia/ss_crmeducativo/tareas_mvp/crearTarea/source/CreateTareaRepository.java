package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source;

import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.callbacks.CrearTareaCallback;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.callbacks.EliminarRecursoCallback;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.local.CreateTareaLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.source.remote.CreateTareaRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.usecase.CrearTareaUseCase;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.crearTarea.usecase.EliminarRecursoUseCase;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.RecursosUI;

import java.util.List;

/**
 * Created by @stevecampos on 21/09/2017.
 */

public class CreateTareaRepository implements CreateTareaDataSource {

    public static final String TAG = CreateTareaRepository.class.getSimpleName();

    public static CreateTareaRepository INSTANCE = null;
    private CreateTareaLocalDataSource localDataSource;
    private CreateTareaRemoteDataSource remoteDataSource;

//    public static CreateTareaRepository getInstance(CreateTareaLocalDataSource localDataSource, CreateTareaRemoteDataSource remoteDataSource) {
//        if (INSTANCE == null) {
//            INSTANCE = new CreateTareaRepository(localDataSource, remoteDataSource);
//        }
//        return INSTANCE;
//    }

    public CreateTareaRepository(CreateTareaLocalDataSource localDataSource, CreateTareaRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }


    @Override
    public void crearTarea(CrearTareaUseCase.RequestValues requestValues, CrearTareaCallback callback) {
        localDataSource.crearTarea(requestValues, callback);
    }

    @Override
    public void eliminarRecurso(EliminarRecursoUseCase.RequestValues requestValues, EliminarRecursoCallback callback) {
        localDataSource.eliminarRecurso(requestValues, callback);
    }

    @Override
    public void getArchivosUsuario(String tareaId, Callback<List<RecursosUI>> callback) {
        localDataSource.getArchivosUsuario(tareaId, callback);
    }

    @Override
    public void updateArchivosTarea(List<RepositorioFileUi> repositorioFileUis) {
        localDataSource.updateArchivosTarea(repositorioFileUis);
    }

}
