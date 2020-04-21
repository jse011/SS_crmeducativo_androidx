package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoDataSource;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoRepository;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;

import java.util.List;

public class SaveAsistencia extends UseCase<SaveAsistencia.RequestValues, SaveAsistencia.ResponseValue> {
    AsistenciaAlumnoRepository asistenciaAlumnoRepository;

    public SaveAsistencia(AsistenciaAlumnoRepository asistenciaAlumnoRepository) {
        this.asistenciaAlumnoRepository = asistenciaAlumnoRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        asistenciaAlumnoRepository.saveAsitenciaAlumnos(requestValues.getAsistenciaUiList(), new AsistenciaAlumnoDataSource.Callback() {
            @Override
            public void onLoad(boolean success) {
                getUseCaseCallback().onSuccess(new ResponseValue(success));
            }
        });
    }


    public static class RequestValues implements UseCase.RequestValues{
        private List<AsistenciaUi>asistenciaUiList;

        public RequestValues(List<AsistenciaUi> asistenciaUiList) {
            this.asistenciaUiList = asistenciaUiList;
        }

        public List<AsistenciaUi> getAsistenciaUiList() {
            return asistenciaUiList;
        }
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        private boolean success;

        public ResponseValue(boolean success) {
            this.success = success;
        }

        public boolean isSuccess() {
            return success;
        }
    }
}
