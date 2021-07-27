package com.consultoraestrategia.ss_crmeducativo.repositorio.data;

import android.net.Uri;

import com.consultoraestrategia.ss_crmeducativo.repositorio.data.remote.RepositorioRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.preferents.RepositorioPreferentsDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.local.RepositorioLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;

import java.util.List;

public class RepositorioRepository implements RepositorioDataSource {
    private RepositorioLocalDataSource localDataSource;
    private RepositorioPreferentsDataSource preferentsDataSource;
    private RepositorioRemoteDataSource remoteDataSource;

    public RepositorioRepository(RepositorioLocalDataSource localDataSource, RepositorioPreferentsDataSource preferentsDataSource, RepositorioRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.preferentsDataSource = preferentsDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void dowloadImage(String url, String nombre, String carpeta, CallbackProgress<String> repositorioFileUiCallback) {
        remoteDataSource.dowloadImage(url,nombre, carpeta, repositorioFileUiCallback);
    }

    @Override
    public void getArchivosConArchivosTareas(int tareaId, Callback<List<RepositorioFileUi>> callback) {
        localDataSource.getArchivosConArchivosTareas(tareaId, callback);
    }

    @Override
    public void getArchivosJustificacion(String asistenciaId, Callback<List<RepositorioFileUi>> callback) {
        localDataSource.getArchivosJustificacion(asistenciaId, callback);
    }

    @Override
    public void uploadFileJustificacion(String archivoId, String urlServidor, String path, final CallbackProgress<String> callback) {
        remoteDataSource.uploadFileJustificacion(archivoId, urlServidor, path, callback);
    }

    @Override
    public void getUrlRepositorioArchivo(Callback<String> stringCallback) {
        localDataSource.getUrlRepositorioArchivo(stringCallback);
    }

    @Override
    public void uploadFileArchivo(String archivoId, String urlServidor, String path, CallbackProgress<String> callbackProgress) {
        remoteDataSource.uploadFileArchivo(archivoId, urlServidor, path, callbackProgress);
    }

    @Override
    public void updateSucessDowload(String archivoId, String path, Callback<Boolean> callback) {
        localDataSource.updateSucessDowload(archivoId, path, callback);
    }

    @Override
    public void updateSucessDowloadAsistenica(String archivoId, String path, Callback<Boolean> booleanCallback) {
        localDataSource.updateSucessDowloadAsistenica(archivoId,path, booleanCallback);
    }

    @Override
    public void updateSucessDowloaComportamiento(String archivoId, String path, Callback<Boolean> booleanCallback) {
        localDataSource.updateSucessDowloadAsistenica(archivoId, path, booleanCallback);
    }

    @Override
    public void uploadFileCaso(UpdateRepositorioFileUi updateRepositorioFileUi, String urlServidor, Uri path, CallbackProgress<String> callbackProgress) {
        remoteDataSource.uploadFileCaso(updateRepositorioFileUi, urlServidor, path, callbackProgress);
    }

    public void updateSucessDowloadRubro(String archivoId, String path, Callback<Boolean> booleanCallback) {
        localDataSource.updateSucessDowloadRubro(archivoId, path, booleanCallback);
    }

    @Override
    public void uploadFileCasoRubro(String archivoId, String urlServidor, Uri path, CallbackProgress<String> callbackProgress) {
        remoteDataSource.uploadFileCasoRubro(archivoId, urlServidor, path, callbackProgress);
    }
}
