package com.consultoraestrategia.ss_crmeducativo.dao.horarioPrograma;

import com.consultoraestrategia.ss_crmeducativo.dao.baseDao.BaseIntegerDao;
import com.consultoraestrategia.ss_crmeducativo.entities.HorarioPrograma;

import java.util.List;

public interface HorarioProgramaDao extends BaseIntegerDao<HorarioPrograma> {
    List<HorarioPrograma> obtenerPorCargacurso(int cargacursoId, int anioAcademicoId);
    List<HorarioPrograma> obtenerPorProgramaEducativo(int programaEducativoId, int anioAcademicoId);
}
