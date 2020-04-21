package com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.domain.UseCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.data.TabsCursoRepository;

public class GetIsAprendizajeColegio {

    private TabsCursoRepository repository;

    public GetIsAprendizajeColegio(TabsCursoRepository repository) {
        this.repository = repository;
    }

    public boolean execute(int entidadId, int georeferenciaId) {
        return repository.isAprendizajeColegio(entidadId, georeferenciaId);
    }
}
