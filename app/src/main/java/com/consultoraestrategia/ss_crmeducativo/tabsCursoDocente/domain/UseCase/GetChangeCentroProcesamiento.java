package com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.domain.UseCase;

import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.data.TabsCursoDataSource;

public class GetChangeCentroProcesamiento {

    TabsCursoDataSource tabsCursoDataSource;

    public GetChangeCentroProcesamiento(TabsCursoDataSource tabsCursoDataSource) {
        this.tabsCursoDataSource = tabsCursoDataSource;
    }

    public boolean execute(int cargaCursoId, int calendarioPeriodoId){
        return tabsCursoDataSource.getExistChangeCentroProcesamiento(cargaCursoId, calendarioPeriodoId);
    }

}
