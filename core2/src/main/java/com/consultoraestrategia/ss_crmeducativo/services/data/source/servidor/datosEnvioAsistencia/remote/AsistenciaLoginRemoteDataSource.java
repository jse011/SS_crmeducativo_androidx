package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioAsistencia.remote;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioAsistencia.BEDatosEnvioAsistenciaDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.remote.ServiceRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioAsistencia;

import retrofit2.Call;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class AsistenciaLoginRemoteDataSource extends ServiceRemoteDataSource<BEDatosEnvioAsistencia> implements BEDatosEnvioAsistenciaDataSource {

    public AsistenciaLoginRemoteDataSource(UtilServidor utilServidor) {
        super(utilServidor);
    }

    @Override
    protected Class<BEDatosEnvioAsistencia> getTableclass() {
        return BEDatosEnvioAsistencia.class;
    }

    @Override
    protected Call<RestApiResponse<BEDatosEnvioAsistencia>> _getDatosLogin(ApiRetrofit apiRetrofit, String usuarioId)  {
        int int_usuarioId = Integer.valueOf(usuarioId);
        return apiRetrofit.flst_ObtenerAsistencia(int_usuarioId);
    }

    @Override
    protected Call<RestApiResponse<BERespuesta>> _sendDatos(ApiRetrofit restAPI, BEDatosEnvioAsistencia item) throws Exception {
        return null;
    }

    @Override
    protected <V extends BEVariables> Call<RestApiResponse<BEDatosEnvioAsistencia>> _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return null;
    }

    /*@Override
    protected JSONObject _sendDatos(ApiRetrofit restAPI, BEDatosEnvioAsistencia item) {
        return null;
    }

    @Override
    protected <V extends BEVariables> JSONObject _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return restAPI.fins_ListarEnvioAsistencia(importar);
    }*/
}
