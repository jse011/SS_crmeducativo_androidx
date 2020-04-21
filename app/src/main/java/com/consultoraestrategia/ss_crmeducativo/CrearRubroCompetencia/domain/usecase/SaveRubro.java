package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.CrearRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.callback.SaveRubroCallBack;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.CrearRubroEvalUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;

/**
 * Created by SCIEV on 16/10/2017.
 */

public class SaveRubro  extends UseCase<SaveRubro.RequestValues, SaveRubro.ResponseValue> {
    private CrearRubroRepository repository;

    public SaveRubro(CrearRubroRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.SaveRubro(requestValues.getUi(), new SaveRubroCallBack() {
            @Override
            public void localSuccess(String rubroEvaluacionId, boolean success) {
                getUseCaseCallback().onSuccess(new ResponseValue(rubroEvaluacionId,success));
            }

        });
    }

    public static final class RequestValues implements UseCase.RequestValues{

        private final CrearRubroEvalUi ui;

        public RequestValues(CrearRubroEvalUi ui) {
            this.ui = ui;
        }

        public CrearRubroEvalUi getUi() {
            return ui;
        }
    }
    public static final class ResponseValue implements UseCase.ResponseValue{
        private String rubroEvlauacionProcesoId;
        private Boolean succes;

        public ResponseValue(String rubroEvlauacionProcesoId, Boolean succes) {
            this.rubroEvlauacionProcesoId = rubroEvlauacionProcesoId;
            this.succes = succes;
        }

        public String getRubroEvlauacionProcesoId() {
            return rubroEvlauacionProcesoId;
        }

        public Boolean getSucces() {
            return succes;
        }
    }
}
