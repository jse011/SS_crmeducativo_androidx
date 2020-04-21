package com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.ChatRepository;

import java.util.Date;
import java.util.List;

public class GetListLastMessage {
    private ChatRepository repository;

    public GetListLastMessage(ChatRepository repository) {
        this.repository = repository;
    }

    public void execute(int emisor, int reseptor, Date date, final GetListLastMessage.Callback callback){
        repository.getListlastMessage(emisor, reseptor, date,new ChatDataSource.Callback<List<Object>>() {
            @Override
            public void onLoad(boolean success, List<Object> item) {
                if(success){
                    callback.onSuccess(item);
                }else {
                    callback.onError();
                }
            }
        });
    }

    public interface Callback{
        void onSuccess(List<Object> item);
        void onError();
    }
}
