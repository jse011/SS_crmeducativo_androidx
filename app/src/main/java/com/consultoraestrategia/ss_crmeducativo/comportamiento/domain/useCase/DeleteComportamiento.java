package com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoDataSource;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;

public class DeleteComportamiento extends UseCase<DeleteComportamiento.RequestValues, DeleteComportamiento.ResponseValue> {

    ComportamientoRepository comportamientoRepository;

    public DeleteComportamiento(ComportamientoRepository comportamientoRepository) {
        this.comportamientoRepository = comportamientoRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        comportamientoRepository.deleteComportamiento(requestValues.getIdcomportamiento(), new ComportamientoDataSource.CallbackSuccess() {
            @Override
            public void onLoad(boolean success) {
                getUseCaseCallback().onSuccess(new ResponseValue(success));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private String idcomportamiento;

        public RequestValues(String idcomportamiento) {
            this.idcomportamiento = idcomportamiento;
        }

        public String getIdcomportamiento() {
            return idcomportamiento;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        private boolean succces;

        public ResponseValue(boolean succces) {
            this.succces = succces;
        }

        public boolean isSuccces() {
            return succces;
        }
    }
}
