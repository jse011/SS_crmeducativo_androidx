package com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase;

import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.ChatRepository;

import java.util.List;

public class GetSalaIntegrantes {
    private ChatRepository repository;

    public GetSalaIntegrantes(ChatRepository repository) {
        this.repository = repository;
    }

    public void execute(String salaId, final Callback callback){
        repository.getSalaIntegrantes(salaId, new ChatDataSource.Callback<List<PersonaUi>>(){
            @Override
            public void onLoad(boolean success, List<PersonaUi> item) {
                if(success){
                    callback.onLoad(item);
                }else {
                    callback.onError();
                }
            }
        } );
    }
    public interface Callback{
        void onLoad(List<PersonaUi> personaUiList);
        void onError();
    }
}
