package com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadRepository;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CompetenciaUi;

import java.util.List;

public class GetCompetenciaCapacidad extends UseCase<GetCompetenciaCapacidad.RequestValues, GetCompetenciaCapacidad.ResponseValues> {

    private UnidadRepository repository;

    public GetCompetenciaCapacidad(UnidadRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getCompentenciasCapacidades(requestValues.getUnidadId(), requestValues.getTipoId(), requestValues.getCalendarioPeriodoId(),new UnidadDataSource.Callback<List<CompetenciaUi>>() {
            @Override
            public void onLoad(boolean success, List<CompetenciaUi> item) {
                getUseCaseCallback().onSuccess(new ResponseValues(item));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private int unidadId;
        private int tipoId;
        private int calendarioPeriodoId;

        public RequestValues(int unidadId, int tipoId, int calendarioPeriodoId) {
            this.unidadId = unidadId;
            this.tipoId = tipoId;
            this.calendarioPeriodoId = calendarioPeriodoId;
        }

        public int getCalendarioPeriodoId() {
            return calendarioPeriodoId;
        }

        public int getUnidadId() {
            return unidadId;
        }

        public int getTipoId() {
            return tipoId;
        }
    }

    public static class ResponseValues implements UseCase.ResponseValue{
        private List<CompetenciaUi> competenciaUis;

        public ResponseValues(List<CompetenciaUi> competenciaUis) {
            this.competenciaUis = competenciaUis;
        }

        public List<CompetenciaUi> getCompetenciaUis() {
            return competenciaUis;
        }
    }
}
