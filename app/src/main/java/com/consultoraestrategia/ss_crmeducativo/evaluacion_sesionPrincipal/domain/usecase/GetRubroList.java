package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.RubroRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetRubroListCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;

import java.util.List;

/**
 * Created by SCIEV on 6/10/2017.
 */

public class GetRubroList extends UseCase<GetRubroList.RequestValues, GetRubroList.ResponseValue> {

    private RubroRepository repository;

    public GetRubroList(RubroRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(GetRubroList.RequestValues requestValues) {
        this.repository.getRubroList(requestValues.getSesionAprendizajeId(),new GetRubroListCallback() {
            @Override
            public void onRecursoLoad(List<RubroEvaluacionUi> rubrosEvaluacionUi) {
                getUseCaseCallback().onSuccess(new ResponseValue(rubrosEvaluacionUi));
            }

            @Override
            public void onError(String error) {
                getUseCaseCallback().onError();
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private final int sesionAprendizajeId;

        public RequestValues(int sesionAprendizajeId) {
            this.sesionAprendizajeId = sesionAprendizajeId;
        }

        public int getSesionAprendizajeId() {
            return sesionAprendizajeId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue{

        private final List<RubroEvaluacionUi> rubroEvaluacionProcesos;

        public ResponseValue(List<RubroEvaluacionUi> rubroEvaluacionProcesos) {
            this.rubroEvaluacionProcesos = rubroEvaluacionProcesos;
        }

        public List<RubroEvaluacionUi> getRubroEvaluacionProcesos() {
            return rubroEvaluacionProcesos;
        }

    }
}
