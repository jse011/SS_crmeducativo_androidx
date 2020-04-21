package com.consultoraestrategia.ss_crmeducativo.chats.useCase;

import com.consultoraestrategia.ss_crmeducativo.chats.data.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chats.data.ChatRepository;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.ChatUi;
import com.consultoraestrategia.ss_crmeducativo.utils.firebase.ListenerFirebase;

import java.util.ArrayList;
import java.util.List;

public class GetAllChats {

    private ChatRepository chatRepository;

    public GetAllChats(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public List<ListenerFirebase> execute(int personaId,int docenteId, final Callback callback){
        List<ListenerFirebase> listenerFirebases = new ArrayList<>();
        ChatDataSource.Callback<List<ChatUi>> listCallback = new ChatDataSource.Callback<List<ChatUi>>() {

            @Override
            public void onLoad(boolean success, List<ChatUi> item) {
                if (success) {
                    callback.onSuccess(item);
                } else {
                    callback.onError();
                }
            }
        };

        listenerFirebases.add(chatRepository.getPersonaChats(personaId, listCallback));
        listenerFirebases.add(chatRepository.getGrupoChats(docenteId,personaId, listCallback));

        return listenerFirebases;
    }

    public interface Callback{
        void onSuccess(List<ChatUi> item);
        void onError();
    }
}
