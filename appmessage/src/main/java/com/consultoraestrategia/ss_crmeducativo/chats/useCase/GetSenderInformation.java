package com.consultoraestrategia.ss_crmeducativo.chats.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.chats.data.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chats.data.ChatRepository;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.UserUi;

public class GetSenderInformation extends UseCase<GetSenderInformation.RequestValues, GetSenderInformation.ResponseValue> {

    ChatRepository chatRepository;

    public GetSenderInformation(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {
        chatRepository.getSenderInformation(requestValues.getIdSender(), new ChatDataSource.Callback<UserUi>() {
            @Override
            public void onLoad(boolean success, UserUi item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }

    public static  class RequestValues implements UseCase.RequestValues{
        private int idSender;

        public int getIdSender() {
            return idSender;
        }

        public RequestValues(int idSender) {
            this.idSender = idSender;
        }
    }

    public static  class ResponseValue implements UseCase.ResponseValue{
        UserUi userUi;

        public ResponseValue(UserUi userUi) {
            this.userUi = userUi;
        }

        public UserUi getUserUi() {
            return userUi;
        }
    }

}
