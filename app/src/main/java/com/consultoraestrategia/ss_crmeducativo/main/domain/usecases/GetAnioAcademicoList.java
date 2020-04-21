package com.consultoraestrategia.ss_crmeducativo.main.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainRepository;
import com.consultoraestrategia.ss_crmeducativo.main.entities.AnioAcademicoUi;

import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class GetAnioAcademicoList {

    private MainRepository repository;

    public GetAnioAcademicoList(MainRepository repository) {
        this.repository = repository;
    }

    public List<AnioAcademicoUi> execute(int usuarioId){
        return repository.getListAnioAcademico(usuarioId);
    }

}
