package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.obtenerDatosLogin.remote;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.remote.ServiceRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEObtenerDatosLogin;

import retrofit2.Call;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEObtenerDatosLoginRemoteDataSource extends ServiceRemoteDataSource<BEObtenerDatosLogin> {

    public BEObtenerDatosLoginRemoteDataSource(UtilServidor utilServidor) {
        super(utilServidor);
    }

    @Override
    protected Class<BEObtenerDatosLogin> getTableclass() {
        return BEObtenerDatosLogin.class;
    }

    @Override
    protected Call<RestApiResponse<BEObtenerDatosLogin>> _getDatosLogin(ApiRetrofit apiRetrofit, String usuarioId) {
        int int_usuarioId = Integer.valueOf(usuarioId);
        return apiRetrofit.flst_ObtenerDatosLogin(int_usuarioId);
    }

    @Override
    protected Call<RestApiResponse<BERespuesta>> _sendDatos(ApiRetrofit restAPI, BEObtenerDatosLogin item) throws Exception {
        return null;
    }

    @Override
    protected <V extends BEVariables> Call<RestApiResponse<BEObtenerDatosLogin>> _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return restAPI.flst_Obtenerlistado_Personas(importar.getUsuarioId());
    }

   /* @Override
    protected <V extends BEVariables> JSONObject _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return restAPI.flst_Obtenerlistado_Personas(importar.getUsuarioId());
    }*/
}
