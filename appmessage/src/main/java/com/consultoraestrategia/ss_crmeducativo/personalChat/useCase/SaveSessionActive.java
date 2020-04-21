package com.consultoraestrategia.ss_crmeducativo.personalChat.useCase;

import com.consultoraestrategia.ss_crmeducativo.personalChat.data.PersonalChatRepository;

public class SaveSessionActive {

    PersonalChatRepository personalChatRepository;

    public SaveSessionActive(PersonalChatRepository personalChatRepository) {
        this.personalChatRepository = personalChatRepository;
    }

    public void execute(int idSender) {
        personalChatRepository.saveActiveSession(idSender);
    }
}
