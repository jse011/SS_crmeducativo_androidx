package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.remote;

import android.text.TextUtils;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo;
import com.consultoraestrategia.ss_crmeducativo.entities.Archivo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo;
import com.consultoraestrategia.ss_crmeducativo.entities.CalendarioPeriodo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoArchivo_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RecursoDidacticoEventoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento;
import com.consultoraestrategia.ss_crmeducativo.entities.SilaboEvento_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasRecursosC;
import com.consultoraestrategia.ss_crmeducativo.entities.TareasRecursosC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje;
import com.consultoraestrategia.ss_crmeducativo.entities.UnidadAprendizaje_Table;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDatosRubro;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEDrive;
import com.consultoraestrategia.ss_crmeducativo.model.docentementor.BEMatrizResultadoDocente;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancelImpl;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.TareasMvpDataSource;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.callbacks.GetTareasListCallback;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.DriveFileUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.entities.TareasUI;
import com.consultoraestrategia.ss_crmeducativo.util.Utils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;
import java.util.concurrent.TimeUnit;

import me.jessyan.progressmanager.ProgressManager;
import okhttp3.OkHttpClient;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class RemoteMvpDataSource implements TareasMvpDataSource {
    private static final String TAG = RemoteMvpDataSource.class.getSimpleName();

    public RemoteMvpDataSource() {
    }


    @Override
    public void updateArchivosTarea(List<RepositorioFileUi> repositorioFileUis) {

    }

    @Override
    public RetrofitCancel getUrlDriveArchivo(String recursoId, Callback<DriveFileUi> callback) {
        ApiRetrofit apiRetrofit = ApiRetrofit.getInstance();
        apiRetrofit.changeSetTime(10,15,15, TimeUnit.SECONDS);
        RetrofitCancel<BEDrive> retrofitCancel = new RetrofitCancelImpl<>(apiRetrofit.getUrlDriveRecursoId(recursoId));
        retrofitCancel.enqueue(new RetrofitCancel.Callback<BEDrive>() {
            @Override
            public void onResponse(BEDrive response) {
                if(response == null) {
                    callback.onLoad(false, null);
                }else {
                    DriveFileUi driveFileUi = new DriveFileUi();
                    driveFileUi.setNombre(response.nombre);
                    driveFileUi.setDriveId(response.idDrive);
                    callback.onLoad(true,driveFileUi );
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onLoad(false, null);
            }
        });
        return retrofitCancel;

    }

    @Override
    public void getTareasUIList(int idUsuario, int idCargaCurso, int tipoTarea, int sesionAprendizajeId, int calendarioPeriodoId, int anioAcademicoId, GetTareasListCallback callback) {

    }

    @Override
    public void getParametroDisenio(int parametroDisenioId, CallbackTareas callbackTareas) {

    }

    @Override
    public void updateSucessDowload(String archivoId, String path, Callback<Boolean> callback) {

    }
}
