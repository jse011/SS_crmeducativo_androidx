package com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;

public class GetCurso {
    private LoginDataRepository repository;

    public GetCurso(LoginDataRepository repository) {
        this.repository = repository;
    }

    public String execute(int silaboEventoId){
        return repository.getNombreCurso(silaboEventoId);
    }
}
