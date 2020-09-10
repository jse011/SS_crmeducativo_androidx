package com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.domain.UseCase;


import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;

import java.util.List;

public class ChangeDataBaseDocenteMentor {
    private LoginDataRepository repository;

    public ChangeDataBaseDocenteMentor(LoginDataRepository repository) {
        this.repository = repository;
    }

    public List<String> execute(){
        return repository.comprobrarCambiosBaseDatosDaocenteMentor();
    }
}
