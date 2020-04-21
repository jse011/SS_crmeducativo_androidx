package com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AlumnosUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoDataSource;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.ComportamientoUi;

import java.util.List;

public class GetComportAlumnoList extends UseCase<GetComportAlumnoList.RequestValues, GetComportAlumnoList.ResponseValue> {

ComportamientoRepository comportamientoRepository;

    public GetComportAlumnoList(ComportamientoRepository comportamientoRepository) {
        this.comportamientoRepository = comportamientoRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
      comportamientoRepository.getListComportamientoAlumno(requestValues.getProgramaEducativoId(), requestValues.getCargaAcademicaId(), requestValues.getCargaCursoId(), requestValues.getDocenteId(), requestValues.getCalendarioPeriodoId(),requestValues.getIdalumno(),
              new ComportamientoDataSource.SucessCallback<AlumnoUi>() {
                  @Override
                  public void onLoad(boolean success, AlumnoUi item) {
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
        private int idalumno;


        public RequestValues(int programaEducativoId, int cargaAcademicaId, int cargaCursoId, int docenteId, int calendarioPeriodoId, int idalumno) {
            this.programaEducativoId = programaEducativoId;
            this.cargaAcademicaId = cargaAcademicaId;
            this.cargaCursoId = cargaCursoId;
            this.docenteId = docenteId;
            this.calendarioPeriodoId = calendarioPeriodoId;
            this.idalumno = idalumno;
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

        public int getIdalumno() {
            return idalumno;
        }

        public int getCalendarioPeriodoId() {
            return calendarioPeriodoId;
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
