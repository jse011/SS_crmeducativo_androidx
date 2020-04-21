package com.consultoraestrategia.ss_crmeducativo.main.domain.usecases;

import com.consultoraestrategia.ss_crmeducativo.main.data.source.MainRepository;
import com.consultoraestrategia.ss_crmeducativo.main.entities.ProgramaEduactivosUI;

import java.util.List;

/**
 * Created by irvinmarin on 03/10/2017.
 */

public class GetProgramasEdcativosList {

    private MainRepository repository;

    public GetProgramasEdcativosList(MainRepository repository) {
        this.repository = repository;
    }

    public List<ProgramaEduactivosUI> execute(int usuarioId, int anioAcademicoId){
        return repository.getListProgramaEducativo(anioAcademicoId, usuarioId);
    }

}
