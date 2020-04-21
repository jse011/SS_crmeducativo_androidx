package com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;

public class GetComportamiento  {

    public ComportamientoRepository comportamientoRepository;

    public GetComportamiento(ComportamientoRepository comportamientoRepository) {
        this.comportamientoRepository = comportamientoRepository;
    }
    public Response execute(Requests requests){
        return new Response(comportamientoRepository.getComportamiento(requests.getIdComportamiento()));
    }

    public static class Requests {
        private String idComportamiento;

        public Requests(String idComportamiento) {
            this.idComportamiento = idComportamiento;
        }

        public String getIdComportamiento() {
            return idComportamiento;
        }

    }
    public class Response {
        private ComportamientoUi comportamientoUi;

        public Response(ComportamientoUi comportamientoUi) {
            this.comportamientoUi = comportamientoUi;
        }

        public ComportamientoUi getComportamientoUi() {
            return comportamientoUi;
        }
    }
}
