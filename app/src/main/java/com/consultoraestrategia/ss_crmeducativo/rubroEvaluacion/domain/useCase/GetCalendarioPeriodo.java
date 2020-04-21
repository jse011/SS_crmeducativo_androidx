package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.PeriodoUi;


/**
 * Created by kike on 09/05/2018.
 */

public class GetCalendarioPeriodo extends UseCase<GetCalendarioPeriodo.RequestValues , GetCalendarioPeriodo.ResponseValues> {

    private RubroEvaluacionProcesoListaRepository repository;

    public GetCalendarioPeriodo(RubroEvaluacionProcesoListaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getCalendarioPeriodo(requestValues.getCalenadrioPeriodoId(), new RubroEvaluacionProcesoListaDataSource.SuccesCallback<PeriodoUi>() {
            @Override
            public void onLoaded(boolean success, PeriodoUi item) {
                if(success) {
                    getUseCaseCallback().onSuccess(new ResponseValues(item));
                }else {
                    getUseCaseCallback().onError();
                }
            }
        });
    }


    public static final class RequestValues implements UseCase.RequestValues {
        private int calenadrioPeriodoId;

        public RequestValues(int calenadrioPeriodoId) {
            this.calenadrioPeriodoId = calenadrioPeriodoId;
        }

        public int getCalenadrioPeriodoId() {
            return calenadrioPeriodoId;
        }
    }

    public final class ResponseValues implements UseCase.ResponseValue {
        private PeriodoUi periodoUi;

        public ResponseValues(PeriodoUi periodoUi) {
            this.periodoUi = periodoUi;
        }

        public PeriodoUi getPeriodoUi() {
            return periodoUi;
        }
    }
}
