package com.consultoraestrategia.ss_crmeducativo.chats.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.chats.data.ChatDataSource;
import com.consultoraestrategia.ss_crmeducativo.chats.data.ChatRepository;

import java.util.List;

public class GetGroups  {

    private ChatRepository chatRepository;

    public GetGroups(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public List<Object> execute(int personaId){
        return chatRepository.getGroups(personaId);
    }

}
