package com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase;

import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.MessageUi2;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.ChatRepository;

import java.util.List;

public class ChangeMessageEstadoEliminado {
    private ChatRepository chatRepository;

    public ChangeMessageEstadoEliminado(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public void excute(List<MessageUi2> messageUi2List){
        chatRepository.changeEstadoEliminado(messageUi2List);
    }
}
