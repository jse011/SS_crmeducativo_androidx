package com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ParametroDisenioUi;

public class GetParametroDisenio {

    public ComportamientoRepository comportamientoRepository;

    public GetParametroDisenio(ComportamientoRepository comportamientoRepository) {
        this.comportamientoRepository = comportamientoRepository;
    }


    public Response execute(Requests request){
        return new Response(comportamientoRepository.getParametroDisenio(request.getIdCargaCurso()));
    }
    public static class Requests {
        private int idCargaCurso;

        public Requests(int idCargaCurso) {
            this.idCargaCurso = idCargaCurso;
        }

        public int getIdCargaCurso() {
            return idCargaCurso;
        }
    }
    public class Response {
        private ParametroDisenioUi parametroDisenioUi;

        public Response(ParametroDisenioUi parametroDisenioUi) {
            this.parametroDisenioUi = parametroDisenioUi;
        }

        public ParametroDisenioUi getParametroDisenioUi() {
            return parametroDisenioUi;
        }
    }
}
