package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.RubBidUi;

public class CalcularMediaDesviacion {
    private EvalRubBidRepository repository;

    public CalcularMediaDesviacion(EvalRubBidRepository repository) {
        this.repository = repository;
    }
    public Response execute(Requests request){
        return  new Response(repository.calculaMediaDesviacion(request.getRubrica()));
    }

    public static class Requests {
        RubBidUi Rubrica;

        public Requests(RubBidUi rubrica) {
            Rubrica = rubrica;
        }

        public RubBidUi getRubrica() {
            return Rubrica;
        }
    }
    public static class Response {
       boolean success;

        public Response(boolean success) {
            this.success = success;
        }

        public boolean isSuccess() {
            return success;
        }
    }
}
