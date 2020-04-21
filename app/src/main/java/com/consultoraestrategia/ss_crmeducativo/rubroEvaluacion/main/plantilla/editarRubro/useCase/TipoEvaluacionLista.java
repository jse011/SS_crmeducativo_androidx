package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.dataSource.EditarRubrosDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.dataSource.EditarRubrosRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.entidad.TipoEvaluacionUi;

import java.util.List;

public class TipoEvaluacionLista extends UseCase<TipoEvaluacionLista.RequestValues, TipoEvaluacionLista.ResponseValue> {

    private EditarRubrosRepository repository;

    public TipoEvaluacionLista(EditarRubrosRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.obtenerTipoEvaluacionLista(requestValues.getRubroProcesoUi(), new EditarRubrosDataSource.Callback<List<TipoEvaluacionUi>>() {
            @Override
            public void onSuccess(List<TipoEvaluacionUi> objeto) {
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
        private List<TipoEvaluacionUi> tipoNotaUiList;

        public ResponseValue(List<TipoEvaluacionUi> tipoNotaUiList) {
            this.tipoNotaUiList = tipoNotaUiList;
        }

        public List<TipoEvaluacionUi> getTipoNotaUiList() {
            return tipoNotaUiList;
        }
    }
}
