package com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoDataSource;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;

import java.util.List;

public class GetComportamientosDestinos  extends UseCase<GetComportamientosDestinos.RequestValues, GetComportamientosDestinos.ResponseValue> {
    ComportamientoRepository comportamientoRepository;

    public GetComportamientosDestinos(ComportamientoRepository comportamientoRepository) {
        this.comportamientoRepository = comportamientoRepository;
    }
    @Override
    protected void executeUseCase(RequestValues requestValues) {
        comportamientoRepository.getComporamientosDestinosList(requestValues.getDocenteId(), requestValues.getAlumnoId(), new ComportamientoDataSource.SucessCallback<List<ComportamientoUi>>() {
            @Override
            public void onLoad(boolean success, List<ComportamientoUi> item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }
    public static class RequestValues implements UseCase.RequestValues{
        int docenteId;
        int alumnoId;

        public RequestValues(int docenteId, int alumnoId) {
            this.docenteId = docenteId;
            this.alumnoId = alumnoId;
        }

        public int getDocenteId() {
            return docenteId;
        }

        public int getAlumnoId() {
            return alumnoId;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        List<ComportamientoUi >comportamientoUiList;

        public ResponseValue(List<ComportamientoUi> comportamientoUiList) {
            this.comportamientoUiList = comportamientoUiList;
        }

        public List<ComportamientoUi> getComportamientoUiList() {
            return comportamientoUiList;
        }
    }

}
