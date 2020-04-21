package com.consultoraestrategia.ss_crmeducativo.personalChat.useCase;

import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatRepository;

public class SaveLastConexion {

    PersonalChatRepository personalChatRepository;

    public SaveLastConexion(PersonalChatRepository personalChatRepository) {
        this.personalChatRepository = personalChatRepository;
    }

    public void execute(int idSender){
        personalChatRepository.saveLastConexion(idSender);
    }
}
