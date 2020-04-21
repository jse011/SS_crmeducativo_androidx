package com.consultoraestrategia.ss_crmeducativo.login.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.LoginDataSource;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.LoginRepository;

public class GetPasswordServidor  extends UseCase<GetPasswordServidor.RequestValues, GetPasswordServidor.ResponseValue> {

    private static final String TAG = GetPasswordServidor.class.getSimpleName();
    private LoginRepository repository;

    public GetPasswordServidor(LoginRepository repository) {
        this.repository = repository;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {

        repository.recuperarPassword( requestValues.getIdpersona(),new LoginDataSource.Callback<String>() {
            @Override
            public void onLoad(boolean success, String item) {
                if(success){
                    getUseCaseCallback().onSuccess(new ResponseValue(item));
                }else {
                    getUseCaseCallback().onError();
                }
            }
        });
    }
    public static final class RequestValues implements UseCase.RequestValues {

        private int idpersona;

        public RequestValues(int idpersona) {
            this.idpersona = idpersona;
        }

        public int getIdpersona() {
            return idpersona;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private String correo;

        public ResponseValue(String correo) {
            this.correo = correo;
        }

        public String getCorreo() {
            return correo;
        }
    }
}
