package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.dataSource.EditarRubrosDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.dataSource.EditarRubrosRepository;

public class ObtenerTipoFormula extends UseCase<ObtenerTipoFormula.RequestValues, ObtenerTipoFormula.ResponseValue>{

    private EditarRubrosRepository repository;

    public ObtenerTipoFormula(EditarRubrosRepository repository) {
        this.repository = repository;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.obtenerTipoFormula(requestValues.getRubroProcesoUi(), new EditarRubrosDataSource.CallBackRegistro<String, String>() {
            @Override
            public void onSuccess(String key, String nombre) {
                getUseCaseCallback().onSuccess(new ResponseValue(key,nombre));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private RubroProcesoUi rubroProcesoUi;

        public RequestValues(RubroProcesoUi rubroProcesoUi) {
            this.rubroProcesoUi = rubroProcesoUi;
        }

        public RubroProcesoUi getRubroProcesoUi() {
            return rubroProcesoUi;
        }

    }
    public static final class ResponseValue implements UseCase.ResponseValue{
        private String key;
        private String nombre;

        public ResponseValue(String key, String nombre) {
            this.key = key;
            this.nombre = nombre;
        }

        public String getKey() {
            return key;
        }

        public String getNombre() {
            return nombre;
        }
    }
}
