package com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.usecase;

import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.data.source.EvalRubBidRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.EvalProcUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacionBidimencional.entity.MensajeUi;

import java.util.List;

public class GetComentarioPred  {

    private EvalRubBidRepository evalRubBidRepository;

    public GetComentarioPred(EvalRubBidRepository evalRubBidRepository) {
        this.evalRubBidRepository = evalRubBidRepository;
    }

    public Response execute(Requests request){
        switch (request.getTipo()){
            case 1:
                //mensajes predeterminados
                return new Response(evalRubBidRepository.getComentarioPred(new EvalProcUi()));
             default:
                 //todos los mensajes
                 return new Response(evalRubBidRepository.getComentarios(request.getRubricaBidimencionalId(), request.getAlumnoId() ));
        }

    }
    public static class Requests {
        private String rubricaBidimencionalId;
        private int alumnoId;
        private int tipo;

        public Requests(String rubricaBidimencionalId, int alumnoId, int tipo) {
            this.rubricaBidimencionalId = rubricaBidimencionalId;
            this.alumnoId = alumnoId;
            this.tipo = tipo;
        }

        public String getRubricaBidimencionalId() {
            return rubricaBidimencionalId;
        }

        public int getAlumnoId() {
            return alumnoId;
        }

        public int getTipo() {
            return tipo;
        }
    }
    public static class Response {
        List<MensajeUi> mensajeUiList;

        public Response(List<MensajeUi> mensajeUiList) {
            this.mensajeUiList = mensajeUiList;
        }

        public List<MensajeUi> getMensajeUiList() {
            return mensajeUiList;
        }
    }
}
