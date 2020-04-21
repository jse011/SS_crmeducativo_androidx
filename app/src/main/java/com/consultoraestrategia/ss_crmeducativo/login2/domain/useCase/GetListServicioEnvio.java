package com.consultoraestrategia.ss_crmeducativo.login2.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.login2.data.repositorio.LoginDataRepository;
import com.consultoraestrategia.ss_crmeducativo.login2.entities.ServiceEnvioUi;

import java.util.ArrayList;
import java.util.List;

public class GetListServicioEnvio {
    private LoginDataRepository repositorio;

    public GetListServicioEnvio(LoginDataRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<ServiceEnvioUi> execute(int anioAcademicoId, int cargaCursoId, int calendarioPeriodoId, int silaboEventoId, int programaAcademicoId) {
        return  repositorio.getDataForSynck(anioAcademicoId, cargaCursoId, calendarioPeriodoId, silaboEventoId, programaAcademicoId);
    }
}
