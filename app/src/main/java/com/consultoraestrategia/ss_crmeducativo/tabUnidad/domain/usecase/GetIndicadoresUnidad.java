package com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadRepository;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.IndicadorUi;

import java.util.List;

public class GetIndicadoresUnidad  {

    UnidadRepository unidadRepository;

    public GetIndicadoresUnidad(UnidadRepository unidadRepository) {
        this.unidadRepository = unidadRepository;
    }


    public Response execute(Requests requests){
        return new Response(unidadRepository.getIndicadoresXunidad(requests.getUnidadAprendizajeId()));
    }

    public static class Requests {
        int unidadAprendizajeId;

        public Requests(int unidadAprendizajeId) {
            this.unidadAprendizajeId = unidadAprendizajeId;
        }

        public int getUnidadAprendizajeId() {
            return unidadAprendizajeId;
        }
    }
    public class Response {
        private List<IndicadorUi>indicadorUis;

        public Response(List<IndicadorUi> indicadorUis) {
            this.indicadorUis = indicadorUis;
        }

        public List<IndicadorUi> getIndicadorUis() {
            return indicadorUis;
        }
    }
}
