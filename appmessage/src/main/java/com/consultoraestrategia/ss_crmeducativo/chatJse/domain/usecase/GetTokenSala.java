package com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase;


import com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.ChatRepository;

public class GetTokenSala {
    private ChatRepository repository;

    public GetTokenSala(ChatRepository repository) {
        this.repository = repository;
    }

    public void execute(String salaId, final Callback callback){
        repository.getTokensSala(salaId, new ChatDataSource.Callback<String>() {
            @Override
            public void onLoad(boolean success, String item) {
                if(success){
                    callback.onSucces(item);
                }else {
                    callback.onError();
                }
            }
        });
    }

    public interface Callback{
        void onSucces(String tokens);
        void onError();
    }
}
