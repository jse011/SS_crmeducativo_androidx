package com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.data.source.CrearRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.CrearRubroCompetencia.entitie.EstrategiaUi;

import java.util.List;

public class GetEstrategiasEvaluacion  {
    CrearRubroRepository rubroRepository;

    public GetEstrategiasEvaluacion(CrearRubroRepository rubroRepository) {
        this.rubroRepository = rubroRepository;
    }

    public Response execute(Request request){
        return new Response(rubroRepository.getEstrategiasEvaluacion(request.getCursoId()));
    }
    public static  class Request{
        private int cursoId;

        public Request(int cursoId) {
            this.cursoId = cursoId;
        }

        public int getCursoId() {
            return cursoId;
        }
    }
    public static class Response{
        private List<EstrategiaUi> estrategiaEvalUis;

        public Response(List<EstrategiaUi> estrategiaEvalUis) {
            this.estrategiaEvalUis = estrategiaEvalUis;
        }

        public List<EstrategiaUi> getEstrategiaEvalUis() {
            return estrategiaEvalUis;
        }
    }
}
