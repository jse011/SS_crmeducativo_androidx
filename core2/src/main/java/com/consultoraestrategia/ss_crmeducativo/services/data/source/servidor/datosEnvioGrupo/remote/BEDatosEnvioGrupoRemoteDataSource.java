package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo.remote;

import androidx.annotation.NonNull;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.entities.T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.remote.ServiceRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo.BEDatosEnvioGrupoDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioGrupo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosEnvioGrupoRemoteDataSource extends ServiceRemoteDataSource<BEDatosEnvioGrupo> implements BEDatosEnvioGrupoDataSource{

    public BEDatosEnvioGrupoRemoteDataSource(UtilServidor utilServidor) {
        super(utilServidor);
    }

    @Override
    protected Class<BEDatosEnvioGrupo> getTableclass() {
        return BEDatosEnvioGrupo.class;
    }

    @Override
    protected Call<RestApiResponse<BEDatosEnvioGrupo>> _getDatosLogin(ApiRetrofit apiRetrofit, String usuarioId) {
        int int_usuarioId = Integer.valueOf(usuarioId);
        return apiRetrofit.flst_ObtenerGrupo(int_usuarioId);
    }

    @Override
    protected Call<RestApiResponse<BERespuesta>> _sendDatos(ApiRetrofit restAPI, BEDatosEnvioGrupo item) throws Exception {
        return restAPI.fins_GuardarGrupoEquipo_Android(item);
    }

    @Override
    protected <V extends BEVariables> Call<RestApiResponse<BEDatosEnvioGrupo>> _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return restAPI.fins_ListarGrupoEquipo_Equipo_EquiIntegrante(importar);
    }

    /*@Override
    protected JSONObject _sendDatos(ApiRetrofit restAPI, BEDatosEnvioGrupo item) throws Exception {
        return restAPI.fins_GuardarGrupoEquipo_Android(item);
    }

    @Override
    protected <V extends BEVariables> JSONObject _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return restAPI.fins_ListarGrupoEquipo_Equipo_EquiIntegrante(importar);
    }*/

    @Override
    public void getDatosExportarRelRubroEvalProceso(List<T_RN_MAE_RUBRO_EVALUACION_PROCESO_EQUIPOC> evaluacion_proceso_equipocList, ObjectCallBack<BEDatosEnvioGrupo> callBack) {

    }

    @Override
    public void comprobarCambiosGrupos(String grupoEquipoId, final DosObjectCallBack<Long, Long> callBack) {
        ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
        Call<RestApiResponse<Long>> responseCall = apiRetrofit.fins_ListarGrupoEquipo_FechaActualizacion(grupoEquipoId);
        responseCall.enqueue(new Callback<RestApiResponse<Long>>() {
            @Override
            public void onResponse(@NonNull Call<RestApiResponse<Long>> call, @NonNull Response<RestApiResponse<Long>> response) {
                RestApiResponse<Long> respuesta = response.body();
                if(respuesta.isSuccessful()){
                    callBack.onResponse(true,respuesta.getValue(),0L);
                    Log.d(TAG,"isSuccessful true");
                }else {
                    callBack.onResponse(false, 0L,0L);
                    Log.d(TAG,"isSuccessful false");
                }
            }

            @Override
            public void onFailure(@NonNull Call<RestApiResponse<Long>> call, @NonNull Throwable t) {
                callBack.onResponse(false,0L,0L);
                Log.d(TAG,"onFailure");
            }
        });
    }

    @Override
    public void getDatosImportarPorUsuario(BEVariables beVariables, final ObjectCallBack<BEDatosEnvioGrupo> callBack) {
        ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
        Call<RestApiResponse<BEDatosEnvioGrupo>> responseCall = apiRetrofit.fins_ListarGruposBYUsuario(beVariables);
        responseCall.enqueue(new Callback<RestApiResponse<BEDatosEnvioGrupo>>() {
            @Override
            public void onResponse(@NonNull Call<RestApiResponse<BEDatosEnvioGrupo>> call, @NonNull Response<RestApiResponse<BEDatosEnvioGrupo>> response) {
                RestApiResponse<BEDatosEnvioGrupo> respuesta = response.body();
                if(respuesta.isSuccessful()){
                    callBack.onResponse(true,respuesta.getValue());
                    Log.d(TAG,"isSuccessful true");
                }else {
                    callBack.onResponse(false, null);
                    Log.d(TAG,"isSuccessful false");
                }
            }

            @Override
            public void onFailure(@NonNull Call<RestApiResponse<BEDatosEnvioGrupo>> call, @NonNull Throwable t) {
                callBack.onResponse(false,null);
                Log.d(TAG,"onFailure");
            }
        });
    }


}
