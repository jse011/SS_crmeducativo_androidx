package com.consultoraestrategia.ss_crmeducativo.personalChat.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatRepository;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.MessageUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.SendDataChatBundle;

import java.util.Date;
import java.util.List;

public class GetMessageBefore extends UseCase<GetMessageBefore.RequestValues, GetMessageBefore.ResponseValue> {

    PersonalChatRepository repository;

    public GetMessageBefore(PersonalChatRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.listBeforeMessage(requestValues.getCodeChat(), requestValues.getLastMessage(),requestValues.getType(), requestValues.getTypePersonGroups(),
                new PersonalChatDataSource.Callback<List<Object>>() {
            @Override
            public void onLoad(boolean success, List<Object> item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }

    public static  class RequestValues implements UseCase.RequestValues {
        private String codeChat;
        private Date lastMessage;
        private SendDataChatBundle.TypeChat type;
        private SendDataChatBundle.TypePerson  typePersonGroups;


        public RequestValues(String codeChat, Date lastMessage,SendDataChatBundle.TypeChat type,SendDataChatBundle.TypePerson  typePersonGroups) {
            this.codeChat = codeChat;
            this.lastMessage = lastMessage;
            this.type=type;
            this.typePersonGroups=typePersonGroups;
        }

        public SendDataChatBundle.TypeChat getType() {
            return type;
        }

        public SendDataChatBundle.TypePerson  getTypePersonGroups() {
            return typePersonGroups;
        }

        public String getCodeChat() {
            return codeChat;
        }

        public Date getLastMessage() {
            return lastMessage;
        }
    }

        public static class ResponseValue implements UseCase.ResponseValue{
        private List<Object> messageUis;

        public ResponseValue(List<Object> messageUis) {
            this.messageUis = messageUis;
        }

        public List<Object> getMessageUis() {
            return messageUis;
        }
    }
}
