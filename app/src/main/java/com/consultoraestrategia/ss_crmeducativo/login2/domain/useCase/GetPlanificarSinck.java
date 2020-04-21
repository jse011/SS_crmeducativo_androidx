package com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.AlarmaUi;

public class GetPlanificarSinck {
    private LoginDataRepository repository;

    public GetPlanificarSinck(LoginDataRepository repository) {
        this.repository = repository;
    }

    public AlarmaUi execute(){
        return repository.getPlanSinck();
    }
}
