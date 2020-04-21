package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.RubroRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetNotaEvaluacionListCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;
import java.util.List;

/**
 * Created by SCIEV on 9/10/2017.
 */

public class GetNotaEvaluacionList extends UseCase<GetNotaEvaluacionList.RequestValues, GetNotaEvaluacionList.ResponseValue> {

    private RubroRepository repository;

    public GetNotaEvaluacionList(RubroRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getNotaEvaluacionList(requestValues.getRubroEvaluacionUi(),requestValues.getItemEvaluacionUis(),new GetNotaEvaluacionListCallback() {
            @Override
            public void onRecursoLoad(List<GrupoEvaluacionUi> itemEvaluacionUis) {
                getUseCaseCallback().onSuccess(new ResponseValue(itemEvaluacionUis));
            }

            @Override
            public void onError(String errot) {
                getUseCaseCallback().onError();
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private RubroEvaluacionUi rubroEvaluacionUi;
        List<GrupoEvaluacionUi> itemEvaluacionUis;

        public RequestValues(RubroEvaluacionUi rubroEvaluacionUi, List<GrupoEvaluacionUi> itemEvaluacionUis) {
            this.rubroEvaluacionUi = rubroEvaluacionUi;
            this.itemEvaluacionUis = itemEvaluacionUis;
        }

        public RubroEvaluacionUi getRubroEvaluacionUi() {
            return rubroEvaluacionUi;
        }

        public List<GrupoEvaluacionUi> getItemEvaluacionUis() {
            return itemEvaluacionUis;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        List<GrupoEvaluacionUi> itemEvaluacionUis;

        public ResponseValue(List<GrupoEvaluacionUi> itemEvaluacionUis) {
            this.itemEvaluacionUis = itemEvaluacionUis;
        }

        public List<GrupoEvaluacionUi> getItemEvaluacionUis() {
            return itemEvaluacionUis;
        }
    }
}
