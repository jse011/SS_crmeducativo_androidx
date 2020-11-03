package com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.ChatRepository;

public class EstadoActivo {

    private ChatRepository chatRepository;

    public EstadoActivo(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public void executeListner(Callback callback){
      // chatRepository.getKeyPressEmisor();
    }

    public void excuteSave(int person, int personaExterna){
        chatRepository.saveKeyPress(person, personaExterna);
    }

    public interface Callback{
        void onKeyPress(boolean estado);
        void onOfflineChat(boolean estado);
    }

}
