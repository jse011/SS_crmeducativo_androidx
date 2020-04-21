package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.RubroRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetIndicadorListCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;

import java.util.List;

/**
 * Created by SCIEV on 30/10/2017.
 */

public class GetIndicadorList extends  UseCase<GetIndicadorList.RequestValues, GetIndicadorList.ResponseValue> {


    private RubroRepository repository;

    public GetIndicadorList(RubroRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getIndicadoresList(requestValues.getSesionAprendizajeId(),requestValues.getRubroEvaluacionUi(), new GetIndicadorListCallback() {
            @Override
            public void onRecursoLoad(List<IndicadorUi> indicadorUis) {
                getUseCaseCallback().onSuccess(new ResponseValue(indicadorUis));
            }

            @Override
            public void onError(String errot) {
                getUseCaseCallback().onError();
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private int sesionAprendizajeId;
        private RubroEvaluacionUi rubroEvaluacionUi;

        public RequestValues(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi) {
            this.sesionAprendizajeId = sesionAprendizajeId;
            this.rubroEvaluacionUi = rubroEvaluacionUi;
        }

        public int getSesionAprendizajeId() {
            return sesionAprendizajeId;
        }

        public RubroEvaluacionUi getRubroEvaluacionUi() {
            return rubroEvaluacionUi;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        List<IndicadorUi> alumnosEvaluacionUis;

        public ResponseValue(List<IndicadorUi> alumnosEvaluacionUis) {
            this.alumnosEvaluacionUis = alumnosEvaluacionUis;
        }

        public List<IndicadorUi> getAlumnosEvaluacionUis() {
            return alumnosEvaluacionUis;
        }
    }
}
