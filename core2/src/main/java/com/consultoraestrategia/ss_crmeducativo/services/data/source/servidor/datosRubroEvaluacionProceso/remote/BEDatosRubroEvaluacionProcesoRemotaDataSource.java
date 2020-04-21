package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso.remote;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.remote.ServiceRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso.BEDatosRubroEvaluacionProcesoDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosRubroEvaluacionProceso;

import java.util.List;

import retrofit2.Call;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosRubroEvaluacionProcesoRemotaDataSource extends ServiceRemoteDataSource<BEDatosRubroEvaluacionProceso> implements BEDatosRubroEvaluacionProcesoDataSource {

    public BEDatosRubroEvaluacionProcesoRemotaDataSource(UtilServidor utilServidor) {
        super(utilServidor);
    }

    @Override
    protected Class<BEDatosRubroEvaluacionProceso> getTableclass() {
        return BEDatosRubroEvaluacionProceso.class;
    }

    @Override
    protected Call<RestApiResponse<BEDatosRubroEvaluacionProceso>> _getDatosLogin(ApiRetrofit apiRetrofit, String usuarioId) {
        int int_usuarioId = Integer.valueOf(usuarioId);
        //apiRetrofit.setTime(30, TimeUnit.SECONDS);
        return apiRetrofit.flst_ObtenerRubroEvaluacionProceso(int_usuarioId);
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
    public void getSimpleDatosExportar(ObjectCallBack<BEDatosRubroEvaluacionProceso> callBack) {

    }

    @Override
    public void getRubroEvalDatosExportar(List<String> rubroEvalIdList, ObjectCallBack<BEDatosRubroEvaluacionProceso> callBack) {

    }

    @Override
    public void onUpdateEvaluacionFormula(SuccessCallBack callBack) {

    }

}
