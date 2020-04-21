package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoRedondeadoUi;


import java.util.List;

/**
 * Created by kike on 4/11/2017.
 */

public class GetTipoRedondeoList extends UseCase<GetTipoRedondeoList.RequestValues, GetTipoRedondeoList.ResponseValue> {

    private RubroEvaluacionProcesoListaDataSource repository;

    public GetTipoRedondeoList(RubroEvaluacionProcesoListaDataSource repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getTipoRedondeo(new RubroEvaluacionProcesoListaDataSource.Callback<List<TipoRedondeadoUi>>() {
            @Override
            public void onLoaded(List<TipoRedondeadoUi> list) {
                getUseCaseCallback().onSuccess(new ResponseValue(list));
            }
        });

    }

    public static class RequestValues implements UseCase.RequestValues{

    }
    public static class ResponseValue implements UseCase.ResponseValue {
        private List<TipoRedondeadoUi> tipoRedondeadoUiList;

        public ResponseValue(List<TipoRedondeadoUi> tipoRedondeadoUiList) {
            this.tipoRedondeadoUiList = tipoRedondeadoUiList;
        }

        public List<TipoRedondeadoUi> getTipoRedondeadoUiList() {
            return tipoRedondeadoUiList;
        }
    }
}
