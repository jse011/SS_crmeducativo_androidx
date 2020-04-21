package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSilaboEventoEnvio.remote;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.remote.ServiceRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosSilaboEventoEnvio;

import retrofit2.Call;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosSilaboEventoEnvioRemoteDataSource extends ServiceRemoteDataSource<GEDatosSilaboEventoEnvio> {

    public BEDatosSilaboEventoEnvioRemoteDataSource(UtilServidor utilServidor) {
        super(utilServidor);
    }

    @Override
    protected Class<GEDatosSilaboEventoEnvio> getTableclass() {
        return GEDatosSilaboEventoEnvio.class;
    }

    @Override
    protected Call<RestApiResponse<GEDatosSilaboEventoEnvio>> _getDatosLogin(ApiRetrofit apiRetrofit, String usuarioId)  {
        int int_usuarioId = Integer.valueOf(usuarioId);
        return apiRetrofit.flst_ObtenerSilaboEvento(int_usuarioId);
    }

    @Override
    protected Call<RestApiResponse<BERespuesta>> _sendDatos(ApiRetrofit restAPI, GEDatosSilaboEventoEnvio item) throws Exception {
        return null;
    }

    @Override
    protected <V extends BEVariables> Call<RestApiResponse<GEDatosSilaboEventoEnvio>> _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return null;
    }

}
