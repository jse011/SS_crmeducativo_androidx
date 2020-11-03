package com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.ChatRepository;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebase;

import java.util.Date;
import java.util.List;

public class GetListLastMessage {
    private ChatRepository repository;

    public GetListLastMessage(ChatRepository repository) {
        this.repository = repository;
    }

    public ListenerFirebase execute(int emisor, int reseptor, Date date, final GetListLastMessage.Callback callback){
        return repository.getListlastMessage(emisor, reseptor, date, new ChatDataSource.ListaMessageCallback() {
            @Override
            public void onLoad(boolean success, String chatId, List<Object> item) {
                if(success){
                    callback.onSuccess(item);
                }else {
                    callback.onError();
                }
            }

            @Override
            public void onRecivedMessage(List<MessageUi2> messageUis) {
                callback.onRecivedMessage(messageUis);
            }
        });
    }

    public interface Callback{
        void onSuccess(List<Object> item);
        void onError();
        void onRecivedMessage(List<MessageUi2> messageUis);
    }
}
