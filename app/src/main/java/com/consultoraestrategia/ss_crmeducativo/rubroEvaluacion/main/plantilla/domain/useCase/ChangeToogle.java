package com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.rubroEvaluacion.main.plantilla.data.source.CasoUsoRepository;

public class ChangeToogle {

    public CasoUsoRepository casoUsoRepository;

    public ChangeToogle(CasoUsoRepository casoUsoRepository) {
        this.casoUsoRepository = casoUsoRepository;
    }

    public Response execute(Requests response){
        return new Response(casoUsoRepository.changeToogleCapacidad(response.getCapacidadId(),response.isToogle()));
    }

    public static class Requests {
        private boolean toogle;
        private int capacidadId;


        public Requests(boolean toogle, int capacidadId) {
            this.toogle = toogle;
            this.capacidadId = capacidadId;
        }

        public boolean isToogle() {
            return toogle;
        }

        public int getCapacidadId() {
            return capacidadId;
        }
    }

    public class Response {
        boolean succes;

        public Response(boolean succes) {
            this.succes = succes;
        }

        public boolean isSucces() {
            return succes;
        }
    }
}
