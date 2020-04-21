package com.consultoraestrategia.ss_crmeducativo.chatJse.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.chatJse.data.source.ChatRepository;
import com.consultoraestrategia.ss_crmeducativo.chatJse.entites.PersonaUi;

public class GetPersona {
    private ChatRepository repository;

    public GetPersona(ChatRepository repository) {
        this.repository = repository;
    }

    public PersonaUi execute(int personaId){
        return repository.getPersona(personaId);
    }
}
