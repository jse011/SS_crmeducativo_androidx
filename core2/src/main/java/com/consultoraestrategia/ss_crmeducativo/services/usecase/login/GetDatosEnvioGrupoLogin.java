package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioGrupo.BEDatosEnvioGrupoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.CancelCall;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioGrupo;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class GetDatosEnvioGrupoLogin implements UseCaseLoginSincrono<GetDatosEnvioGrupoLogin.RequestValues, GetDatosEnvioGrupoLogin.ResponseValue> {

    private BEDatosEnvioGrupoRepository repository;

    public GetDatosEnvioGrupoLogin(BEDatosEnvioGrupoRepository cargaAcademicaLoginRepository) {
        this.repository = cargaAcademicaLoginRepository;
    }

    @Override
    public RetrofitCancel execute(RequestValues request, final Callback<ResponseValue> callback) {
        return repository.getDatosLogin(request.usuarioId,
                new ServiceDataSource.ObjectCallBack<BEDatosEnvioGrupo>() {
                    @Override
                    public void onResponse(boolean success, BEDatosEnvioGrupo item) {
                        if(success){
                            callback.onResponse(true,new ResponseValue(item));
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
        private BEDatosEnvioGrupo item;

        public ResponseValue(BEDatosEnvioGrupo item) {
            this.item = item;
        }

        public BEDatosEnvioGrupo getItem() {
            return item;
        }
    }
}
