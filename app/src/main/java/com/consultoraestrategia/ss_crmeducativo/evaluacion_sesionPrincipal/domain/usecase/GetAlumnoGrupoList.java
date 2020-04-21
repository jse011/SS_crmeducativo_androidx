package com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.RubroRepository;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.data.source.callback.GetAlumnoGrupoListCallback;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.FiltroTableUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.GrupoEvaluacionUi;
import com.consultoraestrategia.ss_crmeducativo.evaluacion_sesionPrincipal.entities.RubroEvaluacionUi;

import java.util.List;

/**
 * Created by SCIEV on 9/10/2017.
 */

public class GetAlumnoGrupoList extends UseCase<GetAlumnoGrupoList.RequestValues, GetAlumnoGrupoList.ResponseValue> {

    private RubroRepository repository;

    public GetAlumnoGrupoList(RubroRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getAlumnoListGrupo(requestValues.getSesionAprendizajeId(),requestValues.getRubroEvaluacionUi(), requestValues.getEntidadId(), requestValues.getGeoreferenciaId(),new GetAlumnoGrupoListCallback() {
            @Override
            public void onRecursoLoad(List<GrupoEvaluacionUi> itemEvaluacionUis) {
                getUseCaseCallback().onSuccess(new ResponseValue(itemEvaluacionUis));
            }

            @Override
            public void onError(String errot) {
                getUseCaseCallback().onError();
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private int sesionAprendizajeId;
        private RubroEvaluacionUi rubroEvaluacionUi;
        private int entidadId;
        private int georeferenciaId;

        public RequestValues(int sesionAprendizajeId, RubroEvaluacionUi rubroEvaluacionUi, int entidadId, int georeferenciaId) {
            this.sesionAprendizajeId = sesionAprendizajeId;
            this.rubroEvaluacionUi = rubroEvaluacionUi;
            this.entidadId = entidadId;
            this.georeferenciaId = georeferenciaId;
        }

        public int getEntidadId() {
            return entidadId;
        }

        public int getGeoreferenciaId() {
            return georeferenciaId;
        }

        public int getSesionAprendizajeId() {
            return sesionAprendizajeId;
        }

        public RubroEvaluacionUi getRubroEvaluacionUi() {
            return rubroEvaluacionUi;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        List<GrupoEvaluacionUi> itemEvaluacionUis;

        public ResponseValue(List<GrupoEvaluacionUi> itemEvaluacionUis) {
            this.itemEvaluacionUis = itemEvaluacionUis;
        }

        public List<GrupoEvaluacionUi> getAlumnosEvaluacionUis() {
            return itemEvaluacionUis;
        }
    }
}
