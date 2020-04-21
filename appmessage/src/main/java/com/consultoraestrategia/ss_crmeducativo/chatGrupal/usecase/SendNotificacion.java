package com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase;

import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.ChatRepository;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

import java.util.List;

public class SendNotificacion {

    private ChatRepository chatRepository;

    public SendNotificacion(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public RetrofitCancel execute(final MessageUi2 messageUi2, List<String> tokens, final CallBack callBack){
        return chatRepository.sendNotificacion(messageUi2, tokens,new ChatDataSource.MessageCallback() {
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
