package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.dataSource.EditarRubricaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.dataSource.EditarRubricaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.entidad.TipoNotaUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;

import java.util.List;

public class TipoNotaLista extends UseCase<TipoNotaLista.RequestValues, TipoNotaLista.ResponseValue> {

    private EditarRubricaRepository repository;

    public TipoNotaLista(EditarRubricaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.obtenerTipoNotaLista(requestValues.getRubBidUi(), new EditarRubricaDataSource.Callback<List<TipoNotaUi>>() {
            @Override
            public void onSuccess(List<TipoNotaUi> objeto) {
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
        private List<TipoNotaUi> tipoNotaUiList;

        public ResponseValue(List<TipoNotaUi> tipoNotaUiList) {
            this.tipoNotaUiList = tipoNotaUiList;
        }

        public List<TipoNotaUi> getTipoNotaUiList() {
            return tipoNotaUiList;
        }
    }
}
