package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.obtenerDatosLogin.BEObtenerDatosLoginRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEObtenerDatosLogin;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class GetObtenerDatosLogin implements UseCaseLoginSincrono<GetObtenerDatosLogin.RequestValues, GetObtenerDatosLogin.ResponseValue> {

    private BEObtenerDatosLoginRepository repository;

    public GetObtenerDatosLogin(BEObtenerDatosLoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public RetrofitCancel execute(RequestValues request, final Callback<ResponseValue> callback) {
        return repository.getDatosLogin(request.usuarioId,
                new ServiceDataSource.ObjectCallBack<BEObtenerDatosLogin>() {
                    @Override
                    public void onResponse(boolean success, BEObtenerDatosLogin item) {
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

    public class ResponseValue{
        private BEObtenerDatosLogin item;

        public ResponseValue(BEObtenerDatosLogin item) {
            this.item = item;
        }

        public BEObtenerDatosLogin getItem() {
            return item;
        }
    }
}
