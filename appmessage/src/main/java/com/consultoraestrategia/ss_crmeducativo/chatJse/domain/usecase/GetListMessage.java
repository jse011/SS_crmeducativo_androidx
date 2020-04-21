package com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.ChatRepository;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebase;

import java.util.List;

public class GetListMessage {
    private ChatRepository repository;

    public GetListMessage(ChatRepository repository) {
        this.repository = repository;
    }

    public ListenerFirebase execute(List<Object> mensajesList, int emisor, int reseptor, final Callback callback){
        return repository.getListaMessage(emisor, reseptor, new ChatDataSource.ListaMessageCallback() {
            @Override
            public void onRecivedMessage(List<MessageUi2> messageUis) {
                callback.onRecivedMessage(messageUis);
            }

            @Override
            public void onChangeEstado() {
                callback.onChangeEstado();
            }

            @Override
            public void onLoad(boolean success, String chatId, List item) {
                if(success){
                    callback.onSuccess(item);
                }else {
                    callback.onError();
                }
            }
        });
    }

    public interface Callback{
        void onRecivedMessage(List<MessageUi2> messageUis);
        void onSuccess(List<Object> item);
        void onError();
        void onChangeEstado();
    }
}
