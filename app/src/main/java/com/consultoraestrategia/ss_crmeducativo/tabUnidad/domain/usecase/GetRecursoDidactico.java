package com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadRepository;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.RecursosDidacticoUi;

import java.util.List;

public class GetRecursoDidactico extends UseCase<GetRecursoDidactico.RequestValue, GetRecursoDidactico.ResponseValue> {

    private UnidadRepository repository;

    public GetRecursoDidactico(UnidadRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValue requestValues) {
        repository.getRecursoDidactico(requestValues.getUnidadAprendizaje(), new UnidadDataSource.Callback<List<RecursosDidacticoUi>>() {
            @Override
            public void onLoad(boolean success, List<RecursosDidacticoUi> item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }

    public static class RequestValue implements UseCase.RequestValues{
        private int unidadAprendizaje;

        public RequestValue(int unidadAprendizaje) {
            this.unidadAprendizaje = unidadAprendizaje;
        }

        public int getUnidadAprendizaje() {
            return unidadAprendizaje;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        private List<RecursosDidacticoUi> recursosDidacticoUis;

        public ResponseValue(List<RecursosDidacticoUi> recursosDidacticoUis) {
            this.recursosDidacticoUis = recursosDidacticoUis;
        }

        public List<RecursosDidacticoUi> getRecursosDidacticoUis() {
            return recursosDidacticoUis;
        }
    }
}
