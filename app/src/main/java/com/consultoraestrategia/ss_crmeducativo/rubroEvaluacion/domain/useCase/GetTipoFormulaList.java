package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.TipoFormulaUi;


import java.util.List;

/**
 * Created by kike on 4/11/2017.
 */

public class GetTipoFormulaList extends UseCase<GetTipoFormulaList.RequestValues, GetTipoFormulaList.ResponseValue> {

    private RubroEvaluacionProcesoListaDataSource repository;

    public GetTipoFormulaList(RubroEvaluacionProcesoListaDataSource repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getTipoFormula(new RubroEvaluacionProcesoListaDataSource.Callback<List<TipoFormulaUi>>() {
            @Override
            public void onLoaded(List<TipoFormulaUi> list) {
                getUseCaseCallback().onSuccess(new ResponseValue(list));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{

    }
    public static class ResponseValue implements UseCase.ResponseValue{
        private List<TipoFormulaUi> formulaUiList;

        public ResponseValue(List<TipoFormulaUi> formulaUiList) {
            this.formulaUiList = formulaUiList;
        }

        public List<TipoFormulaUi> getFormulaUiList() {
            return formulaUiList;
        }
    }
}
