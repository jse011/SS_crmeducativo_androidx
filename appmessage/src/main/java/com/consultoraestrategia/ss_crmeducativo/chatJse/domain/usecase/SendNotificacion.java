package com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.ChatRepository;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

public class SendNotificacion {

    private ChatRepository chatRepository;

    public SendNotificacion(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public RetrofitCancel execute(final MessageUi2 messageUi2, String token, final CallBack callBack){
        return chatRepository.sendNotificacion(messageUi2, token,new ChatDataSource.MessageCallback() {
            @Override
            public void onLoad(boolean success, MessageUi2 messageUi) {
                if(success){
                    callBack.onSucces(messageUi2);
                }else {
                    callBack.onError(messageUi2);
                }
            }
        });
    }

    public interface CallBack{
        void onSucces(MessageUi2 messageUi2);
        void onError(MessageUi2 messageUi2);
    }
}
