package com.consultoraestrategia.ss_crmeducativo.personalChat.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatRepository;
import com.consultoraestrategia.ss_crmeducativo.personalChat.entities.ChatUi;

public class GetChatReceiver extends UseCase<GetChatReceiver.RequestValues, GetChatReceiver.ResponseValue> {

    PersonalChatRepository personalChatRepository;

    public GetChatReceiver(PersonalChatRepository personalChatRepository) {
        this.personalChatRepository = personalChatRepository;
    }



    @Override
    protected void executeUseCase(RequestValues requestValues) {
        personalChatRepository.foundChatReceiver(requestValues.getIdReceiver(), new PersonalChatDataSource.Callback<ChatUi>() {
            @Override
            public void onLoad(boolean success, ChatUi item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }

    public static  class RequestValues implements UseCase.RequestValues {
        private int idReceiver;


        public RequestValues(int idReceiver ) {
            this.idReceiver = idReceiver;

        }



        public int getIdReceiver() {
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
