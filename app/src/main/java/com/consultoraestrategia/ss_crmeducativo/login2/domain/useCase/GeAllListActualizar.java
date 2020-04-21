package com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ActualizarUi;

import java.util.List;

public class GeAllListActualizar {
    private LoginDataRepository repository;

    public GeAllListActualizar(LoginDataRepository repository) {
        this.repository = repository;
    }

    public List<ActualizarUi> execute(int usuarioId, int anioAcademicoId){
        return repository.geTodosLosCurso(usuarioId, anioAcademicoId);
    }
}
