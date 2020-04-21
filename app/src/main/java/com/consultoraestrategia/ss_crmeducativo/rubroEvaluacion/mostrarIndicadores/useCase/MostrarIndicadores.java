package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.mostrarIndicadores.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.data.source.CasoUsoRepository;

/**
 * Created by kike on 10/05/2018.
 */

public class MostrarIndicadores extends UseCase<MostrarIndicadores.RequestValues,MostrarIndicadores.ResponseValue> {

    CasoUsoRepository repository;

    public MostrarIndicadores(CasoUsoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

    }

    public static final class RequestValues implements UseCase.RequestValues{
        private String rubroProcesoKey;

        public RequestValues(String rubroProcesoKey) {
            this.rubroProcesoKey = rubroProcesoKey;
        }

        public String getRubroProcesoKey() {
            return rubroProcesoKey;
        }
    }
    public static final class ResponseValue implements UseCase.ResponseValue{

    }
}
