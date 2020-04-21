package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;

/**
 * Created by kike on 08/12/2017.
 */

public class DeleteRubroProceso extends UseCaseSincrono<DeleteRubroProceso.RequestValues,DeleteRubroProceso.ResponseValue> {

    private RubroEvaluacionProcesoListaRepository repository;

    public DeleteRubroProceso(RubroEvaluacionProcesoListaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(RequestValues requestValues, final Callback<ResponseValue> callback) {
        repository.deleteRubroEvaluacionProceso(requestValues.getRubroProcesoUi(), new RubroEvaluacionProcesoListaDataSource.ObjectCallback() {
            @Override
            public void onDelete(RubroProcesoUi rubroProcesoUi, int validateSuccess) {
                callback.onResponse(true, new ResponseValue(rubroProcesoUi,validateSuccess));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private RubroProcesoUi rubroProcesoUi;

        public RequestValues(RubroProcesoUi rubroProcesoUi) {
            this.rubroProcesoUi = rubroProcesoUi;
        }

        public RubroProcesoUi getRubroProcesoUi() {
            return rubroProcesoUi;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private RubroProcesoUi rubroEvaluacionProcesoUi;
        private int validateSuccess;

        public ResponseValue(RubroProcesoUi rubroEvaluacionProcesoUi, int validateSuccess) {
            this.rubroEvaluacionProcesoUi = rubroEvaluacionProcesoUi;
            this.validateSuccess = validateSuccess;
        }

        public RubroProcesoUi getRubroEvaluacionProcesoUi() {
            return rubroEvaluacionProcesoUi;
        }

        public int getValidateSuccess() {
            return validateSuccess;
        }
    }
}
