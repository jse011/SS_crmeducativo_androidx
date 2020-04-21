package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.RubroRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetAlumnoListCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.AlumnosEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.FiltroTableUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;

import java.util.List;

/**
 * Created by SCIEV on 9/10/2017.
 */

public class GetAlumnoList extends UseCase<GetAlumnoList.RequestValues, GetAlumnoList.ResponseValue> {


    RubroRepository repository;

    public GetAlumnoList(RubroRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getAlumnoList(requestValues.getRubroEvaluacionUi(), requestValues.getFiltroTableUi(), requestValues.getCargaCursoId(), requestValues.getEntidadId(), requestValues.getGeorefereciaId(),new GetAlumnoListCallback() {
            @Override
            public void onRecursoLoad(List<AlumnosEvaluacionUi> alumnosEvaluacionUis) {
                getUseCaseCallback().onSuccess(new ResponseValue(alumnosEvaluacionUis));
            }

            @Override
            public void onError(String errot) {
                getUseCaseCallback().onError();
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private RubroEvaluacionUi rubroEvaluacionUi;
        private FiltroTableUi filtroTableUi;
        private int cargaCursoId;
        private int entidadId;
        private int georefereciaId;

        public RequestValues(RubroEvaluacionUi rubroEvaluacionUi, FiltroTableUi filtroTableUi, int cargaCursoId, int entidadId, int georefereciaId) {
            this.rubroEvaluacionUi = rubroEvaluacionUi;
            this.filtroTableUi = filtroTableUi;
            this.cargaCursoId = cargaCursoId;
            this.entidadId = entidadId;
            this.georefereciaId = georefereciaId;
        }

        public int getEntidadId() {
            return entidadId;
        }

        public int getGeorefereciaId() {
            return georefereciaId;
        }

        public RubroEvaluacionUi getRubroEvaluacionUi() {
            return rubroEvaluacionUi;
        }

        public FiltroTableUi getFiltroTableUi() {
            return filtroTableUi;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        List<AlumnosEvaluacionUi> alumnosEvaluacionUis;

        public ResponseValue(List<AlumnosEvaluacionUi> alumnosEvaluacionUis) {
            this.alumnosEvaluacionUis = alumnosEvaluacionUis;
        }

        public List<AlumnosEvaluacionUi> getAlumnosEvaluacionUis() {
            return alumnosEvaluacionUis;
        }
    }
}
