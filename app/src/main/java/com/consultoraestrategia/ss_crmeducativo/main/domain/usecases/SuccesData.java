package com.consultoraestrategia.ss_crmeducativo.main.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainRepository;

public class SuccesData {
    private MainRepository repository;

    public SuccesData(MainRepository repository) {
        this.repository = repository;
    }

    public boolean execute(){
        return repository.succesData();
    }
}
