package com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.entities.IntencionUI;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.SendMessageBaseRepository;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.domain.data.source.callbacks.CreateMessageCallback;
import com.consultoraestrategia.ss_crmeducativo.SendMessage_base.ui.PersonaRelacionesUI;

import java.util.List;

/**
 * Created by irvinmarin on 12/07/2018.
 */

public class CreateMessageUseCase extends UseCase<CreateMessageUseCase.RequestValues, CreateMessageUseCase.ResponseValue> {

    private SendMessageBaseRepository repository;

    public CreateMessageUseCase(SendMessageBaseRepository repository) {
        this.repository = repository;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.createMessage(requestValues, new CreateMessageCallback() {
            @Override
            public void onCreateMessageLoaded(boolean isSuccess) {
                getUseCaseCallback().onSuccess(new ResponseValue(isSuccess));
            }

            @Override
            public void onError(String error) {
                getUseCaseCallback().onError();

            }
        });

    }


    public static final class RequestValues implements UseCase.RequestValues {
        private final String asunto;
        private final String contenido;
        private final List<PersonaRelacionesUI> personaDestinoMessage;
        private final boolean needResponse;
        private final IntencionUI intencionUI;
        private final int idCargaCurso;


        public RequestValues(String asunto, String contenido, List<PersonaRelacionesUI> personaDestinoMessage, boolean needResponse, IntencionUI intencionUI, int idCargaCurso) {
            this.asunto = asunto;
            this.contenido = contenido;
            this.personaDestinoMessage = personaDestinoMessage;
            this.needResponse = needResponse;
            this.intencionUI = intencionUI;
            this.idCargaCurso = idCargaCurso;
        }

        public int getIdCargaCurso() {
            return idCargaCurso;
        }

        public String getAsunto() {
            return asunto;
        }

        public String getContenido() {
            return contenido;
        }

        public List<PersonaRelacionesUI> getPersonaDestinoMessage() {
            return personaDestinoMessage;
        }

        public boolean isNeedResponse() {
            return needResponse;
        }

        public IntencionUI getIntencionUI() {
            return intencionUI;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final boolean isSucces;

        public ResponseValue(boolean isSucces) {
            this.isSucces = isSucces;
        }

        public boolean isSucces() {
            return isSucces;
        }
    }
}
