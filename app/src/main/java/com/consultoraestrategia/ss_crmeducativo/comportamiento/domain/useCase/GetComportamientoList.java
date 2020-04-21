package com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoDataLocalSource;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoDataSource;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.AlumnoUi;

import java.util.List;

public class GetComportamientoList extends UseCase<GetComportamientoList.RequestValues, GetComportamientoList.ResponseValue> {

    ComportamientoRepository comportamientoRepository;

    public GetComportamientoList(ComportamientoRepository comportamientoRepository) {
        this.comportamientoRepository = comportamientoRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        comportamientoRepository.getListComportamiento(requestValues.getProgramaEducativoId(), requestValues.getCargaAcademicaId(), requestValues.getCargaCursoId(), requestValues.getDocenteId(),
                requestValues.getCalendarioPeriodoId(),requestValues.getGeoreferenciaId()
                ,requestValues.getEntidadId(),new ComportamientoDataSource.SucessCallback<List<AlumnoUi>>() {
                    @Override
                    public void onLoad(boolean success, List<AlumnoUi> item) {
                        getUseCaseCallback().onSuccess(new ResponseValue(item));
                    }
                });
    }

    public static class RequestValues implements UseCase.RequestValues{
        private int programaEducativoId;
        private int cargaAcademicaId;
        private int cargaCursoId;
        private int docenteId;
        private int calendarioPeriodoId;
        private int georeferenciaId;
        private int entidadId;

        public RequestValues(int programaEducativoId, int cargaAcademicaId, int cargaCursoId, int docenteId, int calendarioPeriodoId, int georeferenciaId, int entidadId) {
            this.programaEducativoId = programaEducativoId;
            this.cargaAcademicaId = cargaAcademicaId;
            this.cargaCursoId = cargaCursoId;
            this.docenteId = docenteId;
            this.calendarioPeriodoId = calendarioPeriodoId;
            this.georeferenciaId = georeferenciaId;
            this.entidadId=entidadId;
        }

        public int getProgramaEducativoId() {
            return programaEducativoId;
        }

        public int getCargaAcademicaId() {
            return cargaAcademicaId;
        }

        public int getCargaCursoId() {
            return cargaCursoId;
        }

        public int getDocenteId() {
            return docenteId;
        }

        public int getCalendarioPeriodoId() {
            return calendarioPeriodoId;
        }

        public int getGeoreferenciaId() {
            return georeferenciaId;
        }

        public int getEntidadId() {
            return entidadId;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        private List<AlumnoUi > alumnoUiList;

        public ResponseValue(List<AlumnoUi> alumnoUiList) {
            this.alumnoUiList = alumnoUiList;
        }

        public List<AlumnoUi> getAlumnoUiList() {
            return alumnoUiList;
        }
    }
}
