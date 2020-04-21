package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEnvioAsistencia.BEDatosEnvioAsistenciaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.CancelCall;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEnvioAsistencia;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class GetDatosEnvioAsistenciaLogin implements UseCaseLoginSincrono<GetDatosEnvioAsistenciaLogin.RequestValues, GetDatosEnvioAsistenciaLogin.ResponseValue> {

    private BEDatosEnvioAsistenciaRepository cargaAcademicaLoginRepository;

    public GetDatosEnvioAsistenciaLogin(BEDatosEnvioAsistenciaRepository cargaAcademicaLoginRepository) {
        this.cargaAcademicaLoginRepository = cargaAcademicaLoginRepository;
    }


    @Override
    public RetrofitCancel execute(RequestValues request, final Callback<ResponseValue> callback) {
        return cargaAcademicaLoginRepository.getDatosLogin(request.usuarioId,
                new ServiceDataSource.ObjectCallBack<BEDatosEnvioAsistencia>() {
                    @Override
                    public void onResponse(boolean success, BEDatosEnvioAsistencia item) {
                        if(success){
                            callback.onResponse(true, new ResponseValue(item));
                        }else {
                            callback.onResponse(false, null);
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

        public void setUsuarioId(String usuarioId) {
            this.usuarioId = usuarioId;
        }

    }

    public class ResponseValue {
        private BEDatosEnvioAsistencia item;

        public ResponseValue(BEDatosEnvioAsistencia item) {
            this.item = item;
        }

        public BEDatosEnvioAsistencia getItem() {
            return item;
        }
    }
}
