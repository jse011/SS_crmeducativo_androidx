package com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source.EvaluacionCompetenciaDataSource;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.data.Source.EvaluacionCompetenciaRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacionCompetencias.entidades.FiltradoUi;

import java.util.ArrayList;
import java.util.List;

public class AnclarResultados  extends UseCase<AnclarResultados.Request, AnclarResultados.Response> {

    private EvaluacionCompetenciaRepository repository;

    public AnclarResultados(EvaluacionCompetenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(Request requestValues) {
        repository.anclarResultados(requestValues.getCalendarioPeriodoId(), requestValues.getCargaCursoId(), requestValues.getFiltradoUiList(),new EvaluacionCompetenciaDataSource.Callback2() {
            @Override
            public void onLoad(boolean success, String mensaje) {
                getUseCaseCallback().onSuccess(new Response(success, mensaje));
            }

        });
    }


    public static class Request implements UseCase.RequestValues{
      private int calendarioPeriodoId;
      private int cargaCursoId;
      private List<FiltradoUi> filtradoUiList;

        public Request(int calendarioPeriodoId, int cargaCursoId, ArrayList<FiltradoUi> filtradoUiList) {
            this.calendarioPeriodoId = calendarioPeriodoId;
            this.cargaCursoId = cargaCursoId;
            this.filtradoUiList = filtradoUiList;
        }

        public List<FiltradoUi> getFiltradoUiList() {
            return filtradoUiList;
        }

        public int getCalendarioPeriodoId() {
            return calendarioPeriodoId;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }
    }

    public static class Response implements UseCase.ResponseValue{
        private boolean succes;
        private String mensaje;

        public Response(boolean succes, String mensaje) {
            this.succes = succes;
            this.mensaje = mensaje;
        }

        public boolean isSucces() {
            return succes;
        }

        public String getMensaje() {
            return mensaje;
        }
    }
}
