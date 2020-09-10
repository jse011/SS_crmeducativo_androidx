package com.consultoraestrategia.ss_crmeducativo.main.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainRepository;
import com.consultoraestrategia.ss_crmeducativo.main.entities.PersonaUi;

public class SavePersona {
    private MainRepository repository;

    public SavePersona(MainRepository repository) {
        this.repository = repository;
    }

    public void execute(PersonaUi personaUi){
       repository.savePathPersona(personaUi);
    }
}
