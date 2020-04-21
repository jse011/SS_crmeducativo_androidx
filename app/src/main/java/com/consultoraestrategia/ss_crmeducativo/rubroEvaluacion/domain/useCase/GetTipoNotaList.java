package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaDataSource;

/**
 * Created by kike on 4/11/2017.
 */

public class GetTipoNotaList extends UseCase<GetTipoNotaList.RequestValues, GetTipoNotaList.ResponseValue> {

    private RubroEvaluacionProcesoListaDataSource repository;

    public GetTipoNotaList(RubroEvaluacionProcesoListaDataSource repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        /*LoginPreferentRepository.getTipoNota(new CrearRubroFormulaDataSource.Callback<List<TipoNotaUi>>() {
            @Override
            public void onLoaded(List<TipoNotaUi> list) {
                getUseCaseCallback().onSuccess(new ResponseValue(list));
            }
        });*/
       /* LoginPreferentRepository.getTipoNota(new CrearRubroFormulaDataSource.CallbackObject<String, String>() {
            @Override
            public void onCreateRubroEval(String rubroEvaluacionUi, String succes) {
                getUseCaseCallback().onSuccess(new ResponseValue(rubroEvaluacionUi,succes));
            }
        });*/
       repository.getTipoNota(requestValues.getTipoNotaId(), new RubroEvaluacionProcesoListaDataSource.CallbackObject<String, String>() {
           @Override
           public void onCreateRubroEval(String rubroEvaluacionUi, String succes) {
               getUseCaseCallback().onSuccess(new ResponseValue(rubroEvaluacionUi,succes));
           }
       });

    }

    public static class RequestValues implements UseCase.RequestValues{
        private String tipoNotaId;

        public RequestValues(String tipoNotaId) {
            this.tipoNotaId = tipoNotaId;
        }

        public String getTipoNotaId() {
            return tipoNotaId;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        private String tipoNotaId;
        private String tipoNotaNombre;

        public ResponseValue(String tipoNotaId, String tipoNotaNombre) {
            this.tipoNotaId = tipoNotaId;
            this.tipoNotaNombre = tipoNotaNombre;
        }

        public String getTipoNotaId() {
            return tipoNotaId;
        }

        public String getTipoNotaNombre() {
            return tipoNotaNombre;
        }

        /* private List<TipoNotaUi> tipoNotaUiList;

        public ResponseValue(List<TipoNotaUi> tipoNotaUiList) {
            this.tipoNotaUiList = tipoNotaUiList;
        }

        public List<TipoNotaUi> getTipoNotaUiList() {
            return tipoNotaUiList;
        }*/
    }
}
