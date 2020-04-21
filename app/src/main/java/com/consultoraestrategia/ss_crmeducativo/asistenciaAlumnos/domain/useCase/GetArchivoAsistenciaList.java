package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.domain.useCase;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoDataSource;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data.AsistenciaAlumnoRepository;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenicaArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.base.UseCaseSincrono;

import java.util.List;

public class GetArchivoAsistenciaList extends UseCaseSincrono<GetArchivoAsistenciaList.Request, List<AsistenicaArchivoUi>> {

    private AsistenciaAlumnoRepository asistenciaAlumnoRepository;

    public GetArchivoAsistenciaList(AsistenciaAlumnoRepository asistenciaAlumnoRepository) {
        this.asistenciaAlumnoRepository = asistenciaAlumnoRepository;
    }

    @Override
    public void execute(Request request, final Callback<List<AsistenicaArchivoUi>> callback) {
        asistenciaAlumnoRepository.getArchivoAsistenciaList(request.getAsistenciaId(), request.getJustificacionId(), new AsistenciaAlumnoDataSource.SucessCallback<List<AsistenicaArchivoUi>>() {
            @Override
            public void onLoad(boolean success, List<AsistenicaArchivoUi> item) {
                if(success){
                    callback.onResponse(true, item);
                }else {
                    callback.onResponse(false, null);
                }
            }
        });
    }

    public static class Request {
        private String asistenciaId;
        private String justificacionId;

        public Request(String asistenciaId, String justificacionId) {
            this.asistenciaId = asistenciaId;
            this.justificacionId = justificacionId;
        }

        public String getAsistenciaId() {
            return asistenciaId;
        }

        public String getJustificacionId() {
            return justificacionId;
        }
    }
}
