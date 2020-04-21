package com.consultoraestrategia.ss_crmeducativo.chats.useCase;

import com.consultoraestrategia.ss_crmeducativo.chats.data.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chats.data.ChatRepository;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.services.usecase.login.UseCaseLoginSincrono;
import com.consultoraestrategia.ss_crmeducativo.services.wrapper.RetrofitCancel;

import java.util.List;

public class  GetDatosChatAndUpdatePersonaGrupo {
    private ChatRepository repository;

    public GetDatosChatAndUpdatePersonaGrupo(ChatRepository repository) {
        this.repository = repository;
    }

    public RetrofitCancel execute(int personaId, List<ChatUi> chatUiList, final Callback callback){
        return repository.getDatosChat(personaId, chatUiList, new ChatDataSource.Callback<List<ChatUi>>(){
            @Override
            public void onLoad(boolean success, List<ChatUi> item) {
                if(success){
                    callback.onSuccess(item);
                }else {
                    callback.onError();
                }
            }
        });
    }

    public interface Callback{
        void onSuccess(List<ChatUi> item);
        void onError();
    }
}
