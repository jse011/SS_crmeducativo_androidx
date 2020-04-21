package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.data.source.RubricaBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.data.source.RubricaBidRepository;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;

/**
 * Created by CCIE on 20/03/2018.
 */

public class GetActualizasRubricas extends UseCase<GetActualizasRubricas.RequestValues, GetActualizasRubricas.ResponseValue> {

    private RubricaBidRepository repository;

    public GetActualizasRubricas(RubricaBidRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getRubricaBidItem(requestValues.getRubroEvalRubId(),requestValues.getCountIndicador(), new RubricaBidDataSource.CallBackRubricaBid() {
            @Override
            public void onActualizacionRubricaBid(RubBidUi rubBidUi) {
                getUseCaseCallback().onSuccess(new ResponseValue(rubBidUi));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues {
        private int rubroEvalRubId;
        private int countIndicador;

        public RequestValues(int rubroEvalRubId, int countIndicador) {
            this.rubroEvalRubId = rubroEvalRubId;
            this.countIndicador = countIndicador;
        }

        public int getRubroEvalRubId() {
            return rubroEvalRubId;
        }

        public int getCountIndicador() {
            return countIndicador;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {
        private RubBidUi rubBidUi;

        public ResponseValue(RubBidUi rubBidUi) {
            this.rubBidUi = rubBidUi;
        }

        public RubBidUi getRubBidUi() {
            return rubBidUi;
        }
    }
}
