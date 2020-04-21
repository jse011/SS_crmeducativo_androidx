package com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.DestinoUi;

public class GetDestino {

    public ComportamientoRepository comportamientoRepository;

    public GetDestino(ComportamientoRepository comportamientoRepository) {
        this.comportamientoRepository = comportamientoRepository;
    }

    public Response execute(Requests requests){
        return  new Response(comportamientoRepository.getDestino(requests.getIdcomportamiento(), requests.getGeoreferenciaId()));
    }
    public static class Requests {
        String idcomportamiento;
        int georeferenciaId;

        public Requests(String idcomportamiento, int georeferenciaId) {
            this.idcomportamiento = idcomportamiento;
            this.georeferenciaId = georeferenciaId;
        }

        public int getGeoreferenciaId() {
            return georeferenciaId;
        }

        public String getIdcomportamiento() {
            return idcomportamiento;
        }
    }
    public class Response {
        DestinoUi destinoUi;

        public Response(DestinoUi destinoUi) {
            this.destinoUi = destinoUi;
        }

        public DestinoUi getDestinoUi() {
            return destinoUi;
        }
    }

}
