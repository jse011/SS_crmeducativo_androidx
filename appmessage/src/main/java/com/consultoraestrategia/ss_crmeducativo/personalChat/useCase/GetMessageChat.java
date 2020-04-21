package com.consultoraestrategia.ss_crmeducativo.personalChat.useCase;

import com.consultoraestrategia.ss_crmeducativo.appmessage.R;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatRepository;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.MessageUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.SendDataChatBundle;

import java.util.List;

public class GetMessageChat extends UseCase<GetMessageChat.RequestValues,GetMessageChat.ResponseValue> {

    PersonalChatRepository repository;

    public GetMessageChat(PersonalChatRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.listLastMessage(requestValues.getChatCode(), requestValues.getType(), requestValues.getTypePersonGroups(),new PersonalChatDataSource.Callback<List<Object>>() {
            @Override
            public void onLoad(boolean success, List<Object> item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }

    public static  class RequestValues implements UseCase.RequestValues{
        private String chatCode;
        private SendDataChatBundle.TypeChat type;
        private SendDataChatBundle.TypePerson typePersonGroups;

        public RequestValues(String chatCode,SendDataChatBundle.TypeChat type,SendDataChatBundle.TypePerson  typePersonGroups) {
            this.chatCode = chatCode;
            this.type=type;
            this.typePersonGroups=typePersonGroups;
        }

        public SendDataChatBundle.TypeChat getType() {
            return type;
        }

        public SendDataChatBundle.TypePerson  getTypePersonGroups() {
            return typePersonGroups;
        }

        public String getChatCode() {
            return chatCode;
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
