package com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.domain.UseCase;

import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.data.TabsCursoRepository;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.ParametroDisenioUi;
import com.consultoraestrategia.ss_crmeducativo.tabsSesiones.data.source.TabsSesionesRepository;

public class GetParametroDisenio {
    private TabsCursoRepository repository;

    public GetParametroDisenio(TabsCursoRepository repository) {
        this.repository = repository;
    }

    public ParametroDisenioUi execute(int parametroDisenioId){
        return repository.getParametroDisenio(parametroDisenioId);
    }
}
