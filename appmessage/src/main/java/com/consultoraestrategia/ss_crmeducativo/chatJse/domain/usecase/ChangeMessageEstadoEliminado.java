package com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.ChatRepository;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.MessageUi2;

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
