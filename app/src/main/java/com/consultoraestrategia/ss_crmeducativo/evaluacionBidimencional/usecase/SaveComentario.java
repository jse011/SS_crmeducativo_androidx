package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.MensajeUi;

public class SaveComentario  {


    private EvalRubBidRepository evalRubBidRepository;

    public SaveComentario(EvalRubBidRepository evalRubBidRepository) {
        this.evalRubBidRepository = evalRubBidRepository;
    }


    public Response execute(Requests request){
        return new Response(evalRubBidRepository.saveComentario(request.getMensajeUi()));

    }
    public static class Requests {
        MensajeUi mensajeUi;

        public Requests(MensajeUi mensajeUi) {
            this.mensajeUi = mensajeUi;
        }

        public MensajeUi getMensajeUi() {
            return mensajeUi;
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
