package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosCargaAcademica.BEDatosCargaAcademicaRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.CancelCall;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosCargaAcademica;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class GetDatosCargaAcademicaLogin implements UseCaseLoginSincrono<GetDatosCargaAcademicaLogin.RequestValues, GetDatosCargaAcademicaLogin.ResponseValue> {

    private BEDatosCargaAcademicaRepository cargaAcademicaLoginRepository;

    public GetDatosCargaAcademicaLogin(BEDatosCargaAcademicaRepository cargaAcademicaLoginRepository) {
        this.cargaAcademicaLoginRepository = cargaAcademicaLoginRepository;
    }



    @Override
    public RetrofitCancel execute(RequestValues request, final Callback<ResponseValue> callback) {
        return cargaAcademicaLoginRepository.getDatosLogin(request.getUsuarioId(),
                new ServiceDataSource.ObjectCallBack<BEDatosCargaAcademica>() {
                    @Override
                    public void onResponse(boolean success, BEDatosCargaAcademica item) {
                        if(success){
                            callback.onResponse(true,new ResponseValue(item));
                        }else {
                            callback.onResponse(false,null);
                        }
                    }
                });
    }


    public static class RequestValues implements UseCase.RequestValues{
        private String usuarioId;
        private CancelCall cancelCall;

        public RequestValues(String usuarioId) {
            this.usuarioId = usuarioId;
        }

        public String getUsuarioId() {
            return usuarioId;
        }
    }

    public class ResponseValue implements UseCase.ResponseValue{
        private BEDatosCargaAcademica item;

        public ResponseValue(BEDatosCargaAcademica item) {
            this.item = item;
        }

        public BEDatosCargaAcademica getItem() {
            return item;
        }
    }
}
