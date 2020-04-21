package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.dataSource.EditarRubrosDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.dataSource.EditarRubrosRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.entidad.TipoFormaUi;
import java.util.List;

public class TipoFormaLista extends UseCase<TipoFormaLista.RequestValues, TipoFormaLista.ResponseValue> {

    private EditarRubrosRepository repository;

    public TipoFormaLista(EditarRubrosRepository repository) {
        this.repository = repository;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.obtenerTipoFormaLista(requestValues.getRubroProcesoUi(), new EditarRubrosDataSource.Callback<List<TipoFormaUi>>() {
            @Override
            public void onSuccess(List<TipoFormaUi> objeto) {
                getUseCaseCallback().onSuccess(new ResponseValue(objeto));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private RubroProcesoUi rubroProcesoUi;

        public RequestValues(RubroProcesoUi rubroProcesoUi) {
            this.rubroProcesoUi = rubroProcesoUi;
        }

        public RubroProcesoUi getRubroProcesoUi() {
            return rubroProcesoUi;
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
