package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase;



import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.data.InfoRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.ArchivoUi;

import java.util.List;

public class GetArchivoComentario {

    private InfoRubroRepository repository;

    public GetArchivoComentario(InfoRubroRepository repository) {
        this.repository = repository;
    }

    public List<ArchivoUi> execute(Response response){
        return repository.getArchivoComentarioList(response.getEvaluacionId());
    }

    public static class Response{
        private String evaluacionId;

        public Response(String evaluacionId) {
            this.evaluacionId = evaluacionId;
        }

        public String getEvaluacionId() {
            return evaluacionId;
        }
    }
}
