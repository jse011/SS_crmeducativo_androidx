package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosRubroEvaluacionProceso.remoto;

import androidx.annotation.NonNull;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.remote.ServiceRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosRubroEvaluacionProceso.GEDatosRubroEvaluacionProcesoDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosRubroEvaluacionProceso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SCIEV on 25/05/2018.
 */

public class GEDatosRubroEvaluacionProcesoRemotelDataSource extends ServiceRemoteDataSource<GEDatosRubroEvaluacionProceso> implements GEDatosRubroEvaluacionProcesoDataSource{
    public GEDatosRubroEvaluacionProcesoRemotelDataSource(UtilServidor utilServidor) {
        super(utilServidor);
    }

    @Override
    protected Class<GEDatosRubroEvaluacionProceso> getTableclass() {
        return GEDatosRubroEvaluacionProceso.class;
    }

    @Override
    protected Call<RestApiResponse<GEDatosRubroEvaluacionProceso>> _getDatosLogin(ApiRetrofit apiRetrofit, String usuarioId) {
        return null;
    }


    /*@Override
    protected JSONObject _sendDatos(ApiRetrofit restAPI, GEDatosRubroEvaluacionProceso item) throws Exception {
        Log.d(TAG, item.toString());
        return restAPI.fins_GuardarRubroEvaluacionProcesoAndroid(item);
    }

    @Override
    protected <V extends BEVariables> JSONObject _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return restAPI.fins_ListarRubroEvaluacionProceso(importar);
    }*/

    @Override
    public void getDatosExportar(ObjectCallBack<GEDatosRubroEvaluacionProceso> callBack) {

    }

    @Override
    protected Call<RestApiResponse<BERespuesta>> _sendDatos(ApiRetrofit restAPI, GEDatosRubroEvaluacionProceso item) throws Exception {
        return restAPI.fins_GuardarRubroEvaluacionProcesoAndroid(item);
    }

    @Override
    protected <V extends BEVariables> Call<RestApiResponse<GEDatosRubroEvaluacionProceso>> _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return restAPI.fins_ListarRubroEvaluacionProceso(importar);
    }


    @Override
    public void getGEDatosRubroEvaluacionProcesoDatosExportar(List<String> rubroEvaluacionKeyList, ObjectCallBack<GEDatosRubroEvaluacionProceso> callBack) {

    }

    @Override
    public void comprobarCambiosRubroEvaluacion(String rubroEvalProcesoId, final DosObjectCallBack<Long, Long> callBack) {
        try {
            ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
            Call<RestApiResponse<Long>> responseCall = apiRetrofit.fins_ListarRubroEvaluacionProceso_FechaActualizacion(rubroEvalProcesoId);
            responseCall.enqueue(new Callback<RestApiResponse<Long>>() {
                @Override
                public void onResponse(@NonNull Call<RestApiResponse<Long>> call, @NonNull Response<RestApiResponse<Long>> response) {
                    RestApiResponse<Long> respuesta = response.body();
                    if(respuesta == null){
                        callBack.onResponse(false,null,0L);
                        Log.d(TAG,"isSuccessful null ");
                    }
                    else if(respuesta.isSuccessful()){
                        callBack.onResponse(true,respuesta.getValue(),0L);
                        Log.d(TAG,"isSuccessful true "+respuesta.getValue());
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
        }catch (Exception e){
            e.printStackTrace();
            callBack.onResponse(false, 0L, 0L);
        }

    }

    @Override
    public void getDatosImportarPorCalendarioPeriodo(BEVariables beVariables, final ObjectCallBack<GEDatosRubroEvaluacionProceso> callBack) {
        Log.d(TAG,"getDatosImportarPorCalendarioPeriodo: " + beVariables.toString());
        ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
        Call<RestApiResponse<GEDatosRubroEvaluacionProceso>> responseCall = apiRetrofit.fins_ListarRubroEvaluacionProcesoBYCALENDARIOPERIODO (beVariables);
        responseCall.enqueue(new Callback<RestApiResponse<GEDatosRubroEvaluacionProceso>>() {
            @Override
            public void onResponse(@NonNull Call<RestApiResponse<GEDatosRubroEvaluacionProceso>> call, @NonNull Response<RestApiResponse<GEDatosRubroEvaluacionProceso>> response) {
                RestApiResponse<GEDatosRubroEvaluacionProceso> respuesta = response.body();
                if(respuesta == null){
                    callBack.onResponse(false,null);
                    Log.d(TAG,"isSuccessful null ");
                }else if(respuesta.isSuccessful()){
                    callBack.onResponse(true,respuesta.getValue());
                    Log.d(TAG,"isSuccessful true");
                }else {
                    callBack.onResponse(false, null);
                    Log.d(TAG,"isSuccessful false");
                }
            }

            @Override
            public void onFailure(@NonNull Call<RestApiResponse<GEDatosRubroEvaluacionProceso>> call, @NonNull Throwable t) {
                callBack.onResponse(false,null);
                Log.d(TAG,"onFailure");
            }
        });
    }
}
