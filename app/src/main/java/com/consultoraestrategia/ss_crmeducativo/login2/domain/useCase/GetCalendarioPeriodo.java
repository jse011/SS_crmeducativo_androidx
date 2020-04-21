package com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase;


import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.CalendarioPeriodoUi;

public class GetCalendarioPeriodo {
    private LoginDataRepository repositorio;

    public GetCalendarioPeriodo(LoginDataRepository repositorio) {
        this.repositorio = repositorio;
    }

    public CalendarioPeriodoUi execute(int calendarioPeriodo){
        return repositorio.getCalendarioPeriodo(calendarioPeriodo);
    }
}
