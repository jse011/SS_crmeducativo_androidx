package com.consultoraestrategia.ss_crmeducativo.dao.asistenciaDao;

import com.consultoraestrategia.ss_crmeducativo.dao.asistenciaDao.request.GenerarAsistenciaPorCargaAcademicaRequest;
import com.consultoraestrategia.ss_crmeducativo.dao.asistenciaDao.request.GenerarAsistenciaPorCursoRequest;
import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseDao;
import com.consultoraestrategia.ss_crmeducativo.entities.AsistenciaSesionAlumnoC;

import java.util.Date;
import java.util.List;


/**
 * Created by @stevecampos on 18/04/2018.
 */

public interface AsistenciaSesionAlumnoDao extends BaseDao<AsistenciaSesionAlumnoC> {

    boolean generarAsistenciaPorCurso(GenerarAsistenciaPorCursoRequest request);
    boolean generarAsistenciaPorCargaAcademica(GenerarAsistenciaPorCargaAcademicaRequest request);

    List<AsistenciaSesionAlumnoC> getAsistenciaPorCalendarioPeriodo(int cargaCursoId, int calendarioPeriodoId, int cargaAcademicaId);

    List<Long> getFechaAsistenciaPorCalendarioPeriodo(List<Integer> cargaCursoId, int calendarioPeriodoId,  int anioAcademicoId);

    List<Long> getFechaAsistenciaPorCalendarioPeriodoPorFechaActual(List<Integer> cargaCursoId, int calendarioPeriodoId, Date fechaActual, int anioAcademicoId);

}
