package com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.CalendarioPeriodoUi;

import java.util.List;

public class GetCalendarioPeridoList {
    private LoginDataRepository loginDataRepository;

    public GetCalendarioPeridoList(LoginDataRepository loginDataRepository) {
        this.loginDataRepository = loginDataRepository;
    }

    public List<CalendarioPeriodoUi> execute(int anioAcademicoId, int programaEducativoId){
        return loginDataRepository.getListCalendarioAcademico(anioAcademicoId, programaEducativoId);
    }
}
