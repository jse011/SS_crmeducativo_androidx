package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.data.source.RubroEvaluacionProcesoListaRepository;

public class ValidarRubroProceso {
    RubroEvaluacionProcesoListaRepository rubroEvaluacionProcesoListaRepository;

    public ValidarRubroProceso(RubroEvaluacionProcesoListaRepository rubroEvaluacionProcesoListaRepository) {
        this.rubroEvaluacionProcesoListaRepository = rubroEvaluacionProcesoListaRepository;
    }

    public Response execute(Request request){
        return new Response(rubroEvaluacionProcesoListaRepository.validarEvaluacionRubroNormal(request.getIdRubroProceso()));
    }


    public static class Request {
        private String idRubroProceso;

        public Request(String idRubroProceso) {
            this.idRubroProceso = idRubroProceso;
        }

        public String getIdRubroProceso() {
            return idRubroProceso;
        }
    }
    public class Response{
        private boolean success;

        public Response(boolean success) {
            this.success = success;
        }

        public boolean isSuccess() {
            return success;
        }
    }

}
