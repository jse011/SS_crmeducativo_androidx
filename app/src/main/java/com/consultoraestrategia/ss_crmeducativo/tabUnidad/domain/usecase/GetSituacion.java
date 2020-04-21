package com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadRepository;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.SituacionUi;

import java.util.List;

public class GetSituacion extends UseCase<GetSituacion.RequestValues, GetSituacion.ResponseValues> {

    public UnidadRepository repository;

    public GetSituacion(UnidadRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getSituacion(requestValues.getUnidadId(), requestValues.entidadId, new UnidadDataSource.Callback<List<SituacionUi>>() {
            @Override
            public void onLoad(boolean success, List<SituacionUi> item) {
                getUseCaseCallback().onSuccess(new ResponseValues(item));
            }
        });

    }

    public static class RequestValues implements UseCase.RequestValues{
        private int unidadId;
        private int entidadId;

        public RequestValues(int unidadId, int entidadId) {
            this.unidadId = unidadId;
            this.entidadId = entidadId;
        }

        public int getUnidadId() {
            return unidadId;
        }

        public int getEntidadId() {
            return entidadId;
        }
    }

    public static class ResponseValues implements UseCase.ResponseValue{
        private List<SituacionUi> situacionUis;

        public ResponseValues(List<SituacionUi> situacionUis) {
            this.situacionUis = situacionUis;
        }

        public List<SituacionUi> getSituacionUis() {
            return situacionUis;
        }
    }
}
