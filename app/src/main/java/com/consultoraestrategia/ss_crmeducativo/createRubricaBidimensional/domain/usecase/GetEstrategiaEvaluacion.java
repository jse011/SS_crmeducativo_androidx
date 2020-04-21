package com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.data.source.CreateRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.createRubricaBidimensional.entity.EstrategiaEvalUi;

import java.util.List;

public class GetEstrategiaEvaluacion {

    private CreateRubBidRepository createRubBidRepository;

    public GetEstrategiaEvaluacion(CreateRubBidRepository createRubBidRepository) {
        this.createRubBidRepository = createRubBidRepository;
    }

    public Response execute(Request request){
        return new Response(createRubBidRepository.getEstrategiasEvaluacion(request.getCursoId()));
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
        private List<EstrategiaEvalUi>estrategiaEvalUis;

        public Response(List<EstrategiaEvalUi> estrategiaEvalUis) {
            this.estrategiaEvalUis = estrategiaEvalUis;
        }

        public List<EstrategiaEvalUi> getEstrategiaEvalUis() {
            return estrategiaEvalUis;
        }
    }
}
