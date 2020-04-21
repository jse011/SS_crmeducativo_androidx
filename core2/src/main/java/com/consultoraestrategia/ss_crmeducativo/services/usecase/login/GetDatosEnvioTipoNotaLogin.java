package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioTipoNota.BEDatosEnvioTipoNotaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.CancelCall;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioTipoNota;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class GetDatosEnvioTipoNotaLogin implements UseCaseLoginSincrono<GetDatosEnvioTipoNotaLogin.RequestValues, GetDatosEnvioTipoNotaLogin.ResponseValue> {

    private BEDatosEnvioTipoNotaRepository repository;

    public GetDatosEnvioTipoNotaLogin(BEDatosEnvioTipoNotaRepository repository) {
        this.repository = repository;
    }

    @Override
    public RetrofitCancel execute(RequestValues request, final Callback<ResponseValue> callback) {

        return repository.getDatosLogin(request.usuarioId,
                new ServiceDataSource.ObjectCallBack<BEDatosEnvioTipoNota>() {
                    @Override
                    public void onResponse(boolean success, BEDatosEnvioTipoNota item) {
                        if(success){
                            callback.onResponse(true, new ResponseValue(item));
                        }else {
                            callback.onResponse(false,null);
                        }
                    }
                });

    }

    public static class RequestValues {
        private String usuarioId;

        public RequestValues(String usuarioId) {
            this.usuarioId = usuarioId;
        }

        public String getUsuarioId() {
            return usuarioId;
        }
    }

    public class ResponseValue {
        private BEDatosEnvioTipoNota item;

        public ResponseValue(BEDatosEnvioTipoNota item) {
            this.item = item;
        }

        public BEDatosEnvioTipoNota getItem() {
            return item;
        }
    }
}
