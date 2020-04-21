package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data;

import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenicaArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.FechaAsistenciaUi;

import java.util.List;

public interface AsistenciaAlumnoDataSource {


    interface SucessCallback<T> {
        void onLoad(boolean success, T item);
    }
    interface Callback<T> {
        void onLoad(boolean success);
    }

    interface CallbackAsistencia{
        void onAsistencia(boolean success, AsistenciaUi asistenciaUi);
    }

    void getAsistenciaAlumnos(AsistenciaUi asistenciaUi, SucessCallback<List<AsistenciaUi>>callback );

    void getFechaAsistenciaPorBimestre(int anioAcademicoId, int cargaCursoId, int calendarioPeriodoId, int parametroDisenioId, boolean state, int cargaAcademicaId, SucessCallback<List<FechaAsistenciaUi>> callback);

    void saveAsitenciaAlumnos(List<AsistenciaUi>lista, Callback callback);
    void getTiposJustificacion(SucessCallback<List<TipoUi>>callback);

    void generarAsistencia(AsistenciaUi asistenciaUi, CallbackAsistencia callback);

    void getArchivoAsistenciaList(String asisteniciaId, String justificacionId, SucessCallback<List<AsistenicaArchivoUi>> callback);

}
