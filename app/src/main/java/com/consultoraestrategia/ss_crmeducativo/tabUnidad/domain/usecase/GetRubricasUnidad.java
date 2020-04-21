package com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadRepository;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.RubricaUi;

import java.util.List;

public class GetRubricasUnidad extends UseCase<GetRubricasUnidad.RequestValues, GetRubricasUnidad.ResponseValues> {

    UnidadRepository unidadRepository;

    public GetRubricasUnidad(UnidadRepository unidadRepository) {
        this.unidadRepository = unidadRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        unidadRepository.getRubricasXunidad(requestValues.getUnidadId(), requestValues.getCargaCursoId(), requestValues.getCalendarioPeriodoId(), new UnidadDataSource.Callback<List<RubricaUi>>() {
            @Override
            public void onLoad(boolean success, List<RubricaUi> item) {
                getUseCaseCallback().onSuccess(new ResponseValues(item) );
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
        private List<RubricaUi> rubricaUiList;

        public ResponseValues(List<RubricaUi> rubricaUiList) {
            this.rubricaUiList = rubricaUiList;
        }

        public List<RubricaUi> getRubricaUiList() {
            return rubricaUiList;
        }
    }
}
