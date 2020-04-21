package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.dataSource.EditarRubrosRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.entidad.TipoNotaUi;

import java.util.List;

public class TipoNotaLista extends UseCase<TipoNotaLista.RequestValues, TipoNotaLista.ResponseValue> {

    private EditarRubrosRepository repository;

    public TipoNotaLista(EditarRubrosRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.obtenerTipoNotaLista(requestValues.getRubroProcesoUi(), new EditarRubrosRepository.Callback<List<TipoNotaUi>>() {
            @Override
            public void onSuccess(List<TipoNotaUi> objeto) {
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
        private List<TipoNotaUi> tipoNotaUiList;

        public ResponseValue(List<TipoNotaUi> tipoNotaUiList) {
            this.tipoNotaUiList = tipoNotaUiList;
        }

        public List<TipoNotaUi> getTipoNotaUiList() {
            return tipoNotaUiList;
        }
    }
}
