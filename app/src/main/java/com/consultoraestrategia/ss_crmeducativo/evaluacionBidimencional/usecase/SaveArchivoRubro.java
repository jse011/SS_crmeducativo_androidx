package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ArchivoComentarioUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.MensajeUi;

public class SaveArchivoRubro {


    private EvalRubBidRepository evalRubBidRepository;

    public SaveArchivoRubro(EvalRubBidRepository evalRubBidRepository) {
        this.evalRubBidRepository = evalRubBidRepository;
    }


    public Response execute(Requests request){
        return new Response(evalRubBidRepository.saveComentarioArchivo(request.getArchivoComentarioUi()));

    }
    public static class Requests {
        ArchivoComentarioUi archivoComentarioUi;

        public Requests(ArchivoComentarioUi archivoComentarioUi) {
            this.archivoComentarioUi = archivoComentarioUi;
        }

        public ArchivoComentarioUi getArchivoComentarioUi() {
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
