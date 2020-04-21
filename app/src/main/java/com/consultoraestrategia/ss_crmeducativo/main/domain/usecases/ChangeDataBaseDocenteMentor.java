package com.consultoraestrategia.ss_crmeducativo.main.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.services.data.local.ServiceLocalDataRepository;

import java.util.List;

public class ChangeDataBaseDocenteMentor {
    private ServiceLocalDataRepository serviceLocalDataRepository;

    public ChangeDataBaseDocenteMentor(ServiceLocalDataRepository serviceLocalDataRepository) {
        this.serviceLocalDataRepository = serviceLocalDataRepository;
    }

    public List<String> execute(){
        return serviceLocalDataRepository.comprobrarCambiosBaseDatosDaocenteMentor();
    }
}
