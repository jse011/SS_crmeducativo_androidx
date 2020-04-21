package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioHorario.remote;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.remote.ServiceRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioHorario;

import retrofit2.Call;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosEnvioHorarioRemoteDataSource extends ServiceRemoteDataSource<BEDatosEnvioHorario> {

    public BEDatosEnvioHorarioRemoteDataSource(UtilServidor utilServidor) {
        super(utilServidor);
    }

    @Override
    protected Class<BEDatosEnvioHorario> getTableclass() {
        return BEDatosEnvioHorario.class;
    }

    @Override
    protected Call<RestApiResponse<BEDatosEnvioHorario>> _getDatosLogin(ApiRetrofit apiRetrofit, String usuarioId) {
        int int_usuarioId = Integer.valueOf(usuarioId);
        return apiRetrofit.flst_ObtenerHorario(int_usuarioId);
    }

    @Override
    protected Call<RestApiResponse<BERespuesta>> _sendDatos(ApiRetrofit restAPI, BEDatosEnvioHorario item) throws Exception {
        return null;
    }

    @Override
    protected <V extends BEVariables> Call<RestApiResponse<BEDatosEnvioHorario>> _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return null;
    }


}
