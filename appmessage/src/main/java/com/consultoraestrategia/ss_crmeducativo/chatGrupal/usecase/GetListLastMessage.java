package com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase;

import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.TipoSalaEnum;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.ChatRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetListLastMessage {
    private List<PersonaUi> cache = new ArrayList<>();
    private ChatRepository repository;

    public GetListLastMessage(ChatRepository repository) {
        this.repository = repository;
    }

    public void execute(String salaId, final int personaId, Date date, final GetListLastMessage.Callback callback){
        repository.getListlastMessage(salaId, personaId,date,new ChatDataSource.Callback<List<Object>>() {
            @Override
            public void onLoad(boolean success, List<Object> item) {
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
        void onSuccess(List<Object> item);
        void onError();
    }
}
