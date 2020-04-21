package com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.remote;

import android.content.Context;

import com.consultoraestrategia.ss_crmeducativo.api.RestAPI;
import com.consultoraestrategia.ss_crmeducativo.entities.BEDatosEnvio;
import com.consultoraestrategia.ss_crmeducativo.repositorio.entities.RepositorioFileUi;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.TareasMvpDataSource;
import com.consultoraestrategia.ss_crmeducativo.tareas_mvp.data_source.callbacks.GetTareasListCallback;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class RemoteMvpDataSource implements TareasMvpDataSource {
    private static final String TAG = RemoteMvpDataSource.class.getSimpleName();
    private JSONObject jsonObject = null;
    private RestAPI restAPI;
    private Context context;
    private ObjectMapper mapper;
    private BEDatosEnvio beDatosEnvio;
    private int estado = 0;


    public RemoteMvpDataSource(Context context) {
        restAPI = new RestAPI();
    }


    @Override
    public void updateArchivosTarea(List<RepositorioFileUi> repositorioFileUis) {

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
