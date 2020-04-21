package com.consultoraestrategia.ss_crmeducativo.chatGrupal.usecase;

import com.consultoraestrategia.ss_crmeducativo.chatGrupal.entites.PersonaUi;
import com.consultoraestrategia.ss_crmeducativo.chatGrupal.source.ChatRepository;

public class GetPersona {
    private ChatRepository repository;

    public GetPersona(ChatRepository repository) {
        this.repository = repository;
    }

    public PersonaUi execute(int personaId){
        return repository.getPersona(personaId);
    }
}
