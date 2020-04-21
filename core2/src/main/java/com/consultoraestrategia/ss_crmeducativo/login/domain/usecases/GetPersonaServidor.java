package com.consultoraestrategia.ss_crmeducativo.login.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.LoginDataSource;
import com.consultoraestrategia.ss_crmeducativo.login.data.source.LoginRepository;
import com.consultoraestrategia.ss_crmeducativo.login.entity.PersonaUi;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class GetPersonaServidor extends UseCase<GetPersonaServidor.RequestValues, GetPersonaServidor.ResponseValue> {

    private static final String TAG = GetPersonaServidor.class.getSimpleName();
    private LoginRepository repository;

    public GetPersonaServidor(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.obtenerPersonaUsuario(requestValues.getUserName(), new LoginDataSource.Callback<PersonaUi>() {
            @Override
            public void onLoad(boolean success, PersonaUi item) {
                if(success){
                    getUseCaseCallback().onSuccess(new ResponseValue(item));
                }else {
                    getUseCaseCallback().onError();
                }
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private final String userName;

        public RequestValues(String userName) {
            this.userName = userName;
        }

        public String getUserName() {
            return userName;
        }

    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private PersonaUi personaUi;

        public ResponseValue(PersonaUi personaUi) {
            this.personaUi = personaUi;
        }

        public PersonaUi getPersonaUi() {
            return personaUi;
        }
    }
}
