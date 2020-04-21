package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;

/**
 * Created by SCIEV on 6/02/2018.
 */

public class GetRubroProceso extends UseCase<GetRubroProceso.RequestValues,GetRubroProceso.ResponseValue> {

    private RubroEvaluacionProcesoListaRepository repository;

    public GetRubroProceso(RubroEvaluacionProcesoListaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getRubroProceso(requestValues.getRubroEvaluacionProcesoId(), new RubroEvaluacionProcesoListaDataSource.Callback<RubroProcesoUi>() {
            @Override
            public void onLoaded(RubroProcesoUi rubroProcesoUi) {
                getUseCaseCallback().onSuccess(new ResponseValue(rubroProcesoUi));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
       private String rubroEvaluacionProcesoId;

        public RequestValues(String rubroEvaluacionProcesoId) {
            this.rubroEvaluacionProcesoId = rubroEvaluacionProcesoId;
        }

        private String getRubroEvaluacionProcesoId() {
            return rubroEvaluacionProcesoId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue{
        private RubroProcesoUi rubroProcesoUi;

        public ResponseValue(RubroProcesoUi rubroProcesoUi) {
            this.rubroProcesoUi = rubroProcesoUi;
        }

        public RubroProcesoUi getRubroProcesoUi() {
            return rubroProcesoUi;
        }
    }
}
