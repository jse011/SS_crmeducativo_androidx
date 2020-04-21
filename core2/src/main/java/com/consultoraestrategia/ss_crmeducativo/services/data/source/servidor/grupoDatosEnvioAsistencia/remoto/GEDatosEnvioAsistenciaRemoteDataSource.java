package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosEnvioAsistencia.remoto;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.remote.ServiceRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.grupoDatosEnvioAsistencia.GEDatosEnvioAsistenciaDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosEnvioAsistencia;

import retrofit2.Call;

/**
 * Created by SCIEV on 25/05/2018.
 */

public class GEDatosEnvioAsistenciaRemoteDataSource extends ServiceRemoteDataSource<GEDatosEnvioAsistencia> implements GEDatosEnvioAsistenciaDataSource {
    public GEDatosEnvioAsistenciaRemoteDataSource(UtilServidor utilServidor) {
        super(utilServidor);
    }

    @Override
    protected Class<GEDatosEnvioAsistencia> getTableclass() {
        return GEDatosEnvioAsistencia.class;
    }

    @Override
    protected Call<RestApiResponse<GEDatosEnvioAsistencia>> _getDatosLogin(ApiRetrofit apiRetrofit, String usuarioId) {
        return null;
    }

    @Override
    protected Call<RestApiResponse<BERespuesta>> _sendDatos(ApiRetrofit restAPI, GEDatosEnvioAsistencia item) throws Exception {
        return restAPI.fins_GuardarAsistencia_Android(item);
    }

    @Override
    protected <V extends BEVariables> Call<RestApiResponse<GEDatosEnvioAsistencia>> _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return restAPI.fins_ListarEnvioAsistencia(importar);
    }

    /*
    @Override
    protected JSONObject _sendDatos(ApiRetrofit restAPI, GEDatosEnvioAsistencia item) throws Exception {
        Log.d(TAG, item.toString());
        return restAPI.fins_GuardarAsistencia_Android(item);
    }

    @Override
    protected <V extends BEVariables> JSONObject _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return restAPI.fins_ListarEnvioAsistencia(importar);
    }*/

}
