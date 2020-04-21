package com.consultoraestrategia.ss_crmeducativo.chats.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.chats.data.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chats.data.ChatRepository;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ChatUi;

import java.util.List;

public class GetChats extends UseCase<GetChats.RequestValues, GetChats.ResponseValue> {

   ChatRepository chatRepository;

    public GetChats(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        chatRepository.getChats(requestValues.getIdSender(),requestValues.getType(),  new ChatDataSource.Callback<List<ChatUi>>() {
            @Override
            public void onLoad(boolean success, List<ChatUi> item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }

    public static  class RequestValues implements UseCase.RequestValues{
        private int idSender;
        private int type;

        public RequestValues(int idSender, int type) {
            this.idSender = idSender;
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public int getIdSender() {
            return idSender;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        private List<ChatUi>chatUis;

        public ResponseValue(List<ChatUi> chatUis) {
            this.chatUis = chatUis;
        }

        public List<ChatUi> getChatUis() {
            return chatUis;
        }
    }
}
