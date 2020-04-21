package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source.EvaluacionCompetenciaRepository;

public class ValidarAnclaCalendarioPeriodo {

    public EvaluacionCompetenciaRepository evaluacionCompetenciaRepository;

    public ValidarAnclaCalendarioPeriodo(EvaluacionCompetenciaRepository evaluacionCompetenciaRepository) {
        this.evaluacionCompetenciaRepository = evaluacionCompetenciaRepository;
    }

    public Response execute(Requests requests){
        return new Response(evaluacionCompetenciaRepository.validarAnclaCalendarioPeriodo(requests.getIdcalendarioPeriodo() , requests.getCargaCursoId(), requests.getIsRUbroResultado()));
    }
    public static class Requests {
      private int idcalendarioPeriodo;
      private int cargaCursoId;
      private boolean isRUbroResultado;

        public Requests(int idcalendarioPeriodo, int cargaCursoId, boolean isRUbroResultado) {
            this.idcalendarioPeriodo = idcalendarioPeriodo;
            this.cargaCursoId = cargaCursoId;
            this.isRUbroResultado = isRUbroResultado;
        }

        public int getIdcalendarioPeriodo() {
            return idcalendarioPeriodo;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }

        public boolean getIsRUbroResultado() {
            return isRUbroResultado;
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
