package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoDataSource;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoRepository;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.FechaAsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;

public class GenerarAsistencia extends UseCase<GenerarAsistencia.RequestValues, GenerarAsistencia.ResponseValue> {

    private AsistenciaAlumnoRepository asistenciaAlumnoRepository;

    public GenerarAsistencia(AsistenciaAlumnoRepository asistenciaAlumnoRepository) {
        this.asistenciaAlumnoRepository = asistenciaAlumnoRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
            asistenciaAlumnoRepository.generarAsistencia(requestValues.asistenciaUi,
                    new AsistenciaAlumnoDataSource.CallbackAsistencia() {
                        @Override
                        public void onAsistencia(boolean success, AsistenciaUi asistenciaUi) {
                            if(success){
                                getUseCaseCallback().onSuccess(new ResponseValue(asistenciaUi));
                            }else {
                                getUseCaseCallback().onError();
                            }

                        }
                    });
    }


    public static class RequestValues implements UseCase.RequestValues{

        private AsistenciaUi asistenciaUi;

        public RequestValues(AsistenciaUi asistenciaUi) {
            this.asistenciaUi = asistenciaUi;
        }

        public AsistenciaUi getAsistenciaUi() {
            return asistenciaUi;
        }

        public void setAsistenciaUi(AsistenciaUi asistenciaUi) {
            this.asistenciaUi = asistenciaUi;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue{
        private AsistenciaUi asistenciaUi;

        public ResponseValue(AsistenciaUi asistenciaUi) {
            this.asistenciaUi = asistenciaUi;
        }

        public AsistenciaUi getAsistenciaUi() {
            return asistenciaUi;
        }

        public void setAsistenciaUi(AsistenciaUi asistenciaUi) {
            this.asistenciaUi = asistenciaUi;
        }
    }
}
