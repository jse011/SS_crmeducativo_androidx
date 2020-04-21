package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.dataSource.EditarRubricaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.dataSource.EditarRubricaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.entidad.TipoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;

import java.util.List;

public class TipoEvaluacionLista extends UseCase<TipoEvaluacionLista.RequestValues, TipoEvaluacionLista.ResponseValue> {

    private EditarRubricaRepository repository;

    public TipoEvaluacionLista(EditarRubricaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.obtenerTipoEvaluacionLista(requestValues.getRubBidUi(), new EditarRubricaDataSource.Callback<List<TipoEvaluacionUi>>() {
            @Override
            public void onSuccess(List<TipoEvaluacionUi> objeto) {
                getUseCaseCallback().onSuccess(new ResponseValue(objeto));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private RubBidUi rubBidUi;

        public RequestValues(RubBidUi rubBidUi) {
            this.rubBidUi = rubBidUi;
        }

        public RubBidUi getRubBidUi() {
            return rubBidUi;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<TipoEvaluacionUi> tipoNotaUiList;

        public ResponseValue(List<TipoEvaluacionUi> tipoNotaUiList) {
            this.tipoNotaUiList = tipoNotaUiList;
        }

        public List<TipoEvaluacionUi> getTipoNotaUiList() {
            return tipoNotaUiList;
        }
    }
}
