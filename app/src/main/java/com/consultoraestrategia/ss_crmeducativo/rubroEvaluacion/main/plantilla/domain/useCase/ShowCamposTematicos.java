package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.data.source.CasoUsoDataSource;

/**
 * Created by kike on 25/04/2018.
 */

public class ShowCamposTematicos extends UseCase<ShowCamposTematicos.RequestValues,ShowCamposTematicos.ResponseValues> {

    private CasoUsoDataSource repository;

    public ShowCamposTematicos(CasoUsoDataSource repository) {
        this.repository = repository;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {
       /* LoginPreferentRepository.showListCamposTematicos(requestValues.getRubroEvaluacionProcesoUi(), new CasoUsoDataSource.ListCamposTematicosCallBack() {
            @Override
            public void onListCamposTematicos(List<Object> camposTematicosUis) {
                getUseCaseCallback().onSuccess(new ResponseValues(camposTematicosUis));
            }
        });*/
    }

    public static final class RequestValues implements UseCase.RequestValues{
         /*RubroProcesoUi rubroEvaluacionProcesoUi;

        public RequestValues(RubroProcesoUi rubroEvaluacionProcesoUi) {
            this.rubroEvaluacionProcesoUi = rubroEvaluacionProcesoUi;
        }

        public RubroProcesoUi getRubroEvaluacionProcesoUi() {
            return rubroEvaluacionProcesoUi;
        }

        public void setRubroEvaluacionProcesoUi(RubroProcesoUi rubroEvaluacionProcesoUi) {
            this.rubroEvaluacionProcesoUi = rubroEvaluacionProcesoUi;
        }*/
    }
    public static final class ResponseValues implements UseCase.ResponseValue{
       /* List<Object> camposTematicosUis;

        public ResponseValues(List<Object> camposTematicosUis) {
            this.camposTematicosUis = camposTematicosUis;
        }

        public List<Object> getCamposTematicosUis() {
            return camposTematicosUis;
        }*/

    }
}
