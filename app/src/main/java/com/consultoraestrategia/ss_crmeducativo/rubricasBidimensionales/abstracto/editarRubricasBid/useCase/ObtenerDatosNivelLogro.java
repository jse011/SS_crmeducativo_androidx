package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.dataSource.EditarRubricaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.editarRubricasBid.dataSource.EditarRubricaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;

public class ObtenerDatosNivelLogro extends UseCase<ObtenerDatosNivelLogro.RequestValues,ObtenerDatosNivelLogro.ResponseValue> {
    private EditarRubricaRepository repository;

    public ObtenerDatosNivelLogro(EditarRubricaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.obtenerRegistroTipoNota(requestValues.getRubBidUi(), new EditarRubricaDataSource.CallBackRegistro<String, String>() {
            @Override
            public void onSuccess(String objetoT, String objetoP) {
                getUseCaseCallback().onSuccess(new ResponseValue(objetoT,objetoP));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private RubBidUi rubBidUi;

        public RequestValues(RubBidUi rubBidUi) {
            this.rubBidUi = rubBidUi;
        }

        public RubBidUi getRubBidUi() {
            return rubBidUi;
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
