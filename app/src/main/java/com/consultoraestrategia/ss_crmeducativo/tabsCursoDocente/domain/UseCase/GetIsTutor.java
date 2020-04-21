package com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.domain.UseCase;

import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.data.TabsCursoRepository;

public class GetIsTutor {
    private TabsCursoRepository repository;

    public GetIsTutor(TabsCursoRepository repository) {
        this.repository = repository;
    }

    public boolean execute(int cargaAcademica){
        return repository.isTutor(cargaAcademica);
    }
}
