package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.data.InfoRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.MensajeUi;

public class DeleteComentario {


    private InfoRubroRepository repository;

    public DeleteComentario(InfoRubroRepository repository) {
        this.repository = repository;
    }

    public Response execute(Requests request){
        return new Response(repository.deleteComentario(request.getMensajeUi()));

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
