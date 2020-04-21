package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.IndicadoresCamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.data.source.CasoUsoDataSource;

import java.util.List;

/**
 * Created by kike on 09/05/2018.
 */

public class MostrarCamposAccion extends UseCase<MostrarCamposAccion.RequestValues, MostrarCamposAccion.ResponseValues> {

    private CasoUsoDataSource repository;

    public MostrarCamposAccion(CasoUsoDataSource repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.showListCamposTematicos(requestValues.getRubroEvaluacionKey(), new CasoUsoDataSource.ObjectCallbackList<List<IndicadoresCamposAccionUi>>() {
            @Override
            public void onLoadList(List<IndicadoresCamposAccionUi> list) {
                getUseCaseCallback().onSuccess(new ResponseValues(list));
            }
        });
    }


    public static final class RequestValues implements UseCase.RequestValues {
        private String rubroEvaluacionKey;

        public RequestValues(String rubroEvaluacionKey) {
            this.rubroEvaluacionKey = rubroEvaluacionKey;
        }

        public String getRubroEvaluacionKey() {
            return rubroEvaluacionKey;
        }
    }

    public static final class ResponseValues implements UseCase.ResponseValue {
        List<IndicadoresCamposAccionUi> indicadoresCamposAccionUis;

        public ResponseValues(List<IndicadoresCamposAccionUi> indicadoresCamposAccionUis) {
            this.indicadoresCamposAccionUis = indicadoresCamposAccionUis;
        }

        public List<IndicadoresCamposAccionUi> getIndicadoresCamposAccionUis() {
            return indicadoresCamposAccionUis;
        }
    }
}
