package com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase;

import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.ChatRepository;

import java.util.List;

public class SaveImagenMessage {
    private ChatRepository repository;

    public SaveImagenMessage(ChatRepository repository) {
        this.repository = repository;
    }

    public void execute(List<MessageUi2> messageUi2, final SaveMessage.Callback callback){
        repository.saveImageMensaje(messageUi2, new ChatDataSource.MessageImageCallback() {
            @Override
            public void onLoad(boolean success, MessageUi2 messageUi) {
                if(success){
                    callback.onSuccess(messageUi);
                }else {
                    callback.onError();
                }
            }

            @Override
            public void onProgres(int progres) {

            }
        });
    }

    public interface Callback{
        void onSuccess(MessageUi2 messageUi);
        void onProgres(int progres);
        void onError();
    }
}
