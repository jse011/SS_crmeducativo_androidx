package com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.usecase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.data.source.InfoEstiloAprendizajeRepository;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.data.source.InfoEstiloAprendizajeSource;
import com.consultoraestrategia.ss_crmeducativo.infoEstiloAprendizaje.entities.AlumnoUi;

public class GetListDimensionObse extends UseCase<GetListDimensionObse.RequestValues, GetListDimensionObse.ResponseValue> {
    private InfoEstiloAprendizajeRepository infoEstiloAprendizajeRepository;

    public GetListDimensionObse(InfoEstiloAprendizajeRepository infoEstiloAprendizajeRepository) {
        this.infoEstiloAprendizajeRepository = infoEstiloAprendizajeRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        infoEstiloAprendizajeRepository.getListaDimensiones(requestValues.getAlumnoId(), requestValues.getEntidadId(), requestValues.getGeoreferenciaId(), new InfoEstiloAprendizajeSource.Callback<AlumnoUi>() {
            @Override
            public void load(boolean success, AlumnoUi item) {
                if(success){
                    getUseCaseCallback().onSuccess(new ResponseValue(item));
                }else {
                    getUseCaseCallback().onError();
                }
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private int alumnoId;
        private int entidadId;
        private int georeferenciaId;

        public RequestValues(int alumnoId, int entidadId, int georeferenciaId) {
            this.alumnoId = alumnoId;
            this.entidadId = entidadId;
            this.georeferenciaId = georeferenciaId;
        }

        public int getGeoreferenciaId() {
            return georeferenciaId;
        }

        public int getEntidadId() {
            return entidadId;
        }

        public int getAlumnoId() {
            return alumnoId;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        private AlumnoUi alumnoUi;

        public ResponseValue(AlumnoUi alumnoUi) {
            this.alumnoUi = alumnoUi;
        }

        public AlumnoUi getAlumnoUi() {
            return alumnoUi;
        }
    }
}
