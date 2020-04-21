package com.consultoraestrategia.ss_crmeducativo.personalChat.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.GroupUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatRepository;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.SendDataChatBundle;

public class ValidatePersonGroupExistence extends UseCase<ValidatePersonGroupExistence.RequestValues, ValidatePersonGroupExistence.ResponseValue> {

    PersonalChatRepository repository;

    public ValidatePersonGroupExistence(PersonalChatRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.valideGroupExistence(requestValues.getIdgroup(), requestValues.isInternet(), requestValues.getType(), requestValues.getTypePerson(),new PersonalChatDataSource.SuccessCallback() {
            @Override
            public void onLoad(boolean success) {
                getUseCaseCallback().onSuccess(new ResponseValue(success));
            }
        });
    }

    public static  class RequestValues implements UseCase.RequestValues{
        private String idgroup;
        private boolean internet;
        private SendDataChatBundle.TypeGroup type;
        private SendDataChatBundle.TypePerson  typePerson;


        public RequestValues(String idgroup,boolean internet,  SendDataChatBundle.TypeGroup type,SendDataChatBundle.TypePerson  typePerson) {
            this.idgroup = idgroup;
            this.internet=internet;
            this.type=type;
            this.typePerson=typePerson;
        }

        public SendDataChatBundle.TypePerson  getTypePerson() {
            return typePerson;
        }

        public SendDataChatBundle.TypeGroup getType() {
            return type;
        }

        public String getIdgroup() {
            return idgroup;
        }

        public boolean isInternet() {
            return internet;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{

        private boolean existenceSender;

        public ResponseValue(boolean existenceSender) {
            this.existenceSender = existenceSender;
        }

        public boolean isExistenceSender() {
            return existenceSender;
        }

    }

}
