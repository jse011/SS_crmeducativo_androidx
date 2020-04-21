package com.consultoraestrategia.ss_crmeducativo.personalChat.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatRepository;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.MessageUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.SendDataChatBundle;

import java.util.List;

public class SendMessage extends UseCase<SendMessage.RequestValues, SendMessage.ResponseValue> {

    PersonalChatRepository personalChatRepository;

    public SendMessage(PersonalChatRepository personalChatRepository) {
        this.personalChatRepository = personalChatRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        personalChatRepository.sendMessage(requestValues.getChatCode(), requestValues.getMessageUi(), requestValues.getTypePersonsGroup()
                ,requestValues.getType(),new PersonalChatDataSource.Callback<String>() {
            @Override
            public void onLoad(boolean success, String item) {
                getUseCaseCallback().onSuccess(new ResponseValue(success, item));
            }
        });
    }

    public static  class RequestValues implements UseCase.RequestValues{
        private String chatCode;
        private MessageUi messageUi;
        private SendDataChatBundle.TypePerson typePersonsGroup;
        private SendDataChatBundle.TypeChat  type;

        public RequestValues(String chatCode,MessageUi messageUi, SendDataChatBundle.TypePerson  typePersonsGroup,SendDataChatBundle.TypeChat  type) {
            this.chatCode = chatCode;
            this.messageUi=messageUi;
            this.typePersonsGroup=typePersonsGroup;
            this.type=type;
        }

        public SendDataChatBundle.TypeChat getType() {
            return type;
        }

        public SendDataChatBundle.TypePerson  getTypePersonsGroup() {
            return typePersonsGroup;
        }

        public String getChatCode() {
            return chatCode;
        }

        public MessageUi getMessageUi() {
            return messageUi;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        private boolean created;
        private String messageId;

        public ResponseValue(boolean created,String messageId) {
            this.created = created;
            this.messageId=messageId;
        }

        public boolean isCreated() {
            return created;
        }

        public String getMessageId() {
            return messageId;
        }
    }
}
