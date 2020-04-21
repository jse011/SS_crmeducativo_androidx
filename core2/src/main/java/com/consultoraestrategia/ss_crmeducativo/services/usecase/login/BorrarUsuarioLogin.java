package com.consultoraestrategia.ss_crmeducativo.services.usecase.login;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.base.ServiceDataSource;
import com.consultoraestrategia.ss_crmeducativo.services.data.source.datosCompletosLogin.SEDatosCompletosLoginRepository;
/**
 * Created by SCIEV on 12/07/2018.
 */

public class BorrarUsuarioLogin extends UseCase<BorrarUsuarioLogin.RequestValues,BorrarUsuarioLogin.ResponseValue>  {
    private SEDatosCompletosLoginRepository repository;

    public BorrarUsuarioLogin(SEDatosCompletosLoginRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.borrarSessionUsuario(new ServiceDataSource.SuccessCallBack() {
            @Override
            public void onResponse(boolean success) {
                getUseCaseCallback().onSuccess(new ResponseValue(success));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{

    }

    public class ResponseValue implements UseCase.ResponseValue{
        private boolean success;

        public ResponseValue(boolean success) {
            this.success = success;
        }

        public boolean isSuccess() {
            return success;
        }
    }
}
