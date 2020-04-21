package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaDataSource;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.CapacidadUi;
import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.entities.RubroProcesoUi;

/**
 * Created by kike on 18/05/2018.
 */

public class AnclarUse extends UseCaseSincrono<AnclarUse.RequestValues,AnclarUse.ResponseValue> {

    private RubroEvaluacionProcesoListaRepository repository;

    public AnclarUse(RubroEvaluacionProcesoListaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(RequestValues requestValues, final Callback<ResponseValue> callback) {
        repository.useCaseAnclar(requestValues.getCapacidadUi(), requestValues.getRubroProcesoUi(), new RubroEvaluacionProcesoListaDataSource.ObjetoCallback<CapacidadUi, RubroProcesoUi>() {
            @Override
            public void onObject(CapacidadUi capacidad, RubroProcesoUi rubroProceso) {
                callback.onResponse(true, new ResponseValue(capacidad,rubroProceso, "", true));
            }

            @Override
            public void onError(String mensaje) {
                callback.onResponse(true,new ResponseValue(null,null, mensaje, false));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private CapacidadUi capacidadUi;
        private RubroProcesoUi rubroProcesoUi;

        public RequestValues(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi) {
            this.capacidadUi = capacidadUi;
            this.rubroProcesoUi = rubroProcesoUi;
        }

        public CapacidadUi getCapacidadUi() {
            return capacidadUi;
        }

        public RubroProcesoUi getRubroProcesoUi() {
            return rubroProcesoUi;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue{
        private CapacidadUi capacidadUi;
        private RubroProcesoUi rubroProcesoUi;
        private String mensaje;
        private boolean success;


        public ResponseValue(CapacidadUi capacidadUi, RubroProcesoUi rubroProcesoUi, String mensaje, boolean success) {
            this.capacidadUi = capacidadUi;
            this.rubroProcesoUi = rubroProcesoUi;
            this.mensaje = mensaje;
            this.success = success;
        }

        public CapacidadUi getCapacidadUi() {
            return capacidadUi;
        }

        public RubroProcesoUi getRubroProcesoUi() {
            return rubroProcesoUi;
        }

        public String getMensaje() {
            return mensaje;
        }

        public boolean isSuccess() {
            return success;
        }
    }
}
