package com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.data.CambiarFotoRepository;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.entities.PersonaUi;

public class SavePersona {
    private CambiarFotoRepository repository;

    public SavePersona(CambiarFotoRepository repository) {
        this.repository = repository;
    }

    public void execute(PersonaUi personaUi){
       repository.savePathPersona(personaUi);
    }
}
