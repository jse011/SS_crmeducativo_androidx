package com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase;

import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.ChatRepository;

import java.util.List;

public class GetTokensSala {
    private ChatRepository repository;

    public GetTokensSala(ChatRepository repository) {
        this.repository = repository;
    }

    public void execute(String salaId, final Callback callback){
        repository.getTokensSala(salaId, new ChatDataSource.Callback<List<String>>() {
            @Override
            public void onLoad(boolean success, List<String> item) {
                if(success){
                    callback.onSucces(item);
                }else {
                    callback.onError();
                }
            }
        });
    }

    public interface Callback{
        void onSucces(List<String> tokens);
        void onError();
    }
}
