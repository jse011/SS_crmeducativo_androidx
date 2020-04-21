package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioMensajeria.remote;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.remote.ServiceRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioMensajeria.BEDatosEnvioMensajeriaDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioMensajeria;


import retrofit2.Call;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosEnvioMensajeriaRemoteDataSource extends ServiceRemoteDataSource<BEDatosEnvioMensajeria> implements BEDatosEnvioMensajeriaDataSource {


    public BEDatosEnvioMensajeriaRemoteDataSource(UtilServidor utilServidor) {
        super(utilServidor);
    }

    @Override
    protected Class<BEDatosEnvioMensajeria> getTableclass() {
        return BEDatosEnvioMensajeria.class;
    }

    @Override
    protected Call<RestApiResponse<BEDatosEnvioMensajeria>> _getDatosLogin(ApiRetrofit apiRetrofit, String usuarioId){
        int int_usuarioId = Integer.valueOf(usuarioId);
        return apiRetrofit.flst_ObtenerMensajeria(int_usuarioId);
    }

    @Override
    protected Call<RestApiResponse<BERespuesta>> _sendDatos(ApiRetrofit restAPI, BEDatosEnvioMensajeria item) throws Exception {

        return restAPI.fins_GuardarMensajeria(item);
    }

    @Override
    protected <V extends BEVariables> Call<RestApiResponse<BEDatosEnvioMensajeria>> _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return null;
    }

    @Override
    public void getDatosExportarMesajeria(ObjectCallBack<BEDatosEnvioMensajeria> callBack) {

    }


    /*@Override
    protected JSONObject _sendDatos(ApiRetrofit restAPI, BEDatosEnvioMensajeria item) throws Exception {
        return restAPI.fins_GuardarMensajesProcesoAndroid(item);
    }*/


}
