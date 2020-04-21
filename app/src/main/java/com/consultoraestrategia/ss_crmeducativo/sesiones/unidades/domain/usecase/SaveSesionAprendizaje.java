package com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.domain.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.sesiones.entities.SesionAprendizajeUi;
import com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.data.source.UnidadesRepository;

public class SaveSesionAprendizaje extends UseCase<SaveSesionAprendizaje.RequestValues, SaveSesionAprendizaje.ResponseValue> {

    UnidadesRepository UnidadesRepository;

    public SaveSesionAprendizaje(com.consultoraestrategia.ss_crmeducativo.sesiones.unidades.data.source.UnidadesRepository unidadesRepository) {
        UnidadesRepository = unidadesRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        UnidadesRepository.saveSesionAprendizaje(requestValues.sesionAprendizajeUi);
    }

    public static class RequestValues implements UseCase.RequestValues{
        private SesionAprendizajeUi sesionAprendizajeUi;

        public RequestValues(SesionAprendizajeUi sesionAprendizajeUi) {
            this.sesionAprendizajeUi = sesionAprendizajeUi;
        }

        public SesionAprendizajeUi getSesionAprendizajeUi() {
            return sesionAprendizajeUi;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{

    }
}
