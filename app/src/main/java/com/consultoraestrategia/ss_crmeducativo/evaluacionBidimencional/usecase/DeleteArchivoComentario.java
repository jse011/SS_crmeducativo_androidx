package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ArchivoUi;

public class DeleteArchivoComentario {


    private EvalRubBidRepository evalRubBidRepository;

    public DeleteArchivoComentario(EvalRubBidRepository evalRubBidRepository) {
        this.evalRubBidRepository = evalRubBidRepository;
    }


    public Response execute(Requests request){
        return new Response(evalRubBidRepository.deleteArchivoComentario(request.getArchivoComentarioUi()));

    }
    public static class Requests {
        ArchivoUi archivoComentarioUi;

        public Requests(ArchivoUi archivoComentarioUi) {
            this.archivoComentarioUi = archivoComentarioUi;
        }

        public ArchivoUi getArchivoComentarioUi() {
            return archivoComentarioUi;
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
