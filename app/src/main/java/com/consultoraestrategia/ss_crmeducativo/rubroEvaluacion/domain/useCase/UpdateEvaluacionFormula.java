package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;

/**
 * Created by kike on 14/05/2018.
 */

public class UpdateEvaluacionFormula extends UseCase<UpdateEvaluacionFormula.RequestValues, UpdateEvaluacionFormula.ResponseValue> {

    private RubroEvaluacionProcesoListaRepository repository;

    public UpdateEvaluacionFormula(RubroEvaluacionProcesoListaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.onUpdateEvaluacionFormula(requestValues.getRubroEvalProcesoId(),requestValues.getPersonaId(),requestValues.getEquipoId(), new RubroEvaluacionProcesoListaDataSource.SimpleSuccessCallBack() {
            @Override
            public void onSuccess(boolean success) {
                if(success){
                    getUseCaseCallback().onSuccess(new ResponseValue());
                }else {
                    getUseCaseCallback().onError();
                }
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String rubroEvalProcesoId;
        private int personaId;
        private String equipoId;

        public RequestValues(String rubroEvalProcesoId, int personaId, String rubroEquipoId) {
            this.rubroEvalProcesoId = rubroEvalProcesoId;
            this.personaId = personaId;
            this.equipoId = rubroEquipoId;
        }


        public String getRubroEvalProcesoId() {
            return rubroEvalProcesoId;
        }

        public int getPersonaId() {
            return personaId;
        }

        public String getEquipoId() {
            return equipoId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

    }
}
