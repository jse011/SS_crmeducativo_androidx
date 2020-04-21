package com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.domain.UseCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.entities.PeriodoUi;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.data.TabsCursoDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabsCursoDocente.data.TabsCursoRepository;


import java.util.List;

/**
 * Created by @stevecampos on 20/02/2018.
 */

public class GetPeriodoList {
    private static final String TAG = GetPeriodoList.class.getSimpleName();
    private TabsCursoRepository repository;

    public GetPeriodoList(TabsCursoRepository repository) {
        this.repository = repository;
    }


    public List<PeriodoUi> execute(RequestValues requestValues) {
        return repository.getPeriodoList(requestValues.getCargaCursoId(), requestValues.getCursoId(), requestValues.getParametroDisenioId(), requestValues.getAnioAcademicoId());
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private final int cargaCursoId;
        private final int cursoId;
        private final int parametroDisenioId;
        private final int anioAcademicoId;

        public RequestValues(int cargaCursoId, int cursoId, int parametroDisenioId, int anioAcademicoId) {
            this.cargaCursoId = cargaCursoId;
            this.cursoId = cursoId;
            this.parametroDisenioId = parametroDisenioId;
            this.anioAcademicoId = anioAcademicoId;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }

        public int getCursoId() {
            return cursoId;
        }

        public int getParametroDisenioId() {
            return parametroDisenioId;
        }

        public int getAnioAcademicoId() {
            return anioAcademicoId;
        }
    }

}
