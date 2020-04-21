package com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.entities.Persona;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.SendMessageBaseRepository;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.callbacks.GetPersonasRelacionadasCallback;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.ui.PersonaRelacionesUI;

import java.util.List;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public class GetPersonasRelacionasUseCase extends UseCase<GetPersonasRelacionasUseCase.RequestValues, GetPersonasRelacionasUseCase.ResponseValue> {

    private SendMessageBaseRepository repository;

    public GetPersonasRelacionasUseCase(SendMessageBaseRepository repository) {
        this.repository = repository;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getPersonasRelacionadasList(requestValues, new GetPersonasRelacionadasCallback() {
            @Override
            public void onPersonasRelacionadaListLoaded(List<PersonaRelacionesUI> personaRelacionesList) {
                getUseCaseCallback().onSuccess(new ResponseValue(personaRelacionesList));

            }

            @Override
            public void onError(String error) {
                getUseCaseCallback().onError();
            }
        });


    }


    public static final class RequestValues implements UseCase.RequestValues {
        private final List<Persona> personaList;

        public RequestValues(List<Persona> personaList) {
            this.personaList = personaList;
        }

        public List<Persona> getPersonaList() {
            return personaList;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final List<PersonaRelacionesUI> personaRelacionesList;

        public ResponseValue(List<PersonaRelacionesUI> personaRelacionesList) {
            this.personaRelacionesList = personaRelacionesList;
        }

        public List<PersonaRelacionesUI> getPersonaRelacionesList() {
            return personaRelacionesList;
        }
    }
}
