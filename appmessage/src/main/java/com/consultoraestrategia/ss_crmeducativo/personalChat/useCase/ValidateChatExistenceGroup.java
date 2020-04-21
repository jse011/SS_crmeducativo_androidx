package com.consultoraestrategia.ss_crmeducativo.personalChat.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatRepository;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.SendDataChatBundle;

public class ValidateChatExistenceGroup extends UseCase<ValidateChatExistenceGroup.RequestValues, ValidateChatExistenceGroup.ResponseValue> {

    PersonalChatRepository repository;

    public ValidateChatExistenceGroup(PersonalChatRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(ValidateChatExistenceGroup.RequestValues requestValues) {
        repository.validateChatExistenceGroup(requestValues.getIdsender(), requestValues.getIdreceiver(), requestValues.getTypePerson(),requestValues.getTypeGroup(),new PersonalChatDataSource.Callback<ChatUi>() {
            @Override
            public void onLoad(boolean success, ChatUi item) {
                getUseCaseCallback().onSuccess(new ResponseValue(success,item));
            }
        });
    }


    public static  class RequestValues implements UseCase.RequestValues{
        private int idsender;
        private String idreceiver;
        private SendDataChatBundle.TypePerson  typePerson;
        private SendDataChatBundle.TypeGroup typeGroup;



        public RequestValues(int idsender, String idreceiver,SendDataChatBundle.TypePerson  typePerson, SendDataChatBundle.TypeGroup typeGroup) {
            this.idsender = idsender;
            this.idreceiver = idreceiver;
            this.typePerson=typePerson;
            this.typeGroup=typeGroup;

        }

        public SendDataChatBundle.TypeGroup getTypeGroup() {
            return typeGroup;
        }

        public SendDataChatBundle.TypePerson  getTypePerson() {
            return typePerson;
        }

        public int getIdsender() {
            return idsender;
        }

        public String getIdreceiver() {
            return idreceiver;
        }


    }
    public static class ResponseValue implements UseCase.ResponseValue{
        private boolean existence;
        private ChatUi chatUi;

        public ResponseValue(boolean existence, ChatUi codeChat) {
            this.existence = existence;
            this.chatUi = codeChat;
        }

        public boolean isExistence() {
            return existence;
        }


        public ChatUi getChatUi() {
            return chatUi;
        }
    }

}
