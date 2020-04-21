package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosSilaboEventoEnvio.BEDatosSilaboEventoEnvioRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.CancelCall;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.GEDatosSilaboEventoEnvio;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class GetDatosSilaboEventoEnvio implements UseCaseLoginSincrono<GetDatosSilaboEventoEnvio.RequestValues, GetDatosSilaboEventoEnvio.ResponseValue> {

    private BEDatosSilaboEventoEnvioRepository repository;

    public GetDatosSilaboEventoEnvio(BEDatosSilaboEventoEnvioRepository repository) {
        this.repository = repository;
    }

    @Override
    public RetrofitCancel execute(RequestValues request, final Callback<ResponseValue> callback) {

        return repository.getDatosLogin(request.usuarioId,
                new ServiceDataSource.ObjectCallBack<GEDatosSilaboEventoEnvio>() {
                    @Override
                    public void onResponse(boolean success, GEDatosSilaboEventoEnvio item) {
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

    public class ResponseValue{
        private GEDatosSilaboEventoEnvio item;

        public ResponseValue(GEDatosSilaboEventoEnvio item) {
            this.item = item;
        }

        public GEDatosSilaboEventoEnvio getItem() {
            return item;
        }
    }
}
