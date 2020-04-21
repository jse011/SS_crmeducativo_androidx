package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.CrearRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback.GetListCriterioEvaluacionCallback;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CrearRubroEvalUi;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.ValorTipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;

import java.util.List;

/**
 * Created by SCIEV on 16/10/2017.
 */

public class GetCriterioEval extends UseCase<GetCriterioEval.RequestValues, GetCriterioEval.ResponseValue> {
    private CrearRubroRepository repository;

    public GetCriterioEval(CrearRubroRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

        repository.GetCriterioEvaluacion(requestValues.getCrearRubroEvalUi(), new GetListCriterioEvaluacionCallback() {
            @Override
            public void onTipoNotaLoad(List<ValorTipoNotaUi> valorTipoNotaUis) {
                getUseCaseCallback().onSuccess(new ResponseValue(valorTipoNotaUis));
            }

            @Override
            public void onError(String error) {
                getUseCaseCallback().onError();
            }
        });

    }
    public static final class RequestValues implements UseCase.RequestValues{
        private CrearRubroEvalUi crearRubroEvalUi;

        public RequestValues(CrearRubroEvalUi crearRubroEvalUi) {
            this.crearRubroEvalUi = crearRubroEvalUi;
        }

        public CrearRubroEvalUi getCrearRubroEvalUi() {
            return crearRubroEvalUi;
        }
    }
    public static final class ResponseValue implements UseCase.ResponseValue{
        private List<ValorTipoNotaUi> valorTipoNotaUis;

        public ResponseValue(List<ValorTipoNotaUi> valorTipoNotaUis) {
            this.valorTipoNotaUis = valorTipoNotaUis;
        }

        public List<ValorTipoNotaUi> getValorTipoNotaUis() {
            return valorTipoNotaUis;
        }
    }
}
