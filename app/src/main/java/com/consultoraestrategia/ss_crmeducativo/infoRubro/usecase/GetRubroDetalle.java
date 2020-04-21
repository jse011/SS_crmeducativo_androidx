package com.consultoraestrategia.ss_crmeducativo.infoRubro.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.data.source.InfoRubroDataSource;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.data.source.InfoRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.infoRubro.entities.RubroEvalProcesoUi;

/**
 * Created by SCIEV on 29/08/2018.
 */

public class GetRubroDetalle extends UseCase<GetRubroDetalle.RequestValues, GetRubroDetalle.ResponseValue> {

    private InfoRubroRepository repository;

    public GetRubroDetalle(InfoRubroRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getInformacionRubro(requestValues.getRubroEvalProcesoId(),
                new InfoRubroDataSource.Callback<RubroEvalProcesoUi>() {
                    @Override
                    public void onLoad(boolean success, RubroEvalProcesoUi item) {
                        if(success){
                            getUseCaseCallback().onSuccess(new ResponseValue(item));
                        }else {
                            getUseCaseCallback().onError();
                        }
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String rubroEvalProcesoId;

        public RequestValues(String rubroEvalProcesoId) {
            this.rubroEvalProcesoId = rubroEvalProcesoId;
        }

        public String getRubroEvalProcesoId() {
            return rubroEvalProcesoId;
        }

    }


    public final class ResponseValue implements UseCase.ResponseValue{
        private RubroEvalProcesoUi rubroEvalProcesoUi;

        public ResponseValue(RubroEvalProcesoUi rubroEvalProcesoUi) {
            this.rubroEvalProcesoUi = rubroEvalProcesoUi;
        }

        public RubroEvalProcesoUi getRubroEvalProcesoUi() {
            return rubroEvalProcesoUi;
        }
    }
}
