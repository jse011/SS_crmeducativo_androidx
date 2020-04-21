package com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase;

import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.TipoSalaEnum;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.ChatRepository;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebase;

import java.util.ArrayList;
import java.util.List;

public class GetListMessage {
    private ChatRepository repository;
    private List<PersonaUi> cache = new ArrayList<>();
    public GetListMessage(ChatRepository repository) {
        this.repository = repository;
    }

    public ListenerFirebase execute(String salaId, final int personaId,  final Callback callback){
        return repository.getListaMessage(salaId, personaId, new ChatDataSource.ListaMessageCallback() {
            @Override
            public void onRecivedMessage(List<MessageUi2> messageUis) {
                for (MessageUi2 messageUi2 : messageUis){
                    PersonaUi personaUi = null;
                    for (PersonaUi cache : cache){
                        if(cache.getId()==messageUi2.getEmisorId()){
                            personaUi = cache;
                            break;
                        }
                    }
                    if(personaUi==null){
                        personaUi = repository.getPersonaChatMessage(messageUi2.getEmisorId());
                        if(personaUi!=null)cache.add(personaUi);
                    }
                    messageUi2.setPersonaUi(personaUi);
                }
                callback.onRecivedMessage(messageUis);
            }

            @Override
            public void onChangeEstado() {
                callback.onChangeEstado();
            }

            @Override
            public void onLoad(boolean success, String chatId, List item) {
                if(success){
                    for (Object o1 : item){
                        if(o1 instanceof MessageUi2){
                            MessageUi2 messageUi2 = (MessageUi2)o1;
                            PersonaUi personaUi = null;
                            for (PersonaUi cache : cache){
                                if(cache.getId()==messageUi2.getEmisorId()){
                                    personaUi = cache;
                                    break;
                                }
                            }
                            if(personaUi==null){
                                personaUi = repository.getPersonaChatMessage(messageUi2.getEmisorId());
                                if(personaUi!=null)cache.add(personaUi);
                            }
                            messageUi2.setPersonaUi(personaUi);
                        }
                    }
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
