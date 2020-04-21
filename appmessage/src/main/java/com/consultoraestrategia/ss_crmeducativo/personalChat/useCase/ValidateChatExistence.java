package com.consultoraestrategia.ss_crmeducativo.personalChat.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatRepository;

public class ValidateChatExistence  extends UseCase<ValidateChatExistence.RequestValues, ValidateChatExistence.ResponseValue> {

    PersonalChatRepository repository;

    public ValidateChatExistence(PersonalChatRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(ValidateChatExistence.RequestValues requestValues) {
        repository.validateChatExistence(requestValues.getIdsender(), requestValues.getIdreceiver(), new PersonalChatDataSource.Callback<String>() {
            @Override
            public void onLoad(boolean success, String item) {
                getUseCaseCallback().onSuccess(new ResponseValue(success, item));
            }
        });
    }


    public static  class RequestValues implements UseCase.RequestValues{
        private int idsender;
        private int idreceiver;



        public RequestValues(int idsender, int idreceiver) {
            this.idsender = idsender;
            this.idreceiver = idreceiver;

        }

        public int getIdsender() {
            return idsender;
        }

        public int getIdreceiver() {
            return idreceiver;
        }


    }
    public static class ResponseValue implements UseCase.ResponseValue{
        private boolean existence;
        private String codeChat;

        public ResponseValue(boolean existence, String codeChat) {
            this.existence = existence;
            this.codeChat = codeChat;
        }

        public boolean isExistence() {
            return existence;
        }

        public String getCodeChat() {
            return codeChat;
        }
    }

}
