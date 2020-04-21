package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.obtenerRubroEvaluacionProceso.remote;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.remote.ServiceRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.obtenerRubroEvaluacionProceso.listener.DatosRubroProcesoListener;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosRubroEvaluacionProceso;

import retrofit2.Call;

/**
 * Created by kike on 07/06/2018.
 */

public class BEObtenerDatosRubroProcesoRemote extends ServiceRemoteDataSource<BEDatosRubroEvaluacionProceso> implements DatosRubroProcesoListener {

    public static final String TAG = BEObtenerDatosRubroProcesoRemote.class.getSimpleName();

    public BEObtenerDatosRubroProcesoRemote(UtilServidor utilServidor) {
        super(utilServidor);
    }

    @Override
    protected Class<BEDatosRubroEvaluacionProceso> getTableclass() {
        return BEDatosRubroEvaluacionProceso.class;
    }

    @Override
    protected Call<RestApiResponse<BEDatosRubroEvaluacionProceso>> _getDatosLogin(ApiRetrofit apiRetrofit, String usuarioId) {
        return null;
    }

    @Override
    protected Call<RestApiResponse<BERespuesta>> _sendDatos(ApiRetrofit restAPI, BEDatosRubroEvaluacionProceso item) throws Exception {
        return null;
    }

    @Override
    protected <V extends BEVariables> Call<RestApiResponse<BEDatosRubroEvaluacionProceso>> _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return null;
    }


    @Override
    public void getDatosRubroProces(final int usuarioId, final int calendarioPeriod, final int cargaCurso, DatosRubroProcesoListener.ObjectCallBack objectCallBack) {
        /*BEDatosRubroEvaluacionProceso beDatosRubroEvaluacionProceso;
        RestAPI restAPI = utilServidor.getRestAPI();
        ObjectMapper mapper = utilServidor.getMapper();
        try {
            JSONObject jsonObject = _getDatosLogin(restAPI, "");//
            if (Utils.isSuccess(jsonObject)) {
                String res = jsonObject.optString("Value");
                beDatosRubroEvaluacionProceso  = mapper.readValue(res, getTableclass());
                if (beDatosRubroEvaluacionProceso != null) {
                    Log.d(TAG, "jsonObject: " + true);
                    objectCallBack.onResponse(true, beDatosRubroEvaluacionProceso);
                } else {
                    Log.d(TAG, "jsonObject: " + false);
                    objectCallBack.onResponse(false, null);
                }

            } else {
                Log.d(TAG, "isSuccess: " + jsonObject.toString());
                objectCallBack.onResponse(false, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            objectCallBack.onResponse(false, null);
            Log.d(TAG, "Exception: " + e);
            e.printStackTrace();
        }*/

    }
}


