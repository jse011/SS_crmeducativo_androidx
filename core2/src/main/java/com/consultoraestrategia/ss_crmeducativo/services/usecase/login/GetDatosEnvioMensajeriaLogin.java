package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioMensajeria.BEDatosEnvioMensajeriaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.CancelCall;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioMensajeria;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class GetDatosEnvioMensajeriaLogin implements UseCaseLoginSincrono<GetDatosEnvioMensajeriaLogin.RequestValues, GetDatosEnvioMensajeriaLogin.ResponseValue> {

    private BEDatosEnvioMensajeriaRepository repository;

    public GetDatosEnvioMensajeriaLogin(BEDatosEnvioMensajeriaRepository cargaAcademicaLoginRepository) {
        this.repository = cargaAcademicaLoginRepository;
    }

    @Override
    public RetrofitCancel execute(RequestValues request, final Callback<ResponseValue> callback) {

        return repository.getDatosLogin(request.usuarioId,
                new ServiceDataSource.ObjectCallBack<BEDatosEnvioMensajeria>() {
                    @Override
                    public void onResponse(boolean success, BEDatosEnvioMensajeria item) {
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
        private BEDatosEnvioMensajeria item;

        public ResponseValue(BEDatosEnvioMensajeria item) {
            this.item = item;
        }

        public BEDatosEnvioMensajeria getItem() {
            return item;
        }
    }
}
