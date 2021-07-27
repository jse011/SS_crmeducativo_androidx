package com.consultoraestrategia.ss_crmeducativo.repositorio.data.local;

import android.net.Uri;

import com.consultoraestrategia.ss_crmeducativo.entities.Archivo;
import com.consultoraestrategia.ss_crmeducativo.entities.ArchivoAsistencia;
import com.consultoraestrategia.ss_crmeducativo.entities.ArchivoAsistencia_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.ArchivosRubroProceso;
import com.consultoraestrategia.ss_crmeducativo.entities.ArchivosRubroProceso_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CasoArchivo;
import com.consultoraestrategia.ss_crmeducativo.entities.CasoArchivo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.Rutas;
import com.consultoraestrategia.ss_crmeducativo.entities.Rutas_Table;
import com.consultoraestrategia.ss_crmeducativo.repositorio.data.RepositorioDataSource;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.UpdateRepositorioFileUi;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class RepositorioLocalDataSource implements RepositorioDataSource {


    @Override
    public void dowloadImage(String url, String nombre, String carpeta, CallbackProgress<String> repositorioFileUiCallback) {

    }

    @Override
    public void getArchivosConArchivosTareas(int tareaId, Callback<List<RepositorioFileUi>> callback) {

    }

    @Override
    public void getArchivosJustificacion(String asistenciaId, Callback<List<RepositorioFileUi>> callback) {

    }

    @Override
    public void uploadFileJustificacion(String archivoId, String urlServidor, String path, CallbackProgress<String> callback) {

    }

    @Override
    public void getUrlRepositorioArchivo(Callback<String> stringCallback) {
        Rutas rutas = SQLite.select()
                .from(Rutas.class)
                .where(Rutas_Table.rutaId.eq(Rutas.ARCHIVOS))
                .querySingle();
        if(rutas!=null){
            stringCallback.onLoad(true, rutas.getRuta_ArchivoAsistencia());
        }else {
            stringCallback.onLoad(false, null);
        }
    }

    @Override
    public void uploadFileArchivo(String archivoId, String urlServidor, String path, CallbackProgress<String> callbackProgress) {

    }

    @Override
    public void updateSucessDowload(String archivoId, String path, Callback<Boolean> callback) {
        Archivo archivo = SQLite.select()
                .from(Archivo.class)
                .where(Archivo_Table.key.eq(archivoId))
                .querySingle();
        if(archivo!=null){
            archivo.setLocalpath(path);
            boolean success = archivo.save();
            callback.onLoad(success, success);
        }else {
            callback.onLoad(false, false);
        }
    }

    @Override
    public void updateSucessDowloadAsistenica(String archivoId, String path, Callback<Boolean> booleanCallback) {
        ArchivoAsistencia archivo = SQLite.select()
                .from(ArchivoAsistencia.class)
                .where(ArchivoAsistencia_Table.key.eq(archivoId))
                .querySingle();
        if(archivo!=null){
            archivo.setLocalpath(path);
            boolean success = archivo.save();
            booleanCallback.onLoad(success, success);
        }else {
            booleanCallback.onLoad(false, false);
        }
    }

    @Override
    public void updateSucessDowloaComportamiento(String archivoId, String path, Callback<Boolean> booleanCallback) {
        CasoArchivo archivo = SQLite.select()
                .from(CasoArchivo.class)
                .where(CasoArchivo_Table.key.eq(archivoId))
                .querySingle();
        if(archivo!=null){
            archivo.setLocalPath(path);
            boolean success = archivo.save();
            booleanCallback.onLoad(success, success);
        }else {
            booleanCallback.onLoad(false, false);
        }
    }

    @Override
    public void uploadFileCaso(UpdateRepositorioFileUi updateRepositorioFileUi, String urlServidor, Uri path, CallbackProgress<String> callbackProgress) {

    }

    @Override
    public void updateSucessDowloadRubro(String archivoId, String path, Callback<Boolean> booleanCallback) {
        ArchivosRubroProceso archivo = SQLite.select()
                .from(ArchivosRubroProceso.class)
                .where(ArchivosRubroProceso_Table.key.eq(archivoId))
                .querySingle();
        if(archivo!=null){
            archivo.setLocalpath(path);
            boolean success = archivo.save();
            booleanCallback.onLoad(success, success);
        }else {
            booleanCallback.onLoad(false, false);
        }
    }

    @Override
    public void uploadFileCasoRubro(String archivoId, String urlServidor, Uri path, CallbackProgress<String> callbackProgress) {

    }


}
