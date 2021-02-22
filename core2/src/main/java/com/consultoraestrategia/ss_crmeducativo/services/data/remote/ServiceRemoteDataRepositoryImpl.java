package com.consultoraestrategia.ss_crmeducativo.services.data.remote;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEGuardarEntidadesGlobal;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Jse on 30/12/2018.
 */

public class ServiceRemoteDataRepositoryImpl implements ServiceRemoteDataRepository {

    private static final  String TAG = ServiceRemoteDataRepositoryImpl.class.getSimpleName();
    private UtilServidor utilServidor;

    public ServiceRemoteDataRepositoryImpl(UtilServidor utilServidor) {
        this.utilServidor = utilServidor;
    }

    @Override
    public void SendDatosGlobalSimple(BEGuardarEntidadesGlobal item, RespuestaCallBack<BEGuardarEntidadesGlobal, BERespuesta> callBack) {
        ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
        apiRetrofit.changeSetTime(10,60,60, TimeUnit.SECONDS);
        Call<RestApiResponse<BERespuesta>> responseCall = apiRetrofit.fins_GuardarEntidadesGlobalSimplevJse(item);
        try {

            Response<RestApiResponse<BERespuesta>> response = responseCall.execute();
            if (!response.isSuccessful()){
                callBack.onResponse(false,item,null);
                Log.d(TAG, "SendDatos Response: false");
            }else {
                RestApiResponse<BERespuesta> body = response.body();
                if(body == null){
                    callBack.onResponse(false,item,null);
                    Log.d(TAG, "SendDatos Successful body null ");
                } else if(body.isSuccessful()){
                    callBack.onResponse(true,item,body.getValue());
                    Log.d(TAG, "SendDatos Successful : true");
                }else {
                    callBack.onResponse(false,item, body.getValue());
                    Log.d(TAG, "SendDatos Successful : false");
                }
            }

        }catch (Throwable t){
            t.printStackTrace();
            Log.d(TAG, "getDatosLogin Throwable : false - "+t.getMessage());
            callBack.onResponse(false,item,null);
        }
    }

    @Override
    public void SendDatosGlobalUpdateContratoAlumno(BEGuardarEntidadesGlobal item, RespuestaCallBack<BEGuardarEntidadesGlobal, BERespuesta> callBack) {
        ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
        apiRetrofit.changeSetTime(10,60,60, TimeUnit.SECONDS);
        Call<RestApiResponse<BERespuesta>> responseCall = apiRetrofit.fins_GuardarEntidades_Global_Save_Alumnos(item);
        try {

            Response<RestApiResponse<BERespuesta>> response = responseCall.execute();
            if (!response.isSuccessful()){
                callBack.onResponse(false,item,null);
                Log.d(TAG, "SendDatos Response: false");
            }else {
                RestApiResponse<BERespuesta> body = response.body();
                if(body == null){
                    callBack.onResponse(false,item,null);
                    Log.d(TAG, "SendDatos Successful body null ");
                } else if(body.isSuccessful()){
                    callBack.onResponse(true,item,body.getValue());
                    Log.d(TAG, "SendDatos Successful : true");
                }else {
                    callBack.onResponse(false,item, body.getValue());
                    Log.d(TAG, "SendDatos Successful : false");
                }
            }


        }catch (Throwable t){
            t.printStackTrace();
            Log.d(TAG, "getDatosLogin Throwable : false - "+t.getMessage());
            callBack.onResponse(false,item,null);
        }
    }


}
