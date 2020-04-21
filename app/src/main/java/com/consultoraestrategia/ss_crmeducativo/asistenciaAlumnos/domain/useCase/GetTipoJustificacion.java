package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.JustificacionUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoDataSource;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoLocalDataSource;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoRepository;
import com.consultoraestrategia.ss_crmeducativo.base.UseCase;

import java.util.List;

public class GetTipoJustificacion extends UseCase<GetTipoJustificacion.RequestValues, GetTipoJustificacion.ResponseValue> {

    AsistenciaAlumnoRepository asistenciaAlumnoRepository;

    public GetTipoJustificacion(AsistenciaAlumnoRepository asistenciaAlumnoRepository) {
        this.asistenciaAlumnoRepository = asistenciaAlumnoRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        asistenciaAlumnoRepository.getTiposJustificacion(new AsistenciaAlumnoDataSource.SucessCallback<List<TipoUi>>() {
            @Override
            public void onLoad(boolean success, List<TipoUi> item) {
                getUseCaseCallback().onSuccess(new ResponseValue(item));
            }
        });
    }
    public static class RequestValues implements UseCase.RequestValues{

    }

    public static class ResponseValue implements UseCase.ResponseValue{
        private List<TipoUi> TipoUilis;

        public ResponseValue(List<TipoUi> tipoUilis) {
            TipoUilis = tipoUilis;
        }

        public List<TipoUi> getTipoUilis() {
            return TipoUilis;
        }
    }
}
