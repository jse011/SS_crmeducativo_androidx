package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source;


import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.callbacks.GetTareasListCallback;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.local.TareasLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.remote.RemoteMvpDataSource;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.DriveFileUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;

import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class TareasMvpRepository implements TareasMvpDataSource {
    private TareasLocalDataSource localDataSource;
    private RemoteMvpDataSource remoteDataSource;

    public TareasMvpRepository(TareasLocalDataSource localDataSource, RemoteMvpDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static TareasMvpRepository getInstace(TareasLocalDataSource localDataSource, RemoteMvpDataSource remoteDataSource) {

        if (INSTANCE == null) {
            INSTANCE = new TareasMvpRepository(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }

    private static TareasMvpRepository INSTANCE = null;

    @Override
    public void updateArchivosTarea(List<RepositorioFileUi> repositorioFileUis) {
        localDataSource.updateArchivosTarea(repositorioFileUis);
    }

    @Override
    public RetrofitCancel getUrlDriveArchivo(String recursoId, Callback<DriveFileUi> driveFileUiCallback) {
        return remoteDataSource.getUrlDriveArchivo(recursoId, driveFileUiCallback);
    }

    @Override
    public void getTareasUIList(int idUsuario, int idCargaCurso, int tipoTarea, int sesionAprendizajeId, int calendarioPeriodoId, int anioAcademicoId, GetTareasListCallback callback) {
        localDataSource.getTareasUIList(idUsuario,idCargaCurso,tipoTarea,sesionAprendizajeId,calendarioPeriodoId, anioAcademicoId, callback);
    }

    @Override
    public void getParametroDisenio(int parametroDisenioId, CallbackTareas callbackTareas) {
        localDataSource.getParametroDisenio(parametroDisenioId, callbackTareas);
    }

    @Override
    public void updateSucessDowload(String archivoId, String path, Callback<Boolean> callback) {
        localDataSource.updateSucessDowload(archivoId, path, callback);
    }
}
