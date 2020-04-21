package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.data.source.CasoUsoDataSource;

import java.util.List;

/**
 * Created by kike on 25/04/2018.
 */

public class ShowDesempenioIcds extends UseCase<ShowDesempenioIcds.RequestValues,ShowDesempenioIcds.ResponseValues> {

    private CasoUsoDataSource repository;

    public ShowDesempenioIcds(CasoUsoDataSource repository) {
        this.repository = repository;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {
       /* LoginPreferentRepository.showListDesempenioIcd(requestValues.getRubroEvaluacionProcesoUi(), new CasoUsoDataSource.ListDesempenioIcdsCallBack() {
            @Override
            public void onListDesempenioIcds(List<Object> desempenioIcdUiList) {
                getUseCaseCallback().onSuccess(new ResponseValues(desempenioIcdUiList));
            }
        });*/
    }

    public static final class RequestValues implements UseCase.RequestValues{
         RubroProcesoUi rubroEvaluacionProcesoUi;

        public RequestValues(RubroProcesoUi rubroEvaluacionProcesoUi) {
            this.rubroEvaluacionProcesoUi = rubroEvaluacionProcesoUi;
        }

        public RubroProcesoUi getRubroEvaluacionProcesoUi() {
            return rubroEvaluacionProcesoUi;
        }

        public void setRubroEvaluacionProcesoUi(RubroProcesoUi rubroEvaluacionProcesoUi) {
            this.rubroEvaluacionProcesoUi = rubroEvaluacionProcesoUi;
        }
    }
    public static final class ResponseValues implements UseCase.ResponseValue{
        List<Object> desempenioList;

        public ResponseValues(List<Object> desempenioList) {
            this.desempenioList = desempenioList;
        }

        public List<Object> getDesempenioList() {
            return desempenioList;
        }

    }
}
