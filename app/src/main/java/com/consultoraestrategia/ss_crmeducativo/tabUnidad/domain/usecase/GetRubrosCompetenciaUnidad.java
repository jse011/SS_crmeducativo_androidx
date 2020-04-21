package com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadRepository;

import java.util.List;

public class GetRubrosCompetenciaUnidad extends UseCase<GetRubrosCompetenciaUnidad.RequestValues, GetRubrosCompetenciaUnidad.ResponseValues> {

    UnidadRepository unidadRepository;

    public GetRubrosCompetenciaUnidad(UnidadRepository unidadRepository) {
        this.unidadRepository = unidadRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        unidadRepository.getRubrosXcompetencias(requestValues.getUnidadId(), requestValues.getCargaCursoId(), requestValues.getCalendarioPeriodoId(), new UnidadDataSource.Callback<List<Object>>() {
            @Override
            public void onLoad(boolean success, List<Object> item) {
                getUseCaseCallback().onSuccess(new ResponseValues(item));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private int unidadId;
        private int cargaCursoId;
        private int calendarioPeriodoId;


        public RequestValues(int unidadId, int cargaCursoId, int calendarioPeriodoId) {
            this.unidadId = unidadId;
            this.cargaCursoId = cargaCursoId;
            this.calendarioPeriodoId = calendarioPeriodoId;
        }

        public int getUnidadId() {
            return unidadId;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }

        public int getCalendarioPeriodoId() {
            return calendarioPeriodoId;
        }
    }
    public static class ResponseValues implements UseCase.ResponseValue{
        private List<Object>objects;

        public ResponseValues(List<Object> objects) {
            this.objects = objects;
        }

        public List<Object> getObjects() {
            return objects;
        }
    }
}
