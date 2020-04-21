package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.dataSource.EditarRubricaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.dataSource.EditarRubricaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;

public class GuardarCambios extends UseCase<GuardarCambios.RequestValues, GuardarCambios.ResponseValue> {

    EditarRubricaRepository repository;

    public GuardarCambios(EditarRubricaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.guardarCambios(requestValues.getRubBidUi(), requestValues.getTitulo(), requestValues.getAlias(), requestValues.getKeyTipoEvaluacion(), requestValues.getKeyTipoFormaEvaluacion(),
                new EditarRubricaDataSource.Callback<RubBidUi>() {
                    @Override
                    public void onSuccess(RubBidUi objeto) {
                        getUseCaseCallback().onSuccess(new ResponseValue(objeto));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private RubBidUi rubBidUi;
        private String titulo;
        private String alias;
        private String keyTipoEvaluacion;
        private String keyTipoFormaEvaluacion;

        public RequestValues(RubBidUi rubBidUi, String titulo, String alias, String keyTipoEvaluacion, String keyTipoFormaEvaluacion) {
            this.rubBidUi = rubBidUi;
            this.titulo = titulo;
            this.alias = alias;
            this.keyTipoEvaluacion = keyTipoEvaluacion;
            this.keyTipoFormaEvaluacion = keyTipoFormaEvaluacion;
        }

        public RubBidUi getRubBidUi() {
            return rubBidUi;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getAlias() {
            return alias;
        }

        public String getKeyTipoEvaluacion() {
            return keyTipoEvaluacion;
        }

        public String getKeyTipoFormaEvaluacion() {
            return keyTipoFormaEvaluacion;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private RubBidUi rubBidUi;

        public ResponseValue(RubBidUi rubBidUi) {
            this.rubBidUi = rubBidUi;
        }

        public RubBidUi getRubBidUi() {
            return rubBidUi;
        }
    }
}
