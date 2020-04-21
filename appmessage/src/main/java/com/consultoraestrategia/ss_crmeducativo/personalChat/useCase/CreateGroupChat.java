package com.consultoraestrategia.ss_crmeducativo.personalChat.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.GroupUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatRepository;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.SendDataChatBundle;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupChat  extends UseCase<CreateGroupChat.RequestValues, CreateGroupChat.ResponseValue> {

    PersonalChatRepository personalChatRepository;

    public CreateGroupChat(PersonalChatRepository personalChatRepository) {
        this.personalChatRepository = personalChatRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        personalChatRepository.createChatGroup(requestValues.getIdSender(), requestValues.getIdReceiver(), requestValues.getTypePerson()
                ,requestValues.getType(),new PersonalChatDataSource.Callback<ChatUi>() {
            @Override
            public void onLoad(boolean success, ChatUi item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }
    public static  class RequestValues implements UseCase.RequestValues {
        private int idSender;
        private String idReceiver;
        private SendDataChatBundle.TypePerson   typePerson;
        private SendDataChatBundle.TypeGroup type;


        public RequestValues(String idReceiver,int idSender,SendDataChatBundle.TypePerson   typePerson ,SendDataChatBundle.TypeGroup type) {
            this.idReceiver = idReceiver;
            this.idSender=idSender;
            this.typePerson=typePerson;
            this.type=type;

        }

        public SendDataChatBundle.TypeGroup getType() {
            return type;
        }

        public SendDataChatBundle.TypePerson  getTypePerson() {
            return typePerson;
        }


        public int getIdSender() {
            return idSender;
        }

        public String getIdReceiver() {
            return idReceiver;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        private ChatUi chatUi;

        public ResponseValue(ChatUi chatUi) {
            this.chatUi = chatUi;
        }

        public ChatUi getChatUi() {
            return chatUi;
        }
    }
}
