package com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.UnidadAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.data.source.UnidadesDataSource;
import com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.data.source.UnidadesRepository;

public class UpdateToogleUnidad extends UseCaseSincrono<UpdateToogleUnidad.RequestValues, UpdateToogleUnidad.ResponseValue > {


    UnidadesRepository UnidadesRepository;

    public UpdateToogleUnidad(com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.data.source.UnidadesRepository unidadesRepository) {
        UnidadesRepository = unidadesRepository;
    }

    @Override
    public void execute(RequestValues request, final Callback<ResponseValue> callback) {
        UnidadesRepository.saveToogleUnidad(request.unidadAprendizajeUi, new UnidadesDataSource.Callback() {
            @Override
            public void onLoad(boolean success, Object item) {
                callback.onResponse(true, new ResponseValue(success));
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private UnidadAprendizajeUi unidadAprendizajeUi;

        public RequestValues(UnidadAprendizajeUi unidadAprendizajeUi) {
            this.unidadAprendizajeUi = unidadAprendizajeUi;
        }

        public UnidadAprendizajeUi getUnidadAprendizajeUi() {
            return unidadAprendizajeUi;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
         private Boolean estado;

        public ResponseValue(Boolean estado) {
            this.estado = estado;
        }

        public Boolean getEstado() {
            return estado;
        }
    }

}
