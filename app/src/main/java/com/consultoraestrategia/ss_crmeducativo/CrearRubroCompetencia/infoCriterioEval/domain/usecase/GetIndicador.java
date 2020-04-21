package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.data.source.InfoCriterioEvalDataSource;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.data.source.InfoCriterioEvalRepository;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.infoCriterioEval.entities.IndicadorUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;

public class GetIndicador extends UseCase<GetIndicador.RequestValues, GetIndicador.ResponseValues> {

    private InfoCriterioEvalRepository mRepository;

    public GetIndicador(InfoCriterioEvalRepository mRepository) {
        this.mRepository = mRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        mRepository.getInformacionIndicador(requestValues.getIndicadorId(),
                new InfoCriterioEvalDataSource.Callback<IndicadorUi>() {
                    @Override
                    public void onLoad(boolean success, IndicadorUi item) {
                        if(success){
                            getUseCaseCallback().onSuccess(new ResponseValues(item));
                        }else {
                            getUseCaseCallback().onError();
                        }
                    }
                }
        );
    }

    public static final class RequestValues implements UseCase.RequestValues  {
        private int indicadorId;

        public RequestValues(int indicadorId) {
            this.indicadorId = indicadorId;
        }

        public int getIndicadorId() {
            return indicadorId;
        }

        public void setIndicadorId(int indicadorId) {
            this.indicadorId = indicadorId;
        }
    }


    public static final class ResponseValues implements UseCase.ResponseValue{
        private IndicadorUi indicadorUi;

        public ResponseValues(IndicadorUi indicadorUi) {
            this.indicadorUi = indicadorUi;
        }

        public IndicadorUi getIndicadorUi() {
            return indicadorUi;
        }

        public void setIndicadorUi(IndicadorUi indicadorUi) {
            this.indicadorUi = indicadorUi;
        }
    }
}
