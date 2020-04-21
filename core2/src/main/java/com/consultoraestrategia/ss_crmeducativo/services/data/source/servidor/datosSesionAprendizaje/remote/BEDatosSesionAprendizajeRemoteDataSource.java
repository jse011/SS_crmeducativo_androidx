package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSesionAprendizaje.remote;

import androidx.annotation.NonNull;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.remote.ServiceRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSesionAprendizaje.BEDatosSesionAprendizajeDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosSesionAprendizaje;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosSesionAprendizajeRemoteDataSource extends ServiceRemoteDataSource<BEDatosSesionAprendizaje> implements BEDatosSesionAprendizajeDataSource {

    public BEDatosSesionAprendizajeRemoteDataSource(UtilServidor utilServidor) {
        super(utilServidor);
    }

    @Override
    protected Class<BEDatosSesionAprendizaje> getTableclass() {
        return BEDatosSesionAprendizaje.class;
    }

    @Override
    protected Call<RestApiResponse<BEDatosSesionAprendizaje>> _getDatosLogin(ApiRetrofit apiRetrofit, String usuarioId) {
        return null;
    }

    @Override
    protected Call<RestApiResponse<BERespuesta>> _sendDatos(ApiRetrofit restAPI, BEDatosSesionAprendizaje item) throws Exception {
        return restAPI.fins_GuardarSesion_Android(item);
    }

    @Override
    protected <V extends BEVariables> Call<RestApiResponse<BEDatosSesionAprendizaje>> _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return restAPI.fins_ListarSesiones(importar);
    }

    /*@Override
    protected JSONObject _sendDatos(ApiRetrofit restAPI, BEDatosSesionAprendizaje item) throws Exception {
        return restAPI.fins_GuardarSesion_Android(item);
    }

    @Override
    protected <V extends BEVariables> JSONObject _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return restAPI.fins_ListarSesiones(importar);
    }*/


    @Override
    public void comprobarCambiosSesionAprendizaje(int sesionAprendizajeId, final DosObjectCallBack<Long, Long> dosObjectCallBack) {
        try {
            ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
            Call<RestApiResponse<Long>> responseCall = apiRetrofit.fins_ListarSesion_FechaActualizacion(sesionAprendizajeId);
            responseCall.enqueue(new Callback<RestApiResponse<Long>>() {
                @Override
                public void onResponse(@NonNull Call<RestApiResponse<Long>> call, @NonNull Response<RestApiResponse<Long>> response) {
                    RestApiResponse<Long> respuesta = response.body();
                    if(respuesta==null){
                        dosObjectCallBack.onResponse(false,0L,0L);
                        Log.d(TAG,"isSuccessful null");
                    }
                    else if(respuesta.isSuccessful()){
                        dosObjectCallBack.onResponse(true,respuesta.getValue(),0L);
                        Log.d(TAG,"isSuccessful true");
                    }else {
                        dosObjectCallBack.onResponse(false, 0L,0L);
                        Log.d(TAG,"isSuccessful false");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<RestApiResponse<Long>> call, @NonNull Throwable t) {
                    dosObjectCallBack.onResponse(false,0L,0L);
                    Log.d(TAG,"onFailure");
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            dosObjectCallBack.onResponse(false,0L,0L);
        }

    }

    @Override
    public void getDatosImportarListSesionPorUnidades(BEVariables importar, final ObjectCallBack<BEDatosSesionAprendizaje> callBack) {
        try {
            ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
            Call<RestApiResponse<BEDatosSesionAprendizaje>> responseCall = apiRetrofit.flst_ObtenerSesionByUnidad(importar);
            responseCall.enqueue(new Callback<RestApiResponse<BEDatosSesionAprendizaje>>() {
                @Override
                public void onResponse(@NonNull Call<RestApiResponse<BEDatosSesionAprendizaje>> call, @NonNull Response<RestApiResponse<BEDatosSesionAprendizaje>> response) {
                    RestApiResponse<BEDatosSesionAprendizaje> respuesta = response.body();
                    if(respuesta==null){
                        callBack.onResponse(false, null);
                        Log.d(TAG,"isSuccessful null");
                    }
                    else if(respuesta.isSuccessful()){
                        callBack.onResponse(true,respuesta.getValue());
                        Log.d(TAG,"isSuccessful true");
                    }else {
                        callBack.onResponse(false, null);
                        Log.d(TAG,"isSuccessful false");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<RestApiResponse<BEDatosSesionAprendizaje>> call, @NonNull Throwable t) {
                    callBack.onResponse(false,null);
                    Log.d(TAG,"onFailure");
                }
            });
        }catch (Exception e){
            callBack.onResponse(false,null);
            e.printStackTrace();
        }

    }
}
