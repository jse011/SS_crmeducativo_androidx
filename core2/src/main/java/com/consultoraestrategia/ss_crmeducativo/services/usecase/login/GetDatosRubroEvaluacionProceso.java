package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.CancelCall;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosRubroEvaluacionProceso.BEDatosRubroEvaluacionProcesoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosRubroEvaluacionProceso;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class GetDatosRubroEvaluacionProceso implements UseCaseLoginSincrono<GetDatosRubroEvaluacionProceso.RequestValues, GetDatosRubroEvaluacionProceso.ResponseValue> {

    private BEDatosRubroEvaluacionProcesoRepository repository;

    public GetDatosRubroEvaluacionProceso(BEDatosRubroEvaluacionProcesoRepository repository) {
        this.repository = repository;
    }

    @Override
    public RetrofitCancel execute(RequestValues request, final Callback<ResponseValue> callback) {
        return repository.getDatosLogin(request.usuarioId,
                new ServiceDataSource.ObjectCallBack<BEDatosRubroEvaluacionProceso>() {
                    @Override
                    public void onResponse(boolean success, BEDatosRubroEvaluacionProceso item) {
                        if(success){
                            callback.onResponse(true, new ResponseValue(item));
                        }else {
                            callback.onResponse(false,null);
                        }
                    }
                });

    }

    public static class RequestValues{
        private String usuarioId;

        public RequestValues(String usuarioId) {
            this.usuarioId = usuarioId;
        }

        public String getUsuarioId() {
            return usuarioId;
        }
    }

    public class ResponseValue {
        private BEDatosRubroEvaluacionProceso item;

        public ResponseValue(BEDatosRubroEvaluacionProceso item) {
            this.item = item;
        }

        public BEDatosRubroEvaluacionProceso getItem() {
            return item;
        }
    }
}
