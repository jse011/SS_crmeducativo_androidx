package com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadRepository;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.CampoAccionUi;

import java.util.List;

public class GetCamposAccion extends UseCase<GetCamposAccion.RequestValues, GetCamposAccion.ResponseValues>{

    private UnidadRepository repository;

    public GetCamposAccion(UnidadRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getCamposAccion(requestValues.unidadId, requestValues.getTipoCompetenciaId(), requestValues.getCalendarioPeriodoId(),new UnidadDataSource.Callback<List<CampoAccionUi>>() {
            @Override
            public void onLoad(boolean success, List<CampoAccionUi> item) {
                getUseCaseCallback().onSuccess(new ResponseValues(item));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private int unidadId;
        private int tipoCompetenciaId;
        int calendarioPeriodoId;

        public RequestValues(int unidadId, int tipoCompetenciaId, int calendarioPeriodoId) {
            this.unidadId = unidadId;
            this.tipoCompetenciaId = tipoCompetenciaId;
            this.calendarioPeriodoId = calendarioPeriodoId;
        }

        public int getCalendarioPeriodoId() {
            return calendarioPeriodoId;
        }

        public int getUnidadId() {
            return unidadId;
        }

        public int getTipoCompetenciaId() {
            return tipoCompetenciaId;
        }
    }

    public static class ResponseValues implements UseCase.ResponseValue{
        private List<CampoAccionUi> campoAccionUis;

        public ResponseValues(List<CampoAccionUi> campoAccionUis) {
            this.campoAccionUis = campoAccionUis;
        }

        public List<CampoAccionUi> getCampoAccionUis() {
            return campoAccionUis;
        }
    }
}
