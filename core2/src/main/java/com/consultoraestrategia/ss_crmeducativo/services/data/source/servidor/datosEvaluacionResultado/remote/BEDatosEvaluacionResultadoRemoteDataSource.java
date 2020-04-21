package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEvaluacionResultado.remote;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.remote.ServiceRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEvaluacionResultado;

import retrofit2.Call;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosEvaluacionResultadoRemoteDataSource extends ServiceRemoteDataSource<BEDatosEvaluacionResultado> {

    public BEDatosEvaluacionResultadoRemoteDataSource(UtilServidor utilServidor) {
        super(utilServidor);
    }

    @Override
    protected Class<BEDatosEvaluacionResultado> getTableclass() {
        return BEDatosEvaluacionResultado.class;
    }

    @Override
    protected Call<RestApiResponse<BEDatosEvaluacionResultado>> _getDatosLogin(ApiRetrofit apiRetrofit, String usuarioId){
        int int_usuarioId = Integer.valueOf(usuarioId);
        return apiRetrofit.flst_ObtenerRubroEvaluacionResultado(int_usuarioId);
    }

    @Override
    protected Call<RestApiResponse<BERespuesta>> _sendDatos(ApiRetrofit restAPI, BEDatosEvaluacionResultado item) throws Exception {
        return null;
    }

    @Override
    protected <V extends BEVariables> Call<RestApiResponse<BEDatosEvaluacionResultado>> _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return restAPI.fins_ListarRubroEvaluacionResultado(importar);
    }

    /*@Override
    protected <V extends BEVariables> JSONObject _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return restAPI.fins_ListarRubroEvaluacionResultado(importar);
    }*/
}
