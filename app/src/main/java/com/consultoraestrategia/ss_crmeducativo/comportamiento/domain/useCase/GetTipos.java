package com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.TipoUi;

import java.util.List;

public class GetTipos  {

    public ComportamientoRepository comportamientoRepository;

    public GetTipos(ComportamientoRepository comportamientoRepository) {
        this.comportamientoRepository = comportamientoRepository;
    }

    public Response execute(Requests requests){
        return new Response(comportamientoRepository.getTipos(requests.getGeoreferenciaId(), requests.getEntidadId()));
    }

    public static class Requests {
        private int georeferenciaId;
        private int entidadId;

        public Requests(int georeferenciaId, int entidadId) {
            this.georeferenciaId = georeferenciaId;
            this.entidadId = entidadId;
        }

        public int getGeoreferenciaId() {
            return georeferenciaId;
        }

        public int getEntidadId() {
            return entidadId;
        }
    }

    public class Response {
        private List<TipoUi>tiposlist;

        public Response(List<TipoUi> tiposlist) {
            this.tiposlist = tiposlist;
        }

        public List<TipoUi> getTiposlist() {
            return tiposlist;
        }
    }


}
