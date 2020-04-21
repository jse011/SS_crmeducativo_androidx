package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source.EvaluacionCompetenciaRepository;

public class ChangeToogle {

    public EvaluacionCompetenciaRepository evaluacionCompetenciaRepository;
    public ChangeToogle(EvaluacionCompetenciaRepository evaluacionCompetenciaRepository) {
        this.evaluacionCompetenciaRepository = evaluacionCompetenciaRepository;
    }

    public Response execute(Requests response){
        return new Response(evaluacionCompetenciaRepository.changeToogleCompetencia(response.getCompetenciaId(),response.isToogle()));
    }

    public static class Requests {
        private boolean toogle;
        private int competenciaId;

        public Requests(boolean toogle, int competenciaId) {
            this.toogle = toogle;
            this.competenciaId = competenciaId;
        }

        public boolean isToogle() {
            return toogle;
        }

        public int getCompetenciaId() {
            return competenciaId;
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
