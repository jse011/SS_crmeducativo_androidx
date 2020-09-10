package com.consultoraestrategia.ss_crmeducativo.main.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;

import java.util.List;

public class ChangeDataBaseDocenteMentor {
    private LoginDataRepository serviceLocalDataRepository;

    public ChangeDataBaseDocenteMentor(LoginDataRepository serviceLocalDataRepository) {
        this.serviceLocalDataRepository = serviceLocalDataRepository;
    }

    public List<String> execute(){
        return serviceLocalDataRepository.comprobrarCambiosBaseDatosDaocenteMentor();
    }
}
