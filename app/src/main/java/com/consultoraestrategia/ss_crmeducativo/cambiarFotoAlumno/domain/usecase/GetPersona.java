package com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.data.CambiarFotoRepository;
import com.consultoraestrategia.ss_crmeducativo.cambiarFotoAlumno.entities.PersonaUi;

import java.util.List;

public class GetPersona  {
    private CambiarFotoRepository repository;

    public GetPersona(CambiarFotoRepository repository) {
        this.repository = repository;
    }

    public List<PersonaUi> execute(int cargaCursoId){
        return repository.getPersonasDelCurso(cargaCursoId);
    }
}
