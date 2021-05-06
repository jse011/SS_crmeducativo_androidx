package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.data.InfoRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.ArchivoUi;

public class SaveArchivoRubro {


    private InfoRubroRepository repository;

    public SaveArchivoRubro(InfoRubroRepository repository) {
        this.repository = repository;
    }

    public Response execute(Requests request){
        //return new Response(repository.saveComentarioArchivo(request.getArchivoComentarioUi()));
        return null;
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
