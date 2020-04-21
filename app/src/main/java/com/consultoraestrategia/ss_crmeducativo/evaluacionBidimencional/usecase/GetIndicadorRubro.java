package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.IndicadorUi;

/**
 * Created by SCIEV on 10/04/2018.
 */

public class GetIndicadorRubro extends UseCase<GetIndicadorRubro.RequestValues, GetIndicadorRubro.ResponseValue> implements  EvalRubBidDataSource.Callback<IndicadorUi> {

    private EvalRubBidRepository repository;

    public GetIndicadorRubro(EvalRubBidRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getIndicador(requestValues, this);
    }

    @Override
    public void onLoaded(IndicadorUi object) {
        getUseCaseCallback().onSuccess(new ResponseValue(object));
    }

    public static class RequestValues implements UseCase.RequestValues {
        private int indicadorId;
        private String rubroEvalProcesoId;

        public RequestValues(int indicadorId, String rubroEvalProcesoId) {
            this.indicadorId = indicadorId;
            this.rubroEvalProcesoId = rubroEvalProcesoId;
        }

        public int getIndicadorId() {
            return indicadorId;
        }

        public String getRubroEvalProcesoId() {
            return rubroEvalProcesoId;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue {
        private IndicadorUi indicadorUi;

        public ResponseValue(IndicadorUi indicadorUi) {
            this.indicadorUi = indicadorUi;
        }

        public IndicadorUi getIndicadorUi() {
            return indicadorUi;
        }
    }

}
