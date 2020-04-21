package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.CrearRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback.GetListTipoEvaluacionCallback;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.TipoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;

import java.util.List;

/**
 * Created by SCIEV on 16/10/2017.
 */

public class GetTipoEvaluacion extends UseCase<GetTipoEvaluacion.RequestValues, GetTipoEvaluacion.ResponseValue> {
    private CrearRubroRepository repository;

    public GetTipoEvaluacion(CrearRubroRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.GetTipoEvaluacion(new GetListTipoEvaluacionCallback() {
            @Override
            public void onEvaluacionLoad(List<TipoEvaluacionUi> evaluacionUis) {
                getUseCaseCallback().onSuccess(new ResponseValue(evaluacionUis));
            }

            @Override
            public void onError(String error) {
                getUseCaseCallback().onError();
            }
        });
    }
    public static final class RequestValues implements UseCase.RequestValues{

    }
    public static final class ResponseValue implements UseCase.ResponseValue{
        private List<TipoEvaluacionUi> tipoEvaluacionUis;

        public ResponseValue(List<TipoEvaluacionUi> tipoEvaluacionUis) {
            this.tipoEvaluacionUis = tipoEvaluacionUis;
        }

        public List<TipoEvaluacionUi> getTipoEvaluacionUis() {
            return tipoEvaluacionUis;
        }
    }
}
