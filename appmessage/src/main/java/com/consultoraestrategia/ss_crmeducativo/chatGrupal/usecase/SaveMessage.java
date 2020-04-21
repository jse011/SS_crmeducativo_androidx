package com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase;

import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.ChatRepository;

import java.util.ArrayList;
import java.util.List;

public class SaveMessage {
    private ChatRepository repository;

    public SaveMessage(ChatRepository repository) {
        this.repository = repository;
    }


    public void execute( MessageUi2 messageUi2, final Callback callback){
        repository.saveMensaje(messageUi2, new ChatDataSource.MessageCallback() {
            @Override
            public void onLoad(boolean success, MessageUi2 messageUi) {
                if(success){
                    callback.onSuccess(messageUi);
                }else {
                    callback.onError();
                }
            }
        });
    }

    public interface Callback{
        void onSuccess(MessageUi2 messageUi);
        void onError();
    }
}
