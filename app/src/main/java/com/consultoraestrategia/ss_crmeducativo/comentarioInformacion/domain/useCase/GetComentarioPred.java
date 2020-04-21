package com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.domain.useCase;


import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.data.InfoRubroRepository;
import com.consultoraestrategia.ss_crmeducativo.comentarioInformacion.entities.MensajeUi;

import java.util.List;

public class GetComentarioPred {

    private InfoRubroRepository infoRubroRepository;

    public GetComentarioPred(InfoRubroRepository infoRubroRepository) {
        this.infoRubroRepository = infoRubroRepository;
    }

    public Response execute(Requests request){
                 return new Response(infoRubroRepository.getComentarios(request.getEvaluacionId()));

    }
    public static class Requests {
        private String evaluacionId;

        public Requests(String evaluacionId) {
            this.evaluacionId = evaluacionId;
        }

        public String getEvaluacionId() {
            return evaluacionId;
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
