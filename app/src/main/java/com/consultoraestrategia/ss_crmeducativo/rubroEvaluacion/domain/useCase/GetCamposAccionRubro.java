package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.IndicadoresCamposAccionUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;

import java.util.List;


/**
 * Created by kike on 09/05/2018.
 */

public class GetCamposAccionRubro extends UseCase<GetCamposAccionRubro.RequestValues , GetCamposAccionRubro.ResponseValues> {

    private RubroEvaluacionProcesoListaRepository repository;

    public GetCamposAccionRubro(RubroEvaluacionProcesoListaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.showListCamposTematicosListRubros(requestValues.getRubroProcesoUiList(), new RubroEvaluacionProcesoListaDataSource.Callback<List<IndicadoresCamposAccionUi>>() {
            @Override
            public void onLoaded(List<IndicadoresCamposAccionUi> item) {
                getUseCaseCallback().onSuccess(new ResponseValues(item));
            }
        });
    }


    public static final class RequestValues implements UseCase.RequestValues {
        private List<RubroProcesoUi> rubroProcesoUiList;

        public RequestValues(List<RubroProcesoUi> rubroProcesoUiList) {
            this.rubroProcesoUiList = rubroProcesoUiList;
        }

        public List<RubroProcesoUi> getRubroProcesoUiList() {
            return rubroProcesoUiList;
        }
    }

    public final class ResponseValues implements UseCase.ResponseValue {
        private List<IndicadoresCamposAccionUi> indicadoresCamposAccionUis;

        public ResponseValues(List<IndicadoresCamposAccionUi> indicadoresCamposAccionUis) {
            this.indicadoresCamposAccionUis = indicadoresCamposAccionUis;
        }

        public List<IndicadoresCamposAccionUi> getIndicadoresCamposAccionUis() {
            return indicadoresCamposAccionUis;
        }
    }
}
