package com.consultoraestrategia.ss_crmeducativo.comportamiento.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.base.UseCase;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoDataSource;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.data.ComportamientoRepository;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.AlumnoUi;
import com.consultoraestrategia.ss_crmeducativo.comportamiento.entities.CursoUi;

public class GetCurso extends UseCase<GetCurso.RequestValues, GetCurso.ResponseValue > {
    ComportamientoRepository comportamientoRepository;

    public GetCurso(ComportamientoRepository comportamientoRepository) {
        this.comportamientoRepository = comportamientoRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        comportamientoRepository.getCurso(requestValues.getIdcargaCurso(), requestValues.getAlumnoId(), new ComportamientoDataSource.SucessCallback<CursoUi>() {
            @Override
            public void onLoad(boolean success, CursoUi item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }
    public static class RequestValues implements UseCase.RequestValues{
        private int idcargaCurso;
        private int alumnoId;

        public RequestValues(int idcargaCurso, int alumnoId) {
            this.idcargaCurso = idcargaCurso;
            this.alumnoId = alumnoId;
        }

        public int getIdcargaCurso() {
            return idcargaCurso;
        }

        public int getAlumnoId() {
            return alumnoId;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        private CursoUi cursoUi;

        public ResponseValue(CursoUi cursoUi) {
            this.cursoUi = cursoUi;
        }

        public CursoUi getCursoUi() {
            return cursoUi;
        }
    }
}
