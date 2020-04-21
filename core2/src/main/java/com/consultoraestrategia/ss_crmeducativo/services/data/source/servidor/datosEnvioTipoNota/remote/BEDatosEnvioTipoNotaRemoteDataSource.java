package com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioTipoNota.remote;

import com.consultoraestrategia.ss_crmeducativo.api.retrofit.response.RestApiResponse;
import com.consultoraestrategia.ss_crmeducativo.api.retrofit.ApiRetrofit;
import com.consultoraestrategia.ss_crmeducativo.entities.AsistenciaSesionAlumnoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.remote.ServiceRemoteDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.util.UtilServidor;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioTipoNota.BEDatosEnvioTipoNotaDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.request.BEVariables;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.response.BERespuesta;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioTipoNota;

import java.util.List;

import retrofit2.Call;

/**
 * Created by SCIEV on 17/05/2018.
 */

public class BEDatosEnvioTipoNotaRemoteDataSource extends ServiceRemoteDataSource<BEDatosEnvioTipoNota> implements BEDatosEnvioTipoNotaDataSource {

    public BEDatosEnvioTipoNotaRemoteDataSource(UtilServidor utilServidor) {
        super(utilServidor);
    }

    @Override
    protected Class<BEDatosEnvioTipoNota> getTableclass() {
        return BEDatosEnvioTipoNota.class;
    }

    @Override
    protected Call<RestApiResponse<BEDatosEnvioTipoNota>> _getDatosLogin(ApiRetrofit apiRetrofit, String usuarioId) {
        int int_usuarioId = Integer.valueOf(usuarioId);
        return apiRetrofit.flst_ObtenerTipoNota(int_usuarioId);
    }

    @Override
    protected Call<RestApiResponse<BERespuesta>> _sendDatos(ApiRetrofit restAPI, BEDatosEnvioTipoNota item) throws Exception {
        return null;
    }

    @Override
    protected <V extends BEVariables> Call<RestApiResponse<BEDatosEnvioTipoNota>> _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return restAPI.fins_ListarTipoNotas(importar);
    }


    /*@Override
    protected <V extends BEVariables> JSONObject _getDatosImportar(ApiRetrofit restAPI, V importar) throws Exception {
        return restAPI.fins_ListarTipoNotas(importar);
    }*/

    @Override
    public void getDatosExportarRelRubroEvalProceso(List<RubroEvaluacionProcesoC> rubroEvaluacionProcesoList, ObjectCallBack<BEDatosEnvioTipoNota> callBack) {

    }

    @Override
    public void getDatosExportarRelAsistenciaAlumnos(List<AsistenciaSesionAlumnoC> asistenciaAlumnos, ObjectCallBack<BEDatosEnvioTipoNota> objectCallBack) {

    }
}
