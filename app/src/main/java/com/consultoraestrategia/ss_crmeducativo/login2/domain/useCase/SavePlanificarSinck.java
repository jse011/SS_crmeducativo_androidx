package com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;
import com.consultoraestrategia.ss_crmeducativo.main.entities.AlarmaUi;

public class SavePlanificarSinck {
    private LoginDataRepository repository;

    public SavePlanificarSinck(LoginDataRepository repository) {
        this.repository = repository;
    }

    public boolean execute(int hora, int minute){
        return repository.savePlanSinck(hora, minute);
    }
}
