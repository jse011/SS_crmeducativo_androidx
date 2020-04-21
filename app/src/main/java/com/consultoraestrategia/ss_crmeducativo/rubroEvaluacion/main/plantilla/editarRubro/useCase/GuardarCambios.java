package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.dataSource.EditarRubrosDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.editarRubro.dataSource.EditarRubrosRepository;

public class GuardarCambios extends UseCase<GuardarCambios.RequestValues, GuardarCambios.ResponseValue> {

    private EditarRubrosRepository repository;

    public GuardarCambios(EditarRubrosRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.guardarCambios(requestValues.getCapacidadUi(), requestValues.getRubroProcesoUi(), requestValues.getTitulo(), requestValues.getAlias(), requestValues.getKeyTipoEvaluacion(), requestValues.getKeyTipoFormaEvaluacion(),
                new EditarRubrosDataSource.CallBackRegistro<CapacidadUi, RubroProcesoUi>() {
                    @Override
                    public void onSuccess(CapacidadUi objetoT, RubroProcesoUi objetoP) {
                        getUseCaseCallback().onSuccess(new ResponseValue(objetoT,objetoP));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private CapacidadUi capacidadUi;
        private RubroProcesoUi rubroProcesoUi;
        private String titulo;
        private String alias;
        private String keyTipoEvaluacion;
        private String keyTipoFormaEvaluacion;

        public RequestValues(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, String titulo, String alias, String keyTipoEvaluacion, String keyTipoFormaEvaluacion) {
            this.capacidadUi = capacidadUi;
            this.rubroProcesoUi = rubroProcesoUi;
            this.titulo = titulo;
            this.alias = alias;
            this.keyTipoEvaluacion = keyTipoEvaluacion;
            this.keyTipoFormaEvaluacion = keyTipoFormaEvaluacion;
        }

        public CapacidadUi getCapacidadUi() {
            return capacidadUi;
        }

        public void setCapacidadUi(CapacidadUi capacidadUi) {
            this.capacidadUi = capacidadUi;
        }

        public RubroProcesoUi getRubroProcesoUi() {
            return rubroProcesoUi;
        }

        public void setRubroProcesoUi(RubroProcesoUi rubroProcesoUi) {
            this.rubroProcesoUi = rubroProcesoUi;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getKeyTipoEvaluacion() {
            return keyTipoEvaluacion;
        }

        public void setKeyTipoEvaluacion(String keyTipoEvaluacion) {
            this.keyTipoEvaluacion = keyTipoEvaluacion;
        }

        public String getKeyTipoFormaEvaluacion() {
            return keyTipoFormaEvaluacion;
        }

        public void setKeyTipoFormaEvaluacion(String keyTipoFormaEvaluacion) {
            this.keyTipoFormaEvaluacion = keyTipoFormaEvaluacion;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private CapacidadUi capacidadUi;
        private RubroProcesoUi rubroProcesoUi;

        public ResponseValue(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi) {
            this.capacidadUi = capacidadUi;
            this.rubroProcesoUi = rubroProcesoUi;
        }

        public CapacidadUi getCapacidadUi() {
            return capacidadUi;
        }

        public void setCapacidadUi(CapacidadUi capacidadUi) {
            this.capacidadUi = capacidadUi;
        }

        public RubroProcesoUi getRubroProcesoUi() {
            return rubroProcesoUi;
        }

        public void setRubroProcesoUi(RubroProcesoUi rubroProcesoUi) {
            this.rubroProcesoUi = rubroProcesoUi;
        }
    }
}
