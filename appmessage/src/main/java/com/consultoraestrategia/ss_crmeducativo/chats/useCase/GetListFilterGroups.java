package com.consultoraestrategia.ss_crmeducativo.chats.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.chats.data.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chats.data.ChatRepository;

import java.util.List;

public class GetListFilterGroups extends UseCase<GetListFilterGroups.RequestValues, GetListFilterGroups.ResponseValue> {

    ChatRepository chatRepository;

    public GetListFilterGroups(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        chatRepository.getListFilterGroups(requestValues.getSenderId(), new ChatDataSource.Callback<List<Object>>() {
            @Override
            public void onLoad(boolean success, List<Object> item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }

    public static  class RequestValues implements UseCase.RequestValues{
        private int senderId;

        public RequestValues(int senderId) {
            this.senderId = senderId;
        }

        public int getSenderId() {
            return senderId;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        private List<Object> objects;

        public ResponseValue(List<Object> objects) {
            this.objects = objects;
        }

        public List<Object> getObjects() {
            return objects;
        }
    }
}
