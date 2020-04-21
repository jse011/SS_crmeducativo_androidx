package com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase;

import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.ChatRepository;

public class UpdateTokenSala {
        private ChatRepository chatRepository;

    public UpdateTokenSala(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public void execute(String salaId, String personaId){
        chatRepository.updateTaken(salaId, personaId);
    }
}
