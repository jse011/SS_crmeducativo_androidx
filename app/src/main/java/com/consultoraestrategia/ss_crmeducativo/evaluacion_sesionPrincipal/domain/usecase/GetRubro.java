package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.RubroRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetRubroCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.FiltroTableUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;

/**
 * Created by SCIEV on 6/10/2017.
 */

public class GetRubro extends UseCase<GetRubro.RequestValues, GetRubro.ResponseValue> {

    private RubroRepository repository;

    public GetRubro(RubroRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(GetRubro.RequestValues requestValues) {
        this.repository.getRubro(requestValues.getRubroEvaluacionId(), new GetRubroCallback() {
            @Override
            public void onRecursoLoad(RubroEvaluacionUi rubrosEvaluacionUi) {
                getUseCaseCallback().onSuccess(new ResponseValue(rubrosEvaluacionUi));
            }

            @Override
            public void onError(String error) {
                getUseCaseCallback().onError();
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private String rubroEvaluacionId;

        public RequestValues(String rubroEvaluacionId) {
            this.rubroEvaluacionId = rubroEvaluacionId;
        }

        public String getRubroEvaluacionId() {
            return rubroEvaluacionId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue{
        private RubroEvaluacionUi rubroEvaluacionUi;

        public ResponseValue(RubroEvaluacionUi rubroEvaluacionUi) {
            this.rubroEvaluacionUi = rubroEvaluacionUi;
        }

        public RubroEvaluacionUi getRubroEvaluacionUi() {
            return rubroEvaluacionUi;
        }
    }
}
