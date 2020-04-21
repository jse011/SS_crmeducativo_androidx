package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoDataSource;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoRepository;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;

import java.util.List;

import okhttp3.Request;

public class GetAsistenciaDiariaList extends UseCase<GetAsistenciaDiariaList.RequestValues, GetAsistenciaDiariaList.ResponseValue> {

    AsistenciaAlumnoRepository asistenciaAlumnoRepository;

    public GetAsistenciaDiariaList(AsistenciaAlumnoRepository asistenciaAlumnoRepository) {
        this.asistenciaAlumnoRepository = asistenciaAlumnoRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        asistenciaAlumnoRepository.getAsistenciaAlumnos(requestValues.getAsistenciaUi(), new AsistenciaAlumnoDataSource.SucessCallback<List<AsistenciaUi>>() {
            @Override
            public void onLoad(boolean success, List<AsistenciaUi> item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
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
    }
    public static class ResponseValue implements UseCase.ResponseValue{
        private List<AsistenciaUi> asistenciaUiList;

        public ResponseValue(List<AsistenciaUi> asistenciaUiList) {
            this.asistenciaUiList = asistenciaUiList;
        }

        public List<AsistenciaUi> getAsistenciaUiList() {
            return asistenciaUiList;
        }
    }
}
