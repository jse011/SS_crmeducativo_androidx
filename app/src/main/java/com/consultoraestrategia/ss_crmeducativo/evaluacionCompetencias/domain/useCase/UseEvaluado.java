package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source.EvaluacionCompetenciaDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source.EvaluacionCompetenciaRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.CapacidadUi;

/**
 * Created by kike on 28/05/2018.
 */

public class UseEvaluado extends UseCaseSincrono<UseEvaluado.RequestValues, UseEvaluado.ResponseValue> {

    private EvaluacionCompetenciaRepository repository;

    public UseEvaluado(EvaluacionCompetenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(RequestValues request, final Callback<ResponseValue> callback) {
        repository.onEvaluacionResultado(request.getObject(), request.getCalendarioPeriodoId(), new EvaluacionCompetenciaDataSource.ObjectCallback() {
            @Override
            public void onObject(CapacidadUi capacidadUi, boolean success) {
                callback.onResponse(success, new ResponseValue(capacidadUi, success));
            }

            @Override
            public void onError(String mensaje) {
                callback.onResponse(false, new ResponseValue(mensaje));
            }
        });
    }

    public static  class RequestValues  {
        private Object object;
        private int calendarioPeriodoId;

        public RequestValues(Object object, int calendarioPeriodoId) {
            this.object = object;
            this.calendarioPeriodoId = calendarioPeriodoId;
        }

        public Object getObject() {
            return object;
        }

        public int getCalendarioPeriodoId() {
            return calendarioPeriodoId;
        }
    }

    public static class ResponseValue  {
        private CapacidadUi capacidadUi;
        private boolean success;
        private String mensaje;

        public ResponseValue(CapacidadUi capacidadUi, boolean success) {
            this.capacidadUi = capacidadUi;
            this.success = success;
        }

        public ResponseValue(String mensaje) {
            this.mensaje = mensaje;
        }

        public boolean isSuccess() {
            return success;
        }

        public CapacidadUi getCapacidadUi() {
            return capacidadUi;
        }

        public String getMensaje() {
            return mensaje;
        }
    }
}
