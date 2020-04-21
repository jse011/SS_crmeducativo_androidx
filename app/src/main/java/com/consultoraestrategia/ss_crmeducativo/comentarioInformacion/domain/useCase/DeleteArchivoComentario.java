package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.data.InfoRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.ArchivoComentarioUi;

public class DeleteArchivoComentario {


    private InfoRubroRepository repository;

    public DeleteArchivoComentario(InfoRubroRepository repository) {
        this.repository = repository;
    }

    public Response execute(Requests request){
        return new Response(repository.deleteArchivoComentario(request.getArchivoComentarioUi()));

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
