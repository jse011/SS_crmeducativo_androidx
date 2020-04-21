package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;


import java.util.List;

/**
 * Created by kike on 13/11/2017.
 */

public class SavedRubroFormula extends UseCaseSincrono<SavedRubroFormula.RequestValues, SavedRubroFormula.ResponseValue> {

    private RubroEvaluacionProcesoListaRepository repository;

    public SavedRubroFormula(RubroEvaluacionProcesoListaRepository repository) {
        this.repository = repository;
    }


    @Override
    public void execute(RequestValues requestValues, final Callback<ResponseValue> callback) {
        repository.getDataSavedRubroEvaluacionProceso(requestValues.getRubroEvaluacionProcesoUi(), requestValues.getCargaCursoId(),requestValues.getRubroEvaluacionProcesoUiList(), new RubroEvaluacionProcesoListaDataSource.CallbackObject<RubroProcesoUi, Boolean>() {
            @Override
            public void onCreateRubroEval(RubroProcesoUi rubroEvaluacionUi, Boolean succes) {

                //getUseCaseCallback().onSuccess(new SavedRubroEvaluacionProceso.ResponseValue(rubroEvaluacionUi,succes));
                callback.onResponse(succes, new ResponseValue(rubroEvaluacionUi, succes));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        RubroProcesoUi  rubroEvaluacionProcesoUi;
        List<RubroProcesoUi> rubroEvaluacionProcesoUiList;
        int cargaCursoId;

        public RequestValues(RubroProcesoUi rubroEvaluacionProcesoUi, List<RubroProcesoUi> rubroEvaluacionProcesoUiList, int cargaCursoId) {
            this.rubroEvaluacionProcesoUi = rubroEvaluacionProcesoUi;
            this.rubroEvaluacionProcesoUiList = rubroEvaluacionProcesoUiList;
            this.cargaCursoId = cargaCursoId;
        }

        public RubroProcesoUi getRubroEvaluacionProcesoUi() {
            return rubroEvaluacionProcesoUi;
        }

        public List<RubroProcesoUi> getRubroEvaluacionProcesoUiList() {
            return rubroEvaluacionProcesoUiList;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue {
        private RubroProcesoUi rubroEvaluacionProcesoUi;
        private Boolean aBooleanSucces;

        public ResponseValue(RubroProcesoUi rubroEvaluacionProcesoUi,Boolean aBooleanSucces) {
            this.rubroEvaluacionProcesoUi = rubroEvaluacionProcesoUi;
            this.aBooleanSucces = aBooleanSucces;
        }

        public RubroProcesoUi getRubroEvaluacionProcesoUi() {
            return rubroEvaluacionProcesoUi;
        }

        public void setRubroEvaluacionProcesoUi(RubroProcesoUi rubroEvaluacionProcesoUi) {
            this.rubroEvaluacionProcesoUi = rubroEvaluacionProcesoUi;
        }

        public Boolean getaBooleanSucces() {
            return aBooleanSucces;
        }
    }
}
