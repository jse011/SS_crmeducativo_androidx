package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.servidor.datosEvaluacionResultado.BEDatosEvaluacionResultadoRepository;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.CancelCall;
import com.consultoraestrategia.ss_crmeducativo.services.entidad.servidor.BEDatosEvaluacionResultado;

/**
 * Created by SCIEV on 16/05/2018.
 */

public class GetDatosEvaluacionResultadoLogin extends UseCase<GetDatosEvaluacionResultadoLogin.RequestValues, GetDatosEvaluacionResultadoLogin.ResponseValue>  {

    private BEDatosEvaluacionResultadoRepository repository;

    public GetDatosEvaluacionResultadoLogin(BEDatosEvaluacionResultadoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

        repository.getDatosLogin(requestValues.usuarioId,
                new ServiceDataSource.ObjectCallBack<BEDatosEvaluacionResultado>() {
                    @Override
                    public void onResponse(boolean success, BEDatosEvaluacionResultado item) {
                        if(success){
                            getUseCaseCallback().onSuccess(new ResponseValue(item));
                        }else {
                            getUseCaseCallback().onError();
                        }
                    }
                });

    }

    public static class RequestValues implements UseCase.RequestValues{
        private String usuarioId;

        public RequestValues(String usuarioId) {
            this.usuarioId = usuarioId;
        }

        public String getUsuarioId() {
            return usuarioId;
        }
    }

    public class ResponseValue implements UseCase.ResponseValue{
        private BEDatosEvaluacionResultado item;

        public ResponseValue(BEDatosEvaluacionResultado item) {
            this.item = item;
        }

        public BEDatosEvaluacionResultado getItem() {
            return item;
        }
    }
}
