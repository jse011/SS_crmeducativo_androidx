package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioHorario.BEDatosEnvioHorarioRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.CancelCall;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioHorario;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class GetDatosEnvioHorarioLogin implements UseCaseLoginSincrono<GetDatosEnvioHorarioLogin.RequestValues, GetDatosEnvioHorarioLogin.ResponseValue> {

    private BEDatosEnvioHorarioRepository repository;

    public GetDatosEnvioHorarioLogin(BEDatosEnvioHorarioRepository cargaAcademicaLoginRepository) {
        this.repository = cargaAcademicaLoginRepository;
    }



    @Override
    public RetrofitCancel execute(RequestValues request, final Callback<ResponseValue> callback) {
        return repository.getDatosLogin(request.usuarioId,
                new ServiceDataSource.ObjectCallBack<BEDatosEnvioHorario>() {
                    @Override
                    public void onResponse(boolean success, BEDatosEnvioHorario item) {
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
        private BEDatosEnvioHorario item;

        public ResponseValue(BEDatosEnvioHorario item) {
            this.item = item;
        }

        public BEDatosEnvioHorario getItem() {
            return item;
        }
    }
}
