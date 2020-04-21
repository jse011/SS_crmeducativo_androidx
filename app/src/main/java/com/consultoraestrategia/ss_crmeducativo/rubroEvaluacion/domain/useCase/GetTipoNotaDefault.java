package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import android.util.Log;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoNotaUi;

import java.util.List;

/**
 * Created by @stevecampos on 29/01/2018.
 */

public class GetTipoNotaDefault {

    private static final String TAG = GetTipoNotaDefault.class.getSimpleName();
    private RubroEvaluacionProcesoListaRepository repository;

    public GetTipoNotaDefault(RubroEvaluacionProcesoListaRepository repository) {
        this.repository = repository;
    }


    public TipoNotaUi executeUseCase(RequestValues requestValues) {
        Log.d(TAG, "executeUseCase");
        return repository.getTipoNotaDefault(requestValues.getProgrmaEducativoId(), requestValues.getTipos());
    }

    public static class RequestValues implements UseCase.RequestValues {
        private int progrmaEducativoId;
        private List<TipoNotaUi.Tipo> tipos;

        public RequestValues(int progrmaEducativoId,List<TipoNotaUi.Tipo> tipos) {
            this.progrmaEducativoId = progrmaEducativoId;
            this.tipos = tipos;
        }

        public int getProgrmaEducativoId() {
            return progrmaEducativoId;
        }

        public List<TipoNotaUi.Tipo> getTipos() {
            return tipos;
        }
    }
}
