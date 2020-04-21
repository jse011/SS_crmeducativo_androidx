package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.dataSource.EditarRubricaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.dataSource.EditarRubricaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.entidad.TipoFormaUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;

import java.util.List;

public class TipoFormaLista extends UseCase<TipoFormaLista.RequestValues, TipoFormaLista.ResponseValue> {

    private EditarRubricaRepository repository;

    public TipoFormaLista(EditarRubricaRepository repository) {
        this.repository = repository;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.obtenerTipoFormaLista(requestValues.getRubBidUi(), new EditarRubricaDataSource.Callback<List<TipoFormaUi>>() {
            @Override
            public void onSuccess(List<TipoFormaUi> objeto) {
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
        private List<TipoFormaUi> tipoFormaUiList;

        public ResponseValue(List<TipoFormaUi> tipoNotaUiList) {
            this.tipoFormaUiList = tipoNotaUiList;
        }

        public List<TipoFormaUi> getTipoNotaUiList() {
            return tipoFormaUiList;
        }
    }
}
