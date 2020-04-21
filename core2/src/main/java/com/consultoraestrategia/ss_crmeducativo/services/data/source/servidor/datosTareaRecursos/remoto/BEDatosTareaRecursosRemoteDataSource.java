package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosTareaRecursos.remoto;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.remote.ServiceRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosTareaRecursos.BEDatosTareaRecursosDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosTareasRecursos;

import java.util.List;

import retrofit2.Call;

/**
 * Created by SCIEV on 25/05/2018.
 */

public class BEDatosTareaRecursosRemoteDataSource extends ServiceRemoteDataSource<GEDatosTareasRecursos> implements BEDatosTareaRecursosDataSource {
    public BEDatosTareaRecursosRemoteDataSource(UtilServidor utilServidor) {
        super(utilServidor);
    }

    @Override
    protected Class<GEDatosTareasRecursos> getTableclass() {
        return GEDatosTareasRecursos.class;
    }

    @Override
    protected Call<RestApiResponse<GEDatosTareasRecursos>> _getDatosLogin(ApiRetrofit apiRetrofit, String usuarioId)  {
        return null;
    }

    @Override
    protected Call<RestApiResponse<BERespuesta>> _sendDatos(ApiRetrofit restAPI, GEDatosTareasRecursos item) throws Exception {
        return restAPI.fins_GuardarTareRecursosAndroid(item);
    }

    @Override
    protected <V extends BEVariables> Call<RestApiResponse<GEDatosTareasRecursos>> _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return restAPI.fins_ListarTareRecursos(importar);
    }


    /*@Override
    protected JSONObject _sendDatos(ApiRetrofit restAPI, GEDatosTareasRecursos item) throws Exception {
        Log.d(TAG, item.toString());
        return restAPI.fins_GuardarTareRecursosAndroid(item);
    }

    @Override
    protected <V extends BEVariables> JSONObject _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return restAPI.fins_ListarTareRecursos(importar);
    }*/

    @Override
    public void getGEDatosTareasRecursosDatosExportar(List<String> rubroEvalKeyList, ObjectCallBack<GEDatosTareasRecursos> callBack) {

    }

    @Override
    public void comprobarListaCambiosTareaRecursos(List<String> tareaRecusoIdList, DosObjectCallBack<List<Long>, List<Long>> dosObjectCallBack) {

    }
}
