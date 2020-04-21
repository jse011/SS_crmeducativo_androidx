package com.consultoraestrategia.ss_crmeducativo.chats.useCase;

import com.consultoraestrategia.ss_crmeducativo.chats.data.ChatRepository;
import com.consultoraestrategia.ss_crmeducativo.chats.entities.UsuarioUi;

public class GetUsuario {
    private ChatRepository chatRepository;

    public GetUsuario(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public UsuarioUi execute(){
        return chatRepository.getUsuarioDefault();
    }

}
