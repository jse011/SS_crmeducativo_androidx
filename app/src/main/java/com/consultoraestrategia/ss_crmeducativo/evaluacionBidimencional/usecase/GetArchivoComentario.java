package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.ArchivoComentarioUi;

import java.util.List;

public class GetArchivoComentario {

    private EvalRubBidRepository repository;

    public GetArchivoComentario(EvalRubBidRepository repository) {
        this.repository = repository;
    }

    public List<ArchivoComentarioUi> execute(Response response){
        return repository.getArchivoComentarioList(response.getRubroEvaluacionId(), response.getPersonaId());
    }

    public static class Response{
        private String rubroEvaluacionId;
        private int personaId;

        public Response(String rubroEvaluacionId, int personaId) {
            this.rubroEvaluacionId = rubroEvaluacionId;
            this.personaId = personaId;
        }

        public String getRubroEvaluacionId() {
            return rubroEvaluacionId;
        }

        public int getPersonaId() {
            return personaId;
        }
    }
}
