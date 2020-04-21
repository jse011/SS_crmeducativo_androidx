package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosCargaAcademica.remote;

import androidx.annotation.NonNull;
import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.remote.ServiceRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosCargaAcademica.BEDatosCargaAcademicaDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCargaAcademica;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosCargaAcademicaRemoteDataSource extends ServiceRemoteDataSource<BEDatosCargaAcademica> implements BEDatosCargaAcademicaDataSource {
    private static final String TAG = BEDatosCargaAcademicaRemoteDataSource.class.getSimpleName();

    public BEDatosCargaAcademicaRemoteDataSource(UtilServidor utilServidor) {
        super(utilServidor);
    }

    @Override
    protected Class<BEDatosCargaAcademica> getTableclass() {
        return BEDatosCargaAcademica.class;
    }

    @Override
    protected Call<RestApiResponse<BEDatosCargaAcademica>> _getDatosLogin(ApiRetrofit apiRetrofit, String usuarioId) {
        Log.d(TAG, "Usuario String: " + usuarioId);
        Log.d(TAG, "Usuario Integer: " +Integer.valueOf(usuarioId)+"");
        int int_usuarioId = Integer.valueOf(usuarioId);
        return apiRetrofit.flst_ObtenerCargaAcademica(int_usuarioId);
    }

    @Override
    protected Call<RestApiResponse<BERespuesta>> _sendDatos(ApiRetrofit restAPI, BEDatosCargaAcademica item) throws Exception {
        return null;
    }


    @Override
    protected <V extends BEVariables> Call<RestApiResponse<BEDatosCargaAcademica>> _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return null;
    }


    @Override
    public void getDatosImportarCalendarioPerido(BEVariables importar, final ObjectCallBack<BEDatosCargaAcademica> callBack) {
        ApiRetrofit apiRetrofit = utilServidor.getApiRetrofit();
        Call<RestApiResponse<BEDatosCargaAcademica>> responseCall = apiRetrofit.fins_ListarCalendarioP_PDet_CC_CDet(importar);
        responseCall.enqueue(new Callback<RestApiResponse<BEDatosCargaAcademica>>() {
            @Override
            public void onResponse(@NonNull Call<RestApiResponse<BEDatosCargaAcademica>> call, @NonNull Response<RestApiResponse<BEDatosCargaAcademica>> response) {
                RestApiResponse<BEDatosCargaAcademica> respuesta = response.body();
                if(respuesta == null){
                    callBack.onResponse(true,null);
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
            public void onFailure(@NonNull Call<RestApiResponse<BEDatosCargaAcademica>> call, @NonNull Throwable t) {
                callBack.onResponse(false,null);
                Log.d(TAG,"onFailure");
            }
        });
    }
}
