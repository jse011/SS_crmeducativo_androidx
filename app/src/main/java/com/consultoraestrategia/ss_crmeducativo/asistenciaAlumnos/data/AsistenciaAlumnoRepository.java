package com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.data;


import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.curso.asistenciaPorDia.entities.TipoUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenciaUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.AsistenicaArchivoUi;
import com.consultoraestrategia.ss_crmeducativo.asistenciaAlumnos.entidades.FechaAsistenciaUi;

import java.util.List;

public class AsistenciaAlumnoRepository implements AsistenciaAlumnoDataSource {

    AsistenciaAlumnoLocalDataSource asistenciaAlumnoLocalDataSource;

    public AsistenciaAlumnoRepository(AsistenciaAlumnoLocalDataSource asistenciaAlumnoLocalDataSource) {
        this.asistenciaAlumnoLocalDataSource = asistenciaAlumnoLocalDataSource;
    }

    @Override
    public void getAsistenciaAlumnos(AsistenciaUi asistenciaUi, SucessCallback<List<AsistenciaUi>> callback) {
        asistenciaAlumnoLocalDataSource.getAsistenciaAlumnos(asistenciaUi, callback);
    }

    @Override
    public void getFechaAsistenciaPorBimestre(int anioAcademicoId, int cargaCursoId, int calendarioPeriodoId, int parametroDisenioId, boolean state, int cargaAcademicaId, SucessCallback<List<FechaAsistenciaUi>> callback) {
        asistenciaAlumnoLocalDataSource.getFechaAsistenciaPorBimestre(anioAcademicoId, cargaCursoId, calendarioPeriodoId, parametroDisenioId,state, cargaAcademicaId, callback);
    }


    @Override
    public void saveAsitenciaAlumnos(List<AsistenciaUi> lista,   Callback callback) {
        asistenciaAlumnoLocalDataSource.saveAsitenciaAlumnos(lista, callback);
    }

    @Override
    public void getTiposJustificacion(SucessCallback<List<TipoUi>> callback) {
        asistenciaAlumnoLocalDataSource.getTiposJustificacion(callback);
    }


    @Override
    public void generarAsistencia(AsistenciaUi asistenciaUi, CallbackAsistencia callback) {
        asistenciaAlumnoLocalDataSource.generarAsistencia(asistenciaUi, callback);
    }

    @Override
    public void getArchivoAsistenciaList(String asisteniciaId, String justificacionId, SucessCallback<List<AsistenicaArchivoUi>> callback) {
        asistenciaAlumnoLocalDataSource.getArchivoAsistenciaList(asisteniciaId, justificacionId, callback);
    }


}
