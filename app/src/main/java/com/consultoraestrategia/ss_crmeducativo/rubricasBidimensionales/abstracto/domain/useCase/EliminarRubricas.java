package com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.data.source.RubricaBidDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.data.source.RubricaBidRepository;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubBidUi;
import com.consultoraestrategia.ss_crmeducativo.rubricasBidimensionales.abstracto.entidades.RubEvalProcUi;

import java.util.List;

/**
 * Created by kike on 08/06/2018.
 */

public class EliminarRubricas extends UseCase<EliminarRubricas.ResquestValues, EliminarRubricas.ResponseValue> {

    RubricaBidRepository repository;

    public EliminarRubricas(RubricaBidRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(final ResquestValues requestValues) {
        repository.deleteRubricaBidItem(requestValues.getRubBidUi(), new RubricaBidDataSource.CallBackEliminarRubricaBid() {
            @Override
            public void onEliminarRubricaBid(RubBidUi rubBidUi, int estado, List<RubEvalProcUi> rubEvalProcUis) {

                StringBuilder mensaje = new StringBuilder();
                if(rubEvalProcUis!=null){
                    mensaje.append("Rubros Formula relacionados: \n");
                    for (RubEvalProcUi rubEvalProcUi : rubEvalProcUis){
                        mensaje.append(rubEvalProcUi.getTitulo()).append("\n");
                    }
                }

                getUseCaseCallback().onSuccess(new ResponseValue(rubBidUi,estado,mensaje.toString()));
            }
        });
    }

    public static final class ResquestValues implements UseCase.RequestValues {
        RubBidUi rubBidUi;

        public ResquestValues(RubBidUi rubBidUi) {
            this.rubBidUi = rubBidUi;
        }

        public RubBidUi getRubBidUi() {
            return rubBidUi;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        RubBidUi rubBidUi;
        int estado;
        String mensaje;

        public ResponseValue(RubBidUi rubBidUi, int estado, String mensaje) {
            this.rubBidUi = rubBidUi;
            this.estado = estado;
            this.mensaje = mensaje;
        }

        public RubBidUi getRubBidUi() {
            return rubBidUi;
        }

        public int getEstado() {
            return estado;
        }

        public String getMensaje() {
            return mensaje;
        }
    }
}
