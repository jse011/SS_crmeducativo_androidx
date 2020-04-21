package com.consultoraestrategia.ss_crmeducativo.tabUnidad.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadDataSource;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.data.source.UnidadRepository;
import com.consultoraestrategia.ss_crmeducativo.tabUnidad.entities.ProductoUi;

import java.util.List;

public class GetProductosUnidad extends UseCase<GetProductosUnidad.RequestValues, GetProductosUnidad.ResponseValues> {

    private UnidadRepository repository;

    public GetProductosUnidad(UnidadRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.getProductosUnidad(requestValues.getUnidadId(), requestValues.getCalendarioPeriodoId(),new UnidadDataSource.Callback<List<ProductoUi>>() {
            @Override
            public void onLoad(boolean success, List<ProductoUi> item) {
                getUseCaseCallback().onSuccess(new ResponseValues(item));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private int unidadId;
        private int calendarioPeriodoId;

        public RequestValues(int unidadId, int calendarioPeriodoId) {
            this.unidadId = unidadId;
            this.calendarioPeriodoId = calendarioPeriodoId;
        }

        public int getCalendarioPeriodoId() {
            return calendarioPeriodoId;
        }

        public int getUnidadId() {
            return unidadId;
        }
    }

    public static class ResponseValues implements UseCase.ResponseValue{
        private List<ProductoUi> productoUis;

        public ResponseValues(List<ProductoUi> productoUis) {
            this.productoUis = productoUis;
        }

        public List<ProductoUi> getProductoUis() {
            return productoUis;
        }
    }
}
